<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - List Details Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="text-capitalize mt-2 mb-0">${wishList.title}</h2>
  <ul class="ml-0 list-unstyled mb-4">
    <li class="text-capitalize">By: ${owner.username}</li>
    <c:if test="${wishList.listType != null}">
      <li>List type: ${wishList.listType}</li>
    </c:if>
    <c:if test="${wishList.eventDate != null}">
      <li>Event Date: ${wishList.eventDate}</li>
    </c:if>
  </ul>
  <c:choose>
    <c:when test="${listItems.size() > 0}">
      <table class="table table-sm table-responsive-md">
        <thead>
        <tr>
          <th scope="col">Item</th>
          <th scope="col">Specific</th>
          <th scope="col">Details or Link</th>
          <th scope="col">Priority</th>
          <th scope="col">Price Range</th>
        </thead>
        <tbody>
        <c:forEach items="${listItems}" var="item">
        <tr>
          <th scope="row">${item.name}</th>
          <td>${item.specificItem ? "Yes" : "No"}</td>
          <c:choose>
            <c:when test="${item.details.startsWith(\"http\")}">
              <td>
                <a href="${item.details}" target="_blank">
                  Link to Item <i class="bi bi-box-arrow-up-right"></i>
                </a>
              </td>
            </c:when>
            <c:otherwise>
              <td>${item.details}</td>
            </c:otherwise>
          </c:choose>
          <td>${priorities.get(item.priority)}</td>
          <td>${item.priceRange}</td>
        </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <p>No items on this list yet!</p>
    </c:otherwise>
  </c:choose>


  <div class  ="row mt">
    <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
      <c:if test="${user.equals(owner) || isAdmin}">
        <a class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2"
           href="${webApp}/edit-list?listId=${wishList.id}" role="button">
          Edit Wish List
        </a>
      </c:if>
      <a class="btn btn-secondary col-md-3 col-sm-5 ml-md-2 col-8 mb-2"
         href="${webApp}/user-lists?ownerId=${owner.id}" role="button">Go Back</a>
    </div>
  </div>
  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
