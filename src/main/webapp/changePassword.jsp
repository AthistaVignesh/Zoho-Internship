<%@include file="changeDetailsHeader.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Message Us</title>
</head>
<body>

<h3 class="alert" id="status1" style="display:none;">Password Updated Successfully</h3>

<h3 class="alert" id="status2" style="display:none;">Your old Password is wrong!</h3>

<h3 class="alert" id="status3" style="display:none;">Check the rule for password</h3>

<h3 class="alert" id="status4" style="display:none;">New password and Confirm password does not match!</h3>

<div class="cssForForm">
<form action="changePasswordAct" method='post'>
<h3>Enter Old Password<input class="input-style" type="password" name="oldPassword" id="field1" placeholder="old password..." required></input></h3>
 
  <hr>
 <h3>Enter New Password<input class="input-style" type="password" name="newPassword" id="field2" placeholder="new password..." required></input></h3>
 
 <hr>
<h3>Enter Confirm Password<input class="input-style" type="password" name="newConfirmPassword" id="field3" placeholder="confirm password..." required></input></h3>

<hr>
</div>
 <button class="button"><i class='far fa-arrow-alt-circle-right'></i>Submit</button>
</form>


</body>
<script type="text/javascript">



var result=<%=request.getParameter("returnValue")%>
console.log("return value:"+result);
if(result!=null)
	document.getElementById("status"+result).style.display="block";

</script>
<br><br><br><br>
</html>