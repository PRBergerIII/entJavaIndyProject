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
          <c:if test="${user.admin}">
            <c:choose>
              <c:when test="${isAdmin}">
                <li class="nav-item"><a href="${webApp}/new-list" class="nav-link text-danger">User List</a></li>
                <li class="nav-item"><a href="${webApp}/NV3uxlY8A40mC7i4HzH0a2qV8f7D9ZarFNmSdy19I3auMfpdK2ztLEQov24HRojo8eMung" class="nav-link text-danger">ADMIN RIGHTS ON</a></li>
              </c:when>
              <c:otherwise>
                <li class="nav-item"><a href="${webApp}/NV3uxlY8A40mC7i4HzH0a2qV8f7D9ZarFNmSdy19I3auMfpdK2ztLEQov24HRojo8eMung" class="nav-link text-success">ADMIN RIGHTS OFF</a></li>
              </c:otherwise>
            </c:choose>
          </c:if>
          <li class="nav-item"><a href="${webApp}/log-out" class="nav-link">Log Out</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
    <div class="navbar-nav ml-auto">
      <form action="#" class="needs-validation w-100 m-0 p-0 align-middle" novalidate>
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
