<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - Search Results Page
-->

html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="text-capitalize mt-2 mb-0">Search Results</h2>
  <c:choose>
    <c:when test="${users.size() > 0}">
      <h3>Users</h3>
      <table class="table table-sm table-responsive-md">
        <thead>
        <tr>
          <th scope="col">Username</th>
          <th scope="col">firstName</th>
          <th scope="col">lastName</th>
          <th scope="col">Email</th>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="item">
        <tr>
          <td><a href="${webApp}/profile?ownerId=${user.id}">${user.username}</a></td>
          <td>${user.firstName}</td>
          <td>${user.lastName}</td>
          <td>${user.email}</td>
        </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <p>No items on this list yet!</p>
    </c:otherwise>
  </c:choose>

  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
