package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateAccountCommand implements ActionCommand {
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_NICKNAME = "nickname";
    private static final String PARAM_NAME_DOB = "dob";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String nickname = request.getParameter(PARAM_NAME_NICKNAME).toLowerCase();
        String dob = request.getParameter(PARAM_NAME_DOB);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        try {
            String userNickname = (String) session.getAttribute("nickname");
            int userId = userService.findUserByNickname(userNickname).getUserId();
            if (userService.updateUser(name, surname, nickname, dob, phone, email, userId)) {
                session.setAttribute("nickname", nickname);
                session.setAttribute("profile", userService.findUserByNickname(nickname));
                page = ConfigurationManager.getProperty("path.page.account");
            } else {
                request.setAttribute("updateError", MessageManager.getProperty("message.updateerror"));
                page = ConfigurationManager.getProperty("path.page.registration");
            }
        } catch(ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }
}
