package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class ChangePasswordCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String oldPassword = request.getParameter(OLD_PASSWORD);
        String newPassword = request.getParameter(PASSWORD);
        String newPasswordRepeat = request.getParameter(PASSWORD2);
        String locale = (String) session.getAttribute(CURRENT_LOCALE);
        try {
            String userNickname = (String) session.getAttribute(NICKNAME);
            if (newPassword.equals(newPasswordRepeat) &&
                    userService.changePassword(userNickname, oldPassword, newPassword)) {
                page = ConfigurationManager.getProperty("path.page.account");
            } else {
                session.setAttribute(UPDATE_ERROR_MESSAGE, MessageManager.getProperty("message.updateerror", locale));
                page = ConfigurationManager.getProperty("path.page.change_password");
            }
        } catch(ServiceException e) {
            session.setAttribute(WRONG_ACTION_MESSAGE, MessageManager.getProperty("message.wrongaction", locale));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
