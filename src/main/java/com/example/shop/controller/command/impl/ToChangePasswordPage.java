package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToChangePasswordPage implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("currentPage", "path.page.change_password");
        String page = ConfigurationManager.getProperty("path.page.change_password");
        return new Router(page, RouteType.FORWARD);
    }
}