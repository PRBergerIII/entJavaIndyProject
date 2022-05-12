<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - Search Results Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="text-capitalize mt-2 mb-4">Search Results</h2>
  <h3>User Results</h3>
  <c:choose>
    <c:when test="${users.size() > 0}">
      <table class="table table-sm table-responsive-md">
        <thead>
        <tr>
          <th scope="col">Username</th>
          <th scope="col">firstName</th>
          <th scope="col">lastName</th>
          <c:if test="${isAdmin}">
            <th scope="col">Email</th>
          </c:if>
          <th scope="col">No. of Lists</th>

        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
        <tr>
          <td><a href="${webApp}/profile?ownerId=${user.id}">${user.username}</a></td>
          <td>${user.firstName}</td>
          <td>${user.lastName}</td>
          <c:if test="${isAdmin}">
            <td>${user.email}</td>
          </c:if>
          <td>${user.wishLists.size()}</td>
        </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <p>No Users Found!</p>
    </c:otherwise>
  </c:choose>

  <h3>Wish List Results</h3>
  <c:choose>
    <c:when test="${wishLists.size() > 0}">
      <h3>Users</h3>
      <table class="table table-sm table-responsive-md">
        <thead>
        <tr>
          <th scope="col">Title</th>
          <th scope="col">Owner</th>
          <th scope="col">List Type</th>
          <th scope="col">Event Date</th>
          <th scope="col">No. of Items</th>
        </thead>
        <tbody>
        <c:forEach items="${wishLists}" var="list">
        <tr>
          <td><a href="${webApp}/list-details?listId=${list.id}">${list.title}</a></td>
          <td><a href="${webApp}/profile?ownerId=${list.owner.id}">${list.owner.username}</a></td>
          <td>${list.listType}</td>
          <td>${list.eventDate}</td>
          <td>${list.items.size()}</td>
        </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <p>No Lists Found!</p>
    </c:otherwise>
  </c:choose>

  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
