<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<% String email=session.getAttribute("email").toString();

String status = (String) session.getAttribute("loginStatus");
if(status==null || status.equals("") || status.equals("false"))
	response.sendRedirect("login.jsp");%>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

<!-- <p style="background-image: url('image/headerimage.jpg');"> <img src="image/headerimage.jpg"></img>	 -->

<h1><font color="black">Welcome</font></h1>
</p>
</body>
</html>