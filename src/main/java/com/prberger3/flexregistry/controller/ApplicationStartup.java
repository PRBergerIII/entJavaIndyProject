package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Properties;

// TODO: 5/8/2022 javadocs

/**
 * todo
 *
 * @author  Paul Berger
 */
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

    Properties properties;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * todo
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
        context.setAttribute("cognitoProperties", properties);
 
    }

}
