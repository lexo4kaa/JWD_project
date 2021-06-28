package com.example.shop.controller.command;
import com.example.shop.controller.command.impl.EmptyCommand;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String PARAM_NAME_COMMAND = "command";

    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current;
        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
            current = new EmptyCommand();
        }
        return current;
    }
}