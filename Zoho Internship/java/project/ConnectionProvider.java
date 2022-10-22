package project;
import java.sql.*;
public class ConnectionProvider {
	public static Connection getcon() {
		try {	
			//Remember to create the database onlineapp
			String mysqlUser=""; //Enter Your Mysql User Here
			String mysqlPassword=""; //Enter Your Mysql Password Here
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineapp",mysqlUser,mysqlPassword);
			return con;
		}
	
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
}
}
