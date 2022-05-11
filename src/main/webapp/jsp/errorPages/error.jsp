<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Author: Paul Berger
Date: 2022.01.30
Individual Project: Flex Registry - Home Page
-->

<html lang="en" dir="ltr">
<c:import url="head"/>
<body>
<div class="container">
  <c:import url="header"/>
  <c:import url="/nav"/>

  <h2 class="text-center">Whoops!</h2>
  <img class="img-fluid mx-auto d-block mw-75" src="https://http.cat/${statusCode}.jpg">

  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
