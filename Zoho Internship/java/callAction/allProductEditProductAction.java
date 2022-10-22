package callAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import project.ConnectionProvider;
public class allProductEditProductAction implements SessionAware {
SessionMap<String,String> sessionmap;
private String rdata;
private String returnValue;
public void setSession(Map map) {  
    sessionmap=(SessionMap)map;  
}  


	public String getReturnValue() {
	return returnValue;
}



public void setReturnValue(String returnValue) {
	this.returnValue = returnValue;
}



	public String execute() {
		int count=0;
		String suppID=sessionmap.get("loginId");
		try {
		Connection con=ConnectionProvider.getcon();
		JSONArray ja=new JSONArray();
		JSONObject mainObj = new JSONObject();
		PreparedStatement ps=con.prepareStatement("select prod_id,name,category,price,active from product where supplier_ID=?");
		ps.setString(1,suppID);
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
		rdata=mainObj.toString();
		sessionmap.put("jdata", mainObj.toString());
		
		}
		catch(Exception e) {
			System.out.println("execption   "+e);
		}
		
		if(returnValue!=null && returnValue.equals("1"))
			return "successEditing";
		return "success";
	}
	public String getRdata() {
		return rdata;
	}

	}

