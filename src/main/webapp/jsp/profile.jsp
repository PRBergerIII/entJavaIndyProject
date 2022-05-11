<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - Profile Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="text-capitalize mt-2 mb-4">${ownerLabel} Profile Page</h2>
  <dl class="row">
    <dt class="col-md-3 col-xl-2">Name</dt>
    <dd class="col-md-9 col-xl-10">${owner.firstName} ${owner.lastName}</dd>
    <c:if test="${owner == user || admin == true}">
      <dt class="col-md-3 col-xl-2">Email</dt>
      <dd class="col-md-9 col-xl-10">${owner.email}</dd>
    </c:if>
    <c:choose>
      <c:when test="${owner.street != null
                      && owner.city != null
                      && owner.state != null
                      && owner.zip != null}">
        <c:if test="${owner == user || owner.addressVisibility == 'public' || admin == true}">
          <dt class="col-md-3 col-xl-2">Shipping Address</dt>
          <dd class="col-md-9 col-xl-10">
            <ul class="list-unstyled mb-0">
              <li>${owner.street}</li>
              <li>${owner.city}, ${owner.state} ${owner.zip}</li>
            </ul>
          </dd>
        </c:if>
      </c:when>
      <c:otherwise>
        <c:if test="${owner == user}">
          <dt class="col-md-3 col-xl-2">Shipping Address</dt>
          <dd class="col-md-9 col-xl-10">
            <p>Edit your profile below so people know where to ship your items!</p>
          </dd>
        </c:if>
      </c:otherwise>
    </c:choose>
    <dt class="col-md-3 col-xl-2">About</dt>
    <dd class="col-md-9 col-xl-10">
      <p>
        <c:choose>
          <c:when test="${owner.about != null}">
            ${owner.about}
          </c:when>
          <c:otherwise>
            Nothing here yet!
          </c:otherwise>
        </c:choose>
      </p>
    </dd>
  </dl>

  <div class="row">
    <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
      <a class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2"
         href="${webApp}/user-lists?ownerId=${owner.id}" role="button">
        View Wish Lists
      </a>
      <a class="btn btn-primary col-md-3 col-sm-5 ml-md-2 col-8 mb-2"
         href="#" role="button">
        Edit Profile
      </a>
    </div>
  </div>
  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
