package com.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is a filter class which is used to delete the record from database based on authorized person
 */
public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out = servletResponse.getWriter();
        //Performing typecasting
        HttpServletRequest hreq = (HttpServletRequest) servletRequest;
        if (hreq.getParameter("action").equalsIgnoreCase("delete")) {
            //If the role is admin then only it will goes to servlet
            if (hreq.getParameter("role").equalsIgnoreCase("admin")) {
                RequestDispatcher rd = hreq.getRequestDispatcher("document");
                rd.forward(servletRequest, servletResponse);
            } else {
                out.println("you are not an authorized person");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
