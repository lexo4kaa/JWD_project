package com.example.shop.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

/**
 * Filter for passing error messages to request
 */
@WebFilter(urlPatterns = { "/*" })
public class MessageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        String actOnYourselfMessage = (String)session.getAttribute(ACT_ON_YOURSELF_MESSAGE);
        String banMessage = (String)session.getAttribute(BAN_MESSAGE);
        String errorLoginPassMessage = (String)session.getAttribute(ERROR_LOGIN_PASS_MESSAGE);
        String registrationError = (String)session.getAttribute(REGISTRATION_ERROR_MESSAGE);
        String updateError = (String)session.getAttribute(UPDATE_ERROR_MESSAGE);
        String wrongActionMessage = (String)session.getAttribute(WRONG_ACTION_MESSAGE);
        httpRequest.setAttribute(ACT_ON_YOURSELF_MESSAGE, actOnYourselfMessage);
        httpRequest.setAttribute(BAN_MESSAGE, banMessage);
        httpRequest.setAttribute(ERROR_LOGIN_PASS_MESSAGE, errorLoginPassMessage);
        httpRequest.setAttribute(REGISTRATION_ERROR_MESSAGE, registrationError);
        httpRequest.setAttribute(UPDATE_ERROR_MESSAGE, updateError);
        httpRequest.setAttribute(WRONG_ACTION_MESSAGE, wrongActionMessage);
        session.setAttribute(ACT_ON_YOURSELF_MESSAGE, null);
        session.setAttribute(BAN_MESSAGE, null);
        session.setAttribute(ERROR_LOGIN_PASS_MESSAGE, null);
        session.setAttribute(REGISTRATION_ERROR_MESSAGE, null);
        session.setAttribute(UPDATE_ERROR_MESSAGE, null);
        session.setAttribute(WRONG_ACTION_MESSAGE, null);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
