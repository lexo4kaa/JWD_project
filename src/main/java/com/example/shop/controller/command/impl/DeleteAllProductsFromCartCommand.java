package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class DeleteAllProductsFromCartCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        session.setAttribute(CART_PRODUCTS, null);
        session.setAttribute(CART, new HashMap<Integer, Integer>());
        session.setAttribute(CART_SIZE, 0);
        session.setAttribute(TOTAL_COST, 0.0);
        page = (String) session.getAttribute(CURRENT_PAGE);
        return new Router(page, Router.RouteType.REDIRECT);
    }
}