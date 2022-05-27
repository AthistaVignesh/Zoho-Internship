<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%@include file="header.jsp"%><%@include file="footer.jsp"%> <html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>My Cart</title>
    <style>
      h3 {
        color: red;
        text-align: center;
      }
      th#totalBox {
   	 	background: #ccc;
    	font-size: 20px;
		}
    </style>
  </head>
  <body>
    <div style="color: white; text-align: center; font-size: 30px;">My Cart <i class='fas fa-cart-arrow-down'></i>
    </div>
   	  <%String jsonObj=(String)session.getAttribute("jdata");%>
   	  <table id="mainTable">
      <thead>
        <tr>
          <th scope="col">S.No</th>
         
          <th scope="col">Product Name</th>
          <th scope="col">Category</th>
          <th scope="col">
            <i class="fa fa-inr"></i> price
          </th>
          <th scope="col">Quantity</th>
          <th scope="col">Sub Total</th>
          <th scope="col">Remove <i class='fas fa-trash-alt'></i>
          </th>
        </tr>
      </thead>
      <tbody id="rowJson">
      </tbody>
    </table>
    <table id="priceTable">
    <tbody id="priceTableBody">
     <tr class="totalCol">
      <td></td><td></td><td></td><td></td><td></td><td></td>
          <td scope="col" class="totalBox">Total: </span>
            <i class="fa fa-inr"></i>
            <span id="cartTotal">
          </td>
          <td scope="col" class="totalBox">
            <a href="myCartAct?returnValue=1" class="totalBox">Proceed to order</a>
          </td>
        </tr>
        </tbody>
    </table>
    <br>
    <br>
    <br>
    <script>
      var obj = <%= session.getAttribute("jdata") %>
      var regTable = document.getElementById("rowJson");
      let size = Object.keys(obj).length;
      console.log(size);
      console.log(obj)
      var cell = [];
      var newRow = [];
      let j = 0;
      var sno = 1;
      var cartTotal=0;
      for (var i = 0; i <= size; ++i) {
    	var object = obj[i];
        newRow[i] = regTable.insertRow(i);
        if(i!=0){
        
        cell[j] = newRow[i].insertCell(j);
  	  	cell[j].innerHTML=" <a href = \"removeFromCartAct?idCart="+object[0]["prod_id"]+"\">Remove  <i class = \'fas fa-trash-alt\'></i> </a>";
        }
        for (var ind in object) { 	
          for (var vals in object[ind]) {
        	  console.log(vals);
        	  if(vals.localeCompare("prod_id")==0)
        		  continue;
        	  
        	  cell[j] = newRow[i].insertCell(j);
        	  if(j==0){cell[j].innerHTML=sno; sno++;}
        	//  else if(j==1){cell[j].innerHTML=object[0]["prod_id"];}
        	  else if(j==1){cell[j].innerHTML=object[1]["name"];}
        	  else if(j==2){cell[j].innerHTML=object[2]["category"];}
        	  else if(j==3){cell[j].innerHTML=" <i class = \"fa fa-inr\"></i>" + object[3]["price"];}
        	  else if(j==4){cell[j].innerHTML = " <a href = \"addOneMoreAct?idCart="+object[0]["prod_id"]+"\"> <i class = \'fas fa-plus-circle\'></i> </a>"+object[4]["quantity"]+" <a href = \"subOneMoreAct?idCart="+object[0]["prod_id"]+"\"> <i class = \'fas fa-minus-circle\'></i> </a>";}
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
  </body>
</html>