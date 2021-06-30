package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.OrderServiceImpl;
import com.example.shop.model.service.impl.ProductServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AddOrderCommand implements ActionCommand {
    private static final OrderServiceImpl orderService = new OrderServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        String user = (String) session.getAttribute("user");
        try {
            orderService.addOrder(cart, user);
            session.setAttribute("cart", new HashMap<Integer, Integer>());
            session.setAttribute("cart_size", 0);
            page = ConfigurationManager.getProperty("path.page.cart");
        } catch (ServiceException e) {
            logger.info("Problems with function 'addOrder', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}