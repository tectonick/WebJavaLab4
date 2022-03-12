<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ page import="LR4.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.basic"/>

<fmt:setLocale value="<%=session.getAttribute(\"lang\") %>" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<title>BSUIR Rooms</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/bootstrap.js"></script>
</head>
<body>

<%
User currentUser=(User)session.getAttribute("user");
%>


<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card">
    <a href="/LR4/index.jsp" class="w3-bar-item w3-button"><b>BSUIR Rooms</b>  <fmt:message key="label.main" /></a>
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a href="rooms.jsp" class="w3-bar-item w3-button"> <fmt:message key="label.rooms" /></a>
      	<%if (currentUser==null) {%>
		    <a href="login.jsp" class="w3-bar-item w3-button"> <fmt:message key="label.login" /></a>
      		<a href="register.jsp" class="w3-bar-item w3-button"> <fmt:message key="label.register" /></a>
		<%} else{%>
			<a href="mybooks.jsp" class="w3-bar-item w3-button"> <fmt:message key="label.mybooks" /></a>
      		<a href="logout" class="w3-bar-item w3-button"> <fmt:message key="label.logout" /></a>
      	<%} %>
      
    </div>
      <div class="locale">
		<a class='locale-change' href="?sessionLocale=ru" id='ru'>Рус</a>
        <a class='locale-change' href="?sessionLocale=en" id='en'>En</a>       
  	   </div>
  </div>  
</div>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px; margin-top:80px">
 
 
 
 <div class="half-half-image-text">
<div class="container">
  <div class="row">
    <div class="col-12">
      <h1><fmt:message key="label.welcome" /></h1>
    </div>
  </div>
  <div class="row">
    <div class="col-12 col-lg-6">
      <div class="content">
        <p><fmt:message key="label.greeting" /></p>
        
      </div>
    </div>
    <div class="col-12 col-lg-6">
      <div class="img" style="background: url('img/index.jpg')no-repeat center;background-size:cover;"></div>
    </div>
  </div>
</div>
  </div>
 
 
 
 
<!-- End page content -->
</div>


<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p></p>
</footer>

</body>
</html>
