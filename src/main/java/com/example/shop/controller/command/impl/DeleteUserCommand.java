package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.User;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteUserCommand implements ActionCommand {
    private static final String PARAM_NAME_USER_ID = "user_id";
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            int userId = Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID));
            String nickname = (String) session.getAttribute("nickname");
            User user = userService.findUserByNickname(nickname);
            if(user.getUserId() != userId) {
                userService.deleteUser(userId);
                List<User> users = (List<User>) session.getAttribute("users");
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserId() == userId) {
                        users.remove(i);
                        break;
                    }
                }
                session.setAttribute("users", users);
                session.setAttribute("users_size", users.size());
            } else {
                request.setAttribute("actOnYourselfMessage", MessageManager.getProperty("message.actonyourself"));
            }
            page = ConfigurationManager.getProperty("path.page.users_info");
        } catch (ServiceException e) {
            logger.info("Problems with function 'deleteUser', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
