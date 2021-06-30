package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.Product;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllProductsCommand implements ActionCommand {
    private static final ProductServiceImpl productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<Product> products;
        try {
            products = productService.findAllProducts(); //todo test it (mb delete this)
            request.setAttribute("products", products);
            page = ConfigurationManager.getProperty("path.page.products");
        } catch (ServiceException e) {
            logger.info("Problems with function 'FindAllProducts', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}