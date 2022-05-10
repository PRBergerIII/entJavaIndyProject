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

// TODO: 5/8/2022 javadoc
public class UserPersistenceHandler extends HttpServlet {

    GenericDao<User> userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * todo
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
        User foundUser = null;
        String url = "/";
        String errorUrl = request.getContextPath() + "/"; // TEMP: 5/10/2022 change this to error page url

        // In case everything authenticates but for some reason the user
        // is unavailable (this is a fringe case that a test user experienced)
        try {
            foundUser = findUser(authenticatedUser.getUsername());
        } catch (NullPointerException npex) {
            logger.error("Error getting or validating the token: "
                        + npex.getMessage(), npex);
            response.sendRedirect(errorUrl);
        }

        if (foundUser != null) {

            session.setAttribute("userId", foundUser.getId());

        } else {

            int newUserId = userDao.insert(authenticatedUser);
            session.setAttribute("userId", newUserId);

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

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
