package callAction;

import java.sql.*;
import java.util.Map;
import project.ConnectionProvider;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

public class feedbackReceivedAction implements SessionAware{
	SessionMap<String,String> sessionmap;
	public void setSession(Map map) {
		sessionmap=(SessionMap)map;
	}
	
	public String execute() {
		String idConsumer=sessionmap.get("loginId");
		int count=0;
		try {
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select * from message where about in (select prod_id from product where supplier_ID=?) or about=0 order by about;");
			ps.setString(1, idConsumer);
			
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
			
			return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
	}

}
