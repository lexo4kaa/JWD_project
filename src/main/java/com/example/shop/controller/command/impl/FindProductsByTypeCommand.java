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
import java.util.List;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

/**
 * The command is responsible for finding products by type
 */
public class FindProductsByTypeCommand implements ActionCommand {
    private static final ProductService productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        List<Product> products;
        String type = request.getParameter(TYPE);
        try {
            session.setAttribute(TYPE_OF_PRODUCTS, type);
            products = productService.findProductsByType(type);
            session.setAttribute(PRODUCTS, products);
            page = ConfigurationManager.getProperty("path.page.products");
        } catch (ServiceException e) {
            logger.error("Exception in function 'findProductsProductsByTeam', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
