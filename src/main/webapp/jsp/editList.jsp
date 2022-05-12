<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - Edit List Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>



  <div class  ="row mt">
    <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
      <c:if test="${user.equals(owner) || isAdmin}">
        <a class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2"
           href="#" role="button"> <%--     // TODO: 5/11/2022 this goes to edit page      --%>
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

