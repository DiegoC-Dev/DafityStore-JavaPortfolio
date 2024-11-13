package models;

import views.Constants;

public class Product {
	private int id;
	public static int idCounter = 1001;
	private String name;
	private double price;
	private int discountPercentage;
	private String size;
	private int stock;
	private String imgPath;

	public Product(String name, double price, int discountPercentage, String size, int stock) {
		this.id = idCounter++;
		this.name = name;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.size = size;
		this.stock = stock;
//		this.imgPath = "./db/img/xiaomi redmi note 8.jpeg";
		this.imgPath = Constants.MAIN_IMAGE_PRODUCT;
	}

	public Product(int id, String name, double price, int discountPercentage, String size, int stock) {
		idCounter++;
		this.id = id;
		this.name = name;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.size = size;
		this.stock = stock;
		this.imgPath = Constants.MAIN_IMAGE_PRODUCT;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Product(String name) {
		this.name = name;
	}

	public void reduceStock() {
		stock--;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getFinalPrice() {
		return price - (price * ((double) discountPercentage / 100));
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public String getSize() {
		return size;
	}

	public int getStock() {
		return stock;
	}

	public String getImgPath() {
		return imgPath;
	}

	public String toString() {
		return name + "," + price + "," + discountPercentage + "," + size + "," + stock + "," + imgPath;
	}

	public String[] toStringVector() {
		String[] vector = { name, "" + price, "" + discountPercentage, "" + size, "" + stock };
		return vector;
	}
}