package models;

public class Order {
	private int id;
	public static int idCounter = 1001;
	private String productName;
	private int quantity;
	private String clientName;
 	private MyDate pursacheDate;
	private double baseValue;
	private int discount;
	private double totalValue;

	public Order(String productName, int quantity,String clientName, MyDate pursacheDate,
		double baseValue,int discount,double totalValue) {
		this.id = idCounter++;
		this.productName = productName;
		this.quantity = quantity;
		this.clientName = clientName;
		this.pursacheDate = pursacheDate;
		this.baseValue = baseValue;
		this.discount = discount;
		this.totalValue = totalValue;
	}
	
	public Order(int id,String productName, int quantity,String clientName, MyDate pursacheDate,
			double baseValue,int discount,double totalValue) {
			idCounter++;
			this.id = id;
			this.productName = productName;
			this.quantity = quantity;
			this.clientName = clientName;
			this.pursacheDate = pursacheDate;
			this.baseValue = baseValue;
			this.discount = discount;
			this.totalValue = totalValue;
		}
	
	public int getId() {
		return id;
	}
	
	public String getProductName() {
		return productName;
	}

	public String getClientName() {
		return clientName;
	}

	public int getQuantity() {
		return quantity;
	}

	public MyDate getPursacheDate() {
		return pursacheDate;
	}

	public double getBaseValue() {
		return baseValue;
	}

	public int getDiscount() {
		return discount;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public String[] toStringVector() {
		String[] order= {""+productName,
				""+clientName,""+quantity,""+pursacheDate,
				""+baseValue,
//				""+(discount*baseValue),
				""+discount,
				""+totalValue};
		return order;
	}
	public String toString() {
		return productName+","+quantity+","+clientName+
				","+pursacheDate+","+baseValue+","+
				discount+","+totalValue;
	}

}
