package com.example.shop.controller.command.impl;

import com.example.shop.controller.command.ActionCommand;
import com.example.shop.model.service.ServiceException;
import com.example.shop.model.service.impl.UserServiceImpl;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAccountPageCommand implements ActionCommand {
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("nickname");
        try {
            session.setAttribute("profile", userService.findUserByNickname(nickname));
            session.setAttribute("currentPage", "path.page.account");
            page = ConfigurationManager.getProperty("path.page.account");
        } catch (ServiceException e) {
            logger.info("Problems with userService.findUserByNickname(" + nickname + "), redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
