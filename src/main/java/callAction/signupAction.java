package callAction;
import crypt.securePass;
import project.ConnectionProvider;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionSupport;

public class signupAction extends ActionSupport{
	
	public boolean emailValid() {
		Pattern emailRegex = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
		Matcher emailMatcher = emailRegex.matcher(signEmail);
		return emailMatcher.find();
	}
	
	public boolean mobileValid() {
		Pattern mobileRegex = Pattern.compile("^[789]\\d{9}$");
		Matcher mobileMatcher = mobileRegex.matcher(signMobileNumber);
		return mobileMatcher.find();
	}
	
	public boolean passValid() {
		Pattern passRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
		Matcher passMatcher = passRegex.matcher(signPassword);
		return passMatcher.find();
	}
	
	@Override
	public void validate() {
		try {
			boolean emailres = emailValid();
	        boolean mobileres = mobileValid();
	        boolean passres = passValid();
	        
			if(signName.equals("") || signName==null || signName.equals("hello"))
				addFieldError("signEmail","*Please enter a valid name");
	        if(!emailres)
	        	addFieldError("signMobileNumber","*Please enter a valid email");
	        if(!mobileres)
	        	addFieldError("signPassword","*Please enter a valid mobile number");
	        if(!passres)
	        	addFieldError("signConPassword","*Please enter a valid password");
	        super.validate();}
		
		catch(Exception e) {
			super.validate();
		}
	}

	private String signName;
	private String signEmail;
	private String signMobileNumber;
	private String signPassword;
	private String signConPassword;
	private String signtyp;
	private String signAddress="";
	private String signCity="";
	private String signState="";
	private String signCountry="";
		
	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getSignEmail() {
		return signEmail;
	}

	public void setSignEmail(String signEmail) {
		this.signEmail = signEmail;
	}

	public String getSignMobileNumber() {
		return signMobileNumber;
	}

	public void setSignMobileNumber(String signMobileNumber) {
		this.signMobileNumber = signMobileNumber;
	}

	public String getSignPassword() {
		return securePass.encrypt(signPassword);
	}

	public void setSignPassword(String signPassword) {
		this.signPassword = signPassword;
	}

	public String getSignConPassword() {
		return signConPassword;
	}

	public void setSignConPassword(String signConPassword) {
		this.signConPassword = signConPassword;
	}

	public String getSigntyp() {
		return signtyp;
	}

	public void setSigntyp(String signtyp) {
		this.signtyp = signtyp;
	}

	public String execute() {
			try{
				String signEncryptPassword=securePass.encrypt(signPassword);
				if(!signConPassword.equals(signPassword)) 
						return "incorrect";
				Connection con=ConnectionProvider.getcon();
				PreparedStatement ps=con.prepareStatement("insert into users(name,email,mobileNumber,password,address,city,state,country,Type) values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1,signName);
				ps.setString(2,signEmail);
				ps.setString(3,signMobileNumber);
				ps.setString(4,signEncryptPassword);
				ps.setString(5,signAddress);
				ps.setString(6,signCity);
				ps.setString(7,signState);
				ps.setString(8,signCountry);
				ps.setString(9,signtyp);
				ps.executeUpdate();
				return "success";	
				}
			catch(Exception e){
				System.out.println(e);
				return "failure";	
			}
	}
}
