package callAction;
import project.ConnectionProvider;
import java.sql.*;
import emailSender.sendMail;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;
import java.util.Random;

public class forgotPasswordAction extends ActionSupport implements SessionAware{
		
		HttpServletRequest request=ServletActionContext.getRequest();  
		HttpSession session=request.getSession();
		private String forMail;
		private String forName;
		private String forOTP;
		private String msg=(String) session.getAttribute("msg");
		SessionMap<String,String> sessionmap;  
		public String getForMail() {
			return forMail;
		}
		public void setForMail(String forMail) {
			this.forMail = forMail;
		}
		public void setSession(Map map) {  
		    sessionmap=(SessionMap)map;  
		}  
		
		public String getForName() {
			return forName;
		}
		public void setForName(String forName) {
			this.forName = forName;
		}
		
		public String getForOTP() {
			return forOTP;
		}
		public void setForOTP(String forOTP) {
			this.forOTP = forOTP;
		}
		
		
		
		public String execute() {
		
		Boolean b2=Boolean.valueOf(msg); 
		
		if(!b2){
		Random r = new Random();
		int x=r.nextInt(99999);
		try{
		sendMail.sendMails(forMail, x); 
		System.out.println(forMail);
		sessionmap.put("msg", "True");
		sessionmap.put("key", String.valueOf(x));
		sessionmap.put("resetEmail", forMail);
		return "success";
		}
		catch(Exception e){
			System.out.print(e);
			return "failure";
		}
		}
		else{
			try{
			String k=(String)session.getAttribute("key");
			if(k.equals(forOTP)){
				sessionmap.put("resetStatus", "Yes");
				sessionmap.put("msg","False");
				return "resetPass";
			}
			else{
				sessionmap.put("msg","False");
				return "incorrect";
			}
			}
			catch(Exception e){
				System.out.println(e);
				return "failure";
			}
			
		}
		}
}
