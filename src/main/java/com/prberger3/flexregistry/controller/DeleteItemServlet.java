package com.prberger3.flexregistry.controller;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.WishListItem;
import com.prberger3.flexregistry.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteItemServlet extends HttpServlet {

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

        String url = "/edit-list";
        String title = "Edit List | Flex Registry";
        String queryParam = "";

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
        }

        if (listItem != null) {
            queryParam = "?listId=" + String.valueOf(listItem.getWishList().getId());
            itemDao.delete(listItem);
        }

        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext()
                                      .getRequestDispatcher(url + queryParam);
        dispatcher.forward(request, response);

    }

}
