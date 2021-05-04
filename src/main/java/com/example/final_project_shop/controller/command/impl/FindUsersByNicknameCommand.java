package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.entity.User;
import com.example.final_project_shop.resource.ConfigurationManager;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.model.service.impl.UserServiceImpl;
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
        List<User> users;
        String nickname = request.getParameter(PARAM_NAME_NICKNAME);
        try {
            users = userService.findUsersByNickname(nickname);
            request.setAttribute("lst", users);
            request.setAttribute("lst_length", users.size());
            page = ConfigurationManager.getProperty("path.page.tables");
        } catch (ServiceException e) {
            logger.info("Problems with function 'findUsersByNickname', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
