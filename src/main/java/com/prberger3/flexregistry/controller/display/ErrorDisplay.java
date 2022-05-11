package com.prberger3.flexregistry.controller.display;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorDisplay extends HttpServlet {
// TODO: javadoc
    /**
     *  Handles HTTP GET requests.
     *
     *@param  request  the HttpServletRequest object
     *@param  response  the HttpServletResponse object
     *@throws  ServletException  if there is a Servlet failure
     *@throws  IOException  if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer statusCode = response.getStatus();
        if (statusCode == null) statusCode = 418;
        String url = String.format("https://http.cat/%d/", statusCode);

        response.sendRedirect(url);

    }

}