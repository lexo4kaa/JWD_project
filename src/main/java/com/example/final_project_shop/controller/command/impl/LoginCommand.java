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
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (userService.authorizeUser(login, pass)) {
            request.setAttribute("user", login);
            if (userService.findUserRole(login).equals(PARAM_NAME_ROLE_CLIENT)) {
                List<Product> products;
                products = productService.findAllProducts();
                request.setAttribute("lst", products);
                page = ConfigurationManager.getProperty("path.page.products");
            }
            else
            {
                page = ConfigurationManager.getProperty("path.page.admin_main");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}