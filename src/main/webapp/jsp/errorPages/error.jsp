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
  <div class="row">
    <header class="jumbotron jumbotron-fluid mb-0 w-100 text-dark text-center py-md-3 py-1">
      <h1 class="display-3 text-dark">Whoops!</h1>
    </header>
  </div>
  <c:import url="/nav"/>

  <img class="img-fluid mx-auto d-block mw-75" src="https://http.cat/${statusCode}.jpg">

  <c:import url="footer"/>
</div>
<c:import url="bootstrap"/>
</body>
</html>
