package com.example.shop.controller.command.impl;
import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class LoginCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN).toLowerCase();
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            if (userService.authorizeUser(login, password)) {
                if(!userService.isBanned(login)) {
                    HttpSession session = request.getSession();
                    session.setAttribute(NICKNAME, login);
                    String role = userService.findUserRole(login).get();
                    session.setAttribute(USER_ROLE, role);
                    if (role.equals(CLIENT)) {
                        page = ConfigurationManager.getProperty("path.page.products");
                    } else {
                        page = ConfigurationManager.getProperty("path.page.admin_main");
                    }
                }
                else {
                    request.setAttribute("banMessage", MessageManager.getProperty("message.ban"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        }
        catch(ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}