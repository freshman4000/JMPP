package com.freshamn4000.filters;

import com.freshamn4000.controllers.UserDaoFactory;
import com.freshamn4000.interfaces.ClientService;
import com.freshamn4000.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
            if (req.getSession().getAttribute("role") != null && req.getSession().getAttribute("role").equals("admin")) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect("/index.jsp");
            }
    }
}
