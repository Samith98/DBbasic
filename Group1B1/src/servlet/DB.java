package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Product;

public class DB {

	List<User> userList;
	List<Product> allProductList;

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greatoutdoors", "root", "kashi1968");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public List<User> getUser() {

		Connection con = getConnection();

		try {
			Statement statement = con.createStatement();

			ResultSet userSet = statement.executeQuery("select * from user_table");

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
				user.setState(userSet.getString(9));
				user.setPincode(userSet.getInt(10));
				

				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return userList;

	}
	
	public List<Product> getAllProduct(){
		
		Connection con1 = getConnection();

		try {
			Statement statement = con1.createStatement();

			ResultSet productSet = statement.executeQuery("select * from product_tabledb");

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

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return allProductList;
		
	}

}
