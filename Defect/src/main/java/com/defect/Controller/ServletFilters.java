package com.defect.Controller;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Filter of Defect Application
 */
@WebFilter("/ServletFilters")
public class ServletFilters implements Filter {

    static final Logger log=Logger.getLogger(ServletFilters.class);

    public void init(FilterConfig arg0) throws ServletException {}



    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        PrintWriter out = res.getWriter();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // out.println("role");
        String UserRole= request.getParameter("UserRole");
        String password = request.getParameter("password");
        //out.println(role);

        String method = request.getMethod();
        if(method.equalsIgnoreCase("delete"))
        {
            //out.println((role));
            if(UserRole.equalsIgnoreCase("admin") && password.equals("admin"))
            {
                RequestDispatcher rd = request.getRequestDispatcher("servlets");
                rd.forward(request,response);
            }
            else
                out.println(1);
        }
    }

    public void destroy() {

        /* Called before the Filter instance is removed from service by the web container*/
    }
}
