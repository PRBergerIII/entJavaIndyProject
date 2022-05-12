<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.11
Individual Project: Flex Registry - New List Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="mt-2 mb-0">Add a New Wish List</h2>
  <form class="mt-0 needs-validation" action="${webApp}/new-list" method="POST" novalidate>

    <div class="form-group row mb-4">
      <p class="text-danger mb-0 ml-3">Required fields - *</p>
    </div>

    <div class="form-group row">
      <label for="title"
             class="col-md-2 col-form-label">List Title<span class="text-danger font-weight-bold">*</span></label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="title"
               name="title"
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
          <option value="public" selected>Public</option>
          <option value="private">Private</option>
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
               name="eventDate">
      </div>
    </div>

    <div class="form-group row mt-4">
      <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
        <button class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2" type="submit">Submit</button>
        <a class="btn btn-secondary col-md-3 col-sm-5 ml-md-2 col-8 mb-2" href="${webApp}/user-lists?ownerId=${owner.id}" role="button">Back to Lists</a>
      </div>
    </div>
  </form>
  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
