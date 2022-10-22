<%@include file="changeDetailsHeader.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Message Us</title>
</head>
<body>

<h3 class="alert" style="display:none" id="alertSuccess">Address Successfully Updated !</h3>

<h3 class="alert" style="display:none" id="alertFailed">Some thing Went Wrong! Try Again!</h3>

<div class="cssForForm">
<form action="changeAddressAct" method="post">
<h3>Enter Address<input type="text" class="input-style" name="changeAddress" id="field2" placeholder="Enter your address here"></input></h3>
 
 <hr>
 <h3>Enter city<input type="text" class="input-style" name="changeCity" id="field3" placeholder="Enter your city here"></input></h3>
 
<hr>
<h3>Enter State<input type="text" class="input-style" name="changeState" id="field4" placeholder="Enter your state here"></input></h3>

<hr>
<h3>Enter country<input type="text" class="input-style" name="changeCountry" id="field1" placeholder="Enter your country here"></input></h3>
</div>
<hr>
<button class="button"> <i class='far fa-arrow-alt-circle-right'></i>Save</button>
</form>
<script>
const colObj=<%=session.getAttribute("colDetails")%>;

var i=1;
for( val in colObj){
	document.getElementById("field"+i).value=colObj[val];
	
	i++;
}

var z=<%=request.getParameter("msg")%>;
	
if(z!=null && z==1)
	document.getElementById("alertSuccess").style.display="block";
else if(z!=null && z==0)
	document.getElementById("alertFailed").style.display="block";
</script>
</body>
<br><br><br>
</html>