package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.User;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.UserService;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;
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
            int userId = Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID));
            String nickname = (String) session.getAttribute("nickname");
            User activeUser = userService.findUserByNickname(nickname);
            if(activeUser.getUserId() != userId) {
                userService.deleteUserFromBlacklist(userId);
                List<User> users = (List<User>) session.getAttribute("users");
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    if (user.getUserId() == userId) {
                        user.setIsBanned(false);
                        users.set(i, user);
                        break;
                    }
                }
                session.setAttribute("users", users);
            } else {
                request.setAttribute("actOnYourselfMessage", MessageManager.getProperty("message.actonyourself"));
            }
            page = (String) session.getAttribute("currentPage");
        } catch (ServiceException e) {
            logger.error("Exception in 'DeleteUserFromBlacklistCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}