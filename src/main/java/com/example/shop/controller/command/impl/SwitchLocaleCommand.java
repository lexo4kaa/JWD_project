package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.Product;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class SwitchLocaleCommand implements ActionCommand {
    private static final String PARAM_NAME_LOCALE = "locale";
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            List<Product> products = productService.findProductsByIds(cart.keySet());
            request.setAttribute("cartProducts", products); // todo may be different
            String locale = request.getParameter(PARAM_NAME_LOCALE);
            session.setAttribute("currentLocale", locale);
            page = (String) session.getAttribute("currentPage");
        } catch(ServiceException e) {
            request.setAttribute("wrongAction", MessageManager.getProperty("message.wrongaction") + "\n" + e);
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
