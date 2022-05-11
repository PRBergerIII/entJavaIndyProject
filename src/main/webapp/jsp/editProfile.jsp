<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.11
Individual Project: Flex Registry - Edit Profile Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="mt-2 mb-0">Edit Your Profile</h2>
  <form class="mt-0 needs-validation" action="${webApp}/edit-profile" method="POST" novalidate>

    <div class="form-group row mb-4">
      <p class="text-danger mb-0 ml-3">Required fields - *</p>
    </div>

    <div class="form-group row">
      <label for="firstName"
             class="col-md-2 col-form-label">First Name<span class="text-danger font-weight-bold">*</span></label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="firstName"
               name="firstName"
               value="${user.firstName}"
               placeholder="Your first name" required>
        <div class="invalid-feedback">
          Please provide your first name.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="lastName"
             class="col-md-2 col-form-label">Last Name<span class="text-danger font-weight-bold">*</span></label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="lastName"
               name="lastName"
               value="${user.lastName}"
               placeholder="Your last name" required>
        <div class="invalid-feedback">
          Please provide your last name.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="email"
             class="col-md-2 col-form-label">Email<span class="text-danger font-weight-bold">*</span></label>
      <div class="col-lg-4 col-md-6">
        <input type="email" class="form-control" id="email"
               name="email"
               value="${user.email}"
               placeholder="Your email" required>
        <div class="invalid-feedback">
          Please provide a valid email address.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="street"
             class="col-md-2 col-form-label">Street</label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="street"
               name="street"
               value="${user.street}"
               placeholder="Your Street Address">
        <div class="invalid-feedback">
          Please provide your street address.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="city"
             class="col-md-2 col-form-label">City</label>
      <div class="col-lg-4 col-md-6">
        <input type="text" class="form-control" id="city"
               name="city"
               value="${user.city}"
               placeholder="Your city">
        <div class="invalid-feedback">
          Please provide your city.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="state"
             class="col-md-2 col-form-label">State</label>
      <div class="col-lg-4 col-md-6">
        <select class="custom-select" id="state" name="state">
          <option value="" disabled selected>Your state</option>
          <option value="AL" ${user.state == 'AL' ? 'selected' : ''}>Alabama</option>
          <option value="AK" ${user.state == 'AK' ? 'selected' : ''}>Alaska</option>
          <option value="AZ" ${user.state == 'AZ' ? 'selected' : ''}>Arizona</option>
          <option value="AR" ${user.state == 'AR' ? 'selected' : ''}>Arkansas</option>
          <option value="CA" ${user.state == 'CA' ? 'selected' : ''}>California</option>
          <option value="CO" ${user.state == 'CO' ? 'selected' : ''}>Colorado</option>
          <option value="CT" ${user.state == 'CT' ? 'selected' : ''}>Connecticut</option>
          <option value="DE" ${user.state == 'DE' ? 'selected' : ''}>Delaware</option>
          <option value="DC" ${user.state == 'DC' ? 'selected' : ''}>District Of Columbia</option>
          <option value="FL" ${user.state == 'FL' ? 'selected' : ''}>Florida</option>
          <option value="GA" ${user.state == 'GA' ? 'selected' : ''}>Georgia</option>
          <option value="HI" ${user.state == 'HI' ? 'selected' : ''}>Hawaii</option>
          <option value="ID" ${user.state == 'ID' ? 'selected' : ''}>Idaho</option>
          <option value="IL" ${user.state == 'IL' ? 'selected' : ''}>Illinois</option>
          <option value="IN" ${user.state == 'IN' ? 'selected' : ''}>Indiana</option>
          <option value="IA" ${user.state == 'IA' ? 'selected' : ''}>Iowa</option>
          <option value="KS" ${user.state == 'KS' ? 'selected' : ''}>Kansas</option>
          <option value="KY" ${user.state == 'KY' ? 'selected' : ''}>Kentucky</option>
          <option value="LA" ${user.state == 'LA' ? 'selected' : ''}>Louisiana</option>
          <option value="ME" ${user.state == 'ME' ? 'selected' : ''}>Maine</option>
          <option value="MD" ${user.state == 'MD' ? 'selected' : ''}>Maryland</option>
          <option value="MA" ${user.state == 'MA' ? 'selected' : ''}>Massachusetts</option>
          <option value="MI" ${user.state == 'MI' ? 'selected' : ''}>Michigan</option>
          <option value="MN" ${user.state == 'MIMN' ? 'selected' : ''}>Minnesota</option>
          <option value="MS" ${user.state == 'MS' ? 'selected' : ''}>Mississippi</option>
          <option value="MO" ${user.state == 'MO' ? 'selected' : ''}>Missouri</option>
          <option value="MT" ${user.state == 'MT' ? 'selected' : ''}>Montana</option>
          <option value="NE" ${user.state == 'NE' ? 'selected' : ''}>Nebraska</option>
          <option value="NV" ${user.state == 'NV' ? 'selected' : ''}>Nevada</option>
          <option value="NH" ${user.state == 'NH' ? 'selected' : ''}>New Hampshire</option>
          <option value="NJ" ${user.state == 'NJ' ? 'selected' : ''}>New Jersey</option>
          <option value="NM" ${user.state == 'NM' ? 'selected' : ''}>New Mexico</option>
          <option value="NY" ${user.state == 'NY' ? 'selected' : ''}>New York</option>
          <option value="NC" ${user.state == 'NC' ? 'selected' : ''}>North Carolina</option>
          <option value="ND" ${user.state == 'ND' ? 'selected' : ''}>North Dakota</option>
          <option value="OH" ${user.state == 'OH' ? 'selected' : ''}>Ohio</option>
          <option value="OK" ${user.state == 'OK' ? 'selected' : ''}>Oklahoma</option>
          <option value="OR" ${user.state == 'OR' ? 'selected' : ''}>Oregon</option>
          <option value="PA" ${user.state == 'PA' ? 'selected' : ''}>Pennsylvania</option>
          <option value="RI" ${user.state == 'RI' ? 'selected' : ''}>Rhode Island</option>
          <option value="SC" ${user.state == 'SC' ? 'selected' : ''}>South Carolina</option>
          <option value="SD" ${user.state == 'SD' ? 'selected' : ''}>South Dakota</option>
          <option value="TN" ${user.state == 'TN' ? 'selected' : ''}>Tennessee</option>
          <option value="TX" ${user.state == 'TX' ? 'selected' : ''}>Texas</option>
          <option value="UT" ${user.state == 'UT' ? 'selected' : ''}>Utah</option>
          <option value="VT" ${user.state == 'VT' ? 'selected' : ''}>Vermont</option>
          <option value="VA" ${user.state == 'VA' ? 'selected' : ''}>Virginia</option>
          <option value="WA" ${user.state == 'WA' ? 'selected' : ''}>Washington</option>
          <option value="WV" ${user.state == 'WV' ? 'selected' : ''}>West Virginia</option>
          <option value="WI" ${user.state == 'WI' ? 'selected' : ''}>Wisconsin</option>
          <option value="WY" ${user.state == 'WY' ? 'selected' : ''}>Wyoming</option>
        </select>
      </div>
    </div>

    <div class="form-group row">
      <label for="zip"
             class="col-md-2 col-form-label">Zip Code</label>
      <div class="col-lg-4 col-md-6">
        <input type="zip" class="form-control" id="zip"
               name="zip"
               value="${user.zip}"
               placeholder="Your zip">
        <div class="invalid-feedback">
          Please provide your zip code: exactly 5 digits.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="addressVisibility" class="col-md-2 col-form-label">
        Address Visibility
        <small>
          <i class="bi bi-info-circle-fill ml-1 text-muted align-top"
             title="This decides who can see your full address"></i>
        </small>
      </label>
      <div class="col-lg-4 col-md-6">
        <select class="custom-select" id="addressVisibility"
                name="addressVisibility">
          <option value="private" ${user.addressVisibility == 'private' ? 'selected' : ''}>Private</option>
          <option value="public" ${user.addressVisibility == 'public' ? 'selected' : ''}>Public</option>
          <!-- TODO: add followers option once that system is set up -->
        </select>
        <div class="invalid-feedback">
          Please select a visibility.
        </div>
      </div>
    </div>

    <div class="form-group row">
      <label for="about"
             class="col-md-2 col-form-label">
        About
        <small>
          <i class="bi bi-info-circle-fill ml-1 text-muted align-top"
             title="What do your shoppers need to know?"></i>
        </small>
      </label>
      <div class="col-lg-4 col-md-6">
        <textarea name="about" id="about"
                  placeholder="Anything about yourself. Sizes, favorite colors, or just a bio, your choice!"
                  class="form-control" rows="3">${user.about}</textarea>
        <div class="invalid-feedback">
          Please provide a valid email address.
        </div>
      </div>
    </div>

    <div class="form-group row mt-4">
      <div class="d-sm-flex col-xl-6 col-lg-8 col-md justify-content-md-start justify-content-sm-around justify-content-center">
        <button class="btn btn-primary col-md-3 col-sm-5 mr-md-2 col-8 mb-2" type="submit">Submit</button>
        <a class="btn btn-secondary col-md-3 col-sm-5 ml-md-2 col-8 mb-2" href="${webApp}/profile?ownerId=${userId}" role="button">Cancel</a>
      </div>
    </div>
  </form>

  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
