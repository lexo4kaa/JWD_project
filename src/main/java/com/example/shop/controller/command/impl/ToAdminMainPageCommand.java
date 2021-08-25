package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.Router;
import com.example.shop.controller.command.Router.RouteType;
import com.example.shop.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The command is responsible for forwarding to admin main page
 */
public class ToAdminMainPageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.admin_main");
        return new Router(page, RouteType.FORWARD);
    }
}
