package com.banking.controller;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import org.apache.log4j.Logger;

@WebListener("application context listener")

public class ContextListener implements ServletContextListener{

    static final Logger log =Logger.getLogger(Bankingcontrollerservlet.class);

    /**
     * Initialize log4j when the application is being started
     */


    public void contextInitialized(ServletContextEvent event) {
        // initialize log4j here
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        System.out.println("fullPath "+fullPath);
        PropertyConfigurator.configure(fullPath);

    }


    public void contextDestroyed(ServletContextEvent event) {

        // no matter is required here

    }
}
