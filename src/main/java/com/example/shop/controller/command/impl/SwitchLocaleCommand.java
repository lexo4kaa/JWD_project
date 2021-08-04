package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class SwitchLocaleCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String locale = request.getParameter(PARAM_NAME_LOCALE);
        session.setAttribute(CURRENT_LOCALE, locale);
        page = (String) session.getAttribute(CURRENT_PAGE);
        return new Router(page, RouteType.REDIRECT);
    }
}
