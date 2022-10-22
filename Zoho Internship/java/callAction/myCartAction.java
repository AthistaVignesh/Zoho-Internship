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

public class myCartAction implements SessionAware{
	SessionMap <String,String> sessionmap;
	private String returnValue;
	
	
	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public void setSession(Map map) {
		sessionmap=(SessionMap)map;
	}
	
	public String execute() {
		
		int count=0;
		try {
			String consumerId=sessionmap.get("loginId");
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select p.prod_id,name,category,c.price,quantity,total from cart c,product p where c.prod_id=p.prod_id and consumer_id=?;");
			ps.setString(1,consumerId);
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
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		if(returnValue != null && returnValue.equals("1"))
			return "successToOrderDefault";
		else if(returnValue != null && returnValue.equals("2"))
			return "successToOrderAlt";
		return "success";
	}
}
