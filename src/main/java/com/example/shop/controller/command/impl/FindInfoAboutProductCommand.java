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
import java.util.*;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class FindInfoAboutProductCommand  implements ActionCommand {
    private static final ProductService productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        try {
            int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));
            Product product = productService.findProductsByIds(Set.of(productId)).get(0);
            session.setAttribute(INFO_PRODUCT, product);
            page = ConfigurationManager.getProperty("path.page.product_info");
        } catch (ServiceException e) {
            logger.error("Exception in 'FindInfoAboutProductCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}
