package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SwitchLocaleCommand implements ActionCommand {
    private static final String PARAM_NAME_LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String locale = request.getParameter(PARAM_NAME_LOCALE);
        session.setAttribute("currentLocale", locale);
        page = (String) session.getAttribute("currentPage");
        return page;
    }
}
