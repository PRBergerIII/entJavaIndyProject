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

public class DeleteListServlet extends HttpServlet {

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

        String url = "/user-lists";
        String title = "Edit List | Flex Registry";
        String queryParam = "";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        String idParam = request.getParameter("listId");
        Integer listId = idParam == null || idParam.equals("")
                ? null : Integer.valueOf(idParam);
        Integer ownerId = null;
        WishList wishList = new WishList();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<WishList> listDao = new GenericDao<>(WishList.class);


        if (loggedUserId != null) {
            request.setAttribute("user", userDao.getById(loggedUserId));
        } else {
            response.sendError(403);
            return;
        }

        if (listId != null) {
            wishList = listDao.getById(listId);
        }

        if (wishList != null) {
            ownerId = wishList.getOwner().getId();
            listDao.delete(wishList);
        }

        queryParam = "?ownerId=" + ownerId;

        request.setAttribute("title", title);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url + queryParam);
        dispatcher.forward(request, response);

    }

}