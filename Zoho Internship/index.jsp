<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import = "project.ConnectionProvider" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DB Populate</title>
</head>
<body>
	<% 
	ConnectionProvider.populateDB();
	response.sendRedirect("signup.jsp"); %>
</body>
</html>