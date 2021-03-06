package com.example.shop.controller.filter;

import com.example.shop.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.shop.controller.command.ParameterAndAttribute.*;

/**
 * Filter for control access to guest pages
 */
@WebFilter(urlPatterns = { "/pages/unauthorized/*" })
public class GuestAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        if (!session.getAttribute(USER_ROLE).equals(GUEST)) {
            String page = ConfigurationManager.getProperty("path.page.index");
            httpResponse.sendRedirect(httpRequest.getContextPath() + page);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
