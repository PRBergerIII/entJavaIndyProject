package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * This class stores new users to the database, and checks any users that
 * log in to confirm they are already there.
 */
public class UserPersistenceHandler extends HttpServlet {

    GenericDao<User> userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates the DAO
     *
     * @throws ServletException  if there is a Servlet failure
     */
    @Override
    public void init() throws ServletException {

        userDao = new GenericDao<>(User.class);

    }

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

        HttpSession session = request.getSession();
        User authenticatedUser = (User) request.getAttribute("authenticatedUser");
        User foundUser = findUser(authenticatedUser.getUsername());
        String contextPath = request.getContextPath();
        String url = contextPath + "/";

        if (foundUser != null) {

            session.setAttribute("userId", foundUser.getId());
            session.setAttribute("isAdmin", false);

        } else {

            int newUserId = userDao.insert(authenticatedUser);
            session.setAttribute("isAdmin", false);

        }


        response.sendRedirect(url);

    }

    private User findUser(String userName) {

        List<User> result = userDao.findByPropertyEqual("username", userName);

        // This will never be more than 1; the database requires that username
        // be a unique value to match cognito constraints
        if (result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }

    }

}
