package com.prberger3.flexregistry.controller.display;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.WishList;
import com.prberger3.flexregistry.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A servlet for  todo
 *
 * @author  Paul Berger
 */
public class UserListsDisplay extends HttpServlet {

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

        String url = "/user-lists-jsp";
        String title = "Lists | Flex Registry";

        HttpSession session = request.getSession(); // TODO: look at consolodating code with other servlets
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        String idParam = request.getParameter("ownerId");
        Integer ownerId = idParam == "" || idParam == null
                        ? null : Integer.valueOf(idParam);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User owner = null;
        List<WishList> ownerLists = new ArrayList<>();
        String ownerLabel = "";

        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        }

        if (ownerId != null) {
            owner = userDao.getById(ownerId);
            request.setAttribute("owner", owner);
        }

        if ((loggedUserId == null && ownerId == null) || owner == null) {
            response.sendError(404);
            return;
        } else if (loggedUserId == ownerId) {
            ownerLabel = "My";
        } else {
            ownerLabel = String.format("%s's", owner.getUsername());
        }

        request.setAttribute("ownerLabel", ownerLabel);
        request.setAttribute("title", ownerLabel + " " + title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}