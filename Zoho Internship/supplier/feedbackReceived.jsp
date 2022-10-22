<%@include file="supplierHeader.jsp"%>
<%@include file="../footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
h3 {
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
	<div class="titleCss">
		Messages Received <i class='fas fa-comment-alt'></i>
	</div>
	<table id="mainTable">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">CustomerId</th>
				<th scope="col">Subject</th>
				<th scope="col">Body</th>
			</tr>
		</thead>
		<tbody id="rowJson">
		</tbody>
	</table>
	<br>
	<br>
	<br>
<%String jsonString = (String)session.getAttribute("jdata"); %>
<script>

const obj = <%=jsonString%>;

var regTable = document.getElementById("rowJson");
let size=Object.keys(obj).length;
var cell=[];
var newRow=[];
let j=-1;
var idToEdit=0;

for(var i = 0; i <= size; ++i){	  
	   var object = obj[i];
	  
	   newRow[i] = regTable.insertRow(i);
	   for(var ind in object){
		  
		   for(var vals in object[ind]){
			   
			   j++;	
			   cell[j-1]=newRow[i].insertCell(j-1);
			   
			   if(object[ind][vals]==0)
				   cell[j-1].innerHTML = "General";
			   else
			   	   cell[j-1].innerHTML = object[ind][vals];		  
			   
		   }		   
	   }
	  
	   j=0;
	}
</script>

</body>
</html>