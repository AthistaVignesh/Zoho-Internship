<!DOCTYPE html>
<html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>ForgotPassword</title>
</head>

<body>
	<div id='container'>
		<div class='forgetCss'>
			<% String res=request.getParameter("msg");
	 		   Boolean b1=Boolean.valueOf(res);    
  	 		   if(!b1){%>
			<s:form action="forgotPassAct1" method="post">
				<s:textfield class="cssStyle" name="forName"
					placeholder="Enter your name"></s:textfield>
				<s:textfield class="cssStyle" name="forPhone"
					placeholder="Enter your phone number"></s:textfield>
				<s:textfield class="cssStyle" name="forMail"
					placeholder="Enter your Email"></s:textfield>
				<s:submit value="Submit"></s:submit>
			</s:form>
			<h2>
				<a href="login.jsp">Login</a>
			</h2>
			<%}%>
			<%if(b1){%>
			<s:form action="forgotPassAct2" method="post">
				<s:textfield name="forOTP" placeholder="Enter your OTP"></s:textfield>
				<s:submit value="Submit"></s:submit>
			</s:form>
			<%}%>
		</div>
		<div class='whyforgotPassword'>
			<%if("incorrect".equals(res)){ %>
			<h1>OTP Incorrect! Enter credentials again!!</h1>
			<%}%>
			<h2>Online Shopping Web Application</h2>
			<img src="image/onlineicon.jpg"></img>
		</div>
	</div>
</body>

</html>