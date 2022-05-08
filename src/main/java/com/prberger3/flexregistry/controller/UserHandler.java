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

public class UserHandler extends HttpServlet {

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
        String userName = (String) request.getAttribute("userName");
        User foundUser = findUser(userName);
        String url = "/";

        if (foundUser != null) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("user", foundUser);
        } else {
//            handle adding to the db here
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private User findUser(String userName) {

        List<User> result = userDao.findByPropertyEqual("userName", userName);

        if (result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }

    }

}
