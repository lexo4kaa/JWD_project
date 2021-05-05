package com.example.final_project_shop.controller.command.impl;
import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.entity.Product;
import com.example.final_project_shop.entity.User;
import com.example.final_project_shop.model.service.impl.ProductServiceImpl;
import com.example.final_project_shop.resource.ConfigurationManager;
import com.example.final_project_shop.resource.MessageManager;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ROLE_CLIENT = "client";
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        if (userService.authorizeUser(login, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            session.setAttribute("cart", new HashMap<Integer, Integer>()); // productId & quantity
            if (userService.findUserRole(login).equals(PARAM_NAME_ROLE_CLIENT)) {
                request.setAttribute("lst", productService.findAllProducts());
                page = ConfigurationManager.getProperty("path.page.products");
            } else {
                page = ConfigurationManager.getProperty("path.page.admin_main");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}