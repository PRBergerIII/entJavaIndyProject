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
        String title = "Search Results - Flex Registry";
        String searhType = (String) request.getParameter("search");
        List<User> users = new ArrayList<>();
        List<WishList> wishLists = new ArrayList<>();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<WishList> listDao = new GenericDao<>(WishList.class);

        if (searhType.equals("admin")) {
            users = userDao.getAll();
        } else {
            keyWordSearch(users, wishLists, userDao, listDao);
        }

        request.setAttribute("users", users);
        request.setAttribute("wishLists", wishLists);
        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private void keyWordSearch(List<User> users,
                               List<WishList> wishLists,
                               GenericDao<User> userDao,
                               GenericDao<WishList> listDao) {

    }

}