package com.response.controller;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseFilter implements Filter {
    static final Logger log = Logger.getLogger(ResponseFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out = servletResponse.getWriter();
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestDispatcher rd ;

        if(request.getMethod().equalsIgnoreCase("DELETE"))
        {
            String user = request.getParameter("admin1");
            String pwd = request.getParameter("passwd");
            if(user.equalsIgnoreCase("admin")&& pwd.equals("Admin123"))
            {
                rd= request.getRequestDispatcher("servlets");
                rd.forward(request,res);
            }
            else
                out.print("adminloginfailed");
        }

    }

    @Override
    public void destroy() {

    }
}