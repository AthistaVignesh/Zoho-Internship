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

public class homeAction implements SessionAware{

		SessionMap<String,String> sessionmap;
		private String rdata;

		public void setSession(Map map) {  
		    sessionmap=(SessionMap)map;  
		}  

			public String execute() {
				int count=0;
				
				try {
				Connection con=ConnectionProvider.getcon();
				JSONArray ja=new JSONArray();
				JSONObject mainObj = new JSONObject();
				PreparedStatement ps=con.prepareStatement("select ROW_NUMBER() OVER(ORDER BY active	 desc) as sno,prod_id,name,category,price,active from product order by active desc");
			
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
					System.out.println("exception   "+e);
				}
				
				
				return "success";
			}
			public String getRdata() {
				return rdata;
			}

			
	}




