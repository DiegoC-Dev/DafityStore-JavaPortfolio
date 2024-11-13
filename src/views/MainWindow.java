package views;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import models.Client;
import models.Location;
import models.Order;
import models.Product;
import presenters.Event;
import presenters.Presenter;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String ICON_PATH = "/img/Icon.png";
	private static final String TITLE = "Proyecto v 1.0";
	private ActionListener listener;
	private JPanel contentJpanel = new JPanel();
	private JPManagerMenu managerMenu;
	private JPLogin login;
	private JPClientMenu clientMenu;
	private JPmainBarsPanel top3_products;
	private JPmainBarsPanel top3_clients;
	private JPmainBarsPanel top3_maxPriceProduct;
	private JPmainBarsPanel top3_clientsByRegions;
	private JPOrderList saleList;
	private JPAddProduct addProduct;
	private JPUpdateProduct updateProduct;
	private JPDeleteProduct deleteProduct;
	private ShowProductPanel productTable;
	private JPClientList clientTable;
	private JPUpdateClient updateClient;
	private JPDeleteClient deleteClient;
	private JPRegistration registration;
	private JDdialog dialog;

	public MainWindow(ActionListener listener, ArrayList<Product> productList, ArrayList<Client> clientList,
			ArrayList<Order> orderList, ArrayList<Location> locationList) throws IOException {
		this.listener = listener;
		setSize(Constants.SIZE);
		setLocation((int) (Constants.SCREEN_SIZE.getWidth() / 2) - (getWidth() / 2),
				(int) (Constants.SCREEN_SIZE.getHeight() / 2) - (getHeight() / 2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);

		setIconImage(new ImageIcon(getClass().getResource(ICON_PATH)).getImage());
		instancePnaels(listener, productList, clientList, orderList, locationList);
		createCardLayout();
		setVisible(true);
	}

	private void instancePnaels(ActionListener listener, ArrayList<Product> productList, ArrayList<Client> clientList,
			ArrayList<Order> orderList, ArrayList<Location> locationList) throws IOException {
		dialog = new JDdialog();
		managerMenu = new JPManagerMenu(listener, 0, Constants.PRIMARY_COLOR, WIDTH, HEIGHT, new GridBagLayout(),
				new Point(getX(), getY()));
		login = new JPLogin(listener, 0, Constants.PRIMARY_COLOR, WIDTH, HEIGHT, new GridBagLayout());
		clientMenu = new JPClientMenu(listener, clientList, productList, new Point(getX(), getY()));
		top3_products = new JPmainBarsPanel(listener, Event.JP_ACCESS_MANAGER.toString());
		top3_clients = new JPmainBarsPanel(listener, Event.JP_ACCESS_MANAGER.toString());
		top3_maxPriceProduct = new JPmainBarsPanel(listener, Event.JP_ACCESS_MANAGER.toString());
		top3_clientsByRegions = new JPmainBarsPanel(listener, Event.JP_ACCESS_MANAGER.toString());

		saleList = new JPOrderList(listener);
		repaintSalesTable(orderList);
		addProduct = new JPAddProduct(listener);
		updateProduct = new JPUpdateProduct(listener, productList, 0);
		deleteProduct = new JPDeleteProduct(listener, productList);
		productTable = new ShowProductPanel(listener, productList);
//		clientTable = new JPClientList(listener, clientList, locationList);
		clientTable = new JPClientList(listener, clientList);
		updateClient = new JPUpdateClient(listener, locationList,clientList.get(0));
		deleteClient = new JPDeleteClient(listener);
		registration = new JPRegistration(listener, locationList);
	}

	private void createCardLayout() {
		contentJpanel.setLayout(new CardLayout(0, 0));
		contentJpanel.add(Event.JP_LOGIN.toString(), login);

		contentJpanel.add(Event.TOP5_MAX_PRICE_PRODUCT.toString(), top3_products);
		contentJpanel.add(Event.TOP5_CLIENTS_BY_REGION.toString(), top3_clients);
		
		contentJpanel.add(Event.JP_ACCESS_CLIENT.toString(), clientMenu);
		contentJpanel.add(Event.JP_ACCESS_MANAGER.toString(), managerMenu);
		contentJpanel.add(Event.TOP5_PRODUCTS.toString(), top3_products);
		contentJpanel.add(Event.TOP5_CLIENTS.toString(), top3_clients);
		contentJpanel.add(Event.JP_REGISTRATION.toString(), registration);
		contentJpanel.add(Event.SHOW_UPDATE_CLIENT.toString(), updateClient);
		contentJpanel.add(Event.SHOW_UPDATE_PRODUCT.toString(), updateProduct);
		contentJpanel.add(Event.SHOW_DELETE_PRODUCT.toString(), deleteProduct);
		contentJpanel.add(Event.SHOW_ADD_PRODUCT.toString(), addProduct);
		contentJpanel.add(Event.SHOW_DELETE_CLIENT.toString(), deleteClient);
		contentJpanel.add(Event.SHOW_CLIENT_LIST.toString(), clientTable);
		contentJpanel.add(Event.SHOW_PRODUCT_LIST.toString(), productTable);
		contentJpanel.add(Event.SALES_REPORT.toString(), saleList);
		add(contentJpanel);
	}

	public void repaintSalesTable(ArrayList<Order> list) {
		saleList.repaintTable(list);
	}

	public void showPanel(String Event) {
		CardLayout cl = (CardLayout) (contentJpanel.getLayout());
		cl.show(contentJpanel, Event);
	}

	public String getUser() {
		return login.getUser();
	}

	public String getPassword() {
		return login.getPassword();
	}

	public void placeLoginError(String validate) {
		login.placeLoginError(validate);
	}

	public void sendCoordinates(Point point2d) {
		dialog.sendCoordinates(point2d);
		managerMenu.sendCoordinates(point2d);
		clientMenu.sendCoordinates(point2d);
	}
	public void showDialog(String text) {
		dialog.setDialogText(text);
		dialog.setVisible(true);
	}

	public void hideManagerMenu() {
		managerMenu.hideMenu();
	}

	public void hideClientMenu() {
		clientMenu.hideMenu();
	}

	public void refreshUpdateProduct(ArrayList<Product> productList, int index) throws IOException {
		updateProduct.init(listener, productList, index);
	}

	public void refreshDeleteProduct(ArrayList<Product> productList) throws IOException {
		deleteProduct.init(listener, productList);
	}

	public void repaintProductsTable(ArrayList<Product> list) {
		productTable.repaintTable(list);
	}

	public void repaintOrderTable(ArrayList<Order> list) {
		saleList.repaintTable(list);
	}

	public void repaintClientsTable(ArrayList<Client> list, String event) {
		clientTable.repaintTable(list, event);
	}

	public String[] getClient() throws NumberFormatException, IOException, URISyntaxException {
		return updateClient.getClient();
	}

//	public Client getCurrentClient() throws NumberFormatException, IOException, URISyntaxException {
//		return updateClient.getClient();
//	}
	public void cleanFormUClient() {
		updateClient.resetForm();
	}

	public String getDCpassword() {
		return deleteClient.getPassword();
	}

	public String getDPName() {
		return deleteProduct.getDPSelectedItem();
	}

//	public String getUserName(){
//		clientMenu.getUser();
//		return null;
//	}
	public String[] getAProduct() throws NumberFormatException, IOException, URISyntaxException {
		return addProduct.getProduct();
	}

	public void cleanFormAProduct() {
		addProduct.cleanForm();
	}

	public void refreshProductList(Presenter presenter, ArrayList<Product> productList) throws IOException {
		clientMenu.init(presenter, productList, new Point(getX(), getY()));
	}

	public String[] getUProduct() throws NumberFormatException, IOException, URISyntaxException {
		return updateProduct.getProduct();
	}

//	public String getUPName(){
//		return updateProduct.getUPName();
//	}
	public String getNameUP() {
		return updateProduct.getNameUP();
	}

	public void cleanFormUProduct() {
		updateProduct.cleanForm();
	}

	public void placeAPError(String validation) {
		addProduct.placeAPError(validation);
	}

	public void placeUPError(String validation) {
		updateProduct.placeUPError(validation);
	}

	public String getUPSelectedItem() {
		return updateProduct.getUPSelectedItem();
	}

	public void placeUCError(String validation) {
		updateClient.placeUCError(validation);
	}

	public Client getInitUser() {
		return updateClient.getInitUser();
	}

	public void refreshUpdateProduct(Client client) throws IOException {
		updateClient.init(listener, client);
	}

	public void placeDCError(String error) {
		deleteClient.placeDCError(error);
	}

	public void resetFormLog() {
		login.resetForm();
	}

	public void placeRError(String validation) {
		registration.placeRError(validation);
	}

	public String[] getClientR() throws NumberFormatException, IOException, URISyntaxException {
		return registration.getClient();
	}

	public void resetFormR() {
		registration.resetForm();
	}

	public void refreshBGTopClient(String[] title, String[] nombresEjes, String[] nombresEjeX, int[] valores) {
		top3_clients.refreshBarGraphic(title, nombresEjes, nombresEjeX, valores);
	}
	public void refreshBGTopProducts(String[] title, String[] nombresEjes, String[] nombresEjeX, int[] valores) {
		top3_products.refreshBarGraphic(title, nombresEjes, nombresEjeX, valores);
	}

	public void refreshBGTopMaxPoductPrice(String[] title, String[] nombresEjes, String[] nombresEjeX, int[] valores) {
		top3_maxPriceProduct.refreshBarGraphic(title, nombresEjes, nombresEjeX, valores);
	}
	
	public void refreshBGTopCLientsByRegion(String[] title, String[] nombresEjes, String[] nombresEjeX, int[] valores) {
		top3_clientsByRegions.refreshBarGraphic(title, nombresEjes, nombresEjeX, valores);
	}

}