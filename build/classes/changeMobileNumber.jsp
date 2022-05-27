<%@include file="changeDetailsHeader.jsp"%>
<%@include file="footer.jsp"%>	
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Change Password</title>
</head>
<body>
<h3 class="alert" style="display:none;" id="alert2">Your Mobile Number successfully changed!</h3>

<h3 class="alert" style="display:none;" id="alert1">Your Password is wrong!</h3>
<div class="cssForForm">
<form action="changeMobileNumberAct" method="post" >
 <h3>Enter Your New Mobile Number<input type="text" name="newMobileNumber" id="field" class="input-style" placeholder="Your Number"></input></h3>
 
 <hr>
<h3>Enter Password (For Security)<input type="password" name="passToChangeNumber" class="input-style" placeholder="Your Password"></input></h3>
</div>
<hr>
 <button class="button"><i class='far fa-arrow-alt-circle-right'></i>Submit</button>
</form>
<%String result=request.getParameter("msg");
System.out.println(result);%>
<script>
var number=<%=session.getAttribute("number")%>
console.log(number);
if(number!=null){
	document.getElementById("field").value=number;
	
var res=<%=result%>;
console.log(typeof(res));
console.log("res is ::"+res);

if(res!=null && res==0)
	document.getElementById("alert1").style.display="block";
else if(res!=null && res==1)
	document.getElementById("alert2").style.display="block";

}
</script>
</body>
<br><br><br>
</html>