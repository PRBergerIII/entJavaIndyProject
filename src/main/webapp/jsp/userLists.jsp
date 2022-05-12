<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - User's Lists Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="text-capitalize mt-2 mb-4">${ownerLabel} Wish Lists</h2>

  <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 justify-content-start">
    <!-- start loop -->
    <div class="col">
      <div class="card mb-4">
        <div class="card-header bg-success text-dark">
          <h3 class="mb-0">${wishList.title}</h3>
        </div>
        <div class="card-body bg-light p-2">
          <ul class="list-group list-group-flush bg-light my-0">
            <li class="list-group-item bg-light">List type: ${wishList.listType}</li>
            <li class="list-group-item bg-light">Event Date: ${wishList.eventDate}</li>
            <li class="list-group-item bg-light">Number of Items: ${wishList.items.size()}</li>
          </ul>
        </div>
        <a href="#" class="stretched-link"></a>
      </div>
    </div>
    <!-- end loop -->
  </div>



  <div class="row mt">
    <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
      <a class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2"
         href="${webApp}/user-lists?ownerId=${owner.id}" role="button">
        New Wish List
      </a>
    </div>
  </div>
  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
