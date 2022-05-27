package callAction;
import project.ConnectionProvider;
import emailSender.sendMail;
import java.sql.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import crypt.securePass;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;


public class changeDetailsAction implements SessionAware {
	
	private String oldPassword;
	private String newPassword;
	private String newConfirmPassword;
	SessionMap<String,String> sessionmap;
	public void setSession(Map map) {
		sessionmap=(SessionMap)map;
	}
	
	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}

	public void setNewConfirmPassword(String newConfirmPassword) {
		this.newConfirmPassword = newConfirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
	
	public boolean passValid() {
		Pattern passRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
		Matcher passMatcher = passRegex.matcher(newPassword);
		return passMatcher.find();
	}
	
	
	public String validatePassword() {
		System.out.println("inside validate");
		boolean passres=passValid();
		
		if(!passres)
        	return "rule";
		if(!newPassword.equals(newConfirmPassword))
			return "check";
		
		return null;
	}

	public String execute() {

		int count=0;
		try {
		String idConsumer=(String)sessionmap.get("loginId");
		Connection con=ConnectionProvider.getcon();
		JSONArray ja=new JSONArray();
		JSONObject mainObj = new JSONObject();
		PreparedStatement ps1=con.prepareStatement("select name,email,mobileNumber from users where id=?;");
		ps1.setString(1, idConsumer);
		ResultSet rs=ps1.executeQuery();
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
		catch(Exception e) {
			System.out.println(e);
		}
		return "success";
	}
	
	public String changePassword() {
		try {
			String emailConsumer=(String)sessionmap.get("email");
			String res=validatePassword();
			if(res!=null && res.equals("rule"))
				return "rule";
			else if(res!=null && res.equals("check"))
				return "check";
			String idConsumer=(String)sessionmap.get("loginId");
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("select password from users where id=?");
			ps.setString(1, idConsumer);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				String newEncryptPassword;
				String originalEncryptPassword=rs.getString(1);
				String originalPassword=securePass.decrypt(originalEncryptPassword);
				if(originalPassword.equals(oldPassword)) {
					newEncryptPassword=securePass.encrypt(newPassword);
					PreparedStatement ps1=con.prepareStatement("update users set password=? where id=?");
					ps1.setString(1, newEncryptPassword);
					ps1.setString(2,idConsumer);
					ps1.executeUpdate();
					sendMail.passChanged(emailConsumer);		//
					System.out.println("check for mail sent");
					return "success";
				}
				else
				return "wrong";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "failed";
	}
}
