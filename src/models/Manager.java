package models;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import net.ConnectionMySQL;

public class Manager {
	private static final String ERROR_ID_NOT_FOUND = "Id not found!";
	private static final String ERROR_NAME_NOT_FOUND = "Name not found!";

	private ArrayList<Client> managerList;
	private ArrayList<Product> productList;
	private ArrayList<Order> orderList;
	private ArrayList<Client> clientList;
	private ConnectionMySQL connMySQL;

	private String currentUser;
	private ArrayList<Location> locationList;

	public Manager() throws SQLException {
		currentUser = "";
		productList = new ArrayList<Product>();
		clientList = new ArrayList<Client>();
		orderList = new ArrayList<Order>();
		managerList = new ArrayList<Client>();
		locationList = new ArrayList<Location>();

		 loadData();

//		connMySQL = new ConnectionMySQL("jdbc:mysql://localhost/Dafity_DB?user=user&password=123");
		connMySQL = new ConnectionMySQL("jdbc:mysql://localhost/Dafity_DB?user=root&password=");
		createDBTables();
	}

	private void loadData() {
		Client steven = new Client("Steven", "KingSteven", "Steven", "King", "CC", 100, "M", new MyDate(17, 06, 93));
		Client neena = new Client("Neena", "KochharNeena", "Neena", "Kochhar", "CC", 101, "F", new MyDate(17, 06, 93));
		Client lex = new Client("Lex", "DeHaanLex", "Lex", "De Haan", "CC", 102, "M", new MyDate(17, 06, 93));
		Client alexander = new Client("Alexander", "HunoldAlexander", "Alexander", "Hunold", "CC", 103, "M",
				new MyDate(17, 06, 93));
		Client bruce = new Client("Bruce", "ErnstBruce", "Bruce", "Ernst", "CC", 104, "M", new MyDate(17, 06, 93));
		Client user = new Client("User", "Password", "Bruce", "Ernst", "CC", 104, "M", new MyDate(17, 06, 93));
		Client admin = new Client("Admin", "Password", "Bruce", "Ernst", "CC", 104, "M", new MyDate(17, 06, 93));

		Product busoEvS = new Product("buso evidence S", 84000, 5, "S", 3);
		Product busoEvM = new Product("buso evidence M", 84000, 5, "M", 6);
		Product busoEvL = new Product("buso evidence L", 84000, 5, "L", 4);

		Location sogamoso = new Location(100, "sogamoso");
		Location duitama = new Location(100, "duitama");
		Location tunja = new Location(100, "tunja");

		productList.add(busoEvS);
		productList.add(busoEvM);
		productList.add(busoEvL);

		clientList.add(steven);
		clientList.add(neena);
		clientList.add(lex);
		clientList.add(user);

		orderList.add(new Order(busoEvL.getName(), 1, bruce.getFullName(), new MyDate(9, 5, 21), busoEvL.getPrice(),
				busoEvL.getDiscountPercentage(), busoEvL.getFinalPrice()));
		orderList.add(new Order(busoEvS.getName(), 2, bruce.getFullName(), new MyDate(9, 5, 21), busoEvS.getPrice(),
				busoEvS.getDiscountPercentage(), busoEvS.getFinalPrice()));
		orderList.add(new Order(busoEvS.getName(), 2, alexander.getFullName(), new MyDate(9, 5, 21), busoEvS.getPrice(),
				busoEvS.getDiscountPercentage(), busoEvS.getFinalPrice()));
		orderList.add(new Order(busoEvS.getName(), 2, bruce.getFullName(), new MyDate(9, 5, 21), busoEvS.getPrice(),
				busoEvS.getDiscountPercentage(), busoEvS.getFinalPrice()));

		managerList.add(alexander);
		managerList.add(bruce);
		managerList.add(admin);

		locationList.add(sogamoso);
		locationList.add(duitama);
		locationList.add(tunja);
	}

	private void createDBTables() throws SQLException {
		ArrayList<String[]> aux = connMySQL.getCientes();
		String[] data;
		for (int i = 0; i < aux.size(); i++) {
			data = aux.get(i);
			if (Integer.parseInt(data[0]) == 900) {
				managerList.add(new Client(Integer.parseInt(data[0]), data[6], data[7], data[1], data[2], data[3],
						Integer.parseInt(data[4]), data[8], new MyDate(data[5])));
			} else {
				clientList.add(new Client(Integer.parseInt(data[0]), data[6], data[7], data[1], data[2], data[3],
						Integer.parseInt(data[4]), data[8], new MyDate(data[5])));
			}
		}
		int randomDiscount;
		aux = connMySQL.getProducts();
		for (int i = 0; i < aux.size(); i++) {
			data = aux.get(i);
			randomDiscount = (int) Math.floor(Math.random() * (7 - 1 + 1) + 1);
			productList.add(new Product(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[3]), randomDiscount,
					data[4], Integer.parseInt(data[2])));
		}
//		System.out.println("busqueda..."+
//				searchProduct("Converse")==null?"nullo":"bien");
//		Product n = searchProduct(800);
//				System.out.println("busqueda..."+searchProduct("Converse").getName());
//				System.out.println("busqueda..."+n.getName());
//		for (int i = 0; i < productList.size(); i++) {
//			System.out.println("Nombres..."+productList.get(i).getName());
//			System.out.println("Codigos..."+productList.get(i).getId());
//		}

		aux = connMySQL.getCompras();
		for (int i = 0; i < aux.size(); i++) {
			data = aux.get(i);
			orderList.add(new Order(Integer.parseInt(data[0]), data[7], Integer.parseInt(data[9]), data[14],
					new MyDate(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4]),
					Double.parseDouble(data[5])));
//			}
		}
		System.out.println();
		aux = connMySQL.getUbicaciones();
		for (int i = 0; i < aux.size(); i++) {
			data = aux.get(i);
			locationList.add(new Location(Integer.parseInt(data[5]), data[6]));
//			}
		}

	}

	public void addOrder(Order order) throws Exception {
		orderList.add(order);

		Product product = searchProduct(order.getProductName());
//		System.out.println(product);
		if (product.getStock()-1 != 0)
			product.reduceStock();
		else {
//			Product.idCounter--;
			deleteProduct(getIndexProduct(product.getName()));
		}
	}

	public void insertPromocion(int idPromocion, int descuento, int stock) throws SQLException {
		connMySQL.insertPromocion(idPromocion, descuento, stock);
	}

	public void insertDetalle(int idFactura, int idProducto, int idPromocion, int idCantidad, double precio)
			throws SQLException {
		connMySQL.insertDetalle(idFactura, idProducto, idPromocion, idCantidad, precio);
	}

	public void insertOrder(int idFactura, int idPersona, Date fechaFactura, double precioCompleto,
			int descuentoCompleto, double pagoTotal) throws SQLException {
//		System.out.println("id problema .."+idPersona);
		connMySQL.insertFactura(idFactura, idPersona, fechaFactura, precioCompleto, descuentoCompleto, pagoTotal);
	}

	public void alterStockProd(int idProducto, int newStock) throws SQLException {
		connMySQL.alterStockProd(idProducto, newStock);
	}

	public void deleteBDUser(int idUser) throws SQLException {
		connMySQL.deletUser(idUser);
	}

	public Product searchProduct(String name) {
		for (Product listComponent : productList) {
			if (listComponent.getName().equals(name)) {
				return listComponent;
			}
		}
		return null;
	}

	public Client searchClient(String user) {
		for (Client listComponent : clientList) {
			if (listComponent.getUser().equals(user)) {
				return listComponent;
			}
		}
		return null;
	}
	
	public Client searchClient(int id) {
		for (Client listComponent : clientList) {
			if (listComponent.getId()==id) {
				return listComponent;
			}
		}
		return null;
	}

	public int getIndexProduct(String name) throws Exception {
		int index = -1;
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getName().equals(name)) {
				index = i;
			}
		}
		return index;
	}

	public int getIndexClient(String user) throws Exception {
		int index = -1;
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getUser().equals(user)) {
				index = i;
			}
		}
		return index;
	}
	public int getIndexClient(int id) throws Exception {
		int index = -1;
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getId()==id) {
				index = i;
			}
		}
		return index;
	}
//	public static Product createProduct(String name,double price,String size,
//			int discountPercentage,int stock,String file) throws IOException, URISyntaxException {
//		String path ="";	
//		Product product = null;
//		try {
//			if(!file.equals("")) {				
//				path = "./db/img/" + Product.idCounter+1
//						+ file.substring(file.lastIndexOf("."), file.length());
//				Files.copy(Paths.get(file), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
//			}else 
//				Product.idCounter--;
//			product = new Product(name, price, discountPercentage,size,stock);
//		} catch (NoSuchFileException | StringIndexOutOfBoundsException e) {
//			product = new Product(name, price, discountPercentage,size,stock);
//		}
//	return product;
//	}
	public void addProduct(Product product) throws SQLException {
		productList.add(product);

		connMySQL.insertProducto(product.getId(), product.getName(),
				product.getStock(), product.getPrice(), product.getSize());
	}
	
	public void addClient(Client client) throws SQLException {
		clientList.add(client);
		connMySQL.insertClient(client.getId(), client.getFirstName(), client.getLastName(), client.getDocumentType(),
				client.getDocumentId(), new Date(client.getBirthDateSt()), client.getUser(), client.getPassword(),
				cityNameToId(client.getCityName()));
	}
	public int cityNameToId(String name) {
		for (int i = 0; i < locationList.size(); i++) {
			if(locationList.get(i).getName().equals(name))
				return locationList.get(i).getId();
		}
		return -1;
	}

	public void setProduct(Product product, String name) throws Exception {
		int idProduct = searchProduct(name).getId();
		Product newProduct = product;
		product.setId(idProduct);
		
		productList.set(getIndexProduct(name), newProduct);
		alterProducto(idProduct, product.getName(), product.getStock(), product.getPrice(), product.getSize());
	}

//	public static Client updateClient(String user,String password) throws IOException, URISyntaxException {//ACTUALIZAR--|	
//			return new Client(user,password);
//	}
	public void setClient(Client client, String oldUser) throws Exception {
		int idClient = searchClient(oldUser).getId();
		Client newClient = client;
//		newClient.setCityName(""+cityNameToId(client.getCityName()));
		clientList.set(getIndexClient(oldUser), newClient);
		
//		System.out.println("llega asi...."+client.getCityName());
//		System.out.println("lo convierto...."+cityNameToId(client.getCityName()));
		

		connMySQL.alterUser(idClient,client.getFirstName(), client.getLastName(),
				client.getDocumentType(), client.getDocumentId(), 
				new Date(client.getBirthDateSt()), client.getUser(),
				client.getPassword(), 
//				Integer.parseInt(client.getCityName())
				cityNameToId(client.getCityName())
				);
	}

	public void deleteProduct(int index) {
//		Product.idCounter--;
		productList.remove(index);
//		reassignCodes();

	}

	public void deleteClient(int id) throws Exception {
//		Client.idCounter--;
		clientList.remove(getIndexClient(id));
		deleteBDUser(id);
	}

	public boolean alterProducto(int idProducto, String nombreProducto, int newStock, double precio, String talla)
			throws SQLException {
		return connMySQL.alterProducto(idProducto, nombreProducto, newStock, precio, talla);
	}
//	public static Product updateProduct(String name,double price,
//			 int discountPercentage,String size,int stock, String file,int id) throws IOException, URISyntaxException {//ACTUALIZAR--|	
//		String path = "";
//		Product product = null;
//		try {
//			if(!file.equals("")) {			
//				path = "./db/img/" + id
//						+ file.substring(file.lastIndexOf("."), file.length());
//				Files.copy(Paths.get(file), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
//				Product.idCounter--;
//				product = new Product(name, price, discountPercentage,size,stock, path);
//			}
//		} catch (NoSuchFileException | StringIndexOutOfBoundsException e) {
//			product = new Product(name, price, discountPercentage,size,stock, "");
//		}
//		return product;
//	}
//	public void reassignCodes() {
//		for (int i = 0; i < productList.size(); i++) {
//			productList.get(i).setId(i+1);
//		}
//	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public ArrayList<Location> getLocationList() {

		return locationList;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isClient(String user, String password) {
		boolean isClient = false;
		Client currentClient = null;
		for (int i = 0; i < clientList.size(); i++) {
			currentClient = clientList.get(i);
			if (currentClient.getUser().equals(user) && currentClient.getPassword().equals(password)) {
				isClient = true;
			}
		}
		return isClient;
	}

	public boolean isManager(String user, String password) {
		boolean isManager = false;
		Client currentManager = null;
		for (int i = 0; i < managerList.size(); i++) {
			currentManager = managerList.get(i);
			if (currentManager.getUser().equals(user) && currentManager.getPassword().equals(password)) {
				isManager = true;
			}
		}
		return isManager;
	}

	public boolean isUserExist(String user) {
		boolean isExist = false;
		Client currentUser = null;
		for (int i = 0; i < managerList.size(); i++) {
			currentUser = managerList.get(i);
			if (currentUser.getUser().equals(user))
				isExist = true;
		}
		for (int i = 0; i < clientList.size(); i++) {
			currentUser = clientList.get(i);
			if (currentUser.getUser().equals(user))
				isExist = true;
		}
		return isExist;
	}

	public boolean isProductExist(String name) {
		boolean isExist = false;
		Product currentProduct = null;
		for (int i = 0; i < productList.size(); i++) {
			currentProduct = productList.get(i);
			if (currentProduct.getName().toUpperCase().equals(name.toUpperCase())) {
				isExist = true;
			}
		}
		return isExist;
	}

	public Client createClient(String[] data) throws IOException, URISyntaxException {
		if (data[0].equals("") || data[1].equals("") || data[2].equals("") || data[3].equals("") || data[4].equals("")
				|| data[5].equals("") || data[6].equals("") || data[7].equals(""))
			return null;
		else {
			try {
				return new Client(data[0], data[1], data[2], data[3], data[4],
						Integer.parseInt(data[5]), data[6],
						new MyDate(data[7]));
			} catch (NumberFormatException e1) {
				return new Client(data[0], data[1], data[2], data[3], data[4], -1, data[6], new MyDate(data[7]));
			}
		}

	}

	public Product createProduct(String[] data) throws IOException, URISyntaxException {
		if (data[0].equals("") || data[1].equals("") || data[2].equals("") || data[3].equals("") || data[4].equals(""))
			return null;
		else {
			try {
				return new Product(data[0], Double.parseDouble(data[1]),
						Integer.parseInt(data[2]), data[3], Integer.parseInt(data[4]));
//				System.out.println("img path : "+p.getImgPath());
//				return p;
			} catch (NumberFormatException e1) {
				return new Product(data[0], -1, -1, data[3], -1);
			}
		}

	}

//	public Product pathToAddProduct(String name,double price,int discountPercentage,
//			String size,int quantity,String file) throws IOException, URISyntaxException {
//		String path ="";	
//		Product product = null;
////		System.out.println("paso por aca");
//		try {
//			if(!file.equals("")) {				
//				path = "./db/img/" + Product.idCounter+1
//						+ file.substring(file.lastIndexOf("."), file.length());
//				Files.copy(Paths.get(file), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
//			}else {				
//				Product.idCounter--;
//			}
//			product = new Product(name, price, discountPercentage,size, quantity,path);
//		} catch (NoSuchFileException | StringIndexOutOfBoundsException e) {
//			product = new Product(name, price, discountPercentage, size,quantity, "");
//		}
//		return product;
//	}
//	public Product pathToUpdateProduct(String name,double price, 
//			int discountPercentage,String size,int quantity, String file) throws IOException, URISyntaxException {//ACTUALIZAR--|	
//		String path = "";
//		Product product = null;
//		try {
//			if(!file.equals("")) {			
//				path = "./db/img/" + product.idCounter
//						+ file.substring(file.lastIndexOf("."), file.length());
//				Files.copy(Paths.get(file), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
//				Product.idCounter--;
//			}
//			product = new Product(name, price, discountPercentage ,size, quantity,path);
//		} catch (NoSuchFileException | StringIndexOutOfBoundsException e) {
//			product = new Product(name, price, discountPercentage, size,quantity, "");
//		}
//		return product;
//	}
	public Product updateProduct(String[] data) throws IOException, URISyntaxException {
		if (data[0].equals("") || data[1].equals("") || data[2].equals("") || data[3].equals("") ||
				data[4].equals(""))
			return null;
		else {
			try {
				return new Product(data[0], Double.parseDouble(data[1]),
						Integer.parseInt(data[2]), data[3], Integer.parseInt(data[4]));
			} catch (NumberFormatException e1) {
				return new Product(data[0],-1,-1, data[3], -1);
			}
		}
	}

	public String verifyAddProduct(Product product) throws SQLException {
		if (product == null)
			return ModelConstants.EMPTY_ERROR.toString();
		if (isProductExist(product.getName()))
			return ModelConstants.EXIST_ERROR.toString();
		if (product.getPrice() == -1)
			return ModelConstants.NUMBER_ERROR.toString();
		if (product.getImgPath().equals(""))
			return ModelConstants.PATH_ERROR.toString();
		addProduct(product);
		return ModelConstants.NONE_ERROR.toString();
	}

	public String verifyUpdateProduct(Product product, String[] data, String name) throws Exception {
//		System.out.println("bbb.."+searchProduct(name).getId());
		if (product == null)
			return ModelConstants.EMPTY_ERROR.toString();
		if (isProductExist(product.getName()) && !product.getName().equals(name))
			return ModelConstants.EXIST_ERROR.toString();
		if (product.getPrice() == -1)
			return ModelConstants.NUMBER_ERROR.toString();
		if (product.getImgPath().equals(""))
			return ModelConstants.PATH_ERROR.toString();
		setProduct(product, name);
		return ModelConstants.NONE_ERROR.toString();
	}

	public String verifyAccess(String user, String password) {
		if (isClient(user, password)) {
			currentUser = user;
			return ModelConstants.ACC_CLIENT.toString();
		}
		if (isManager(user, password)) {
			currentUser = user;
			return ModelConstants.ACC_MANAGER.toString();
		}
		return ModelConstants.ACC_DENIED.toString();
	}

	public String verifyRegistration(Client client) throws SQLException {
		if (client == null)
			return ModelConstants.EMPTY_ERROR.toString();
		if (isUserExist(client.getUser()))
			return ModelConstants.EXIST_ERROR.toString();
		if (client.getDocumentId() == -1)
			return ModelConstants.NUMBER_ERROR.toString();
		if (client.getBirthDate().getYear() == 0)
			return ModelConstants.DATE_ERROR.toString();
		addClient(client);
		return ModelConstants.NONE_ERROR.toString();
	}

	public String verifyUpdateClient(Client newClient, String oldUser) throws Exception {
		if (newClient == null)
			return ModelConstants.EMPTY_ERROR.toString();
		if (isUserExist(newClient.getUser()) && !newClient.getUser().equals(oldUser))
			return ModelConstants.EXIST_ERROR.toString();
		if (newClient.getDocumentId() == -1)
			return ModelConstants.NUMBER_ERROR.toString();
		if (newClient.getBirthDate().getYear() == 0)
			return ModelConstants.DATE_ERROR.toString();
		setClient(newClient, oldUser);
		setCurrentUser(newClient.getUser());
		return ModelConstants.NONE_ERROR.toString();
	}

	public String verifyDeleteClient(Client currentClient, String IdEntered) throws Exception {
		int newId = verifyIdToDelete(IdEntered);
//		System.out.println("id ingresado..."+newId);
		if (IdEntered.equals(""))
			return ModelConstants.EMPTY_ERROR.toString();
		if (newId == -1)
			return ModelConstants.NUMBER_ERROR.toString();
		if (searchClient(newId)==null)
			return ModelConstants.NOT_EXIST.toString();
		deleteClient(newId);
		return ModelConstants.NONE_ERROR.toString();
	}
	

	private int verifyIdToDelete(String id_Entered) {
		try {
			return Integer.parseInt(id_Entered); 
		} catch (NumberFormatException e1) {
			return -1;
		}
	}

	private void deleteCurrentUser() {
		currentUser = "";
	}
	public String getTopProductList() throws SQLException {
		return getTop(connMySQL.getTopProductos());
	}
	public String getTopClientList() throws SQLException {
		return getTop(connMySQL.getTopClients());
	}
	public String getTopMaxPriceProduct() throws SQLException {
		return getTop(connMySQL.getTopMaxPrecio());
	}
	public String getTopClientByRegion() throws SQLException {
		return getTop(connMySQL.getTopRegiones());
	}
	public String getTop(ArrayList<String []> list) throws SQLException {
		String data;
		String names ="";
		String values ="";
		ArrayList<String []> topProducts = list;
		for (int i = 0; i < topProducts.size(); i++) {
			for (int j = 0; j < topProducts.get(i).length; j++) {
				data = topProducts.get(i)[j];
				if(j==0)
					names+=data+"�";
				else
				values+=data+"�";
			}
		}
		return names+"|"+values;
	}

}