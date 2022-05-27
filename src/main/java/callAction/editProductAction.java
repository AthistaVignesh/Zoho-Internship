package callAction;
import project.ConnectionProvider;
import java.sql.*;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
public class editProductAction implements SessionAware{
	SessionMap<String,String> sessionmap;
	private String idForEdit;
	private String editActive;
	private String editName;
	private String editPrice;
	private String editCategory;
	private String prodIdForDelete;
	

	public String getEditName() {
		return editName;
	}

	public void setEditName(String editName) {
		this.editName = editName;
	}

	public String getEditActive() {
		return editActive;
	}

	public void setEditActive(String editActive) {
		this.editActive = editActive;
	}

	public String getEditPrice() {
		return editPrice;
	}

	public void setEditPrice(String editPrice) {
		this.editPrice = editPrice;
	}

	public String getEditCategory() {
		return editCategory;
	}

	public void setEditCategory(String editCategory) {
		this.editCategory = editCategory;
	}

	public String getIdForEdit() {
		return idForEdit;
	}

	public void setIdForEdit(String idForEdit) {
		this.idForEdit = idForEdit;
	}
	
	public void setSession(Map map) {  
	    sessionmap=(SessionMap)map;  
	}

	public String execute() {
		System.out.println("check"+"\t\t\t"+idForEdit);
		JSONObject colDetails=new JSONObject();
		try {
		Connection con=ConnectionProvider.getcon();
		PreparedStatement ps=con.prepareStatement("Select name,category,price,active from product where prod_id=?");
		ps.setString(1,idForEdit);
		ResultSet rs=ps.executeQuery();
		ResultSetMetaData rsmd=rs.getMetaData();
		while(rs.next()) {
			int colsize=rsmd.getColumnCount();		
			for(int i=1;i<=colsize;i++) {
				String colName= rsmd.getColumnName(i);
                colDetails.put(colName,rs.getObject(colName));
				
			}
		}
		sessionmap.put("colDetails", colDetails.toString());
		sessionmap.put("idForEdit", idForEdit);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "success";
	}

	public String changeProdDetails() {
		try {
		Connection con=ConnectionProvider.getcon();
		String idForEdit=sessionmap.get("idForEdit");
		PreparedStatement ps=con.prepareStatement("update product set price=?,name=?,category=?,active=? where prod_id=?");
		ps.setString(1, editPrice);
		ps.setString(2, editName);
		ps.setString(3, editCategory);
		ps.setString(4, editActive);
		ps.setString(5, idForEdit);
		int i=ps.executeUpdate();
		
		return "success";
		}
		catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		
	}
	private ResultSetMetaData getMetaData() {
		return null;
	}
	
	public String deleteProduct() {
		String idForEdit=sessionmap.get("idForEdit");
		try {
			Connection con=ConnectionProvider.getcon();
			PreparedStatement ps1=con.prepareStatement("delete from product where prod_id=?");
			
			ps1.setString(1, idForEdit);
			
			ps1.execute();
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		return "success";
	}
}
