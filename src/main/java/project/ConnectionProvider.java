package project;
import java.sql.*;
public class ConnectionProvider {
	public static Connection getcon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineapp","root","Athista123!");
			return con;
		}
	
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
}
}
