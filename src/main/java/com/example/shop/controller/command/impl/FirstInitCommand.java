package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
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

public class FirstInitCommand implements ActionCommand {
    private static final ProductServiceImpl productService = new ProductServiceImpl();
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            session.setAttribute("currentLocale", "en_US");
            session.setAttribute("user_role", "guest");
            session.setAttribute("products", productService.findAllProducts());
            session.setAttribute("typeOfProducts", "all");
            session.setAttribute("cart", new HashMap<Integer, Integer>());
            session.setAttribute("cart_size", 0);
            session.setAttribute("total_cost", 0.0);
            List<User> users = userService.findAllUsers();
            session.setAttribute("users", users);
            session.setAttribute("users_size", users.size());
            session.setAttribute("currentPage", "path.page.products");
            page = ConfigurationManager.getProperty("path.page.products");
        } catch (ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return new Router(page, RouteType.FORWARD);
    }
}
