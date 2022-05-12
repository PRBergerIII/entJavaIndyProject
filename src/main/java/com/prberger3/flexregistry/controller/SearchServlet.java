package com.prberger3.flexregistry.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * A servlet for Flex Registry whose sole purpose is to forward the request and
 * response to the search results JSP.
 *
 * @author  Paul Berger
 */
public class SearchServlet extends HttpServlet {

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

        String url = "/index";
        String title = "Search Results - Flex Registry";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        request.setAttribute("title", title);
        dispatcher.forward(request, response);

    }

}