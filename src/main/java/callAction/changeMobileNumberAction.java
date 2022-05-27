package callAction;

import java.util.Map;
import project.ConnectionProvider;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import crypt.securePass;

import java.sql.*;

public class changeMobileNumberAction implements SessionAware {
	private String newMobileNumber;
	private String passToChangeNumber;
	SessionMap<String,String> sessionmap;
	
	public String getNewMobileNumber() {
		return newMobileNumber;
	}

	public void setNewMobileNumber(String newMobileNumber) {
		this.newMobileNumber = newMobileNumber;
	}



	public String getPassToChangeNumber() {
		return passToChangeNumber;
	}



	public void setPassToChangeNumber(String passToChangeNumber) {
		this.passToChangeNumber = passToChangeNumber;
	}

	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	}

	
	
	public String execute() {
		String idConsumer=sessionmap.get("loginId");
		try {
			
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select password from users where id="+idConsumer);
			ResultSet rs = ps.executeQuery();	
			rs.next();
			String decryptPassword= rs.getString(1);
			String actualPassword=securePass.decrypt(decryptPassword);
			
				if(actualPassword.equals(passToChangeNumber)){
					PreparedStatement ps1=con.prepareStatement("update users set mobileNumber="+newMobileNumber);
					PreparedStatement ps2=con.prepareStatement("update cart set mobileNumber="+newMobileNumber);
					ps1.execute();
					ps2.execute();
					sessionmap.put("number", newMobileNumber);
					
					return "success";
				}
				else
					System.out.println("check from changemobilenumebr3");
					return "invalid";
				
				}
		
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}		
	}
}
