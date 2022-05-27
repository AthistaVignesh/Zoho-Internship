package callAction;
import java.sql.*;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import project.ConnectionProvider;
public class messageUsAction implements SessionAware {
	SessionMap<String,String> sessionmap;
	private String aboutWhat;
	private String content;
	
	
	
	public String getAboutWhat() {
		return aboutWhat;
	}

	public void setAboutWhat(String aboutWhat) {
		this.aboutWhat = aboutWhat;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSession(Map map) {
		sessionmap=(SessionMap)map;
	}
	
	public String execute() {
		try {
		String[] fields=new String[] {"Value","Text"};
		int count=0;
		String idConsumer=sessionmap.get("loginId");
		Connection con=ConnectionProvider.getcon();
		PreparedStatement ps=con.prepareStatement("select p.prod_id,product_name from product p,orderlist where product_name = name and consumer_id=? and status=?;");
		ps.setString(1, idConsumer);
		ps.setString(2,"Delivered");
		ResultSet rs=ps.executeQuery();
		JSONArray ja=new JSONArray();
		ResultSetMetaData rsmd=rs.getMetaData();

		while(rs.next()) {
			int colsize=rsmd.getColumnCount();
			JSONObject obj = new JSONObject();
			for(int i=1;i<=colsize;i++) {
                String col= rsmd.getColumnName(i);
                obj.put(fields[i-1],rs.getObject(col));
                
			}	
			ja.put(obj);
		}
		
		sessionmap.put("jdata", ja.toString());
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "success";
	}
	
	public String fetchMessage() {
		String consumerId=sessionmap.get("loginId");
		try {
		Connection con=ConnectionProvider.getcon();
		PreparedStatement ps=con.prepareStatement("insert into message (customerId,about,body) values (?,?,?)");
		ps.setString(1,consumerId);
		ps.setString(2, aboutWhat);
		ps.setString(3,content);
		ps.execute();
		
		return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		
	}

}
