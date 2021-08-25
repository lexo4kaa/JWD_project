package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.entity.User;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

/**
 * The command is responsible for finding all users
 */
public class FindAllUsersCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            List<User> users = userService.findAllUsers();
            session.setAttribute(USERS, users);
            session.setAttribute(USERS_SIZE, users.size());
            page = ConfigurationManager.getProperty("path.page.users_info");
        } catch (ServiceException e) {
            logger.error("Exception in userService.findAllUsers(), redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
