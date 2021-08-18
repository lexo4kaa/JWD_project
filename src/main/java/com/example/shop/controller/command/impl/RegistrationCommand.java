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

public class RegistrationCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_role = (String) session.getAttribute("user_role");
        String page;
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String nickname = request.getParameter(NICKNAME).toLowerCase();
        String dob = request.getParameter(DOB);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String password2 = request.getParameter(PASSWORD2);
        String locale = (String) session.getAttribute(CURRENT_LOCALE);
        try {
            if(user_role.equals(ADMINISTRATOR)) {
                if (password.equals(password2) &&
                        userService.registerUser(name, surname, nickname, password, dob, phone, email, ADMINISTRATOR)) {
                    page = ConfigurationManager.getProperty("path.page.admin_main");
                } else {
                    session.setAttribute(REGISTRATION_ERROR_MESSAGE, MessageManager.getProperty("message.registrationerror", locale));
                    page = ConfigurationManager.getProperty("path.page.registration");
                }
            } else {
                if (password.equals(password2) &&
                        userService.registerUser(name, surname, nickname, password, dob, phone, email, CLIENT)) {
                    page = ConfigurationManager.getProperty("path.page.login");
                } else {
                    session.setAttribute(REGISTRATION_ERROR_MESSAGE, MessageManager.getProperty("message.registrationerror", locale));
                    page = ConfigurationManager.getProperty("path.page.registration");
                }
            }
        } catch(ServiceException e) {
            session.setAttribute(WRONG_ACTION_MESSAGE, MessageManager.getProperty("message.wrongaction", locale));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
