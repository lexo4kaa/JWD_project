package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
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

public class ChangeUserRoleCommand implements ActionCommand {
    private static final UserService userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        try {
            int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
            String nickname = userService.findUserById(userId).get().getNickname();
            String role = userService.findUserRole(nickname).get();
            String newRole = role.equals(ADMINISTRATOR) ? CLIENT : ADMINISTRATOR;
            String activeNickname = (String) session.getAttribute(NICKNAME);
            User activeUser = userService.findUserByNickname(activeNickname).get();
            if(activeUser.getUserId() != userId) {
                userService.changeRole(userId, newRole);
                List<User> users = (List<User>) session.getAttribute(USERS);
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    if (user.getUserId() == userId) {
                        user.setRole(newRole);
                        users.set(i, user);
                        break;
                    }
                }
                session.setAttribute(USERS, users);
            } else {
                session.setAttribute(ACT_ON_YOURSELF_MESSAGE, MessageManager.getProperty("message.actonyourself"));
            }
            page = (String) session.getAttribute(CURRENT_PAGE);
        } catch (ServiceException e) {
            logger.error("Exception in 'ChangeQuantityOfProductInCartCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}