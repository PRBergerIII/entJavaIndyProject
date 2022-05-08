<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.01.30
Individual Project: Registry - Home
-->

<html lang="en" dir="ltr">
<c:import url="/imports/head.jsp"/>
<body>

<div class="container">

  <div class="card">
    <div class="card-body">
      <h1>Welcome!</h1>
      <c:choose>
        <c:when test="${empty userName}">
          <a href = "logIn">Log in</a>
        </c:when>
        <c:otherwise>
          <h3>Welcome ${userName}</h3>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>
<c:import url="/imports/bootstrap.jsp"/>
</body>
</html>
