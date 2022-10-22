<%@page errorPage="error.jsp" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/home-style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
    <!--Header-->
    <br>
    <div class="topnav sticky">
    		<% String logemail=session.getAttribute("email").toString();%>
            <center><h2><font color="white">Online shopping</font></h2></center>
            <h2><a href="welcome.jsp"><%=logemail%><i class='fas fa-user-alt'></i></a></h2>
            <a href="homeAct" id="homelink">Home<i class="fa fa-institution"></i></a>
            <a href="myCartAct">My Cart<i class='fas fa-cart-arrow-down'></i></a>
            <a href="myOrderAct">My Orders  <i class='fab fa-elementor'></i></a>
            <a href="changeDetailsAct">Change Details <i class="fa fa-edit"></i></a>
            <a href="messageUsAct">Message Us <i class='fas fa-comment-alt'></i></a>
            
            <a href="logOutAct">Logout <i class='fas fa-share-square'></i></a>
            <div class="search-container">
            	
             	<form action="searchAct" method='post'>
             	
				<input name="searchQuery" type="text" placeholder="Search for product"></input>
				<button><i class="fa fa-search"></i></button>
				</form>
              	
           	</div>
    </div>
           <br>
           <!--table-->
<script>
$(document).ready(function(){
    $("#homelink").trigger('click'); 
});
</script>