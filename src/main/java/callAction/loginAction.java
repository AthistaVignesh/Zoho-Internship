package callAction;
import java.sql.*;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import project.ConnectionProvider;
import crypt.securePass;

public class loginAction implements SessionAware{
	private String logEmail;
	private String logPassword;
	private String actualPassword;
	private String userType;
	SessionMap<String,String> sessionmap;  
	
	public String getLogEmail() {
		return logEmail;
	}

	public void setLogEmail(String logEmail) {
		this.logEmail = logEmail;
	}

	public String getLogPassword() {
		return logPassword;
	}

	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
	}
	
	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	    sessionmap.put("loginStatus","false");
	}  

	public String execute() {
		
		
		
		String decryptPassword="";
		try{
			
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select password,Type,id,mobileNumber from users where email=?");
			ps.setString(1,logEmail);
			ResultSet sol=ps.executeQuery();
			
			boolean res=sol.next();
			if(res){	
				decryptPassword= sol.getString(1);
				userType = sol.getString(2);
				String loginId = sol.getString(3);
				String mobileNumber=sol.getString(4);
				
				actualPassword=securePass.decrypt(decryptPassword);
				if(actualPassword.equals(logPassword)){
					sessionmap.put("loginStatus","true");
					sessionmap.put("email", logEmail);
					sessionmap.put("loginId",loginId);
					sessionmap.put("number", mobileNumber);
					if(userType.equals("Supplier")){
						return "supplier";
					}
					else{
						return "consumer";
					}
				}
				else
					return "invalid";
				}
			else
				return "notfound";
			}
		catch(Exception e){
			System.out.println(e);
			return "error";
		}
	}

	public String logOut() {
		
		
		sessionmap.clear();
		return "success";
	}
}

