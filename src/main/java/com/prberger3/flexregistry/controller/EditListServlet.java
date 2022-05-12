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
import java.util.*;

public class EditListServlet extends HttpServlet {

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

        String url = "/edit-list-jsp";
        String title = "Edit List | Flex Registry";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        String idParam = request.getParameter("listId");
        Integer listId = idParam == null || idParam.equals("")
                ? null : Integer.valueOf(idParam);
        WishList wishList = null;
        Map<Integer, String> priorities = new HashMap<>();

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

        if (wishList == null) {
            response.sendError(404);
            return;
        }

        if (loggedUserId != wishList.getOwner().getId()
                && !(boolean) session.getAttribute("isAdmin")) {
            response.sendError(403);
            return;
        }


        List<WishListItem> listItems = new ArrayList<>(wishList.getItems());
        Collections.sort(listItems, Comparator.comparingInt(WishListItem::getId));
        priorities.put(1, "Lowest");
        priorities.put(2, "Low");
        priorities.put(3, "Medium");
        priorities.put(4, "High");
        priorities.put(5, "Highest");

        request.setAttribute("priorities", priorities);
        request.setAttribute("listItems", listItems);
        request.setAttribute("wishList", wishList);
        request.setAttribute("owner", wishList.getOwner());
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

        String url = request.getContextPath() + "/user-lists";
        String queryParam = "";

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("userId");
        User loggedUser = null;
        String idParam = request.getParameter("listIdToUpdate");
        Integer listId = idParam == null || idParam.equals("")
                ? null : Integer.valueOf(idParam);
        WishList updatedList = new WishList();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<WishList> listDao = new GenericDao<>(WishList.class);

        if (loggedUserId != null) {
            loggedUser = userDao.getById(loggedUserId);
        } else {
            response.sendError(403);
            return;
        }

        if (listId == null) {
            response.sendError(424);
            return;
        }

        updatedList.setId(listId);
        updatedList.setOwner(loggedUser);
        updatedList.setTitle(request.getParameter("title"));
        updatedList.setVisibility(request.getParameter("visibility"));
        updatedList.setListType(request.getParameter("listType"));
        if (!request.getParameter("eventDate").equals("")) {
            updatedList.setEventDate(LocalDate.parse(request.getParameter("eventDate")));
        }

        listDao.saveOrUpdate(updatedList);

        queryParam = String.format("?ownerId=%d", loggedUserId);
        response.sendRedirect(url + queryParam);

    }


}
