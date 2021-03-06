package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.WishList;
import com.prberger3.flexregistry.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A servlet for Flex Registry whose sole purpose is to forward the request and
 * response to the search results JSP.
 *
 * @author  Paul Berger
 */
public class SearchServlet extends HttpServlet {


    private List<User> users;
    private List<WishList> wishLists;
    GenericDao<User> userDao;
    GenericDao<WishList> listDao;

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
        Integer loggedUserId = (Integer) session.getAttribute("userId");

        String url = "/search-results-jsp";
        String title = "Search Results - Flex Registry";
        String searhType = request.getParameter("search");
        String searchTerm = request.getParameter("searchTerm");

        userDao = new GenericDao<>(User.class);
        listDao = new GenericDao<>(WishList.class);

        if (searhType != null && searhType.equals("admin")) {
            users = userDao.getAll();
        } else {
            keyWordSearch(searchTerm);
        }

        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        }

        request.setAttribute("users", users);
        request.setAttribute("wishLists", wishLists);
        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private void keyWordSearch(String searchTerm) {

        users = userDao.findByPropertyLike("firstName", searchTerm);
        users.addAll(userDao.findByPropertyLike("lastName", searchTerm));
        users.addAll(userDao.findByPropertyLike("username", searchTerm));

        wishLists = listDao.findByPropertyLike("title", searchTerm);

    }

}