package com.prberger3.flexregistry.controller.display;

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
import java.util.*;

/**
 * A servlet for doing some initial processing before forwarding the request
 * to the list details JSP.
 *
 * @author  Paul Berger
 */
public class ListDetailsDisplay extends HttpServlet {

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

        String url = "/list-details-jsp";
        String title = "List Details | Flex Registry";

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
        }

        if (listId != null) {
            wishList = listDao.getById(listId);
        }

        if (wishList == null) {
            response.sendError(404);
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

}
