package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.entity.Product;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class SwitchLocaleCommand implements ActionCommand {
    private static final String PARAM_NAME_LOCALE = "locale";
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        List<Product> products = productService.findProductsByIds(cart.keySet());
        request.setAttribute("cartProducts", products); // todo may be different
        String locale = request.getParameter(PARAM_NAME_LOCALE);
        session.setAttribute("currentLocale", locale);
        String page = (String)session.getAttribute("currentPage");
        return page;
    }
}
