<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>ForgotPassword</title>
</head>
<%String mail=request.getParameter("resetEmail"); 
if(mail!=null)
	session.setAttribute("resetEmail",mail);%>
<body>
	<div id='container'>
		<div class='resetCss'>
			<s:form method="post" action="resetPasswordAct">
				<s:password name="newPassword" placeholder="Enter new password"></s:password>
				<s:password name="conNewPassword" placeholder="Enter password again"></s:password>
				<s:submit value="Submit"></s:submit>
			</s:form>
		</div>
		<div class='whyforgotPassword'>
			<h2>Reset Password</h2>
			<img src="image/onlineicon.jpg"></img>
		</div>
	</div>
</body>
</html>