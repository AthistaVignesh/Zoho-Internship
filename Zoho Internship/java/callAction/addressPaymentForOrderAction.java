package callAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import project.ConnectionProvider;

public class addressPaymentForOrderAction implements SessionAware{
	SessionMap<String,String> sessionmap;
	private String paymentAddress;
	private String paymentCity;
	private String paymentState;
	private String paymentCountry;
	private String paymentMobile;
	private String paymentMode;
	private String paymentTransactionId;
	private String fetchWhat;
	private String change;
	
	
	
	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getFetchWhat() {
		return fetchWhat;
	}

	public void setFetchWhat(String fetchWhat) {
		this.fetchWhat = fetchWhat;
	}

	public String getPaymentMobile() {
		return paymentMobile;
	}

	public void setPaymentMobile(String paymentMobile) {
		this.paymentMobile = paymentMobile;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentTransactionId() {
		return paymentTransactionId;
	}

	public void setPaymentTransactionId(String paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}

	public String getPaymentAddress() {
		return paymentAddress;
	}

	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}

	public String getPaymentCity() {
		return paymentCity;
	}

	public void setPaymentCity(String paymentCity) {
		this.paymentCity = paymentCity;
	}

	public String getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	public String getPaymentCountry() {
		return paymentCountry;
	}

	public void setPaymentCountry(String paymentCountry) {
		this.paymentCountry = paymentCountry;
	}

	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	}
	
	public String execute() {
		System.out.println(fetchWhat);
		String consumer_id=(String)sessionmap.get("loginId");
		try {
			JSONObject colDetails=new JSONObject();
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=null;
			if(fetchWhat!=null && fetchWhat.equals("1"))
				ps=con.prepareStatement("Select u.address,u.city,u.state,u.country,u.mobileNumber,c.transactionId,c.paymentMode from users u,cart c where id=? and u.id=c.consumer_id");
			else if(fetchWhat!=null && fetchWhat.equals("2"))
				ps=con.prepareStatement("Select c.address,c.city,c.state,c.country,u.mobileNumber,c.transactionId,c.paymentMode from users u,cart c where id=? and u.id=c.consumer_id");
			ps.setString(1,consumer_id);
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
		}
		catch(Exception e) {
			System.out.println("here?\n\n\n\n"+e);
		}
		if(fetchWhat!=null && fetchWhat.equals("1"))
			return "successFromUsers";
		else if(fetchWhat!=null && fetchWhat.equals("2"))
			return "successFromCart";
		return "successFromUsers";
	}
	
	public String updateAddress() {
		try {
			System.out.println(change);
			String consumer_id=(String)sessionmap.get("loginId");
			Connection con=ConnectionProvider.getcon();
			if(change!=null && change.equals("all")) {
			PreparedStatement ps=con.prepareStatement("update users set address=?,city=?,state=?,country=? where id=?");
			ps.setString(1, paymentAddress);
			ps.setString(2, paymentCity);
			ps.setString(3, paymentState);
			ps.setString(4, paymentCountry);
			ps.setString(5,consumer_id);
			int changes1=ps.executeUpdate();
			}
			PreparedStatement ps1=con.prepareStatement("update cart set address=?,city=?,state=?,country=?,paymentMode=?,transactionId=?,orderDate=now(),status=\'bill\',deliveryDate=DATE_ADD(orderDate,INTERVAL 7 DAY) where consumer_id=?");
			ps1.setString(1, paymentAddress);
			ps1.setString(2, paymentCity);
			ps1.setString(3, paymentState);
			ps1.setString(4, paymentCountry);
			ps1.setString(5, paymentMode);
			ps1.setString(6, paymentTransactionId);
			ps1.setString(7,consumer_id);
			int changes2=ps1.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("Exeception :"+e);
		}
		return "success";
	}
}
