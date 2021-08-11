package com.example.shop.controller.command.impl;
import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.model.service.ProductService;
import com.example.shop.model.service.UserService;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class LoginCommand implements ActionCommand {
    private static final UserService userService = new UserServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN).toLowerCase();
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            if (userService.authorizeUser(login, password)) {
                if(!userService.isBanned(login)) {
                    session.setAttribute(NICKNAME, login);
                    User user = userService.findUserByNickname(login).get();
                    String role = user.getRole();
                    session.setAttribute(USER_ROLE, role);
                    int userId = user.getUserId();
                    session.setAttribute(FAVOURITES, productService.findFavouriteProducts(userId));
                    if (role.equals(CLIENT)) {
                        page = ConfigurationManager.getProperty("path.page.products");
                    } else {
                        page = ConfigurationManager.getProperty("path.page.admin_main");
                    }
                }
                else {
                    session.setAttribute(BAN_MESSAGE, MessageManager.getProperty("message.ban")); // todo attr to constant
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            } else {
                session.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.loginerror")); // todo attr to constant
                page = ConfigurationManager.getProperty("path.page.login");
            }
        }
        catch(ServiceException e) {
            session.setAttribute(WRONG_ACTION_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}