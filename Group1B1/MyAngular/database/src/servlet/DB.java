package servlet;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Product;

public class DB {

	List<User> userList;
	List<Product> productList;
	Connection con4;
	Statement statement;
	PreparedStatement ps5;

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greatoutdoordb", "root", "root");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return con;
	}

	public List<User> getUser() {

		Connection con = getConnection();

		try {
			Statement statement = con.createStatement();

			ResultSet userSet = statement.executeQuery("select * from user");

			userList = new ArrayList<User>();

			while (userSet.next()) {

				User user = new User();

				user.setId(userSet.getInt(1));
				user.setFirstName(userSet.getString(2));
				user.setLastName(userSet.getString(3));
				user.setPhoneNumber(userSet.getInt(4));
				user.setEmail(userSet.getString(5));
				user.setPassword(userSet.getString(6));
				user.setAddressLine1(userSet.getString(7));
				user.setAddressLine2(userSet.getString(8));
				
				

				userList.add(user);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

		return userList;

	}
	
	
	
	
	public List<Product> getProduct() {

		Connection con = getConnection();
		
		try {
			Statement statement = con.createStatement();

			ResultSet productSet = statement.executeQuery("select * from product_table");

			productList = new ArrayList<Product>();

			while (productSet.next()) {

				Product product = new Product();

				product.setId(productSet.getInt(1));
				product.setName(productSet.getString(2));
				product.setdiscription(productSet.getString(3));
				product.setprice(productSet.getInt(4));
				product.setcategory(productSet.getString(5));
				
				productList.add(product);

			}

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		return productList;

	}
	
public int login(String email, String password){
		
		int i=0;
		Connection con2 = getConnection();
		
		try {
			
			PreparedStatement ps = con2.prepareStatement("select * from user where email=? and password=?");
			
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				i=1;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return i;
		
	}
	
	public int register(String first_name, String last_name, 
						String phone, String email, String password1, String address_line1, 
						String address_line2, String state, String pincode){
		
		int j = 0;
		Connection con3 = getConnection();
		
		try {
			
			PreparedStatement ps1 = con3.prepareStatement("insert into user values(id,?,?,?,?,?,?,?,?,?)");
			
	
			ps1.setString(1, first_name);
			ps1.setString(2, last_name);
			ps1.setString(3, phone);
			ps1.setString(4, email);
			ps1.setString(5, password1);
			ps1.setString(6, address_line1);
			ps1.setString(7, address_line2);
			ps1.setString(8, state);
			ps1.setString(9, pincode);
			
			j = ps1.executeUpdate();
			
			System.out.println("Registration Successful");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return j;
		
	}
public List<Product> getAllProduct(){
		
		Connection con1 = getConnection();
		List<Product> allProductList;

		try {
			Statement statement = con1.createStatement();

			ResultSet productSet = statement.executeQuery("select * from product_table");

			allProductList = new ArrayList<Product>();

			while (productSet.next()) {

				Product product = new Product();

				product.setId(productSet.getInt(1));
				product.setName(productSet.getString(2));
				product.setdiscription(productSet.getString(3));
				product.setprice(productSet.getInt(4));
				product.setcategory(productSet.getString(5));

				allProductList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(allProductList);
		return allProductList;
	
	}

public User getUserByEmail(String email) {
	User user = new User();
	con4 = getConnection();
	try {
		ps5 = con4.prepareStatement("select * from user where email=?");
		ps5.setString(1, email);
		ResultSet userSet = ps5.executeQuery();
		while (userSet.next()) {
			user.setId(userSet.getInt(1));
			user.setFirstName(userSet.getString(2));
			user.setLastName(userSet.getString(3));
			user.setPhoneNumber(userSet.getInt(4));
			user.setEmail(email);
			user.setAddressLine1(userSet.getString(7));
			user.setAddressLine2(userSet.getString(8));
			user.setState(userSet.getString(9));
			user.setPincode(userSet.getInt(10));
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return user;

}
}