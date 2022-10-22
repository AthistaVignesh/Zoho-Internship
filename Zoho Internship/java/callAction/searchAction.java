package callAction;
import java.sql.*; 
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import project.ConnectionProvider;
public class searchAction implements SessionAware {
	private String searchQuery;
	SessionMap<String,String> sessionmap;
	

	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	}  
	
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String execute() {
		
		int count=0;
		try {
			Connection con=ConnectionProvider.getcon();
			JSONArray ja=new JSONArray();
			JSONObject mainObj = new JSONObject();
			PreparedStatement ps=con.prepareStatement("select ROW_NUMBER() OVER(ORDER BY active	 desc) as sno,prod_id,name,category,price,active from product where (name like ? or category like ?) order by active desc");
			ps.setString(1, '%'+searchQuery+'%');
			ps.setString(2, '%'+searchQuery+'%');
			System.out.println(ps);
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
