<%@include file="supplierHeader.jsp"%>
<%@include file="../footer.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<html>
<head>
<% String logStatus=(String)session.getAttribute("loginStatus");
if(logStatus==null)
response.sendRedirect("../login.jsp");%>
<link rel="stylesheet" href="../css/addNewProduct-style.css">
<title>Add New Product</title>
<style>
.back {
	color: white;
	margin-left: 2.5%
}
</style>
</head>
<body>
	<h2>
		<a class="back" href="allProductEditProduct.jsp"><i
			class='fas fa-arrow-circle-left'> Back</i></a>
	</h2>
	
	<%String colDetails=(String)session.getAttribute("colDetails"); %>
	<s:form action="editProdChangeAct" method='post'>
	<div class="left-div">
		<h3>Enter Name<input class="input-style" type='text' id='field2' name="editName"/></h3>

		<hr>
	</div>

	<div class="right-div">
		<h3>Enter Category<input class="input-style" type='text' id='field4' name="editCategory"/></h3>

		<hr>
	</div>

	<div class="left-div">
		<h3>Enter Price<input class="input-style" type='text' id='field1' name="editPrice"/></h3>

		<hr>
	</div>

	<div class="right-div">
		<h3>Active<select class="input-style" id='field3' name="editActive">
		<option value="Yes">Yes</option>
		<option value="No">No</option>
		</select>
		</h3>

		<hr>
	</div>
	
	<button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>
	</s:form>
	<form action="deleteProductAct">
	<button class="button">Delete<i class='far fa-arrow-alt-circle-right'></i></button>
	</form>
<script>
const obj=<%=colDetails%>;
console.log(obj)
var i=1;
for( val in obj){
	document.getElementById("field"+i).value=obj[val];
	i++;
}
</script>

</body>
<br>
<br>
<br>
</html>