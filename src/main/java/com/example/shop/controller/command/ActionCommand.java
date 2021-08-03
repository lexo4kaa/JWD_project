package com.example.shop.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Router execute(HttpServletRequest request);
}
