package presenters;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import models.Client;
import models.Manager;
import models.Order;
import models.Product;
import util.Convert;
import models.ModelConstants;
import models.MyDate;
import views.MainWindow;


public class Presenter implements ActionListener{
	private Manager modelDao;
	private MainWindow mainWindow; 

	public Presenter() throws SQLException {
		modelDao = new Manager();
		try {
			mainWindow = new MainWindow(this,modelDao.getProductList(),
					modelDao.getClientList(),modelDao.getOrderList(),
					modelDao.getLocationList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		searchCoordinates();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Event.valueOf(e.getActionCommand())) {
		case BUY:
			try {
				getCardData(e);
			} catch (IOException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			break;
		case SHOW_ADD_PRODUCT:
			showAddProduct();
			break;
		case ADD_PRODUCT:
			try {
				addProduct();
			} catch (SQLException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
			break;		
		case SHOW_UPDATE_PRODUCT:
			showUpdateProduct();
			break;
		case UPDATE_PRODUCT:
			try {
				updateProduct();
			} catch (Exception e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			break;
		case CHANGE_SELECTION:
			changeSelection();			
			break;
		case SHOW_DELETE_PRODUCT:
			showDeleteProduct();
			break;
		case DELETE_PRODUCT:
			try {
				deleteProduct();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;	
		case SHOW_PRODUCT_LIST:
			showProductList();
			break;
		case JP_ACCESS:
			access();
			break;
		case SHOW_REGISTRATION:
			mainWindow.showPanel(Event.JP_REGISTRATION.toString());							
			break;
		case JP_REGISTRATION:
			try {
				registration();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}				
			break;
		case JP_LOGIN:
			login();
			break;
		case JP_ACCESS_MANAGER:
			accessManager();
			break;	
		case TOP5_CLIENTS:
			try {
				top3Clients();
			} catch (SQLException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			break;
		case TOP5_PRODUCTS:
			try {
				top3Products();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			break;
		case TOP5_MAX_PRICE_PRODUCT:
			try {
				top3MaxProductPrice();
			} catch (SQLException e4) {
				e4.printStackTrace();
			}
			break;	
		case TOP5_CLIENTS_BY_REGION:
			try {
				top3ClientsByRegions();
			} catch (SQLException e4) {
				e4.printStackTrace();
			}
			break;
		case SALES_REPORT:
			mainWindow.showPanel(Event.SALES_REPORT.toString());
			break;
		case CLIENTS_REPORT:
			showClientList();
			break;
		case SHOW_PROFILE:
			showProfile();
			break;
		case JP_ACCESS_CLIENT:
			accessClient();
			break;
		case SHOW_UPDATE_CLIENT:
			showUpdateClient();
			break;
		case UPDATE_CLIENT:
			try {
				updateClient();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			break;
		case SHOW_DELETE_CLIENT:
			showDeleteClient();
			break;	
		case DELETE_CLIENT:
			try {
				deleteClient();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void accessClient() {
		mainWindow.placeDCError(ModelConstants.NONE_ERROR.toString());
		mainWindow.showPanel(Event.JP_ACCESS_CLIENT.toString());
	}

	private void top3Products() throws SQLException {
		String[] topProducts = modelDao.getTopProductList().split("[|]");
		String[] names = topProducts[0].split("[°]");
		int[] values = Convert.StringToint(topProducts[1].split("[°]"));
		
		mainWindow.refreshBGTopProducts(new String[]{"Unidades vendidas","Vs","Productos"},
				new String[] {"Unidades ","Productos"},
				names, 
				values);
		mainWindow.showPanel(Event.TOP5_PRODUCTS.toString());
	}

	private void top3Clients() throws SQLException {
		String[] topClients = modelDao.getTopClientList().split("[|]");
		String[] names = topClients[0].split("[°]");
		int[] values = Convert.StringToint(topClients[1].split("[°]"));

		mainWindow.refreshBGTopClient(new String[]{"Unidades adquiridas","Vs","Clientes"},
				new String[] {"Cantidad ","Clientes"},
				names, 
				values);
		mainWindow.showPanel(Event.TOP5_CLIENTS.toString());
	}
	
	private void top3ClientsByRegions() throws SQLException {
		String[] topClientsByRegion = modelDao.getTopClientByRegion().split("[|]");
		String[] names = topClientsByRegion[0].split("[°]");
		int[] values = Convert.StringToint(topClientsByRegion[1].split("[°]"));

		mainWindow.refreshBGTopClient(new String[]{"Numero de clintes","Vs","Regiones"},
				new String[] {"Cantidad ","Regiones"},
				names, 
				values);
		mainWindow.showPanel(Event.TOP5_CLIENTS.toString());
	}

	private void top3MaxProductPrice() throws SQLException {
		String[] topMaxPriceProducts = modelDao.getTopMaxPriceProduct().split("[|]");
		String[] names = topMaxPriceProducts[0].split("[°]");
		int[] values = Convert.StringToint(topMaxPriceProducts[1].split("[°]"));

		mainWindow.refreshBGTopClient(new String[]{"Precios","Vs","Productos"},
				new String[] {"Precios ","Productos"},
				names, 
				values);
		mainWindow.showPanel(Event.TOP5_CLIENTS.toString());
	}


	private void registration() throws SQLException {
		try {
			String[] data = mainWindow.getClientR();
			Client client = modelDao.createClient(data);
			String validation = modelDao.verifyRegistration(client);
			mainWindow.placeRError(validation);
			if (validation.equals(ModelConstants.NONE_ERROR.toString())) {
				updateProducts(modelDao.getProductList());
				mainWindow.showPanel(Event.JP_LOGIN.toString());	
				mainWindow.showDialog("Registro completado");
			}			
		} catch (NumberFormatException | IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	private void showDeleteClient() {
		mainWindow.hideManagerMenu();
		mainWindow.showPanel(Event.SHOW_DELETE_CLIENT.toString());
	}

	private void showUpdateClient() {
		mainWindow.hideClientMenu();
		try {
			mainWindow.refreshUpdateProduct(
					modelDao.searchClient(modelDao.getCurrentUser()));
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		mainWindow.showPanel(Event.SHOW_UPDATE_CLIENT.toString());
	}

	private void showProfile() {
		mainWindow.hideClientMenu();
		ArrayList<Client> auxList = new ArrayList<Client>();
		try {
			auxList.add(modelDao.searchClient(modelDao.getCurrentUser()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		mainWindow.repaintClientsTable(auxList,
				Event.JP_ACCESS_CLIENT.toString());
		mainWindow.showPanel(Event.SHOW_CLIENT_LIST.toString());
	}
	
	private void showClientList() {
		mainWindow.hideClientMenu();
//		ArrayList<Client> auxList = model
		
//		ArrayList<Client> auxList = new ArrayList<Client>();
//		try {
//			auxList.add(modelDao.searchClient(modelDao.getCurrentUser()));
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		mainWindow.repaintClientsTable(modelDao.getClientList(),
				Event.JP_ACCESS_MANAGER.toString());
		mainWindow.showPanel(Event.SHOW_CLIENT_LIST.toString());
	}

	private void accessManager() {
		mainWindow.cleanFormAProduct();
		mainWindow.placeAPError(ModelConstants.NONE_ERROR.toString());
		mainWindow.showPanel(Event.JP_ACCESS_MANAGER.toString());
	}

	private void login() {
//		mainWindow.placeRError(false);
		mainWindow.placeRError(ModelConstants.NONE_ERROR.toString());
		mainWindow.resetFormLog();
		mainWindow.resetFormR();
		mainWindow.showPanel(Event.JP_LOGIN.toString());
	}

	private void showProductList() {
		mainWindow.hideManagerMenu();
		mainWindow.repaintProductsTable(modelDao.getProductList());
		mainWindow.showPanel(Event.SHOW_PRODUCT_LIST.toString());
	}

	private void showDeleteProduct() {
		mainWindow.hideManagerMenu();
		try {
			mainWindow.refreshDeleteProduct(modelDao.getProductList());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mainWindow.showPanel(Event.SHOW_DELETE_PRODUCT.toString());
	}

	private void changeSelection() {
		int index;
		try {
			index = modelDao.getIndexProduct(mainWindow.getUPSelectedItem());
			mainWindow.refreshUpdateProduct(modelDao.getProductList(),index);
		} catch (IOException e3) {
			e3.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void showUpdateProduct() {
		mainWindow.hideManagerMenu();
		try {
			mainWindow.refreshUpdateProduct(modelDao.getProductList(),0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mainWindow.showPanel(Event.SHOW_UPDATE_PRODUCT.toString());
	}

	private void showAddProduct() {
		mainWindow.hideManagerMenu();
		mainWindow.showPanel(Event.SHOW_ADD_PRODUCT.toString());
	}
	
	private void access() {
		String user = mainWindow.getUser();
		String password = mainWindow.getPassword();
		String access = modelDao.verifyAccess(user, password);
		mainWindow.placeLoginError(access);
		if(access.equals(ModelConstants.ACC_CLIENT.toString())) 
			mainWindow.showPanel(Event.JP_ACCESS_CLIENT.toString());
		else if(access.equals(ModelConstants.ACC_MANAGER.toString())) 
			mainWindow.showPanel(Event.JP_ACCESS_MANAGER.toString());			
	}
	
	private void getCardData(ActionEvent e) throws IOException {
		try {
			Product product = modelDao.searchProduct(getProfileId(e));
			Client client = modelDao.searchClient(modelDao.getCurrentUser());
			Order order = new Order(product.getName(), 1,
					client.getFullName(),
					new MyDate(),product.getPrice(),
					product.getDiscountPercentage(),
					product.getFinalPrice());
			modelDao.addOrder(order);
			
			modelDao.insertOrder(order.getId(),client.getId(),
					new Date(order.getPursacheDate().toString()),
					order.getBaseValue(), 
					order.getDiscount(), 
					order.getTotalValue());
			modelDao.insertPromocion(order.getId(), order.getDiscount(),
					1);
			modelDao.insertDetalle(order.getId(), product.getId(),
					order.getId(), 1, product.getPrice());
			
			modelDao.alterStockProd(product.getId(), product.getStock());
			mainWindow.showDialog("Compra exitosa");
			

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		mainWindow.repaintOrderTable(modelDao.getOrderList());
		updateProducts(modelDao.getProductList());
//		orderWindow.repaintTable(modelDao.getOrderList());
//			pursacheWindow.setVisible(false);
	}
	private String getProfileId(ActionEvent e) {
//		return Integer.parseInt(((JButton)e.getSource()).getName());
		return (((JButton)e.getSource()).getName());
	}
	
	private void addProduct() throws SQLException {
		try {
			String data[] = mainWindow.getAProduct();
			Product product = modelDao.createProduct(data);
			String error = modelDao.verifyAddProduct(product);
			mainWindow.placeAPError(error);
			if(error.equals(ModelConstants.NONE_ERROR.toString())) {
				updateProducts(modelDao.getProductList());
				mainWindow.showPanel(Event.JP_ACCESS_MANAGER.toString());					
			}
		} catch (NumberFormatException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}	
	}
	private void updateProducts(ArrayList<Product> productList) throws IOException {
		mainWindow.refreshProductList(this, productList);
		mainWindow.repaintProductsTable(productList);
		mainWindow.refreshUpdateProduct(productList,0);
		mainWindow.refreshDeleteProduct(productList);
	}
//	private void refreshList() throws IOException {
//		mainWindow.refreshProductList(this, modelDao.getClientList(),modelDao.getProductList());
//	}
	
	private void updateProduct() throws Exception {
		try {
			String data[]= mainWindow.getUProduct();
			Product product = modelDao.updateProduct(data);
			String name = mainWindow.getNameUP();
			String error = modelDao.verifyUpdateProduct(product,data,name);
			System.out.println("error.."+error);
			mainWindow.placeUPError(error);
			if(error.equals(ModelConstants.NONE_ERROR.toString())) {
					updateProducts(modelDao.getProductList());
					mainWindow.showPanel(Event.JP_ACCESS_MANAGER.toString());
					mainWindow.showDialog("Producto actualizado");
			}		} catch (NumberFormatException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	private void updateClient() throws Exception {
		try {
			String data[] = mainWindow.getClient();
			Client newClient = modelDao.createClient(data);
			String oldUser = mainWindow.getInitUser().getUser(); 
			String validation = modelDao.verifyUpdateClient(newClient,oldUser);
			mainWindow.placeUCError(validation);
			if (validation.equals(ModelConstants.NONE_ERROR.toString())) {
				updateProducts(modelDao.getProductList());
				mainWindow.showPanel(Event.JP_ACCESS_CLIENT.toString());
				mainWindow.showDialog("Perfil actualizado");
			}
		} catch (NumberFormatException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
		
	private void deleteProduct() throws Exception {
		modelDao.deleteProduct(modelDao.getIndexProduct(mainWindow.getDPName()));
		mainWindow.showPanel(Event.JP_ACCESS_MANAGER.toString());
	}
	private void deleteClient() throws Exception {
		mainWindow.resetFormLog();
		String id = mainWindow.getDCpassword();
		Client currentClient = modelDao.searchClient(modelDao.getCurrentUser());
		String validation = modelDao.verifyDeleteClient(currentClient, id);
		mainWindow.placeDCError(validation);	
		if (validation.equals(ModelConstants.NONE_ERROR.toString())) {
			mainWindow.showPanel(Event.JP_LOGIN.toString());
			mainWindow.showDialog("Cliente eliminado");
		}
	}
	
	/////////
//presentador	
	private void searchCoordinates() {
		mainWindow.addComponentListener(new ComponentAdapter() {
			int x=0;
			int y=0;
			@Override
			public void componentMoved(ComponentEvent e) {
				super.componentMoved(e);
//			System.out.println(jFmainFrame.getLocationOnScreen().getX());
				x=(int) mainWindow.getLocationOnScreen().getX(); 
				y=(int) mainWindow.getLocationOnScreen().getY(); 
				sendCoordinates(new Point(x, y));
			}
		});
	}
	private void sendCoordinates(Point point2d) {
		mainWindow.sendCoordinates(point2d);
	}

}