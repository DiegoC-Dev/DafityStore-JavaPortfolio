package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
import models.Product;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;
import views.components.ZLabel;
import views.components.ZPanelTextField;

public class JPRegistration extends JPanel{
	private static final String SELECT_CITY = "Seleccione lugar de residencia : ";
	private static final String SELECT_DOCUMENT_TYPE = "Seleccione tipo de documento : ";
	private static final String TITLE_USER = "Usuario :";
	private static final String TXT_USER = "Usuario";
	private static final String DATE_ERROR = "Fecha incorrecta";
	private static final String TXT_BIRTH_DAY = "dd/mm/yy";
	private static final String TXT_CITY_ID = "Ciudad";
	private static final String TXT_DOCUMENT_TYPE = "Tipo de documento";
	private static final String TXT_LAST_NAME = "Apellido";
	private static final String TXT_FIRST_NAME = "Nombre";
	private static final String TITLE_BITH_DAY = "Fecha de nacimiento : ";
	private static final String TITLE_CITY_ID = "Id de ciudad : ";
	private static final String TITLE_DOCUMENT_TYPE = "Tipo de documento : ";
	private static final String TITLE_LAST_NAME = "Apellido : ";
	private static final String TITLE_FIRST_NAME = "Nombre : ";
	private static final String USER_EXIST = "El usuario ya existe";
	private static final String TXT_PASSWORD = "Contrasenia";
	private static final String TXT_ID = "Numero de documento";
	private static final String TXT_CANCEL = "Cancelar";
	private static final String TXT_ACEPT = "Registrarse";
	private static final String TITLE_PASSWORD = "Contrasenia : ";
	private static final String TITLE_ID = "Numero de documento : ";
	private static final String NUMERIC_ERROR = "Numero de documento debe ser numerico";
	private static final long serialVersionUID = 1L;
	private ZPanelTextField txId;
	private ZPanelTextField txPassword;
	private JPanel panelContent;
	private ZLabel labelError;
	private ZPanelTextField txLName;
	private ZPanelTextField txBirthDate;
	private ZPanelTextField txFName;
	private ZPanelTextField txUser;
	private JComboBox<String> typeDcumentBox;
	private JComboBox<String> locationBox;
	private ArrayList<Location> locationList;

	public JPRegistration(ActionListener listener,ArrayList<Location> locationList) throws IOException {
		this.locationList = locationList;
		setSize(Constants.SIZE);
		setLocation((int)(Constants.SCREEN_SIZE.getWidth()/2)-(getWidth()/2), 
				(int)(Constants.SCREEN_SIZE.getHeight()/2)-(getHeight()/2));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Constants.PRIMARY_COLOR);
		init(listener);
	}
	public void init(ActionListener listener) throws IOException {
		createContentPanel();
		addFieldList();
		addMargins(listener);
		createButtonsBar(listener);
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
	private void addFieldList() {
		txUser = new ZPanelTextField(TITLE_USER, TXT_USER);
		panelContent.add(txUser);

		txPassword = new ZPanelTextField(TITLE_PASSWORD,TXT_PASSWORD);
		panelContent.add(txPassword);
		
		txFName = new ZPanelTextField(TITLE_FIRST_NAME, TXT_FIRST_NAME);
		panelContent.add(txFName);
		
		txLName = new ZPanelTextField(TITLE_LAST_NAME, TXT_LAST_NAME);
		panelContent.add(txLName);
		
		addTypeDcumentBox();
		
		txId = new ZPanelTextField(TITLE_ID, TXT_ID);
		panelContent.add(txId);
		
		addLocationBox();

		txBirthDate = new ZPanelTextField(TITLE_BITH_DAY,TXT_BIRTH_DAY);
		panelContent.add(txBirthDate);

	}
	private void addTypeDcumentBox() {
		String[] nameList=new String[3];
		nameList[0] = "TI";
		nameList[1] = "CC";
		nameList[2] = "DE";

		typeDcumentBox = new JComboBox<String>(nameList);
		typeDcumentBox.setBackground(Constants.PRIMARY_COLOR); 
		typeDcumentBox.setSelectedIndex(0);
		typeDcumentBox.setBorder(new BMainBorder(SELECT_DOCUMENT_TYPE));
		((JLabel)typeDcumentBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);	
		
		panelContent.add(typeDcumentBox);
	}
	private void addLocationBox() {
		String[] nameList=new String[locationList.size()];
		for (int i = 0; i < nameList.length; i++) {
			nameList[i] = ""+locationList.get(i).getName();
		}

		locationBox = new JComboBox<String>(nameList);
		locationBox.setBackground(Constants.PRIMARY_COLOR); 
		locationBox.setSelectedIndex(0);
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
		ZButton btnOrder = new ZButton(listener, TXT_ACEPT,Event.JP_REGISTRATION.toString());
		panelButtonAdd.center.add(btnOrder);
		panelButtonAdd.addPanels();
		panelButtons.add(panelButtonAdd);

		XPanel panelButtonCancel = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnCancel = new ZButton(listener, TXT_CANCEL,Event.JP_LOGIN.toString());
		panelButtonCancel.center.add(btnCancel);
		panelButtonCancel.addPanels();
		panelButtons.add(panelButtonCancel);
		
		add(panelButtons, BorderLayout.PAGE_END);
	}
	public String[] getClient() {
		int index = typeDcumentBox.getSelectedIndex();
//		String idLocation = ""+locationList.get(index).getId();
		String data[] ={txUser.getText(),txPassword.getText(),
				txFName.getText(), txLName.getText(),
				typeDcumentBox.getSelectedItem().toString(),
				txId.getText(),
				locationBox.getSelectedItem().toString(),
				txBirthDate.getText()};
		return  data;
	}
	
	public void resetForm() {
//		txUser.setText("");
//		txFName.setText("");
//		txLName.setText("");
//		txTypeDoc.setText("");
//		txId.setText("");
//		txPassword.setText("");
//		txCityId.setText("");
//		txBirthDate.setText("");

		txUser.setText(TXT_USER);
		txFName.setText(TXT_FIRST_NAME);
		txLName.setText(TXT_LAST_NAME);
		txId.setText(TXT_ID);
		txPassword.setText(TXT_PASSWORD);
		txBirthDate.setText(TXT_BIRTH_DAY);

	}
	public void placeRError(String error) {
		if(error.equals(ModelConstants.NONE_ERROR.toString()))
			labelError.setText("");
		else if(error.equals(ModelConstants.EMPTY_ERROR.toString()))
			labelError.setText(Constants.EMPTY_FIELD_ERROR);
		else if(error.equals(ModelConstants.EXIST_ERROR.toString()))
			labelError.setText(USER_EXIST);
		else if(error.equals(ModelConstants.NUMBER_ERROR.toString()))
			labelError.setText(NUMERIC_ERROR);
		else if(error.equals(ModelConstants.DATE_ERROR.toString()))
			labelError.setText(DATE_ERROR);

	}
}