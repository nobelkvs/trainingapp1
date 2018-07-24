package com.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out=servletResponse.getWriter();
        out.println("hai");
        HttpServletRequest hreq=(HttpServletRequest)servletRequest;

        if(hreq.getParameter("action").equalsIgnoreCase("delete")) {
            if (hreq.getParameter("role").equalsIgnoreCase("admin")) {
                RequestDispatcher rd = hreq.getRequestDispatcher("document");
                rd.forward(servletRequest,servletResponse);
            }
            else{
                out.println("you are not an authorized person");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
