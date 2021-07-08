package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ActionCommand {
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_NICKNAME = "nickname";
    private static final String PARAM_NAME_DOB = "dob";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD2 = "password2";
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_role = (String) session.getAttribute("user_role");
        String page;
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String nickname = request.getParameter(PARAM_NAME_NICKNAME).toLowerCase();
        String dob = request.getParameter(PARAM_NAME_DOB);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String password2 = request.getParameter(PARAM_NAME_PASSWORD2);
        try {
            if(user_role.equals("administrator")) {
                if (password.equals(password2) &&
                        userService.registerUser(name, surname, nickname, password, dob, phone, email, "administrator")) {
                    page = ConfigurationManager.getProperty("path.page.admin_main");
                } else {
                    request.setAttribute("registrationError", MessageManager.getProperty("message.registrationerror"));
                    page = ConfigurationManager.getProperty("path.page.registration");
                }
            } else {
                if (password.equals(password2) &&
                        userService.registerUser(name, surname, nickname, password, dob, phone, email, "client")) {
                    page = ConfigurationManager.getProperty("path.page.login");
                } else {
                    request.setAttribute("registrationError", MessageManager.getProperty("message.registrationerror"));
                    page = ConfigurationManager.getProperty("path.page.registration");
                }
            }
        } catch(ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }
}
