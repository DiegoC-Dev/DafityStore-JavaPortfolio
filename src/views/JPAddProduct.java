package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import models.ModelConstants;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;
import views.components.ZLabel;
import views.components.ZPanelTextField;

public class JPAddProduct extends JPanel{
	private static final String TXT_DISCOUNT_PERCENTAGE = "Porcentaje de descuento:";
	private static final String NUMERIC_ERROR = "Precio, decuento y cantidad deben ser numericos";
	private static final String PATH_ERROR = "Direccion de imagen invalida";
	private static final String PRICE_ERROR = "El precio debe ser numerico";
	private static final String PRODUCT_EXIST_ERROR = "El producto ya existe";
	private static final String TXT_QUANTITY = "Cantidad : ";
	private static final String TXT_SIZE = "Talla : ";
	private static final String TXT_PRICE = "Precio : ";
	private static final String TXT_NAME = "Nombre : ";
	private static final String TXT_CANCEL = "Cancelar";
	private static final String TXT_ACEPT = "Agregar";
	private static final long serialVersionUID = 1L;
	private static final String TXT_IMG = "Arrasre la imagen aca";
	private ZPanelTextField txName;
	private ZPanelTextField txPrice;
	private ZPanelTextField txSize;
	private ZPanelTextField txQuantity;
	private ZLabel labelError;
	private JPanel contentPanel;
	private ZPanelTextField txDiscountPerc;

	public JPAddProduct(ActionListener listener) throws IOException {
		setSize(Constants.SIZE);
		setLocation((int)(Constants.SCREEN_SIZE.getWidth()/2)-(getWidth()/2), 
				(int)(Constants.SCREEN_SIZE.getHeight()/2)-(getHeight()/2));

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Constants.PRIMARY_COLOR);
		init(listener);
	}

	private void init(ActionListener listener) throws IOException {
		createContentPanel();
		addFiedList();
		addMargins(listener);
		createButtonsBar(listener);		
	}
	private void createContentPanel() {
		contentPanel = new JPanel(new GridLayout(6,1));
		contentPanel.setBackground(Constants.PRIMARY_COLOR);
	}
	private void createButtonsBar(ActionListener listener) throws IOException {
		JPanel panelButtons = new JPanel(new  GridLayout(3,1));
		
		XPanel panelLabelError = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		labelError = new ZLabel("", Constants.FONT_COLOR_ERROR);
		panelLabelError.center.add(labelError);
		panelLabelError.addPanels();
		panelButtons.add(panelLabelError);
		
		XPanel panelButtonAdd = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnOrder = new ZButton(listener, TXT_ACEPT,Event.ADD_PRODUCT.toString());
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
	private void addFiedList() {
		txName = new ZPanelTextField(TXT_NAME, "");
		contentPanel.add(txName);
		
		txPrice = new ZPanelTextField(TXT_PRICE, "");
		contentPanel.add(txPrice);
		
		txDiscountPerc = new ZPanelTextField(TXT_DISCOUNT_PERCENTAGE,"");
		contentPanel.add(txDiscountPerc);
		
		txSize = new ZPanelTextField(TXT_SIZE, "");
		contentPanel.add(txSize);
		
		txQuantity = new ZPanelTextField(TXT_QUANTITY, "");
		contentPanel.add(txQuantity);
		
	}
	private void addMargins(ActionListener listener) throws IOException {
		XPanel margins = new XPanel(1, 8, 1, 1, 9, 0,
				 new Dimension(Constants.SIZE.width, 350), listener);
		margins.center.add(contentPanel);
		margins.addPanels();
		add(margins,BorderLayout.CENTER);
	}
	public void cleanForm() {
		txName.setText("");
		txPrice.setText("");
		txDiscountPerc.setText("");
		txSize.setText("");
		txQuantity.setText("");
	}
	
	public String[] getProduct() {
		String data[] ={txName.getText(), txPrice.getText(),
				txDiscountPerc.getText(),
				txSize.getText(),txQuantity.getText()};
		return  data;
	}

	public void placeAPError(String error) {
		if(error.equals(ModelConstants.NONE_ERROR.toString()))
			labelError.setText("");
		else if(error.equals(ModelConstants.EMPTY_ERROR.toString()))
			labelError.setText(Constants.EMPTY_FIELD_ERROR);
		else if(error.equals(ModelConstants.EXIST_ERROR.toString()))
			labelError.setText(PRODUCT_EXIST_ERROR);
		else if(error.equals(ModelConstants.NUMBER_ERROR.toString()))
			labelError.setText(NUMERIC_ERROR);
		else if(error.equals(ModelConstants.PATH_ERROR.toString()))
			labelError.setText(PATH_ERROR);
	}
}