package com.example.shop.controller;
import com.example.shop.controller.command.ActionCommand;
import com.example.shop.controller.command.ActionFactory;
import com.example.shop.controller.command.Router;
import com.example.shop.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand command = ActionFactory.defineCommand(request);
        Router router = command.execute(request);
        String page = router.getPagePath();
        HttpSession session = request.getSession();
        session.setAttribute("currentPage", page);
        switch (router.getRouteType()) {
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + page);
                break;
            default:
                logger.error("incorrect route type " + router.getRouteType());
                page = ConfigurationManager.getProperty("path.page.error");
                response.sendRedirect(request.getContextPath() + page);
        }
    }
}