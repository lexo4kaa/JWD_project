package com.example.final_project_shop.command;
import com.example.final_project_shop.command.impl.EmptyCommand;
import com.example.final_project_shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String PARAM_NAME_COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}