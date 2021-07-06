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

public class FindUsersByNicknameCommand implements ActionCommand {
    private static final String PARAM_NAME_NICKNAME = "nickname";
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            String nickname = request.getParameter(PARAM_NAME_NICKNAME);
            List<User> users = userService.findUsersByPartOfNickname(nickname);
            request.setAttribute("users", users);
            request.setAttribute("users_size", users.size());
            page = ConfigurationManager.getProperty("path.page.users_info");
        } catch (ServiceException e) {
            logger.info("Problems with function 'findUsersByNickname', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
