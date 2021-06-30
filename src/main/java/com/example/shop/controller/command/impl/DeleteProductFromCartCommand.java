package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.ProductService;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class DeleteProductFromCartCommand implements ActionCommand {
    private static final String PARAM_NAME_PRODUCT_ID = "product_id";
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        int cart_size = (int) session.getAttribute("cart_size");
        String stringProductId = request.getParameter(PARAM_NAME_PRODUCT_ID);
        int productId = Integer.parseInt(stringProductId);
        if(productService.deleteProductFromCart(cart, productId)) {
            session.setAttribute("cart_size", cart_size > 1 ? cart_size - 1 : 0);
        }
        String page = ConfigurationManager.getProperty("path.page.products");
        return page;
    }
}