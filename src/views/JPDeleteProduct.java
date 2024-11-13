package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import models.Product;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;
import views.components.ZLabel;

public class JPDeleteProduct extends JPanel{
	private static final long serialVersionUID = 1L;
	private static final String TXT_ACEPT = "Borrar";
	private static final String TXT_CANCEL = "Cancelar";
	private static final String SELECT_CODE = "Seleccione el codigo que desea borrar";
	private JComboBox<String> nameBox;

	public JPDeleteProduct(ActionListener listener, ArrayList<Product> list) throws IOException {
		setSize(Constants.SIZE.width,Constants.SIZE.height/4);
		setLocation((int)(Constants.SCREEN_SIZE.getWidth()/2)-(getWidth()/2), 
				(int)(Constants.SCREEN_SIZE.getHeight()/2)-(getHeight()/2));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Constants.PRIMARY_COLOR);
		init(listener, list);
	}

	public void init(ActionListener listener,ArrayList<Product> list) throws IOException {
		removeAll();
		createCentralpanel(list);
		createButtonsBar(listener);
		revalidate();
	}
	private void createButtonsBar(ActionListener listener) throws IOException {
		JPanel panelButtons = new JPanel(new  GridLayout(2,1));
		
		XPanel panelButtonAdd = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnOrder = new ZButton(listener, TXT_ACEPT,Event.DELETE_PRODUCT.toString());
		panelButtonAdd.center.add(btnOrder);
		panelButtonAdd.addPanels();
		panelButtons.add(panelButtonAdd);

		XPanel panelButtonCancel = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnCancel = new ZButton(listener, TXT_CANCEL,Event.JP_ACCESS_MANAGER.toString());
		panelButtonCancel.center.add(btnCancel);
		panelButtonCancel.addPanels();
		panelButtons.add(panelButtonCancel);
		
		add(panelButtons, BorderLayout.PAGE_END);
	}

	private void createCentralpanel(ArrayList<Product> list) throws IOException {
		
		XPanel panelButtonAdd = new XPanel(1, 10, 1, 4, 2, 4, 
				new Dimension(Constants.SIZE.width, 400), null);

		JPanel panelComboBox = createPanelComboBox(list);
		panelButtonAdd.center.add(panelComboBox);
		
		panelButtonAdd.addPanels();
		add(panelButtonAdd);
	}

	private JPanel createPanelComboBox(ArrayList<Product> list) {
		String[] nameList=new String[list.size()];
		for (int i = 0; i < nameList.length; i++) {
			nameList[i] = ""+list.get(i).getName();
		}
		
		JPanel panelComboBox = new JPanel(new  GridLayout(2,1));
		panelComboBox.setBackground(Constants.PRIMARY_COLOR);
		ZLabel lblNameBox = new ZLabel(SELECT_CODE,Constants.SECUNDARY_FONT_COLOR);
		panelComboBox.add(lblNameBox);
		nameBox = new JComboBox<String>(nameList);
		panelComboBox.add(nameBox);
		
		return panelComboBox;
	}
	
	public String getDPSelectedItem() {
		return nameBox.getSelectedItem().toString();
	}

}