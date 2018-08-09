package servlet;

import java.util.*;
import java.sql.*;

import model.Product;
import model.User;

public class DB {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/greatoutdoorsdb";
	String userName = "root";
	String password = "samith@12";
	String query = "select * from user";
	String query1 = "select * from user where email=?, password=?";
	String query2 =  "insert into product_table values(?,?,?,?,?)";
	
	List<User> userList;
	List<Product> allProductList;
	
	public Connection getConnection(){
		
		Connection con = null;
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, userName, password);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
		
	}

	public List<User> getUser(){
		
		Connection con = getConnection();
		
		try {
			
			Statement statement = con.createStatement();
			
			ResultSet userSet = statement.executeQuery(query);
			
			userList = new ArrayList<User>();
			
			while(userSet.next()){
				
				User user = new User();
				
				user.setId(userSet.getInt(1));
				user.setFirstName(userSet.getString(2));
				user.setLastName(userSet.getString(3));
				user.setPhone(userSet.getInt(4));
				user.setEmail(userSet.getString(5));
				user.setPassword(userSet.getString(6));
				user.setAddressLine1(userSet.getString(7));
				user.setAddressLine2(userSet.getString(8));
				user.setState(userSet.getString(9));
				user.setPincode(userSet.getInt(10));
				
				userList.add(user);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
		
		return userList;
		
	}
	
	public List<Product> getAllProduct(){
		
			Connection con1 = getConnection();
			
			try {
				Statement statement = con1.createStatement();

				ResultSet productSet = statement.executeQuery("select * from product_table");

				allProductList = new ArrayList<Product>();

				while (productSet.next()) {

					Product product = new Product();

					product.setId(productSet.getInt(1));
					product.setName(productSet.getString(2));
					product.setDescription(productSet.getString(3));
					product.setPrice(productSet.getInt(4));
					product.setCategory(productSet.getString(5));

					allProductList.add(product);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			return allProductList;
		
		}
	
	public int login(String email, String password){
		
		int i=0;
		Connection con2 = getConnection();
		
		try {
			
			PreparedStatement ps = con2.prepareStatement(query1);
			
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				i++;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return i;
		
	}
	
	public void register(String first_name, String last_name, 
						String phone, String email, String password1, String address_line1, 
						String address_line2, String state, String pincode){
		
		Connection con3 = getConnection();
		
		try {
			
			PreparedStatement ps1 = con3.prepareStatement(query2);
			
			ps1.setString(2, first_name);
			ps1.setString(3, last_name);
			ps1.setString(4, phone);
			ps1.setString(5, email);
			ps1.setString(6, password1);
			ps1.setString(7, address_line1);
			ps1.setString(8, address_line2);
			ps1.setString(9, state);
			ps1.setString(10, pincode);
			
			ps1.executeUpdate();
			
			System.out.println("Registration Successful");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
}
