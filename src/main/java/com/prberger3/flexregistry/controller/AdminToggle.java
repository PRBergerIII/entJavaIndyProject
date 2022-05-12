package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.WishList;
import com.prberger3.flexregistry.persistence.GenericDao;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A servlet for setting or revoking admin rights on the session, depending
 * on their current status.
 *
 * @author  Paul Berger
 */
public class AdminToggle extends HttpServlet {

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

        String url = request.getContextPath() + "/";
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User loggedUser = null;

        if (loggedUserId != null) {
            loggedUser = userDao.getById(loggedUserId);
        } else {
            response.sendError(401);
            return;
        }

        if (loggedUser == null || !loggedUser.isAdmin()) {
            response.sendError(403);
            return;
        }

        boolean isAdmin = (boolean) session.getAttribute("isAdmin");

        if (isAdmin) {
            session.setAttribute("isAdmin", false);
        } else {
            session.setAttribute("isAdmin", true);
        }

        response.sendRedirect(url);

    }



}