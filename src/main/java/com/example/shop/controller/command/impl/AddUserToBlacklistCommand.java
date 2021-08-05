package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
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

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class AddUserToBlacklistCommand implements ActionCommand {
    private static final UserService userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            String banReason = request.getParameter(PARAM_NAME_BAN_REASON);
            int userId = Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID));
            String nickname = (String) session.getAttribute(NICKNAME);
            User activeUser = userService.findUserByNickname(nickname);
            if(activeUser.getUserId() != userId) {
                userService.addUserToBlacklist(userId, banReason);
                List<User> users = (List<User>) session.getAttribute(USERS);
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    if (user.getUserId() == userId) {
                        user.setIsBanned(true);
                        users.set(i, user);
                        break;
                    }
                }
                session.setAttribute(USERS, users);
            } else {
                request.setAttribute("actOnYourselfMessage", MessageManager.getProperty("message.actonyourself"));
            }
            page = (String) session.getAttribute(CURRENT_PAGE);
        } catch (ServiceException e) {
            logger.error("Exception in 'AddUserToBlacklistCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
