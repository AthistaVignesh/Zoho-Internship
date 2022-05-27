<%@include file="supplierHeader.jsp"%>
<%@include file="../footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/ordersReceived-style.css">
<title>Cancelled Order</title>
<style>
.th-style {
	width: 25%;
}
</style>
</head>
<body>
	<div class="titleCss">
		Cancel Orders <i class='fas fa-window-close'></i>
	</div>
	
	  <div class="search-container">
            	
             	<form action="filterByCategoryAct?showStatus=Cancel" method='post'>
             	<button><i class="fa fa-search"></i></button>
             	<select name="filterCategory" onchange="categoryStructure(this);" required>
             	<option selected value="all">All</option>
             	<option value="product_name">Product Name</option>
             	<option value="sub_total">Total</option>
             	<option value="order_date">Date</option>
             	<option value="paymentMode">Payment Method</option>
             	<option value="quantity">Quantity</option>
             	</select>
             	 </div>
				<input name="searchQuery" id="searchField1" type="" placeholder="Press enter to show all record"></input>
				<input name="searchQueryEndDate" id="searchField2" type="date" placeholder="Enter the End Date" style="display:none;"></input>
				
				</form>
              	
    
	

	<table id="customers">
	<thead>
		<tr>
			<th>Mobile Number</th>
			<th scope="col">Product Name</th>
			<th scope="col">Quantity</th>
			<th scope="col"><i class="fa fa-inr"></i> Sub Total</th>
			<th>Address</th>
			<th>City</th>
			<th>State</th>
			<th>Country</th>
			<th scope="col">Order Date</th>
			<th scope="col">Expected Delivery Date</th>
			<th scope="col">Payment Method</th>
			<th scope="col">T-ID</th>
			<th scope="col">Status</th>
		</tr>
	</thead>
	<tbody ID="rowJson">
	</tbody>
	</table>
	<span id="totalRate"></span>
	<br><br>
	<br><br>
	<br><br>
<%String jsonString = (String)session.getAttribute("jdata"); %>
<script>

const obj = <%=jsonString%>;
var totalPrice=0;
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
			   if(j<13){
			   j++;	
			   cell[j-1]=newRow[i].insertCell(j-1);
			   
			
			   if(vals.localeCompare("prod_id")==0)
				   j--;
			   else if(vals.localeCompare("sub_total") == 0){
				   totalPrice+=object[ind][vals];
				   cell[j-1].innerHTML = "<i class=\"fa fa-inr\"></i>"+object[ind][vals];
			   }
			   else{
				   var x = object[11]['transactionId'];

				  	if(object[ind][vals].toString().localeCompare(" ")==0 || object[ind][vals]==null)
				  		cell[j-1].innerHTML = "-";
				  	else	
			   			cell[j-1].innerHTML = object[ind][vals];		  
			   }
			   }
		   }  
	   }
	   j=0;

	}
</script>
<script>
function categoryStructure(that){
	var x=that.value;
	var y=document.getElementById("searchField1");
	var z=document.getElementById("searchField2");
	z.style.display="none";
	console.log(x.localeCompare("product_name")==0)
	console.log(y);
	if(x.localeCompare("product_name")==0){
		y.type="text";
		y.placeholder="Enter the name of the product";
	}
	else if(x.localeCompare("sub_total")==0){
		y.type="number";
		y.placeholder="Enter the max limit";
	}
	else if(x.localeCompare("order_date")==0){
		y.type="date";
		y.placeholder="Enter Start Date";
		z.style.display="block";
	}
	else if(x.localeCompare("paymentMode")==0){
		y.type="text";
		y.placeholder="Enter the mode of payment";
	}
	else if(x.localeCompare("quantity")==0){
		y.type="number";
		y.placeholder="Enter the amount of quantity";
	}
	else{
		y.type="text";
		y.placeholder="Press enter to show all record";
	}
	
}
document.getElementById("totalRate").innerHTML ="The total price of products cancelled based on the given criteria is  <i class=\"fa fa-inr\"></i> "+totalPrice;
</script>
</body>
</html>