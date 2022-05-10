<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.05.09
Individual Project: Flex Registry - Log Out Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<c:import url="header"/>
<c:import url="/nav"/>
<div class="container">

  <div class="card">
    <div class="card-body">
      Are you sure you want to log out?
      <button><a href="${webApp}/log-out-servlet">Yes</a></button>
      <button><a href="${webApp}/user-lists">No</a></button>
    </div>
  </div>
</div>
<c:import url="bootstrap"/>
<c:import url="footer"/>
</body>
</html>
