package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.model.service.ProductService;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.model.service.impl.ProductServiceImpl;
import com.example.final_project_shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class AddProductToCartCommand implements ActionCommand {
    private static final String PARAM_NAME_PRODUCT_ID = "product_id";
    private static final ProductService productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        String stringProductId = request.getParameter(PARAM_NAME_PRODUCT_ID);
        int productId = Integer.parseInt(stringProductId);
        try {
            productService.addProductToCart(cart, productId);
            cart.entrySet().forEach(entry -> {
                System.out.println(entry.getKey() + " " + entry.getValue()); //fixme
            });
            page = ConfigurationManager.getProperty("path.page.products");
        } catch (ServiceException e) {
            logger.info("Problems with function 'addProductToCart', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}