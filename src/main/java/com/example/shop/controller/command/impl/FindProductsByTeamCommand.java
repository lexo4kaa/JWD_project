package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.entity.Product;
import com.example.shop.model.service.ProductService;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindProductsByTeamCommand implements ActionCommand {
    private static final String PARAM_NAME_TEAM = "team";
    private static final String ALL_TEAMS = "All";
    private static final ProductService productService = new ProductServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<Product> products;
        String team = request.getParameter(PARAM_NAME_TEAM);
        try {
            if(team.equals(ALL_TEAMS)) {
                products = productService.findAllProducts();
            }
            else {
                products = productService.findProductsByTeam(team);
            }
            request.setAttribute("products", products);
            page = ConfigurationManager.getProperty("path.page.products");
        } catch (ServiceException e) {
            logger.info("Problems with function 'findProductsProductsByTeam', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
