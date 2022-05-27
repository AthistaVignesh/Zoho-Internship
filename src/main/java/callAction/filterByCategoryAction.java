package callAction;

import java.util.Map;
import project.ConnectionProvider;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class filterByCategoryAction implements SessionAware {
SessionMap<String,String> sessionmap;
private String filterCategory;
private String searchQuery;
private String searchQueryEndDate;
private String showStatus;

public void setSession(Map map) {
	sessionmap=(SessionMap)map;
}

public String getShowStatus() {
	return showStatus;
}

public void setShowStatus(String showStatus) {
	this.showStatus = showStatus;
}



public String getSearchQueryEndDate() {
	return searchQueryEndDate;
}



public void setSearchQueryEndDate(String searchQueryEndDate) {
	this.searchQueryEndDate = searchQueryEndDate;
}



public String getFilterCategory() {
	return filterCategory;
}

public void setFilterCategory(String filterCategory) {
	this.filterCategory = filterCategory;
}

public String getSearchQuery() {
	return searchQuery;
}

public void setSearchQuery(String searchQuery) {
	this.searchQuery = searchQuery;
}

public String execute() {
	System.out.println(searchQuery+"\n"+filterCategory+"\n"+searchQueryEndDate+"\n\n\n"+showStatus);
	String loginId = (String)sessionmap.get("loginId");
	try {
	Connection con=ConnectionProvider.getcon();
	PreparedStatement ps = null;
	switch(filterCategory) {
		case "product_name":
			ps=con.prepareStatement("select u.mobileNumber,product_name,quantity,sub_total,o.address,o.city,o.state,o.country,order_date,delivery_date,paymentMode,transactionId,status,prod_id,consumer_id from orderlist o,users u where product_name like ? and id=? and product_name in (select name from product where supplier_Id=?) and status like ?;");
			ps.setString(1,"%"+searchQuery+"%");
			ps.setString(2, loginId);
			ps.setString(3, loginId);
			ps.setString(4, showStatus);
			break;
		case "sub_total":
			ps=con.prepareStatement("select u.mobileNumber,product_name,quantity,sub_total,o.address,o.city,o.state,o.country,order_date,delivery_date,paymentMode,transactionId,status,prod_id,consumer_id from orderlist o,users u where sub_total>? and id=? and product_name in (select name from product where supplier_ID =?) and status like ?;");
			ps.setString(1, searchQuery);
			ps.setString(2,loginId);
			ps.setString(3, loginId);
			ps.setString(4, showStatus);
			break;
		case "order_date":
			ps=con.prepareStatement("select u.mobileNumber,product_name,quantity,sub_total,o.address,o.city,o.state,o.country,order_date,delivery_date,paymentMode,transactionId,status,prod_id,consumer_id from orderlist o,users u where order_date between ? and ? and id=? and product_name in (select name from product where supplier_ID =?) and status like ?;");
			ps.setString(1,searchQueryEndDate );
			ps.setString(2,searchQuery);
			ps.setString(3,loginId);
			ps.setString(4, loginId);
			ps.setString(5, showStatus);
			break;
		case "paymentMode":
			ps=con.prepareStatement("select u.mobileNumber,product_name,quantity,sub_total,o.address,o.city,o.state,o.country,order_date,delivery_date,paymentMode,transactionId,status,prod_id,consumer_id from orderlist o,users u where paymentMode=? and id=? and product_name in (select name from product where supplier_ID =?) and status like ?;");
			ps.setString(1, searchQuery);
			ps.setString(2,loginId);
			ps.setString(3, loginId);
			ps.setString(4, showStatus);
			break;
		case "quantity":
			ps=con.prepareStatement("select u.mobileNumber,product_name,quantity,sub_total,o.address,o.city,o.state,o.country,order_date,delivery_date,paymentMode,transactionId,status,prod_id,consumer_id from orderlist o,users u where quantity>=? and id=? and product_name in (select name from product where supplier_ID =?) and status like ?;");
			ps.setString(1, searchQuery);
			ps.setString(2,loginId);
			ps.setString(3, loginId);
			ps.setString(4, showStatus);
			break;
		default:
			ps=con.prepareStatement("select u.mobileNumber,product_name,quantity,sub_total,o.address,o.city,o.state,o.country,order_date,delivery_date,paymentMode,transactionId,status,prod_id,consumer_id from orderlist o,users u where id=? and product_name in (select name from product where supplier_ID =?) and status like ?;");
			ps.setString(1,loginId);
			ps.setString(2, loginId);
			ps.setString(3, showStatus);
			break;
	}
	System.out.println(ps);
	int count=0;
	JSONArray ja=new JSONArray();
	JSONObject mainObj = new JSONObject();
	ResultSet rs=ps.executeQuery();
	ResultSetMetaData rsmd = rs.getMetaData();
	
	while(rs.next()) {
		count++;
		  int colsize=rsmd.getColumnCount();
          for(int i=1;i<=colsize;i++)
          {
              
        	  JSONObject obj = new JSONObject();
              String col= rsmd.getColumnName(i);
              obj.put(col,rs.getObject(col));
              ja.put(obj);
          }	
          mainObj.put(String.valueOf(count),ja);
          ja=new JSONArray();
	}
	sessionmap.put("jdata", mainObj.toString());
	if(showStatus!=null && showStatus.equals("Cancel"))
		return "successToCancel";
	else if(showStatus!=null && showStatus.equals("Delivered"))
		return "successToDelivered";
	else
		return "success";
	}
	catch(Exception e) {
		System.out.println(e);
		return "failure";
	}
}

}
