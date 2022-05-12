<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="row navbar navbar-expand-md navbar-light bg-light" role="navigation">
  <a class="navbar-brand" href="${webApp}/"><img src="${webApp}/img/flex-registry.png" width="45" height="45" alt="Flex Registry"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
          aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse " id="navbar">
    <ul class="navbar-nav mr-auto">
      <c:choose>
        <c:when test="${empty user}">
          <li class="nav-item"><a href="${webApp}/logIn" class="nav-link">Log In / Sign Up</a></li>
        </c:when>
        <c:otherwise>
          <li class="nav-item"><a href="${webApp}/profile?ownerId=${userId}" class="nav-link">My Profile</a></li>
          <li class="nav-item"><a href="${webApp}/user-lists?ownerId=${userId}" class="nav-link">My Lists</a></li>
          <li class="nav-item"><a href="${webApp}/new-list" class="nav-link">New List</a></li>
          <li class="nav-item"><a href="${webApp}/log-out" class="nav-link">Log Out</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
    <div class="navbar-nav ml-auto">
      <form action="#" class="needs-validation w-100 m-0 p-0 align-middle" novalidate> <!-- // TODO: 5/11/2022 change validation and requirements and add  -->
        <div class="form-group form-row m-0">
          <div class="p-0 m-0 d-inline-flex">
            <input type="text" class="form-control"
                   name="searchTerm" id="searchTerm"
                   maxlength="40" required/>
            <button type="submit" name="searchBtn" class="d-flex justify-content-center align-items-center col-2 btn btn-secondary"><i class="bi bi-search"></i></button>
          </div>
        </div>
      </form>
    </div>
  </div>
</nav>
