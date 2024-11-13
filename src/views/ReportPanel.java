package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import presenters.Event;
import views.components.ZButton;
import views.components.ZLabel;
import views.components.ZPanel;

public class ReportPanel extends ZPanel{
	private static final String TOP_5_PRODUCTS = "Top 5 Productos";
	private static final String TOP_5_MAX_PRICE_PRODUCT = "Top 5 Productos mas costosos";
	private static final String TOP_5_CLIENTS_BY_REGION = "Top 5 Clientes por region";
	private static final String TXT_EXIT = "Salir";
	private static final String TOP_5_CLIENTS = "Top 5 Clientes";
	private static final String TXT_SALES_REPORT = "Reporte de ventas";
	private static final String TXT_ClIENTS_REPORT = "Lista de clientes";
	private static final long serialVersionUID = 1L;
	private ZLabel lbImage;
	private ZButton btnSalesReport;
	private ZButton btnTopClients;
	private ZButton btnTopProducts;
	private ZButton btnExit;
	private ZButton btnClientsReport;
	private ZButton btnTopMaxPriceProduct;
	private ZButton btnTopClientsByRegion;
	private static final String MAIN_IMG = "/img/MainImage.png";
	public static final Dimension SIZE= new Dimension(700, 600);


	public ReportPanel(ActionListener listener,int sizeMargin, Color background, int width, int height, LayoutManager layout) {
		super(sizeMargin, background, width, height, layout);
		setSize(SIZE);
		setPreferredSize(SIZE);
		init(listener);
	}

	private void init(ActionListener listener) {
		lbImage = new ZLabel(MAIN_IMG, 500, 200);	
		modifyConstraints(c,1,1,11,1,1.5,lbImage);
		add(lbImage,c);

		btnClientsReport = new ZButton(listener, TXT_ClIENTS_REPORT, Event.CLIENTS_REPORT.toString());
		modifyConstraints(c,1,2,11,1,0.5,btnClientsReport);
		add(btnClientsReport,c);

		btnSalesReport = new ZButton(listener, TXT_SALES_REPORT, Event.SALES_REPORT.toString());
		modifyConstraints(c,1,3,11,1,0.5,btnSalesReport);
		add(btnSalesReport,c);
		
		
		btnTopClients = new ZButton(listener, TOP_5_CLIENTS, Event.TOP5_CLIENTS.toString());
		modifyConstraints(c,1,4,11,1,0.5,btnTopClients);
		add(btnTopClients,c);

		btnTopProducts = new ZButton(listener, TOP_5_PRODUCTS, Event.TOP5_PRODUCTS.toString());
		modifyConstraints(c,1,5,11,1,0.5,btnTopProducts);
		add(btnTopProducts,c);
		
		btnTopMaxPriceProduct = new ZButton(listener,TOP_5_MAX_PRICE_PRODUCT, Event.TOP5_MAX_PRICE_PRODUCT.toString());
		modifyConstraints(c,1,6,11,1,0.5,btnTopMaxPriceProduct);
		add(btnTopMaxPriceProduct,c);
		
		btnTopClientsByRegion = new ZButton(listener, TOP_5_CLIENTS_BY_REGION, Event.TOP5_CLIENTS_BY_REGION.toString());
		modifyConstraints(c,1,7,11,1,0.5,btnTopClientsByRegion);
		add(btnTopClientsByRegion,c);

		
	/////////////////









	}

}
