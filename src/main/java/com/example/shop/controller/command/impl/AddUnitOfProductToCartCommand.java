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

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class AddUnitOfProductToCartCommand implements ActionCommand {
    private static final ProductService productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        try {
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute(CART);
            String stringProductId = request.getParameter(PRODUCT_ID);
            int productId = Integer.parseInt(stringProductId);
            if((cart.get(productId) == null ? 0 : cart.get(productId)) < 99) {
                cart = productService.addUnitOfProductToCart(cart, productId);
                session.setAttribute(CART, cart);
                session.setAttribute(CART_PRODUCTS, productService.findProductsByIds(cart.keySet()));
                Product product = productService.findProductsByIds(Set.of(productId)).get(0);
                double cost = (double) session.getAttribute(TOTAL_COST);
                session.setAttribute(TOTAL_COST, cost + product.getPrice());
                int cart_size = (int) session.getAttribute(CART_SIZE);
                session.setAttribute(CART_SIZE, cart_size + 1);
            }
            page = (String) session.getAttribute(CURRENT_PAGE);
        } catch (ServiceException e) {
            logger.error("Exception in 'AddUnitOfProductToCartCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}