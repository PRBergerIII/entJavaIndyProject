package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.WishList;
import com.prberger3.flexregistry.entity.WishListItem;
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

        String url = request.getContextPath() + "/edit-list";
        String queryParam = "";
        String idParam = request.getParameter("listId");
        Integer listId = idParam == null || idParam.equals("")
                ? null : Integer.valueOf(idParam);
        WishListItem newItem = new WishListItem();

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");

        GenericDao<WishList> listDao = new GenericDao<>(WishList.class);
        GenericDao<WishListItem> itemDao = new GenericDao<>(WishListItem.class);

        if (loggedUserId == null) {
            response.sendError(403);
            return;
        }

        if (listId == null) {
            response.sendError(424);
            return;
        }

        newItem.setWishList(listDao.getById(listId));
        newItem.setName(request.getParameter("name"));
        newItem.setSpecificItem(Boolean.parseBoolean(
                                request.getParameter("specificItem")));
        newItem.setDetails(nullifyIfEmpty(request.getParameter("details")));
        newItem.setPriority(Integer.parseInt(request.getParameter("priority")));
        newItem.setPriceRange(nullifyIfEmpty(request.getParameter("priceRange")));

        itemDao.insert(newItem);

        queryParam = String.format("?listId=%d", listId);
        response.sendRedirect(url + queryParam);

    }

    /**
     * Converts empty strings to null values for proper database default
     * utilization.
     *
     * @param parameter
     * @return null if an empty string, the original string otherwise
     */
    private String nullifyIfEmpty(String parameter) {
        return parameter.equals("") ? null : parameter;
    }

}
