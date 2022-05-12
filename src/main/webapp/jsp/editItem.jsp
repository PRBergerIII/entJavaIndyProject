<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.11
Individual Project: Flex Registry - Add / Edit Item Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="mt-2 mb-0">Add an Item</h2>
  <form class="mt-0 needs-validation" action="${webApp}/edit-item" method="POST" novalidate>

    <div class="form-group row mb-4">
      <p class="text-danger mb-0 ml-3">Required fields - *</p>
    </div>

    <div class="form-group row">
      <label for="name"
             class="col-md-2 col-form-label">Item Name<span class="text-danger font-weight-bold">*</span></label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="name"
               name="name"
               value="${listItem.name}"
               placeholder="A name for the item" required>
        <div class="invalid-feedback">
          Please provide a name for the item.
        </div>
      </div>
    </div>


    <div class="form-group row">
      <label for="specificItem" class="col-md-2 col-form-label">
        Specific Item?<span class="text-danger font-weight-bold">*</span>
        <small>
          <i class="bi bi-info-circle-fill ml-1 text-muted align-top"
             title="Select no unless you have one particular item in mind"></i>
        </small>
      </label>
      <div class="col-lg-4 col-md-6">
        <select class="custom-select" id="specificItem"
                name="specificItem">
          <option value="false" ${!listItem.specificItem ? 'selected' : ''}>No</option>
          <option value="true" ${listItem.specificItem ? 'selected' : ''}>Yes</option>
        </select>
        <div class="invalid-feedback">
          Please select whether or not this is for a specific item.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="details"
             class="col-md-2 col-form-label">
        Details or Link
        <small>
          <i class="bi bi-info-circle-fill ml-1 text-muted align-top"
             title="Tip: include ONLY a link if this is a specific item."></i>
        </small>
      </label>
      <div class="col-lg-4 col-md-6">
          <textarea name="details" id="details"
                    placeholder="A link if it's specific, some ideas if it's not!"
                    class="form-control" rows="3">${listItem.details}</textarea>
      </div>
    </div>


    <div class="form-group row">
      <label for="priority" class="col-md-2 col-form-label">
        Priority
        <small>
          <i class="bi bi-info-circle-fill ml-1 text-muted align-top"
             title="How much do you want this relative to other items on the list?"></i>
        </small>
      </label>
      <div class="col-lg-4 col-md-6">
        <select class="custom-select" id="priority"
                name="priority">
          <option value="1" ${listItem.priority == 1 ? 'selected' : ''}>Lowest</option>
          <option value="2" ${listItem.priority == 2 ? 'selected' : ''}>Low</option>
          <option value="3" ${listItem.priority == 3 ? 'selected' : ''}>Medium</option>
          <option value="4" ${listItem.priority == 4 ? 'selected' : ''}>High</option>
          <option value="5" ${listItem.priority == 5 ? 'selected' : ''}>Highest</option>
        </select>
      </div>
    </div>

    <div class="form-group row">
      <label for="priceRange"
             class="col-md-2 col-form-label">Price Range</label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="priceRange"
               name="priceRange"
               value="${listItem.priceRange}"
               placeholder="A range or exact">
      </div>
    </div>

    <div class="form-group row mt-4">
      <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
        <button class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2" type="submit">Submit</button>
        <a class="btn btn-secondary col-md-3 col-sm-5 ml-md-2 col-8 mb-2" href="${webApp}/edit-list?listId=${listId}" role="button">Back to List</a>
      </div>
    </div>
    <input type="hidden" name="itemId" value="${listItem.id}">
  </form>
  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>

