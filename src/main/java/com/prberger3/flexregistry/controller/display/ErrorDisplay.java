package com.prberger3.flexregistry.controller.display;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is responsible for handling error status codes and forwarding
 * to the JSP.
 *
 * @author Paul Berger
 */
public class ErrorDisplay extends HttpServlet {

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
        String url = "/error-jsp";
        String title = String.format("%d | Flex Registry", statusCode);

        request.setAttribute("statusCode", statusCode);

        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}