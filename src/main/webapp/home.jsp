<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>

<html>
<head>
<% String email=session.getAttribute("email").toString();

String status = (String) session.getAttribute("loginStatus");
if(status==null || status.equals("") || status.equals("false"))
	response.sendRedirect("login.jsp");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
h3 {
	text-align: center;
    color: yellow;
    font-size: 21px;
    font-weight: 900;
    -webkit-text-stroke-width: 1px;
    -webkit-text-stroke-color: orangered;
}
</style>
</head>
<body>

	<div style="color: black; text-align: center; font-size: 30px;">
		Home <i style="color:white;" class="fa fa-institution"></i>
	</div>
	<%String msg=request.getParameter("msg");
	 if(msg!=null && msg.equals("added")){%>
	<h3 class="alert">Product added successfully!</h3>
	<%}
	 if(msg!=null && msg.equals("exist")){%>
	<h3 class="alert">Product Quantity increased!</h3>
	<%}
	 if(msg!=null && msg.equals("failed")){%>
	<h3 class="alert">Password change successfully!</h3>
	<%} %>
	<table id="mainTable">
		<thead>
			<tr>
				<th scope="col">S.No</th>
				<th scope="col">Name</th>
				<th scope="col">Category</th>
				<th scope="col"><i class="fa fa-inr"></i> Price</th>
				<th scope="col">Add to cart <i class='fas fa-cart-plus'></i></th>
			</tr>
		</thead>
		<%String jsonString=(String)session.getAttribute("jdata");%>
		  <div class="marginTable"  data-count="5">
		<tbody id="rowJson">
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
console.log(obj);
for(var i = 0; i <= size; ++i){	  
	   var object = obj[i];
	  
	   newRow[i] = regTable.insertRow(i);
	   for(var ind in object){
		  
		   for(var vals in object[ind]){
			   if(vals.localeCompare("active")==0){
				   break;
			   }
			   else if(vals.localeCompare("prod_id")==0)
				   continue;	
			   j++;	
			   cell[j-1]=newRow[i].insertCell(j-1);
			   cell[j-1].innerHTML = object[ind][vals];		  
			   if(j-1==0)
				   idToEdit = object[ind][vals];	
		   }		   
	   }
	   if(i!=0){	
	   cell[j]=newRow[i].insertCell(j);
	   if(object[5]['active'].localeCompare("Yes")==0)
	   cell[j].innerHTML = "<a href=\"addToCartAct?idCart="+object[1]['prod_id']+"\">Add to cart <i class=\'fas fa-cart-plus\'></i></a>";
	   else
		   cell[j].innerHTML = "<b>Out Of Stock</b>";
	   }
	   j=0;
	}

</script>
</body>
</html>