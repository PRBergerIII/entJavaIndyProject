package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Properties;

/**
 * A class for setting up the application environment.
 *
 * @author  Paul Berger
 */
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

    Properties properties;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The init method, which loads the cognito properties, and also
     * assigns a context attribute to the context path for easy access
     *
     * @throws ServletException  if there is a Servlet failure
     */
    @Override
    public void init() throws ServletException {

        try {
            properties = loadProperties("/cognito.properties");
        } catch (IOException ioException) {
            logger.error("Cannot load properties..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties" + e.getMessage(), e);
        }

        ServletContext context = getServletContext();
        String contextPath = context.getContextPath();
        context.setAttribute("cognitoProperties", properties);
        context.setAttribute("webApp", contextPath);
    }

}
