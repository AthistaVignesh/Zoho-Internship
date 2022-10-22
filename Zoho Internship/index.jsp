<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import = "project.ConnectionProvider" %><!---->
<%@ page import = "java.sql.*" %><!---->
<%@ page import = "java.io.*" %><!---->
<%@ page import = "java.net.*" %><!---->
<%@ page import = "com.ibatis.common.jdbc.ScriptRunner" %><!---->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>hello</h1>
	<% 
	try{
		System.out.println("Starting to Populate the schema");
		URL r = this.getClass().getResource("/");
		String path = URLDecoder.decode(r.getFile(), "UTF-8");
		String aSQLScriptFilePath = path + "../../table/schema.sql";
		Connection con=ConnectionProvider.getcon();
		Statement stmt = null;
		ScriptRunner sr = new ScriptRunner(con, false, false);
		// Give the input file to Reader
		Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
		// Exctute script
		sr.runScript(reader);
		System.out.println("Done Populating the schema");
	
	}
	catch(Exception e){
		System.out.println("Failed to Execute The error is " + e.getMessage());
	}
	response.sendRedirect("signup.jsp"); %>
</body>
</html>