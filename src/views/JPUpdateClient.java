package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Client;
import models.Location;
import models.Manager;
import models.ModelConstants;
import models.MyDate;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;
import views.components.ZLabel;
import views.components.ZPanelTextField;

public class JPUpdateClient extends JPanel{
	private static final String SELECT_CITY = "Seleccione lugar de residencia : ";
	private static final String SELECT_DOCUMENT_TYPE = "Seleccione tipo de documento : ";
	private static final String DATE_ERROR = "Fecha incorrecta";
	private static final String ERROR_EXIST = "El usuario ya existe";
	private static final String TITLE_USER = "Usuario :";
	private static final String TXT_BIRTH_DAY = "dd/mm/yy";
	private static final String TXT_GENDER = "M/F";
	private static final String TXT_DOCUMENT_TYPE = "Tipo de documento";
	private static final String TXT_LAST_NAME = "Apellido";
	private static final String TXT_FIRST_NAME = "Nombre";
	private static final String TITLE_BITH_DAY = "Fecha de nacimiento : ";
	private static final String TITLE_CITY_ID = "Id de ciudad : ";
	private static final String TITLE_DOCUMENT_TYPE = "Tipo de documento : ";
	private static final String TITLE_LAST_NAME = "Apellido : ";
	private static final String TITLE_FIRST_NAME = "Nombre : ";
	private static final String USER_EXIST = ERROR_EXIST;
	private static final String TXT_PASSWORD = "Contraseña";
	private static final String TXT_ID = " Numero de documento";
	private static final String TXT_CANCEL = "Cancelar";
	private static final String TXT_ACEPT = "Actualizar";
	private static final String TITLE_PASSWORD = "Contraseña : ";
	private static final String TITLE_ID = "Numero de documento : ";
	private static final String NUMERIC_ERROR = "Documento debe ser numerico";
	private static final long serialVersionUID = 1L;
	private ZPanelTextField txId;
	private ZPanelTextField txPassword;
	private JPanel panelContent;
	private ZLabel labelError;
	private Client client;
	private ZPanelTextField txFName;
	private ZPanelTextField txLName;
	private ZPanelTextField txBirthDate;
	private ZPanelTextField txUser;
	private ArrayList<Location> locationList;
	private JComboBox<String> locationBox;
	private JComboBox<String> typeDcumentBox;

	public JPUpdateClient(ActionListener listener,ArrayList<Location> locationList,
			Client client) throws IOException {
		this.locationList = locationList;
		setSize(Constants.SIZE);
		setLocation((int)(Constants.SCREEN_SIZE.getWidth()/2)-(getWidth()/2), 
				(int)(Constants.SCREEN_SIZE.getHeight()/2)-(getHeight()/2));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Constants.PRIMARY_COLOR);
		init(listener, client);
	}
	public void init(ActionListener listener,Client client) throws IOException {
		removeAll();
		this.client = client;
		createContentPanel();
		addFieldList(client);
		addMargins(listener);
		createButtonsBar(listener);
		revalidate();
	}
	private void createContentPanel() {
		panelContent = new JPanel(new GridLayout(8,1));
		panelContent.setBackground(Constants.PRIMARY_COLOR);
	}
	private void addMargins(ActionListener listener) throws IOException {
		XPanel margins = new XPanel(1, 8, 1, 2, 7, 1,
				 new Dimension(Constants.SIZE.width, 350), listener);
		margins.center.add(panelContent);
		margins.addPanels();
		add(margins,BorderLayout.CENTER);
	}
	private void addFieldList(Client client) {
		txUser = new ZPanelTextField(TITLE_USER, ""+client.getUser());
		panelContent.add(txUser);

		txPassword = new ZPanelTextField(TITLE_PASSWORD,""+client.getPassword());
		panelContent.add(txPassword);

		txFName = new ZPanelTextField(TITLE_FIRST_NAME, ""+client.getFirstName());
		panelContent.add(txFName);
		
		txLName = new ZPanelTextField(TITLE_LAST_NAME, ""+client.getLastName());
		panelContent.add(txLName);
		
		addTypeDcumentBox(client);
		
		txId = new ZPanelTextField(TITLE_ID, ""+client.getDocumentId());
		panelContent.add(txId);
		
		addLocationBox(client);

		txBirthDate = new ZPanelTextField(TITLE_BITH_DAY,""+client.getBirthDateSt());
		panelContent.add(txBirthDate);
				
	}
	
	private void addTypeDcumentBox(Client client) {
		String[] nameList=new String[3];
		nameList[0] = "TI";
		nameList[1] = "CC";
		nameList[2] = "DE";
		
		int index = -1;
		for (int i = 0; i < nameList.length; i++) {
			if(nameList[i].equals(client.getDocumentType()))
				index = i;
		}

		typeDcumentBox = new JComboBox<String>(nameList);
		typeDcumentBox.setBackground(Constants.PRIMARY_COLOR); 
		typeDcumentBox.setSelectedIndex(index);
		typeDcumentBox.setBorder(new BMainBorder(SELECT_DOCUMENT_TYPE));
		((JLabel)typeDcumentBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);	
		
		panelContent.add(typeDcumentBox);
	}
	private void addLocationBox(Client client) {
		String[] nameList=new String[locationList.size()];
		for (int i = 0; i < nameList.length; i++) {
			nameList[i] = ""+locationList.get(i).getName();
		}
		
		int index = -1;
		for (int i = 0; i < nameList.length; i++) {
			if(nameList[i].equals(client.getCityName()))
				index = i;
		}

		locationBox = new JComboBox<String>(nameList);
		locationBox.setBackground(Constants.PRIMARY_COLOR); 
		locationBox.setSelectedIndex(index);
		locationBox.setBorder(new BMainBorder(SELECT_CITY));
		((JLabel)locationBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);	

		panelContent.add(locationBox);
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
		ZButton btnOrder = new ZButton(listener, TXT_ACEPT,Event.UPDATE_CLIENT.toString());
		panelButtonAdd.center.add(btnOrder);
		panelButtonAdd.addPanels();
		panelButtons.add(panelButtonAdd);

		XPanel panelButtonCancel = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnCancel = new ZButton(listener, TXT_CANCEL,Event.JP_ACCESS_CLIENT.toString());
		panelButtonCancel.center.add(btnCancel);
		panelButtonCancel.addPanels();
		panelButtons.add(panelButtonCancel);
		
		add(panelButtons, BorderLayout.PAGE_END);
	}
	
	public String[] getClient() {
		int index = locationBox.getSelectedIndex();
//		String idLocation = ""+locationList.get(index).getName();
		String idLocation = ""+locationList.get(index).getId();
//		System.out.println("nombre .."+locationBox.getSelectedItem());
//		System.out.println("id..."+idLocation);
		String data[] ={txUser.getText(),txPassword.getText(),
				txFName.getText(), txLName.getText(),
				typeDcumentBox.getSelectedItem().toString(),
				txId.getText(),
//				idLocation,
				locationBox.getSelectedItem().toString(),
				txBirthDate.getText()};
		return  data;
	} 
	
	public void resetForm() {
		txUser.setText("");
		txFName.setText("");
		txLName.setText("");
		txId.setText("");
		txPassword.setText("");
		txBirthDate.setText("");
	}
	public void placeUCError(String error) {
		if(error.equals(ModelConstants.NONE_ERROR.toString()))
			labelError.setText("");
		else if(error.equals(ModelConstants.EMPTY_ERROR.toString()))
			labelError.setText(Constants.EMPTY_FIELD_ERROR);
		else if(error.equals(ModelConstants.EXIST_ERROR.toString()))
			labelError.setText(ERROR_EXIST);
		else if(error.equals(ModelConstants.NUMBER_ERROR.toString()))
			labelError.setText(NUMERIC_ERROR);
		else if(error.equals(ModelConstants.DATE_ERROR.toString()))
			labelError.setText(DATE_ERROR);

	}
	public Client getInitUser(){
		return client;
	}
	
}