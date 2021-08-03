package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.entity.Product;
import com.example.shop.model.service.ProductService;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

public class AddProductToCartCommand implements ActionCommand {
    private static final String PARAM_NAME_PRODUCT_ID = "product_id";
    private static final ProductService productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        try {
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            String stringProductId = request.getParameter(PARAM_NAME_PRODUCT_ID);
            int productId = Integer.parseInt(stringProductId);
            cart = productService.addProductToCart(cart, productId);
            session.setAttribute("cart", cart);
            session.setAttribute("cartProducts", productService.findProductsByIds(cart.keySet()));
            Product product = productService.findProductsByIds(Set.of(productId)).get(0);
            double cost = (double) session.getAttribute("total_cost");
            session.setAttribute("total_cost", cost + product.getPrice());
            int cart_size = (int) session.getAttribute("cart_size");
            session.setAttribute("cart_size", cart_size + 1);
            page = (String) session.getAttribute("currentPage");
        } catch (ServiceException e) {
            logger.error("Exception in 'AddProductToCartCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}