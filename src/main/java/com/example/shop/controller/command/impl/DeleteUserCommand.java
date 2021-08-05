package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
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

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class DeleteUserCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));//todo check valueOf -> parseInt
            String nickname = (String) session.getAttribute(NICKNAME);
            User user = userService.findUserByNickname(nickname);
            if(user.getUserId() != userId) {
                userService.deleteUser(userId);
                List<User> users = (List<User>) session.getAttribute(USERS);
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserId() == userId) {
                        users.remove(i);
                        break;
                    }
                }
                session.setAttribute(USERS, users);
                session.setAttribute(USERS_SIZE, users.size());
            } else {
                request.setAttribute("actOnYourselfMessage", MessageManager.getProperty("message.actonyourself"));
            }
            page = ConfigurationManager.getProperty("path.page.users_info");
        } catch (ServiceException e) {
            logger.error("Exception in function 'deleteUser', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
