package com.prberger3.flexregistry.controller;

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
import java.time.LocalDate;

public class NewItemServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/new-item-jsp";
        String title = "New Item | Flex Registry";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        String listId = request.getParameter("listId");
        GenericDao<User> userDao = new GenericDao<>(User.class);


        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        } else {
            response.sendError(403);
            return;
        }

        if (listId == null) {
            response.sendError(404);
            return;
        }

        request.setAttribute("listId", listId);
        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Handles HTTP POST requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String url = request.getContextPath() + "/edit-list";
//        String queryParam = "";
//
//        HttpSession session = request.getSession();
//        Integer loggedUserId = (Integer) session.getAttribute("userId");
//        User loggedUser = null;
//        WishList newList = new WishList();
//
//        GenericDao<User> userDao = new GenericDao<>(User.class);
//        GenericDao<WishList> listDao = new GenericDao<>(WishList.class);
//
//        if (loggedUserId != null) {
//            loggedUser = userDao.getById(loggedUserId);
//        } else {
//            response.sendError(403);
//            return;
//        }
//
//        newList.setOwner(loggedUser);
//        newList.setTitle(request.getParameter("title"));
//        newList.setVisibility(request.getParameter("visibility"));
//        newList.setListType(request.getParameter("listType"));
//        if (!request.getParameter("eventDate").equals("")) {
//            newList.setEventDate(LocalDate.parse(request.getParameter("eventDate")));
//        }
//
//        int newId = listDao.insert(newList);
//
//        queryParam = String.format("?listId=%d", newId);
//        response.sendRedirect(url + queryParam);

    }

}
