package callAction;
import java.sql.*;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

import project.ConnectionProvider;


public class billAction implements SessionAware{
	SessionMap<String,String> sessionmap;
	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	}
	public String execute() {
		JSONObject colDetails=new JSONObject();
		try {
			String idConsumer=(String)sessionmap.get("loginId");
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select u.name,u.mobileNumber,u.email,c.orderDate,c.deliveryDate,c.paymentMode,c.transactionId,c.address,c.city,c.state,c.country from users u,cart c where consumer_id=id and id=?;");
			ps.setString(1, idConsumer);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			while(rs.next()) {
				int colsize=rsmd.getColumnCount();		
				for(int i=1;i<=colsize;i++) {
					String colName= rsmd.getColumnName(i);
	                colDetails.put(colName,rs.getObject(colName));
					
				}
			}
			sessionmap.put("colDetails", colDetails.toString());
			PreparedStatement ps1=con.prepareStatement("update cart set status=\"Processing\" where consumer_id="+idConsumer+";" );
			ps1.execute();
			System.out.println(ps1);
			PreparedStatement ps2=con.prepareStatement("insert into orderlist select consumer_id,email,name,p.category,p.price,quantity,total,orderDate,deliveryDate,paymentMode,Status,transactionId,address,city,state,country,c.prod_id from cart c,product p where c.prod_id=p.prod_id and consumer_id=? and not status=? and not status=?;");
			ps2.setString(1,idConsumer);
			ps2.setString(2, "Bill");
			ps2.setString(3,"Cancelled");
			ps2.execute();
			PreparedStatement ps3=con.prepareStatement("delete from cart where consumer_id='"+idConsumer+"' and status='Processing'");
			ps3.execute();
			return "success";
		}
		catch(Exception e) {System.out.println("here in bill?\n\n\n\n\n"+e);	return "failure";}
	}
}
