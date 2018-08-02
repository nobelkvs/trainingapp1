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

    // Create instance of logger in defectServiceImplementation class
    static final Logger log=Logger.getLogger(ServletFilters.class);

    /**
     * Called only once and only when the servlet is created, and not called for any user requests afterwards
     * @param arg0
     * @throws ServletException
     */
    public void init(FilterConfig arg0) throws ServletException {}

    /**
     * Called by the container each time a request/response pair is passed through the chain due to a client request for a resource at the end of the chain
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        log.info("in servlet Filters");

        // PrintWriter enables you to write formatted data into text format
        PrintWriter out = res.getWriter();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Took input data from UI
        String UserRole= request.getParameter("UserRole");
        String password = request.getParameter("password");

        String method = request.getMethod();

        if(method.equalsIgnoreCase("delete"))
        {
            if(UserRole.equalsIgnoreCase("admin") && password.equals("admin"))
            {
                // The getRequestDispatcher() method of ServletRequest interface returns the object of RequestDispatcher and RequestDispatcher interface provides facility of dispatching the request to defectController servlet
                RequestDispatcher rd = request.getRequestDispatcher("servlets");

                // Forwards a request from a ServletFilters servlet to defectController servlet
                rd.forward(request,response);
            }
            else{
                out.println(1);
            }
        }
    }

    /**
     * Called before the Filter instance is removed from service by the web container
     */
    public void destroy() {}
}
