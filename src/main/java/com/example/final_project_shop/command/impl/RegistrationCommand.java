package com.example.final_project_shop.command.impl;

import com.example.final_project_shop.command.ActionCommand;
import com.example.final_project_shop.resource.ConfigurationManager;
import com.example.final_project_shop.resource.MessageManager;
import com.example.final_project_shop.service.ServiceException;
import com.example.final_project_shop.service.impl.UserServiceImpl;

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
        if (password == password2 && userService.registerUser(name, surname, nickname, password, dob, phone, email)) {
            //request.setAttribute("user", login);
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }
        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
