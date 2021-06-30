package com.example.shop.controller.command.impl;
import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ROLE_CLIENT = "client";
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            if (userService.authorizeUser(login, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", login);
                String role = userService.findUserRole(login).get();
                session.setAttribute("user_role", role);
                if (role.equals(PARAM_NAME_ROLE_CLIENT)) {
                    request.setAttribute("products", productService.findAllProducts());
                    page = ConfigurationManager.getProperty("path.page.products");
                } else {
                    page = ConfigurationManager.getProperty("path.page.admin_main");
                }
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        }
        catch(ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }
}