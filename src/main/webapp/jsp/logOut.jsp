<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - Log Out Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">

  <div class="card">
    <div class="card-body"><c:choose>
      <c:when test="${logOutConfirm == false}">
        Are you sure you want to log out?
        <button><a href="${webApp}/log-out-servlet">Yes</a></button>
        <button><a href="${webApp}/user-lists?ownerId=${userId}">No</a></button>
      </c:when>
      <c:otherwise>
        See you soon!
        <button><a href="${webApp}/">Thanks!</a></button>
      </c:otherwise>
    </c:choose>
    </div>
  </div>
</div>
<c:import url="bootstrap"/>
</body>
</html>
