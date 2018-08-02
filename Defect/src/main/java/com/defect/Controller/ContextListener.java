package com.defect.Controller;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener("application context listener")
/**
 * Context Listener for Log4j
 * instantiate application object
 */
public class ContextListener implements ServletContextListener {

    /**
     * the web application initialization process is started
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {

        //get the servlet context object
        ServletContext context = event.getServletContext();

        //Getting the value of the initialization parameter
        String log4jConfigFile = context.getInitParameter("log4j-config-location");

        //Convert a web content path to absolute file system path
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        System.out.println("fullPath "+fullPath);

        //Allows the configuration of log4j from an external file
        PropertyConfigurator.configure(fullPath);

    }

    /**
     * The servlet context is about to be shut down.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {}

}