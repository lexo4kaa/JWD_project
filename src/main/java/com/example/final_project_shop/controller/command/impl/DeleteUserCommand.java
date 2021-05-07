package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.entity.User;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.model.service.impl.UserServiceImpl;
import com.example.final_project_shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteUserCommand implements ActionCommand {
    private static final String PARAM_NAME_USERID = "user_id";
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<User> users;
        try {
            int userId = Integer.valueOf(request.getParameter(PARAM_NAME_USERID));
            userService.deleteUser(userId);
            users = userService.findAllUsers();
            request.setAttribute("lst", users);
            request.setAttribute("lst_length", users.size());
            page = ConfigurationManager.getProperty("path.page.tables");
        } catch (ServiceException e) {
            logger.info("Problems with function 'deleteUser', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
