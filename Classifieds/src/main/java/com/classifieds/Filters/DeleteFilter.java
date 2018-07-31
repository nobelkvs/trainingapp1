package com.classifieds.Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Filter class which only allows admin to delete
 */
public class DeleteFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        HttpServletResponse res = (HttpServletResponse) servletResponse;

        PrintWriter out = res.getWriter();

        if(req.getMethod().equalsIgnoreCase("DELETE")){

            String role = req.getParameter("role");

            String password = req.getParameter("password");

            if(role.equalsIgnoreCase("admin") && password.equals("admin")){

                //if(role.equalsIgnoreCase("admin")){

                RequestDispatcher rd = req.getRequestDispatcher("servlets");

                rd.forward(req,res);
            }

            else{

                out.print("unauthorizedAccess");
            }
        }
    }

    public void destroy() {

    }
}
