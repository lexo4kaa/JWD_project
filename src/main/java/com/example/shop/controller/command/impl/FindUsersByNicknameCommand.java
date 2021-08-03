package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.entity.User;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindUsersByNicknameCommand implements ActionCommand {
    private static final String PARAM_NAME_NICKNAME = "nickname";
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            String nickname = request.getParameter(PARAM_NAME_NICKNAME).toLowerCase();
            List<User> users = userService.findUsersByPartOfNickname(nickname);
            session.setAttribute("users", users);
            session.setAttribute("users_size", users.size());
            page = ConfigurationManager.getProperty("path.page.users_info");
        } catch (ServiceException e) {
            logger.error("Exception in function 'findUsersByNickname', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
