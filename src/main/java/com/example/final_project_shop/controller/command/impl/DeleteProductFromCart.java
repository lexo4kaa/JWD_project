package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.model.service.ProductService;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.model.service.impl.ProductServiceImpl;
import com.example.final_project_shop.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class DeleteProductFromCart implements ActionCommand {
    private static final String PARAM_NAME_PRODUCT_ID = "product_id";
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page;
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        String stringProductId = request.getParameter(PARAM_NAME_PRODUCT_ID);
        int productId = Integer.parseInt(stringProductId);
        productService.deleteProductFromCart(cart, productId);
        cart.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue()); //fixme
        });
        request.setAttribute("lst", productService.findAllProducts());
        page = ConfigurationManager.getProperty("path.page.products");
        return page;
    }
}