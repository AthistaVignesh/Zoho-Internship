<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">My Orders <i class='fab fa-elementor'></i></div>
<%String jsonString=(String)session.getAttribute("jdata");%>
<table id="mainTable">
        <thead>
          <tr>
            <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i>  Price</th>
            <th scope="col">Quantity</th>
            <th scope="col"><i class="fa fa-inr"></i> Sub Total</th>
            <th scope="col">Order Date</th>
            <th scope="col">Expected Delivery Date</th>
            <th scope="col">Payment Method</th>
            <th scope="col">Status</th>
            <th scope="col">Cancel Order</th>            
          </tr>
        </thead>
        
        <tbody id="rowJson">
        </tbody>
      </table>
      <br>
      <br>
      <br>
<script>
var loginId=<%=session.getAttribute("loginId")%>;
const obj = JSON.parse('<%=jsonString%>');
console.log(obj);
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
			   console.log("index = "+ind+"Value = "+vals);
			   j++;	
			   cell[j-1]=newRow[i].insertCell(j-1);
			   if(ind==3 || ind==5)
				   cell[j-1].innerHTML = "<i class=\"fa fa-inr\"></i>"+object[ind][vals];
			   else if(vals.localeCompare("prod_id")==0){
				   j--
				   break;}
			   else
			   	   cell[j-1].innerHTML = object[ind][vals];		  
			   if(j-1==0)
				   idToEdit = object[ind][vals];	
		   }		   
	   }
	   if(i!=0){
	  
	   if(object[9]['status'].localeCompare("Processing")==0)
	   			cell[j].innerHTML = "<a href=\"supplier/removeProdFromOrderAct?productId="+object[10]['prod_id']+"&consumerId="+loginId+"&showStatus=myOrder\">Cancel <i class='fas fa-window-close'></i></a>";
	   			
	   else if(object[9]['status'].localeCompare("Cancel")==0)
  			cell[j].innerHTML = "<b>CANCELLED</b>";
	   
	   else if(object[9]['status'].localeCompare("Delivered")==0)
 			cell[j].innerHTML = "<b>DELIVERED</b>";
		}
	   j=0
	}
	
	   
	

</script>
</body>
</html>