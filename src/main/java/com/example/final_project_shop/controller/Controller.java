package com.example.final_project_shop.controller;
import com.example.final_project_shop.command.ActionCommand;
import com.example.final_project_shop.command.ActionFactory;
import com.example.final_project_shop.resource.ConfigurationManager;
import com.example.final_project_shop.resource.MessageManager;
import com.example.final_project_shop.service.ServiceException;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}