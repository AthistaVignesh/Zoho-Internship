<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>Sign-up</title>
</head>
<body>
	<div id='container'>
		<div class='signup'>
			<s:form method="post" action="signupAct">
				<s:textfield name="signName" placeholder="Enter your name"
					label="Name"></s:textfield>
				<s:textfield name="signEmail" placeholder="Enter your email"
					label="Email ID"></s:textfield>
				<s:textfield name="signMobileNumber"
					placeholder="Enter your mobile number" label="Mobile Number"></s:textfield>
				<s:password name="signPassword" placeholder="Enter password"
					label="Password"></s:password>
				<s:password name="signConPassword" placeholder="Confirm password"
					label="Confirm Password"></s:password>
				<s:select label="Account Type" name="signtyp" headerKey="Consumer"
					headerValue="Select Type"
					list="#{'Consumer':'Consumer', 'Supplier':'Supplier'}"
					value="selectedTyp" />

				<s:submit value="submit"></s:submit>
			</s:form>
			<h2>
				<a href="login.jsp">Login</a>
			</h2>
		</div>
		<div class='whysign'>
			<%
	String msg=request.getParameter("msg");
    if("".equals(msg) || msg==null){%>
			<font color="red">*Password must contain atleast 1 special
				character,uppercase,lowercase and number</font>
			<%} %>
			<% 
	if("valid".equals(msg)){
	%>
			<h1>Successfully Updated</h1>
			<%}%>

			<%
	if("invalid".equals(msg)){
	%>
			<h1>Account already exists.</h1>
			<%}%>
			<%
	if("incorrect".equals(msg)){
	%>
			<h1>Your passwords don't match!</h1>
			<%}%>
			<h2>Registration Page</h2>
			<img src="image/onlineicon.jpg"></img>
		</div>
	</div>

</body>
</html>