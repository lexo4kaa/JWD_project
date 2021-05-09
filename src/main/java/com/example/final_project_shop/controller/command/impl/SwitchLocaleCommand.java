package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SwitchLocaleCommand implements ActionCommand {
    private static final String PARAM_NAME_LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String locale = request.getParameter(PARAM_NAME_LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute("currentLocale", locale);
        String page = (String)session.getAttribute("currentPage");
        return page;
    }
}
