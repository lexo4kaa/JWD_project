package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class FirstInitCommand implements ActionCommand {
    private static final String ALL_VALUE = "all";
    private static final String EN_US_LOCALE = "en_US";
    private static final ProductServiceImpl productService = new ProductServiceImpl();
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            session.setAttribute(CURRENT_LOCALE, EN_US_LOCALE);
            session.setAttribute(USER_ROLE, GUEST);
            List<Product> products = productService.findAllProducts();
            session.setAttribute(PRODUCTS, products);
            List<User> users = userService.findAllUsers();
            session.setAttribute(USERS, users);
            session.setAttribute(USERS_SIZE, users.size());
            session.setAttribute(CART, new HashMap<Integer, Integer>());
            session.setAttribute(CART_SIZE, 0);
            session.setAttribute(TOTAL_COST, 0.0);
            session.setAttribute(TYPE_OF_PRODUCTS, ALL_VALUE);
            page = ConfigurationManager.getProperty("path.page.products");
        } catch (ServiceException e) {
            session.setAttribute(WRONG_ACTION_MESSAGE, MessageManager.getProperty("message.wrongaction", EN_US_LOCALE));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
