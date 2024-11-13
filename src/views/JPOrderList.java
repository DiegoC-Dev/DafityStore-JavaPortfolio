package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import models.Order;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;

public class JPOrderList extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPTableOrderList jpTableElements;
		
	public JPOrderList(ActionListener listener) throws IOException {
		setLayout(new BorderLayout());
		setBackground(Constants.PRIMARY_COLOR);
		init(listener);
	}

	private void init(ActionListener listener) throws IOException {
		jpTableElements = new JPTableOrderList(listener);
		this.add(jpTableElements, BorderLayout.CENTER);
		createButtonBar(listener);
	}
	
	public void repaintTable(ArrayList<Order> list) {
		jpTableElements.repaintTable(list);
	}
	private void createButtonBar(ActionListener listener) throws IOException {
		XPanel panelButtons = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnOrder = new ZButton(listener, "Regresar",Event.JP_ACCESS_MANAGER.toString());
		panelButtons.center.add(btnOrder);
		panelButtons.addPanels();
		add(panelButtons, BorderLayout.PAGE_END);
	}
}