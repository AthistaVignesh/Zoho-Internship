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

public class addChangeAddressAction implements SessionAware {
	private String changeAddress;
	private String changeCity;
	private String changeState;
	private String changeCountry;
	private String statusShow;
	
	
	
	public String getStatusShow() {
		return statusShow;
	}
	public void setStatusShow(String statusShow) {
		this.statusShow = statusShow;
	}
	public String getChangeAddress() {
		return changeAddress;
	}
	public void setChangeAddress(String changeAddress) {
		this.changeAddress = changeAddress;
	}
	public String getChangeCity() {
		return changeCity;
	}
	public void setChangeCity(String changeCity) {
		this.changeCity = changeCity;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public String getChangeCountry() {
		return changeCountry;
	}
	public void setChangeCountry(String changeCountry) {
		this.changeCountry = changeCountry;
	}
	SessionMap<String,String> sessionmap;
	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	}
	public String execute() {
		
		String consumer_id=(String)sessionmap.get("loginId");
		try {
			JSONObject colDetails=new JSONObject();
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select address,city,state,country from users where id=?");
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
		if(statusShow !=null && statusShow.equals("1"))
			return "successChanged";
		else if(statusShow !=null && statusShow.equals("0"))
			return "failedChange";
		else
			return "success";
	}
	public String changeAddress() {
		String consumer_id=(String)sessionmap.get("loginId");
		try {
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps1=con.prepareStatement("update users set address=?,city=?,state=?,country=? where id=?");
			ps1.setString(1,changeAddress);
			ps1.setString(2,changeCity);
			ps1.setString(3,changeState);
			ps1.setString(4,changeCountry);
			ps1.setString(5, consumer_id);
			ps1.execute();
			System.out.println("ps1 success from add change address");
			PreparedStatement ps2=con.prepareStatement("update cart set address=?,city=?,state=?,country=? where consumer_id=?");
			ps2.setString(1,changeAddress);
			ps2.setString(2,changeCity);
			ps2.setString(3,changeState);
			ps2.setString(4,changeCountry);
			ps2.setString(5, consumer_id);
			ps2.execute();
			System.out.println("ps2 success from add change address");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "success";
	}
}
