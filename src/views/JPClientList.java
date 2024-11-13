package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Client;
import models.Location;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;


public class JPClientList extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPTableClientList jpTableElements;
//	private String returnToPanel;
	private ZButton btnOrder;
		
	public JPClientList(ActionListener listener,ArrayList<Client> clientList) throws IOException {
		setLayout(new BorderLayout());
		setBackground(Constants.PRIMARY_COLOR);
		init(listener,clientList);
	}

//	private void init(ActionListener listener,ArrayList<Client> clientList,ArrayList<Location> locationList) throws IOException {
	private void init(ActionListener listener,ArrayList<Client> clientList) throws IOException {

//		jpTableElements = new JPTableClientList(listener,locationList);
		jpTableElements = new JPTableClientList(listener);
		this.add(jpTableElements, BorderLayout.CENTER);
		createButtonBar(listener);
		repaintTable(clientList, null);
	}
	
	public void repaintTable(ArrayList<Client> list,String event) {
		jpTableElements.repaintTable(list);
		btnOrder.setActionCommand(event);
	}
	private void createButtonBar(ActionListener listener) throws IOException {
		XPanel panelButtons = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		btnOrder = new ZButton(listener, "Regresar",null);
		panelButtons.center.add(btnOrder);
		panelButtons.addPanels();
		add(panelButtons, BorderLayout.PAGE_END);
	}
}