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

public class EditItemServlet extends HttpServlet {

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

        String url = "/edit-item-jsp";
        String title = "Edit Item | Flex Registry";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        String idParam = request.getParameter("itemId");
        Integer itemId = idParam == null || idParam.equals("")
                ? null : Integer.valueOf(idParam);
        WishListItem listItem = new WishListItem();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<WishListItem> itemDao = new GenericDao<>(WishListItem.class);


        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        } else {
            response.sendError(403);
            return;
        }

        if (itemId != null) {
            listItem = itemDao.getById(itemId);
            if (listItem != null) {
                request.setAttribute("listItem", listItem);
            }
        }

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
        String itemIdParam = request.getParameter("itemId");
        Integer itemId = itemIdParam == null || itemIdParam.equals("")
                ? null : Integer.valueOf(itemIdParam);
        WishList ownerList = null;
        WishListItem oldItem = null;
        WishListItem updatedItem = new WishListItem();

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");

        GenericDao<WishListItem> itemDao = new GenericDao<>(WishListItem.class);

        if (loggedUserId == null) {
            response.sendError(403);
            return;
        }

        if (itemId != null) {
            oldItem = itemDao.getById(itemId);
        } else {
            response.sendError(424);
            return;
        }

        ownerList = oldItem.getWishList();
        updatedItem.setId(itemId);
        updatedItem.setWishList(ownerList);
        updatedItem.setName(request.getParameter("name"));
        updatedItem.setSpecificItem(Boolean.parseBoolean(
                request.getParameter("specificItem")));
        updatedItem.setDetails(nullifyIfEmpty(request.getParameter("details")));
        updatedItem.setPriority(Integer.parseInt(request.getParameter("priority")));
        updatedItem.setPriceRange(nullifyIfEmpty(request.getParameter("priceRange")));

        itemDao.saveOrUpdate(updatedItem);

        queryParam = String.format("?listId=%d", ownerList.getId());
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
