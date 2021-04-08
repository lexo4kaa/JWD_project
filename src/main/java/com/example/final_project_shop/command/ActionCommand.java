package com.example.final_project_shop.command;

import com.example.final_project_shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws ServiceException;
}
