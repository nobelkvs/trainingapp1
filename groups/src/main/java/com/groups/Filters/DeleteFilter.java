package com.groups.Filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteFilter implements Filter {
    public static final Logger log = Logger.getLogger(DeleteFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        PrintWriter out = res.getWriter();
        if (req.getMethod().equalsIgnoreCase("DELETE")) {
            String username = req.getParameter("username");
            String pwd  = req.getParameter("password");
            if (username.equalsIgnoreCase("admin") && pwd.equals("admin123")) {

                RequestDispatcher rd = req.getRequestDispatcher("servlets");
                //  rd.forward(request,response);
                rd.forward(req, res);
                log.info("Admin filter success");
            } else {
                out.print("UnauthorizedAccess");
                log.info("UnauthorizedAccess");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
