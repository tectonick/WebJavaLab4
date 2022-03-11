<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ page import="LR4.*" %>
<%@ page import="java.util.List" %>


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
if (currentUser!=null) {out.println(currentUser.getLogin());}
%>


<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card">
    <a href="/LR4/index.jsp" class="w3-bar-item w3-button"><b>BSUIR Rooms</b> Главная</a>
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a href="rooms.jsp" class="w3-bar-item w3-button">Комнаты</a>
      	<%if (currentUser==null) {%>
		    <a href="login.jsp" class="w3-bar-item w3-button">Войти</a>
      		<a href="register.jsp" class="w3-bar-item w3-button">Зарегистрироваться</a>
		<%} else{%>
      		<a href="logout" class="w3-bar-item w3-button">Выйти</a>
      	<%} %>
      
    </div>
  </div>
</div>

<!-- Header -->
<header class="w3-display-container w3-content w3-wide" style="max-width:1500px;" id="home">
  <img class="w3-image" src="/w3images/architect.jpg" alt="Architecture" width="1500" height="800">
</header>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px; margin-top:10px">
 
<% 

%>

<div class="card-columns">
<%
DB db=new DB();
db.init("root","12345678");
RoomsDAO rdao=new RoomsDAO(db);
BookingDAO bdao=new BookingDAO(db);


List<Room> rooms=rdao.getAllRooms();


%>

   <% for(Room room:rooms) { %>
  <div class="card">
    <img class="card-img-top" src="img/<%=room.getId() %>.jpg" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">Номер <%=room.getId() %></h5>
      <pre class="card-text">
      Кровати: <%=room.getBeds() %>
      Wifi: <%=room.isWifi()?"Есть":"Нет" %>
      Холодильник: <%=room.isFreezer()?"Есть":"Нет" %>
      Чайник: <%=room.isTeapot()?"Есть":"Нет" %>
      Телевизор: <%=room.isTv()?"Есть":"Нет" %>
      Курение: <%=room.isFreezer()?"Разрешено":"Запрещено" %>
      </pre> 
        
      <p class="card-text">
      	<!-- <small class="text-muted">Last updated 3 mins ago</small> -->
      </p>
    </div>
            <div class="card-footer text-muted">
        <%if (currentUser!=null) {%>
        
        <%
        List<Booking> booksForRoom=bdao.getBookingsForRoom(room.getId());
        %>
                
        <%if (booksForRoom.size()!=0) {%>
        <p>Комната недоступна в следующие даты</p>
        <pre><% for(Booking book:booksForRoom) { %>С <%=book.getStartDate()%> по <%=book.getEndDate()%><br><%} %></pre>
        <%}%>
        
        
		    <form class="book-form" method="post" action="book">
		        <input type="date" name="startdate" value="2022-06-01">
		    	<input type="date" name="enddate" value="2022-06-01">
		    	<input type="submit" value="Забронировать"></input>
		    	<input type="hidden" name="roomid" value="<%=room.getId() %>">

		    </form>
		<%}%>
        </div>
  </div>
    <% } %>


</div>


<!-- End page content -->
</div>


<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p></p>
</footer>

</body>
</html>
