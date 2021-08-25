package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The command is responsible for forwarding to page 'registration'
 */
public class ToRegistrationPageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        return new Router(page, RouteType.FORWARD);
    }
}
