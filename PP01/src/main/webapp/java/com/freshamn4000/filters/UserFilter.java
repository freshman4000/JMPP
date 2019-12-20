package com.freshamn4000.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/*")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String role = (String) req.getSession().getAttribute("role");
        if (role != null && (role.equals("user") || role.equals("admin"))) {
            filterChain.doFilter(req, res);
        } else {
            res.sendRedirect("/index.jsp");
        }
    }
}