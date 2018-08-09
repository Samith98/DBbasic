package servlet;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.User;
import model.Product;

public class JSONManager {

	private static final String Null = null;
	
	public String getuserJSON(List<User> userList) {

		JsonArray userArray = new JsonArray();

		for (User user : userList) {

			JsonObject jsonObject = new JsonObject();

			jsonObject.addProperty("id", user.getId());
			jsonObject.addProperty("firstName", user.getFirstName());
			jsonObject.addProperty("lastName", user.getLastName());
			jsonObject.addProperty("phoneNumber", user.getPhoneNumber());
			jsonObject.addProperty("email", user.getEmail());
			jsonObject.addProperty("password", user.getPassword());
			jsonObject.addProperty("addressLine1", user.getAddressLine1());
			jsonObject.addProperty("addressLine2", user.getAddressLine2());
			
			userArray.add(jsonObject);
			}
		return null ;

		}
		public String getproductJSON(List<Product> productList) {

			JsonArray productArray = new JsonArray();

			for(Product product : productList) {

				JsonObject jsonObject = new JsonObject();

				jsonObject.addProperty("id", product.getId());
				jsonObject.addProperty("Name", product.getName());
				jsonObject.addProperty("discription", product.getdiscription());
				jsonObject.addProperty("price", product.getprice());
				jsonObject.addProperty("category", product.getcategory());
				
				
				productArray.add(jsonObject);

			}

		return productArray.toString();

	}
}