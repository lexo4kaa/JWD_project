package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.resource.ConfigurationManager;
import com.example.final_project_shop.resource.MessageManager;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

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
    public String execute(HttpServletRequest request) throws ServiceException {
        String page;
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String nickname = request.getParameter(PARAM_NAME_NICKNAME);
        String dob = request.getParameter(PARAM_NAME_DOB);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String password2 = request.getParameter(PARAM_NAME_PASSWORD2);
        if (password.equals(password2) && userService.registerUser(name, surname, nickname, password, dob, phone, email)) {
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            System.out.println(userService.registerUser(name, surname, nickname, password, dob, phone, email));
            request.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.registrationerror"));
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }
}