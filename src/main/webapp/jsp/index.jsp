<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.01.30
Individual Project: Registry - Home
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<c:import url="header"/>
<c:import url="/nav"/>
<div class="container">

  <div class="card">
    <div class="card-body">
      <c:choose>
        <c:when test="${empty user}">
          <h1>Welcome!</h1>
          <a href = "logIn">Log innnnn</a>
        </c:when>
        <c:otherwise>
          <h3>Welcome ${user.firstName} ${user.lastName}!</h3>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>
<c:import url="bootstrap"/>
<c:import url="footer"/>
</body>
</html>
