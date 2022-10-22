package callAction;
import java.sql.*;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import project.ConnectionProvider;

public class addToCartAction implements SessionAware{
	private String idCart;
	SessionMap<String,String> sessionmap;
	public String getIdCart() {
		return idCart;
	}

	public void setIdCart(String idCart) {
		this.idCart = idCart;
	}
	
	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	}  
	
	public String execute() {
		
		int quantity=1;
		int product_price=0;
		int product_total=0;
		int cart_total=0;
		int z=0;
		String idConsumer=sessionmap.get("loginId").toString();
		String emailConsumer = sessionmap.get("email");
		
		try {		
			Connection con=ConnectionProvider.getcon();
			//PreparedStatement lock=con.prepareStatement(")
			PreparedStatement ps=con.prepareStatement("select * from product where prod_id=?");
			ps.setString(1, idCart);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				product_price=rs.getInt(4);
				product_total+=product_price;
			}	
			PreparedStatement ps1=con.prepareStatement("select * from cart where prod_id=? and consumer_id=?;");	
			ps1.setString(1, String.valueOf(idCart));
			ps1.setString(2, String.valueOf(idConsumer));
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next()) {
				cart_total=rs1.getInt(5);
				cart_total+=product_total;
				quantity=rs1.getInt(3)+1;
				z=1;
			}
			if(z==1) {	
				Statement st=con.createStatement();
				st.executeUpdate("update cart set total='"+cart_total+"',quantity='"+quantity+"' where prod_id='"+idCart+"' and email='"+emailConsumer+"';");
				return "exist";
			}
			else {
				Statement st1=con.createStatement();
				
				
				PreparedStatement ps2 = con.prepareStatement("insert into cart (email,consumer_id,prod_id,quantity,price,total,mobileNumber) values (?,?,?,?,?,?,?);");
				ps2.setString(1,emailConsumer);
				ps2.setString(2,idConsumer);
				ps2.setString(3,idCart);
				ps2.setString(4,String.valueOf(quantity));
				ps2.setString(5,String.valueOf(product_price));
				ps2.setString(6,String.valueOf(product_total));
				ps2.setString(7, String.valueOf('0'));
				ps2.executeUpdate();
				ResultSet mobNum=st1.executeQuery("select users.mobileNumber from users,cart where users.id=cart.consumer_id and users.id='"+idConsumer+"'");
				mobNum.next();
				String mobNumber=mobNum.getString(1);
				PreparedStatement ps3=con.prepareStatement("update cart set mobileNumber=?,transactionId=?,paymentMode=? where consumer_id=?;" );
				ps3.setString(1, mobNumber);
				ps3.setString(2," ");
				ps3.setString(3," ");
				ps3.setString(4,idConsumer);
				ps3.execute();
				return "added";
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return "failed";
		}
	}
	
	public String decreaseQuantity() {
		try {
			Connection con=ConnectionProvider.getcon();
			String idConsumer=sessionmap.get("loginId").toString();
			PreparedStatement ps=con.prepareStatement("update cart set quantity = quantity - 1,total = total - price where prod_id=? and consumer_id=?;");
			ps.setString(1, idCart);
			ps.setString(2, idConsumer);
			try {ps.execute();}	catch(Exception e) {return "invalid";}
			return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
	}
	
	public String removeProduct() {
		try {
			Connection con=ConnectionProvider.getcon();
			String idConsumer=sessionmap.get("loginId").toString();
			PreparedStatement ps=con.prepareStatement("delete from cart where consumer_id=? and prod_id=?");
			ps.setString(1, idConsumer);
			ps.setString(2, idCart);
			ps.execute();
			return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
	}
}
