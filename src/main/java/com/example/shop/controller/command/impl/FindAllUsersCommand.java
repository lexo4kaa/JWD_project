package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.User;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllUsersCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<User> users;
        try {
            users = userService.findAllUsers();
            request.setAttribute("lst", users);
            request.setAttribute("lst_length", users.size());
            page = ConfigurationManager.getProperty("path.page.tables");
        } catch (ServiceException e) {
            logger.info("Problems with function 'findAllUsers', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}