package callAction;
import java.sql.*;
import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import project.ConnectionProvider;


public class addNewProductAction implements SessionAware {
	private String prodName;
	private String prodCategory;
	private String prodPrice;
	private String prodActive;
	
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}
	public String getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdActive() {
		return prodActive;
	}
	public void setProdActive(String prodActive) {
		this.prodActive = prodActive;
	}

	SessionMap<String,String> sessionmap;  
	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	   
	}  
	public String execute(){
		try {
		int id;
		String prodID = null;
		Connection con=ConnectionProvider.getcon();
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery("select max(prod_id) from product");
		while(rs.next()) {	
			id=rs.getInt(1);
			if(id==0)
				id=999;
			
			id+=1;
			prodID=String.valueOf(id);
		}
		sessionmap.put("prodID", prodID);

		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "success";
	}
	
	public String addToDB() {
		try {
			int newid=Integer.parseInt((String)sessionmap.get("prodID"));
			newid++;
			sessionmap.put("prodID",String.valueOf(newid));
			String loginId=sessionmap.get("loginId");
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps=con.prepareStatement("insert into product(name,category,price,active,supplier_ID) values(?,?,?,?,?);");
			ps.setString(1,prodName);
			ps.setString(2, prodCategory);
			ps.setString(3, prodPrice);
			ps.setString(4, prodActive);
			ps.setString(5, loginId);
			ps.executeUpdate();
			return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		
	}

}
