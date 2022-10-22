
<html>
<head>
<link rel="stylesheet" href="css/bill.css">
<title>Bill</title>
</head>
<body>

<h3>Online shopping Bill</h3>
<hr>
<div class="left-div"><h3>Name:<span class="detailsStyle" id="field6"></span>  </h3></div>
<div class="right-div-right"><h3>Email:<span class="detailsStyle" id="field10"></span></h3></div>
<div class="right-div"><h3>Mobile Number:<span class="detailsStyle" id="field5"></span></h3></div>  

<div class="left-div"><h3>Order Date:<span class="detailsStyle" id="field9"></span></h3></div>
<div class="right-div-right"><h3>Payment Method:<span class="detailsStyle" id="field3"></span></h3></div>
<div class="right-div"><h3>Expected Delivery:<span class="detailsStyle" id="field8"></span></h3></div> 

<div class="left-div"><h3>Transaction Id:<span class="detailsStyle" id="field11"></span></h3></div>
<div class="right-div-right"><h3>City:<span class="detailsStyle" id="field4"></span></h3></div> 
<div class="right-div"><h3>Address:<span class="detailsStyle" id="field2"></span></h3></div> 

<div class="left-div"><h3>State:<span class="detailsStyle" id="field7"></span></h3></div>
<div class="right-div-right"><h3>Country:<span class="detailsStyle" id="field1"></span></h3></div>  

<hr>


	
	<br>
	
<table id="customers">
<h3>Product Details</h3>
<thead>
  <tr>
    <th>S.No</th>
   
    <th>Product Name</th>
    <th>Category</th>
    <th>Price</th>
    <th>Quantity</th>
     <th>Sub Total</th>
  </tr>
</thead>
<tbody id="rowJson"></tbody>
 
 </tbody>
</table>
<h3>Total: <span id="cartTotal"></span></h3>
<a href="homeAct"><button id="hidePrint1" class="button left-button">Continue Shopping</button></a>
<a onclick="window.print();"><button id="hidePrint2" class="button right-button">Print</button></a>
<br><br><br><br>
<script>
var obj = <%= session.getAttribute("jdata") %>
console.log(obj);
var regTable = document.getElementById("rowJson");
let size = Object.keys(obj).length;
var cell = [];
var newRow = [];
let j = 0;
var sno = 1;
var cartTotal=0;
for (var i = 0; i <= size; ++i) {
	var object = obj[i];
  newRow[i] = regTable.insertRow(i);     
  for (var ind in object) { 	
    for (var vals in object[ind]) {  
    	if(vals.localeCompare("prod_id")==0)
  		  continue;
  	  cell[j] = newRow[i].insertCell(j);
  	  if(j==0){cell[j].innerHTML=sno; sno++;}
  	  //else if(j==1){cell[j].innerHTML=object[0]["prod_id"];}
  	  else if(j==1){cell[j].innerHTML=object[1]["name"];}
  	  else if(j==2){cell[j].innerHTML=object[2]["category"];}
  	  else if(j==3){cell[j].innerHTML=" <i class = \"fa fa-inr\"></i>" + object[3]["price"];}
  	  else if(j==4){cell[j].innerHTML = object[4]["quantity"];}
  	  else cell[j].innerHTML=object[j][vals];
  	  j++;
    } 
  }

  if(i!=0){
  cell[j]=newRow[i].insertCell(j);
  cell[j].innerHTML=object[5]["total"];
  cartTotal+=object[5]["total"];
  j=0;
  }
}
document.getElementById("cartTotal").innerHTML = cartTotal;
const colObj=<%=session.getAttribute("colDetails")%>;
console.log(colObj);
var i=1;
for( val in colObj){
	document.getElementById("field"+i).innerHTML=colObj[val];
	
	i++;
}
</script>
</body>
</html>