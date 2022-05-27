package callAction;
import java.sql.*;
import java.util.Map;
import project.*;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

public class ordersReceivedAction implements SessionAware{
	SessionMap<String,String> sessionmap;
	private String productId;
	private String consumerId;
	private String showStatus;
	private String resValue;
	
	public String getResValue() {
		return resValue;
	}

	public void setResValue(String resValue) {
		this.resValue = resValue;
	}

	public void setSession(Map map) {
		sessionmap = (SessionMap)map;
	}
	
	public String getShowStatus() {
		return showStatus;
	}



	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}



	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String execute() {
		
		String idSupplier = (String)sessionmap.get("loginId");
		int count=0;
		try {
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select u.mobileNumber,product_name,quantity,sub_total,o.address,o.city,o.state,o.country,order_date,delivery_date,paymentMode,transactionId,status,prod_id,consumer_id from orderlist o,users u where product_name in (Select name from product where supplier_ID = ? ) and id=consumer_id and status=? order by consumer_id;");
			ps.setString(1, idSupplier);
			ps.setString(2,showStatus);
			
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
			if(showStatus.equals("Cancel"))
				return "successToCancel";
			else if(showStatus.equals("Delivered"))
				return "successToDelivered";
			else
				return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
	}
	
	public String removeFromOrder() {
		
		try {
		Connection con=ConnectionProvider.getcon();
		PreparedStatement ps=con.prepareStatement("update orderlist set status=\"Cancel\" where consumer_id=? and prod_id=?;");
		ps.setString(1, consumerId);
		ps.setString(2, productId);
		ps.execute();
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		if(showStatus!=null && showStatus.equals("myOrder")) {
			
			return "sucessToConsumer";}
		return "success";
	}
	
	public String deliveredOrder() {
		try {
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("update orderlist set status=\"Delivered\" where consumer_id=? and prod_id=?;");
			ps.setString(1, consumerId);
			ps.setString(2, productId);
			ps.execute();
	
			}
			catch(Exception e) {
				System.out.println(e);
			}
		
		return "success";
	}
	
}
