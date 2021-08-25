package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
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
import java.util.Set;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

/**
 * The command is responsible for finding favourite products
 */
public class FindFavouriteProductsCommand implements ActionCommand {
    private static final ProductService productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_role = (String) session.getAttribute(USER_ROLE);
        String page;
        try {
            if(!user_role.equals(GUEST)) {
                Set<Integer> favourites = (Set<Integer>) session.getAttribute(FAVOURITES);
                List<Product> products = productService.findProductsByIds(favourites);
                session.setAttribute(FAVOURITE_PRODUCTS, products);
                page = ConfigurationManager.getProperty("path.page.favourites");
            } else {
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch (ServiceException e) {
            logger.error("Exception in function 'findProductsByIds', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}
