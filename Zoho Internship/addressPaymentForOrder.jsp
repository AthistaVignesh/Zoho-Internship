
<%@include file="footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/home-style.css">
<link rel="stylesheet" href="css/addressPaymentForOrder-style.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Home</title>
<script>if(window.history.forward(1)!=null)
		window.history.forward(1);</script>
<style>thead#tHeadCss {
    size: 10px;
    font-size: 20px;
}
input#field5::placeholder {
    color: red;
    font-weight: bold;
}
</style>
</head>
<body>
<br>
<table id="mainTable">

<thead id="tHeadCss">

          <tr>
          <th scope="col" ><a href="myCart.jsp" id="backToCart"><i class='fas fa-arrow-circle-left'> Back</i></a></th>
           
          </tr>
        </thead>
        <thead>
          <tr>
          <th scope="col">S.No</th>
          
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Sub Total</th>
          </tr>
        </thead>
        <tbody id="rowJson">
        </tbody>
      </table>
      <table id="priceTable">
      	<tbody id="priceTableBody">
       <tr>
        <td><td><td><td><td><td><td>
        <th scope="col" class="totalBox">Total:<i class="fa fa-inr"></i> <span id="cartTotal"></span> </th>       
        </tr>
  		</tbody>
      </table>
      
<hr style="width: 100%">
<button class="addressTab" onclick="window.location.href='myCartAct?returnValue=2'">Address 1</button>
<button class="addressTab" onclick="window.location.href='myCartAct?returnValue=1'">Default Address</button>

<%String change=request.getParameter("upd"); %>
	
<form action="addressChangeAct?change=<%=change%>" method="post">
 <div class="left-div">
 <h3>Enter Address</h3>
<input type="text" name="paymentAddress" class="input-style" id="field2"></input>
 </div>

<div class="right-div">
<h3>Enter city</h3>
<input type="text" name="paymentCity" class="input-style" id="field3"></input>
</div> 

<div class="left-div">
<h3>Enter State</h3>
<input type="text" name="paymentState" class="input-style" id="field6"></input>
</div>

<div class="right-div">
<h3>Enter country</h3>
<input type="text" name="paymentCountry" class="input-style" id="field1"></input>
</div>

<hr style="width: 100%">
<div class="left-div">
<h3>Select way of Payment</h3>
 <select name="paymentMode" class="input-style" id="field4" onchange="codOrNot(this);" required>
 <option value="Cash On Delivery">Cash On Delivery</option>
 <option value="Online Payment">Online Payment</option>
 </select>
</div>

<div class="right-div">
<h3>Pay online</h3>
<input type="text" name="paymentTransactionId" style="display:none;" class="input-style" placeholder="Enter Transaction ID" id="field7"></input>
</div>
<hr style="width: 100%">

<div class="left-div">
<h3>Mobile Number</h3>
<input type="text" name="paymentMobile" class="input-style" placeholder="*Your default number is already taken" id="field5" disabled></input>
	
</div>
<div class="right-div">
<h3 style="color: red">*Check before clicking the "Save and Proceed to Pay" Button</h3>	
<button class="button" type="submit">Save and Proceed To Pay<i class='far fa-arrow-alt-circle-right'></i></button>

</div>
</form>
<br><br><br>
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
        	 // else if(j==1){cell[j].innerHTML=object[0]["prod_id"];}
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
    </script>
    <script>
    const colObj=<%=session.getAttribute("colDetails")%>;
    
    var i=1;
    for( val in colObj){
    	document.getElementById("field"+i).value=colObj[val];
    	
    	i++;
    }
    
    codOrNot("field4");
    function codOrNot(that){
    	document.getElementById("field5").innerHTML = colObj['mobileNumber'];
    	if(that.value=="Cash On Delivery")
    		document.getElementById("field7").style.display= "none";
    	else
    		document.getElementById("field7").style.display= "block" ;
    }
    </script>
</body>
</html>