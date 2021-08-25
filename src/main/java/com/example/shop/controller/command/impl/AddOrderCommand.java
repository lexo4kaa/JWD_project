package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.entity.User;
import com.example.shop.model.service.OrderService;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.UserService;
import com.example.shop.model.service.impl.OrderServiceImpl;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

/**
 * The command is responsible for adding order
 */
public class AddOrderCommand implements ActionCommand {
    private static final OrderService orderService = new OrderServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute(CART);
        String nickname = (String) session.getAttribute(NICKNAME);
        String user_role = (String) session.getAttribute(USER_ROLE);
        String methodOfReceiving = request.getParameter(METHOD_OF_RECEIVING);
        String methodOfPayment = request.getParameter(METHOD_OF_PAYMENT);
        String address = request.getParameter(ADDRESS);
        try {
            if(cart.size() != 0) {
                if(!user_role.equals(GUEST)) {
                    User user = userService.findUserByNickname(nickname).get();
                    String stringCost = session.getAttribute(TOTAL_COST).toString();
                    double cost = Double.parseDouble(stringCost);
                    orderService.addOrder(cart, cost, user.getUserId(), methodOfReceiving, methodOfPayment, address);
                    session.setAttribute(CART_PRODUCTS, null);
                    session.setAttribute(CART, new HashMap<Integer, Integer>());
                    session.setAttribute(CART_SIZE, 0);
                    session.setAttribute(TOTAL_COST, 0.0);
                    page = ConfigurationManager.getProperty("path.page.cart");
                } else {
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            } else {
                page = ConfigurationManager.getProperty("path.page.products");
            }
        } catch (ServiceException e) {
            logger.error("Exception in function 'addOrder', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}