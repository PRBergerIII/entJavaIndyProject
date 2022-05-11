package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfileServlet  extends HttpServlet {

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

        String url = "/edit-profile-jsp";
        String title = "Edit Profile | Flex Registry";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        GenericDao<User> userDao = new GenericDao<>(User.class);


        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        } else {
            response.sendError(403);
            return;
        }

        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     *  Handles HTTP POST requests.
     *
     *@param  request  the HttpServletRequest object
     *@param  response  the HttpServletResponse object
     *@throws ServletException  if there is a Servlet failure
     *@throws IOException  if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getContextPath() + "/profile";
        String queryParam = "";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User loggedUser = null;
        User updatedUser = new User();

        if (loggedUserId != null) {
            loggedUser = userDao.getById(loggedUserId);
            queryParam = String.format("?ownerId=%d", loggedUserId);
        } else {
            response.sendError(403);
            return;
        }

        updatedUser.setId(loggedUserId);
        updatedUser.setUsername(loggedUser.getUsername());
        updatedUser.setFirstName(request.getParameter("firstName"));
        updatedUser.setLastName(request.getParameter("lastName"));
        updatedUser.setEmail(request.getParameter("email"));
        updatedUser.setStreet(nullifyIfEmpty(request.getParameter("street")));
        updatedUser.setCity(nullifyIfEmpty(request.getParameter("city")));
        updatedUser.setState(nullifyIfEmpty(request.getParameter("state")));
        updatedUser.setZip(nullifyIfEmpty(request.getParameter("zip")));
        updatedUser.setAddressVisibility(request.getParameter("addressVisibility"));
        updatedUser.setAbout(nullifyIfEmpty(request.getParameter("about")));
        updatedUser.setAdmin(loggedUser.isAdmin());

        userDao.saveOrUpdate(updatedUser);

        response.sendRedirect(url + queryParam);

    }
// TODO: 5/11/2022 javadoc 
    private String nullifyIfEmpty(String parameter) {
        return parameter == "" ? null : parameter;
    }

}