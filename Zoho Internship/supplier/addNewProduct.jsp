<%@include file="supplierHeader.jsp"%>
<%@include file="../footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="../css/addNewProduct-style.css">
<title>Add New Product</title>
</head>
<body>
	<%String id=(String)session.getAttribute("prodID");
String prodmsg=(String)request.getParameter("prodmsg");
%>

	<s:form action="addNewProdAct" method="post">
		<%if(prodmsg!=null && prodmsg.equals("valid")){ %>
		<h3 class="alert">Product Added Successfully!</h3>
		<%}if(prodmsg!=null && prodmsg.equals("invalid")){%>
		<h3 class="alert">Some thing went wrong! Try Again!</h3>
		<%} %>

		<h3 style="color: yellow;">
			Product ID:<%out.println(id); %>
		</h3>
		<input type="hidden" name="id" value="<%out.println(id); %>">
		<div class="left-div">
			<h3>Enter Name</h3>
			<input class="input-style" type="text" name="prodName"
				placeholder="Enter the name of the product" required>
			<hr>
		</div>

		<div class="right-div">
			<h3>Enter Category</h3>

			<select class="input-style" name="prodCategory" required>
				<option value="All Department" selected>All Department</option>
				<option value="Arts & Crafts">Arts & Crafts</option>
				<option value="Beauty & Personal Care">Beauty & Personal Care</option>
				<option value="Books">Books</option>
				<option value="Computers">Computers</option>
				<option value="Electronics">Electronics</option>
				<option value="Grocery">Grocery</option>
				<option value="Home & Kitchen">Home & Kitchen</option>
				<option value="Sports">Sports</option>
				<option value="Toys & Games">Toys & Games</option>
			</select>
			<hr>
		</div>

		<div class="left-div">
			<h3>Enter Price</h3>
			<input class="input-style" type="text" name="prodPrice"
				placeholder="Enter the price of the product" required>
			<hr>
		</div>

		<div class="right-div">
			<h3>Active</h3>
			<select class="input-style" name="prodActive">
				<option value="Yes">Yes</option>
				<option value="No">No</option>
			</select>
			<hr>
		</div>
		<button class="button" value="Submit">
			<i class='far fa-arrow-alt-circle-right'></i>
		</button>
	</s:form>
</body>
<br>
<br>
<br>
</body>
</html>