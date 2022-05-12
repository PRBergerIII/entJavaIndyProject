<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.11
Individual Project: Flex Registry - Edit List Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="mt-2 mb-0">Edit "${wishList.title}"</h2>
  <form class="mt-0 needs-validation" action="${webApp}/edit-list" method="POST" novalidate>

    <div class="form-group row mb-4">
      <p class="text-danger mb-0 ml-3">Required fields - *</p>
    </div>

    <div class="form-group row">
      <label for="title"
             class="col-md-2 col-form-label">List Title<span class="text-danger font-weight-bold">*</span></label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="title"
               name="title"
               value="${wishList.title}"
               placeholder="Your list's title" required>
        <div class="invalid-feedback">
          Please provide a title for your wish list.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="visibility" class="col-md-2 col-form-label">
        List Visibility<span class="text-danger font-weight-bold">*</span>
        <small>
          <i class="bi bi-info-circle-fill ml-1 text-muted align-top"
             title="This decides who can see this list only."></i>
        </small>
      </label>
      <div class="col-lg-4 col-md-6">
        <select class="custom-select" id="visibility"
                name="visibility" required>
          <option value="public" ${wishList.visibility != 'private' ? 'selected' : ''}>Public</option>
          <option value="private" ${wishList.visibility == 'private' ? 'selected' : ''}>Private</option>
          <!-- TODO: add followers option once that system is set up -->
        </select>
        <div class="invalid-feedback">
          Please select a visibility.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="listType"
             class="col-md-2 col-form-label">
        List Type<span class="text-danger font-weight-bold">*</span>
        <small>
          <i class="bi bi-info-circle-fill ml-1 text-muted align-top"
             title="Holiday, Birthday, Wedding, etc."></i>
        </small>
      </label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="listType"
               name="listType"
               value="${wishList.listType}"
               placeholder="Your list's type" required>
        <div class="invalid-feedback">
          Please provide type for your list.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="eventDate" class="col-md-2 col-form-label">Event Date</label>
      <div class="col-lg-4 col-md-6">
        <input type="date" class="form-control" id="eventDate"
               name="eventDate"
               value="${wishList.eventDate}">
      </div>
    </div>

    <div class="form-group row mt-4 mb-5">
      <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
        <button class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2" type="submit">Submit</button>
        <a class="btn btn-secondary col-md-3 col-sm-5 ml-md-2 col-8 mb-2" href="${webApp}/user-lists?ownerId=${owner.id}" role="button">Back to Lists</a>
      </div>
      <input type="hidden" name="listIdToUpdate" value="${wishList.id}">
    </div>
  </form>

  <c:choose>
    <c:when test="${listItems.size() > 0}">
      <table class="table table-sm table-responsive-md">
        <thead>
        <tr>
          <th scope="col">Item</th>
          <th scope="col">Specific</th>
          <th scope="col">Details or Link</th>
          <th scope="col">Priority</th>
          <th scope="col">Price Range</th>
          <th scope="col"></th>
          <th scope="col"></th>
        </thead>
        <tbody>
        <c:forEach items="${listItems}" var="item">
        <tr>
          <th scope="row">${item.name}</th>
          <td>${item.specificItem ? "Yes" : "No"}</td>
          <c:choose>
            <c:when test="${item.details.startsWith(\"http\")}">
              <td>
                <a href="${item.details}" target="_blank">
                  Link to Item <i class="bi bi-box-arrow-up-right"></i>
                </a>
              </td>
            </c:when>
            <c:otherwise>
              <td>${item.details}</td>
            </c:otherwise>
          </c:choose>
          <td>${priorities.get(item.priority)}</td>
          <td>${item.priceRange}</td>
          <td><a href="#" class="text-primary px-0"><i class="bi bi-pencil-square"></i></a></td>
          <td><a href="#" class="text-danger px-0"><i class="bi bi-trash"></i></a></td>
        </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <p>No items on this list yet!</p>
    </c:otherwise>
  </c:choose>


  <div class  ="row mt">
    <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
      <a class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2"
           href="#" role="button"> <%--     // TODO: 5/11/2022 this goes to new item page      --%>
          Add Item
      </a>
    </div>
  </div>
  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>

