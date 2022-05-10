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
        String title = "Profile - Flex Registry";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        Integer profileUserId = (Integer) request.getAttribute("profileUserId");
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User profileUser = null;
        String owner = "";

        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        }

        if (profileUserId != null) {
            profileUser = userDao.getById(profileUserId);
            request.setAttribute("profileUser", profileUser);
        }

        if (loggedUserId == null && (profileUserId == null || profileUser == null)) {
            response.sendRedirect(request.getContextPath() + "/"); // TODO: send to 404 error page instead?
            return;
        } else if (loggedUserId == profileUserId) {
            owner = "My ";
        } else {
            owner = String.format("%s's ", profileUser.getUsername());

        }

        request.setAttribute("profileOwner", owner);
        request.setAttribute("title", owner + title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
