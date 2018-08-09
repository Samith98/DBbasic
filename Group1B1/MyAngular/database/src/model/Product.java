package model;

public class Product{
	int id;
	String Name;
	String discription;
	int price;
	String category;
	String password;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getdiscription(){
		return discription;
	}
	public void setdiscription(String discription) {
		this.discription = discription;
	}
	public int getprice() {
		return price;
	}
	public void setprice(int price) {
		this.price = price;
	}
	
	public String getcategory() {
		return category;
	}
	public void setcategory(String category) {
		this.category = category;
	}
	
	
	
}
