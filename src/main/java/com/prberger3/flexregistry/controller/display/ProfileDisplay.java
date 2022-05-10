package com.prberger3.flexregistry.controller.display;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet for Flex Registry whose sole purpose is to forward the request and
 * response to the index JSP.
 *
 * @author  Paul Berger
 */
public class ProfileDisplay extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request  the HttpServletRequest object
     *@param  response  the HttpServletResponse object
     *@throws ServletException  if there is a Servlet failure
     *@throws IOException  if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/profile-jsp";
        String title = " - Flex Registry";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        Integer profileUserId = (Integer) request.getAttribute("profileUserId");
        GenericDao<User> userDao = new GenericDao<>(User.class);

        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        }

        if (profileUserId != null) {

            request.setAttribute("profileUser", userDao.getById(profileUserId));
//            title =

        }

        request.setAttribute("title", title);
        dispatcher.forward(request, response);

    }

}
