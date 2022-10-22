<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/messageUs.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

<title>Message Us</title>
</head>

<body>
<div style="color: white; text-align: center; font-size: 30px;">Message Us <i class='fas fa-comment-alt'></i></div>

<h3 style="text-align:center; color:yellow; display:none;" id="field1" class="alert">Message successfully sent. Our team will contact you soon!</h3>

<h3 style="text-align:center; display:none;" id="field2" class="alert">Some thing Went Wrong! Try Again!</h3>
<%String jsondata=(String)session.getAttribute("jdata");%>
<form action="messageFeedbackAct" method="post">
<span id="selectText">Select on what you want give us feedback:</span><select name="aboutWhat" id="aboutWhat"><option value="0" selected>General</option></select><br>
<br<br>
<textarea id="content" name="content" class="input-style" rows="4" cols="50" placeholder="Enter your message here.."></textarea><br>	
<button type="submit" class="button"	>Submit</button>
</form>
<br><br><br>
<script>
var listitems = '';
const temp=<%=jsondata%>
console.log(temp);
$.each(temp, function(key, value){
    listitems += '<option value=' + value["Value"] + '>' + value["Text"] + '</option>';
});

$('#aboutWhat').append(listitems);
var z=<%=request.getParameter("msg")%>;
console.log("z="+z);
if(z!=null && z==1)
	document.getElementById("field1").style.display="block";
else if(z!=null && z==0)
	document.getElementById("field2").style.display="block";
</script>     
</body>
</html>