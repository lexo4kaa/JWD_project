package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.OrderServiceImpl;
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
        String user_role = (String) session.getAttribute("user_role");
        try {
            if(cart.size() != 0) {
                if(user_role != "guest") {
                    orderService.addOrder(cart, user);
                    session.setAttribute("cartProducts", null);
                    session.setAttribute("cart", new HashMap<Integer, Integer>());
                    session.setAttribute("cart_size", 0);
                    session.setAttribute("total_cost", 0.0);
                    page = ConfigurationManager.getProperty("path.page.cart");
                } else {
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            } else {
                page = ConfigurationManager.getProperty("path.page.products");
            }
        } catch (ServiceException e) {
            logger.info("Problems with function 'addOrder', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}