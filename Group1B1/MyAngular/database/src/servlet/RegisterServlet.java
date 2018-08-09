package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data;
		String line;
		
		StringBuilder builder = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		
		while((line = reader.readLine())!= null){
			builder.append(line);
		}
		
		data = builder.toString();
		
//----------------------------Convert request to json---------------
		
		JsonParser parse = new JsonParser();
		JsonObject regObj = parse.parse(data).getAsJsonObject();
		
//--------------------------Register User---------------------------
		
		DB dbObj = new DB();
		
		String first_name = regObj.get("firstName").getAsString();
		String last_name = regObj.get("lastName").getAsString();
		String phone = regObj.get("phone").getAsString();
		String email = regObj.get("email").getAsString();
		String password = regObj.get("password").getAsString();
		String address_line1 = regObj.get("addressLine1").getAsString();
		String address_line2 = regObj.get("addressLine2").getAsString();
		String state = regObj.get("state").getAsString();
		String pincode = regObj.get("pincode").getAsString();
		
		int check = dbObj.register(first_name, last_name, phone, email, password, address_line1, address_line2, state, pincode);
	
		
//--------------------Send result in Json format--------------------------//
		
		PrintWriter out = response.getWriter();
		
		JsonObject messageObj1;
		
		if(check == 1) {
			messageObj1 = new JsonObject();
			messageObj1.addProperty("message1", 1);
			out.write(messageObj1.toString());
			System.out.println("Registration Successful");
		} else {
			messageObj1 = new JsonObject();
			messageObj1.addProperty("message1", 0);
			out.write(messageObj1.toString());
			System.out.println("Registration Unsuccessful");
		}
	}

}