package com.example.final_project_shop.controller.command;

import com.example.final_project_shop.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws ServiceException;
}