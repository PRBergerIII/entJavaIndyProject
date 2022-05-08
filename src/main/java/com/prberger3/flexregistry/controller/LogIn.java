package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

// TODO: 5/8/2022 javadocs
/** Begins the authentication process using AWS Cognito
 *
 */
public class LogIn extends HttpServlet implements PropertiesLoader {

    Properties properties;
    public static String CLIENT_ID;
    public static String LOGIN_URL;
    public static String REDIRECT_URL;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        super.init();
        getProperties();
    }

    /**
     * todo
     */
    // 4 to do this work a single time and put the properties in the application scope
    private void getProperties() {

        ServletContext context = getServletContext();
        properties = (Properties) context.getAttribute("cognitoProperties");

        CLIENT_ID = properties.getProperty("client.id");
        LOGIN_URL = properties.getProperty("loginURL");
        REDIRECT_URL = properties.getProperty("redirectURL");

    }

    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO if properties weren't loaded properly, route to an error page
        String url = LOGIN_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
        resp.sendRedirect(url);
    }
}
