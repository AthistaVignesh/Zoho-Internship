<%@include file="supplierHeader.jsp"%>
<%@include file="../footer.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<% String logStatus=(String)session.getAttribute("loginStatus");
if(logStatus==null)
response.sendRedirect("../login.jsp");%>
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
		All Products & Edit Products <i class='fab fa-elementor'></i>
	</div>
	<%String res=request.getParameter("msg");
	
	if(!(res==null) && res.equals("refresh")){%>
	<h3 class="alert">Product Successfully Updated!</h3>
	<%}
	if(res!=null && !res.equals("refresh")){%>
	<h3 class="alert">Some thing went wrong! Try again!</h3>
	<%} %>
	<table id="mainTable">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">Category</th>
				<th scope="col"><i class="fa fa-inr"></i> Price</th>
				<th>Status</th>
				<th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
			</tr>
		</thead>
		
		<tbody id="rowJson">
		<%String jsonString=(String)session.getAttribute("jdata");%>
		  <div class="marginTable"  data-count="5">
		</div>			
		</tbody>
		
	</table>
	<br>
	<br>
	<br>
	
<script>
const obj = JSON.parse('<%=jsonString%>');
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
			   cell[j-1].innerHTML = object[ind][vals];		  
			   if(j-1==0)
				   idToEdit = object[ind][vals];	
		   }		   
	   }
	   if(i!=0){
	   cell[j]=newRow[i].insertCell(j);
	   cell[j].innerHTML = "<a href=\"editProdAct?idForEdit="+idToEdit+"\">Edit <i class=\'fas fa-pen-fancy\'></i></a>";
	   }
	   j=0;
	}
</script>

</body>
</html>