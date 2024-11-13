package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import models.Client;
import models.Product;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;

public class JPClientMenu extends JPanel{

	private static final String TXT_MENU_CLIENTS = "  Menu clientes";
	private static final String TXT_EXIT = "Salir";
	private static final long serialVersionUID = 1L;
//	private JComboBox<String> clientNameBox;
	private GradientButtonClient gradientButton;

	public JPClientMenu(ActionListener listener, ArrayList<Client> list1, 
			ArrayList<Product> list2,Point2D point2d) throws IOException {
		setLayout(new BorderLayout());
		setBackground(Constants.PRIMARY_COLOR);
		init(listener, list2,point2d);
		setVisible(true);
	}

	public void init(ActionListener listener,
			ArrayList<Product> list2,Point2D point2d) throws IOException {	
		removeAll();
		gradientButtonPanel(listener, point2d);
		createCardsSection(listener, list2);
		createButton(listener);
		revalidate();
	}
	private void createButton(ActionListener listener) throws IOException {
		XPanel panelButtons = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnOrder = new ZButton(listener, TXT_EXIT,Event.JP_LOGIN.toString());
		panelButtons.center.add(btnOrder);
		panelButtons.addPanels();
		add(panelButtons, BorderLayout.PAGE_END);
	}

	private void gradientButtonPanel(ActionListener listener, Point2D point2d) {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel buttonPanel = new JPanel(new BorderLayout());
		mainPanel.add(buttonPanel,BorderLayout.WEST);
		
		gradientButton = new GradientButtonClient(point2d,listener);
		buttonPanel.add(gradientButton,BorderLayout.CENTER);
		
		JPanel labelPanel = new JPanel(new BorderLayout());
		labelPanel.setBackground(Constants.PRIMARY_COLOR);
		
		JLabel lb = new JLabel(TXT_MENU_CLIENTS);
		lb.setFont(Constants.MAIN_FONT);
		lb.setForeground(Constants.PRIMARY_FONT_COLOR);
		lb.setBackground(Constants.PRIMARY_COLOR);
		lb.setHorizontalAlignment(SwingConstants.LEFT);
		labelPanel.add(lb);
		
		mainPanel.add(labelPanel,BorderLayout.CENTER);
		
		add(mainPanel,BorderLayout.NORTH);
	}


	private void createCardsSection(ActionListener listener, ArrayList<Product> list) {
		JPanel panelCenterScrol = new JPanel(new BorderLayout());
		
		JPanel panelCenter = new JPanel(
				new GridLayout(list.size()%2==0?list.size()/2:list.size()/2+1,2));
		
		for (int i = 0; i < list.size(); i++) {
			Card productCard = new Card(listener, list.get(i));
			panelCenter.add(productCard,BorderLayout.CENTER);			
		}
		
		panelCenterScrol.add(panelCenter, BorderLayout.CENTER);
		
		add(new JScrollPane(panelCenterScrol), BorderLayout.CENTER);
	}
	public void sendCoordinates(Point point2d) {
		gradientButton.sendCoordinates(point2d);
	}
	public void hideMenu() {
		gradientButton.hideMenu();
	}
//	public String getUser() {
//		String sd = clientNameBox.getSelectedItem().toString();
//		System.out.println(sd==null);
//		return clientNameBox.getSelectedItem().toString();
//	}
	
}