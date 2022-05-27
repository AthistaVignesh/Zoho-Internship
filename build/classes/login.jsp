<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>Login</title>
</head>

<body>
	<div id='container'>
		<div class='loginCss'>
			<s:form method="post" action="loginAct">
				<s:textfield name="logEmail" placeholder="Enter your email"></s:textfield>
				<s:password name="logPassword" placeholder="Enter password"></s:password>
				<s:submit value="Submit"></s:submit>
			</s:form>
			<h2>
				<a href="signup.jsp">SignUp</a>
			</h2>
			<h2>
				<a href="forgotPassword.jsp">Forgot Password</a>
			</h2>
		</div>
		<div class='whysignLogin'>
			<% String msg=request.getParameter("msg");
      		if("invalid".equals(msg)){%>
			<h1>Incorrect Username or Password</h1>
			<%}%>
			
			<%if("changed".equals(msg)){%>
			<h1>Password Changed</h1>
			<%}%>

			<%if("notfound".equals(msg)){%>
			<h1>Create a account!</h1>
			<%}%>

			<%if("error".equals(msg)){%>
			<h1>Something Went Wrong! Try Again!</h1>
			<%}%>

			<h2>Online Shopping Web Application</h2>
			<img src="image/onlineicon.jpg"></img>
		</div>
	</div>
</body>

</html>