package callAction;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;
import project.ConnectionProvider;
import java.sql.*;
import crypt.securePass;

public class resetPasswordAction extends ActionSupport{
	HttpServletRequest request=ServletActionContext.getRequest();  
	HttpSession session=request.getSession();
	private String newPassword;
	private String conNewPassword;
	private String mail=(String)session.getAttribute("resetEmail");
	
	
	public boolean passValid() {
		Pattern passRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
		Matcher passMatcher = passRegex.matcher(newPassword);
		return passMatcher.find();
	}
	
	@Override
	public void validate() {
		boolean passres=passValid();
		try {
		if(!passres)
        	addFieldError("conNewPassword","*Please enter a valid password");
		super.validate();
		}
		catch(Exception e) {
			super.validate();
		}
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getConNewPassword() {
		return conNewPassword;
	}


	public void setConNewPassword(String conNewPassword) {
		this.conNewPassword = conNewPassword;
	}


	public String execute() {
		System.out.println(mail);
		boolean condition1=!newPassword.equals(conNewPassword);
		try{
			session.invalidate();  
			if(condition1) throw new Exception ("some error message");
			String newEncryptPassword = securePass.encrypt(newPassword);
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("update users set password= '"+newEncryptPassword+ "' where email= '"+mail+"'");
			ps.executeUpdate();
			return "success";
		}
		catch(Exception e){
			System.out.println(e);
			return "success";
		}
		
		
	}
}

