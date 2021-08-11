package com.example.shop.controller.command;
import com.example.shop.controller.command.impl.EmptyCommand;
import com.example.shop.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

public class ActionFactory {
    private static final String PARAM_NAME_COMMAND = "command";

    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current;
        HttpSession session = request.getSession();
        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            session.setAttribute(WRONG_ACTION_MESSAGE, action + MessageManager.getProperty("message.wrongaction"));
            current = new EmptyCommand();
        }
        return current;
    }
}