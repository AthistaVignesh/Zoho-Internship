<%@include file="changeDetailsHeader.jsp"%>
<%@include file="footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<title>Change Details</title>
<style>
hr
{width:70%;}</style>
<%String colDetails=(String)session.getAttribute("jdata"); %>
</head>
<body>
<form action="" method='post'>
<div class="cssForForm">
<h3>Name:<input class="input-style" type="text" id="field1" name="changeName" readonly="readonly" style="background:#EBECF0;color:black;"></input></h3>
<hr>
<h3>Email:<input class="input-style" type="text" id="field2" name="changeEmail" readonly="readonly" style="background:#EBECF0;color:black;"></input> </h3>
<hr>
<h3>Mobile Number:<input class="input-style" type="text" id="field3" name="changeMobile" readonly="readonly" style="background:#EBECF0;color:black;"></input></h3>
<hr>
</div>

</form>
<br><br><br>
<script>
const obj=<%=colDetails%>;
console.log(obj);
var i=1;
var object = obj[i];   
for (var ind in object) { 	
  for (var vals in object[ind]){
	document.getElementById("field"+i).value=object[ind][vals];
	
	i++;
}
}
</script>
</body>
</html>