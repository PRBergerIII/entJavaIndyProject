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

  <div class="card">
    <div class="card-body">
      <p class="text-capitalize">${ownerLabel} Profile Page</p>
    </div>
  </div>
</div>
<c:import url="bootstrap"/>
<c:import url="footer"/>
</body>
</html>
