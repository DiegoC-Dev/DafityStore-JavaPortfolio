package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Product;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;

public class ShowProductPanel extends JPanel{
	private static final String TXT_RETURN = "Regresar";
	private static final long serialVersionUID = 1L;
	private JPTableProductList jpTableElements;
		
	public ShowProductPanel(ActionListener listener,ArrayList<Product> list) throws IOException {
		setLayout(new BorderLayout());
		init(listener,list);
	}

	private void init(ActionListener listener,ArrayList<Product> list) throws IOException {
		jpTableElements = new JPTableProductList(listener);
		this.add(jpTableElements, BorderLayout.CENTER);
		createButtonsBar(listener);
		repaintTable(list);
	}
	
	public void repaintTable(ArrayList<Product> list) {
		jpTableElements.repaintTable(list);
	}
	private void createButtonsBar(ActionListener listener) throws IOException {
		JPanel panelButtons = new JPanel(new  GridLayout(1,1));

		XPanel panelButtonCancel = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnCancel = new ZButton(listener, TXT_RETURN,Event.JP_ACCESS_MANAGER.toString());
		panelButtonCancel.center.add(btnCancel);
		panelButtonCancel.addPanels();
		panelButtons.add(panelButtonCancel);
		
		add(panelButtons, BorderLayout.PAGE_END);
	}
}