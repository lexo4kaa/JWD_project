package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class FirstInitCommand implements ActionCommand {
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            session.setAttribute("currentLocale", "en_US");
            session.setAttribute("products", productService.findAllProducts());
            session.setAttribute("cart", new HashMap<Integer, Integer>());
            session.setAttribute("user_role", "guest");
            page = ConfigurationManager.getProperty("path.page.products");
        } catch (ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction") + "\n" + e);
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }
}
