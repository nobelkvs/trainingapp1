package com.company.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FilterController implements Filter{
    public void init(FilterConfig arg0) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        PrintWriter out = res.getWriter();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        String role = request.getParameter("userRole");

        String method = request.getMethod();
        if (method.equalsIgnoreCase("delete")) {
            out.println((role));
            if (role.equalsIgnoreCase("admin")) {
                RequestDispatcher rd = request.getRequestDispatcher("companies");
                rd.forward(request, response);
                out.println("done!!");
            } else
                out.println("no permission given to you to delete");
        }
    }

    public void destroy() {

        /* Called before the Filter instance is removed from service by the web container*/
    }

}
