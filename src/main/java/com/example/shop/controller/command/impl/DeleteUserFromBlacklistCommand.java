package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.User;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.UserService;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteUserFromBlacklistCommand implements ActionCommand {
    private static final String PARAM_NAME_USER_ID = "user_id";
    private static final UserService userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            String stringUserId = request.getParameter(PARAM_NAME_USER_ID);
            int userId = Integer.parseInt(stringUserId);
            userService.deleteUserFromBlacklist(userId);
            List<User> users = userService.findAllUsers();
            session.setAttribute("users", users);
            session.setAttribute("users_size", users.size());
            page = ConfigurationManager.getProperty("path.page.users_info");
        } catch (ServiceException e) {
            logger.info("Problems in 'DeleteUserFromBlacklistCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}