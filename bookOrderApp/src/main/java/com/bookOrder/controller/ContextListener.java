package com.bookOrder.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;


public class ContextListener implements ServletContextListener {

    // Create Logger instance
    static final Logger log = Logger.getLogger(ContextListener.class);

    /**
     * Initialize log4j when the application is being started
     *
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Initialize log4j here
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        System.out.println("fullPath " + fullPath);
        PropertyConfigurator.configure(fullPath);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Do nothing
    }
}
