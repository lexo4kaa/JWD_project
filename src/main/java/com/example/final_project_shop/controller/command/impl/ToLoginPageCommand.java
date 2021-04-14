package com.example.final_project_shop.controller.command.impl;

import com.example.final_project_shop.controller.command.ActionCommand;
import com.example.final_project_shop.model.service.ServiceException;
import com.example.final_project_shop.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToLoginPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        return ConfigurationManager.getProperty("path.page.login");
    }
}
