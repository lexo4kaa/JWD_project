package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements ActionCommand {
    private static final String PARAM_NAME_OLD_PASSWORD = "old_password";
    private static final String PARAM_NAME_NEW_PASSWORD = "password";
    private static final String PARAM_NAME_NEW_PASSWORD_REPEAT = "password2";
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String oldPassword = request.getParameter(PARAM_NAME_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NAME_NEW_PASSWORD);
        String newPasswordRepeat = request.getParameter(PARAM_NAME_NEW_PASSWORD_REPEAT);
        try {
            String userNickname = (String) session.getAttribute("nickname");
            if (newPassword.equals(newPasswordRepeat) &&
                    userService.changePassword(userNickname, oldPassword, newPassword)) {
                page = ConfigurationManager.getProperty("path.page.account");
            } else {
                request.setAttribute("updateError", MessageManager.getProperty("message.updateerror"));
                page = ConfigurationManager.getProperty("path.page.change_password");
            }
        } catch(ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }
}
