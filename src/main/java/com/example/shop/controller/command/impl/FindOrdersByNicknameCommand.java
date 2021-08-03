package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.entity.Order;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.OrderServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindOrdersByNicknameCommand implements ActionCommand {
    private static final OrderServiceImpl orderService = new OrderServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("nickname");
        try {
            List<Order> orders = orderService.findOrdersByNickname(nickname);
            session.setAttribute("orders", orders);
            session.setAttribute("orders_size", orders.size());
            page = ConfigurationManager.getProperty("path.page.orders_info");
        } catch (ServiceException e) {
            logger.error("Exception in orderService.findOrdersByNickname(" + nickname + "), redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, RouteType.REDIRECT);
    }
}
