package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import models.Manager;
import models.ModelConstants;
import models.Product;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;
import views.components.ZLabel;
import views.components.ZPanelTextField;

public class JPUpdateProduct extends JPanel{
	private static final String TXT_DISCOUNT_PERCENTAGE = "Porcentaje de descuento:";
	private static final long serialVersionUID = 1L;
	private static final String TXT_CANCEL = "Cancelar";
	private static final String TXT_ACEPT = "Actualizar";
	private static final String PRODUCT_EXIST_ERROR = "El producto ya existe";
	private static final String TXT_PRICE = "Precio : ";
	private static final String TXT_NAME = "Nombre : ";
	private static final String TXT_QUANTITY = "Cantidad : ";
	private static final String TXT_SIZE = "Talla : ";
	private static final String SELECT_CODE = "Seleccione el codigo para actualizar : ";
	private static final String TXT_IMG = "Arrasre la imagen aca";
	private static final String PATH_ERROR = "Direccion de imagen invalida";
	private static final String PRICE_ERROR = "El precio debe ser numerico";
	private static final String NUMERIC_ERROR = "Precio, descuento y cantidad deben ser numericos";
	private ZPanelTextField txName;
	private ZPanelTextField txPrice;
	private JComboBox<String> nameBox;
	private ZLabel labelError;
	private int index;
	private JPanel panelContent;
	private ZPanelTextField txSize;
	private ZPanelTextField txQuantity;
	private ZPanelTextField txDiscountPerc;

	public JPUpdateProduct(ActionListener listener, ArrayList<Product> list,int index) throws IOException {
		setSize(Constants.SIZE);
		setLocation((int)(Constants.SCREEN_SIZE.getWidth()/2)-(getWidth()/2), 
				(int)(Constants.SCREEN_SIZE.getHeight()/2)-(getHeight()/2));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Constants.PRIMARY_COLOR);
		init(listener, list,index);
	}
	public void init(ActionListener listener,ArrayList<Product> list,int index) throws IOException {
		removeAll();
		this.index = index;
		createContentPanel();
		addComboBox(listener, list,index);
		addFieldList(list,index);
		addMargins(listener);
		createButtonsBar(listener);
		revalidate();
	}
	private void createContentPanel() {
		panelContent = new JPanel(new GridLayout(7,1));
		panelContent.setBackground(Constants.PRIMARY_COLOR);
	}
	private void addMargins(ActionListener listener) throws IOException {
		XPanel margins = new XPanel(1, 8, 1, 1, 8, 1,
				 new Dimension(Constants.SIZE.width, 350), listener);
		margins.center.add(panelContent);
		margins.addPanels();
		add(margins,BorderLayout.CENTER);
	}
	private void addComboBox(ActionListener listener, ArrayList<Product> list,int index) {
		String[] nameList=new String[list.size()];
		for (int i = 0; i < nameList.length; i++) {
			nameList[i] = ""+list.get(i).getName();
		}

		nameBox = new JComboBox<String>(nameList);
		nameBox.setBackground(Constants.PRIMARY_COLOR); 
		nameBox.setSelectedIndex(index);
		nameBox.addActionListener(listener);
		nameBox.setActionCommand(Event.CHANGE_SELECTION.toString());
		nameBox.setBorder(new BMainBorder(SELECT_CODE));
		((JLabel)nameBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);	
		
		panelContent.add(nameBox);
	}
	private void addFieldList(ArrayList<Product> list,int index) {
		Product product = list.get(index);
		txName =  new ZPanelTextField(TXT_NAME,product.getName());
		panelContent.add(txName);
		
		txPrice = new ZPanelTextField(TXT_PRICE,""+product.getPrice());
		panelContent.add(txPrice);
		
		txDiscountPerc = new ZPanelTextField(TXT_DISCOUNT_PERCENTAGE,""+product.getDiscountPercentage());
		panelContent.add(txDiscountPerc);
		
		txSize = new ZPanelTextField(TXT_SIZE,product.getSize());
		panelContent.add(txSize);
		
		txQuantity = new ZPanelTextField(TXT_QUANTITY,""+product.getStock());
		panelContent.add(txQuantity);
		
	}
	private void createButtonsBar(ActionListener listener) throws IOException {
		JPanel panelButtons = new JPanel(new  GridLayout(3,1));
		
		XPanel panelLabelError = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		labelError = new ZLabel("", Color.red);
		panelLabelError.center.add(labelError);
		panelLabelError.addPanels();
		panelButtons.add(panelLabelError);
		
		
		XPanel panelButtonAdd = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnOrder = new ZButton(listener, TXT_ACEPT,Event.UPDATE_PRODUCT.toString());
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

	public String getNameUP() {
		return nameBox.getSelectedItem().toString();
	}
	
	public String[] getProduct() throws NumberFormatException, IOException, URISyntaxException{
		String data[] ={txName.getText(), txPrice.getText(),
				txDiscountPerc.getText(),
				txSize.getText(),txQuantity.getText()};
		return  data;
	}
	
	public String getUPName() {
		return nameBox.getSelectedItem().toString();
	}
	
	public void cleanForm() {
		txName.setText("");
		txDiscountPerc.setText("");
		txPrice.setText("");
		txSize.setText("");
		txQuantity.setText("");
	}
	
	public String getUPSelectedItem() {
		return nameBox.getSelectedItem().toString();
	}
	public void placeUPError(String error) {
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