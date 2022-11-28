package project;
import java.sql.*;
import java.io.*;
import java.net.*;
import com.ibatis.common.jdbc.ScriptRunner;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
public class ConnectionProvider {
	public static final String mysqlUser=""; //Enter Your Mysql User Here
	public static final String mysqlPassword=""; //Enter Your Mysql Password Here
	public static final String dbName="";
	public static Connection getcon() {
		try {	
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,mysqlUser,mysqlPassword);
			return con;
		}
	
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
}
	public static void populateDB(){
		try{
			System.out.println("Starting to Populate the schema");
			HttpServletRequest request = ServletActionContext.getRequest(); 
    			String url = request.getRequestURI();
			String appName=(url.equals("/"))?"/ROOT/":url;
			String catalinaBase = System.getProperty("catalina.base");
			String scriptLocation = catalinaBase+"/webapps"+appName+"table/schema.sql";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306",mysqlUser,mysqlPassword);
			Statement st=con.createStatement();  
			boolean result=st.execute("create database "+dbName); 
			con.setCatalog(dbName);
			Statement stmt = null;
			ScriptRunner sr = new ScriptRunner(con, false, false);
			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(scriptLocation));
			// Exctute script
			sr.runScript(reader);
			System.out.println("Done Populating the schema");
		
		}
		catch(Exception e){
			System.out.println("Failed to Execute The error is " + e.getMessage());
		}
	}

}
