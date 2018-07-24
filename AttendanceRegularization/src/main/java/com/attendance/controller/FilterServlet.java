package com.attendance.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebFilter("/servletfilter")
/**
 * Create FilterServlet Class
 */
public class FilterServlet implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    //Declare doFilter method
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        PrintWriter out = res.getWriter();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        String role = request.getParameter("userRole");


        String method = request.getMethod();
        if (method.equalsIgnoreCase("delete")) {
            out.println((role));
            if (role.equalsIgnoreCase("admin")) {
                RequestDispatcher rd = request.getRequestDispatcher("servlets");
                rd.forward(request, response);
            } else
                out.println("you are not eligible for the delete");
        }
    }

    public void destroy() {

        /* Called before the Filter instance is removed from service by the web container*/
    }
}
