package callAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import project.ConnectionProvider;

public class myOrderAction implements SessionAware{
	SessionMap <String,String> sessionmap;
	public void setSession(Map map) {
		sessionmap=(SessionMap)map;
	}
	
	public String execute() {
		
		int count=0;
		try {
			String consumerId=sessionmap.get("loginId");
			System.out.println(consumerId);
			Connection con=ConnectionProvider.getcon();
			JSONArray ja=new JSONArray();
			JSONObject mainObj = new JSONObject();
			PreparedStatement ps=con.prepareStatement("select ROW_NUMBER() OVER(ORDER BY status desc) as sno,product_name,Category,price,quantity,sub_total,order_date,delivery_date,paymentMode,status,prod_id from orderlist where consumer_id=? order by status desc;");
			ps.setString(1,consumerId);
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
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return "success";
	}
}

