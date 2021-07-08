package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.User;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToUsersInfoPageCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            List<User> users = userService.findAllUsers();
            session.setAttribute("users", users);
            session.setAttribute("users_size", users.size());
            session.setAttribute("currentPage", "path.page.users_info");
            page = ConfigurationManager.getProperty("path.page.users_info");
        } catch (ServiceException e) {
            logger.info("Problems with userService.findAllUsers(), redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
