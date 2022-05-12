package com.prberger3.flexregistry.controller.display;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * A servlet for building the title attribute for and forwarding requests to
 * the home page.
 *
 * @author  Paul Berger
 */
public class IndexDisplay extends HttpServlet {

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
        String title = "Home | Flex Registry";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        GenericDao<User> userDao = new GenericDao<>(User.class);

        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        }

        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}