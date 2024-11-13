package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import models.ModelConstants;
import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;
import views.components.ZLabel;
import views.components.ZPanelTextField;

public class JPDeleteClient extends JPanel{
	private static final String NOT_EXIST = "El cliente no existe";
	private static final String NUMBER_ERROR = "El id debe ser numerico";
	private static final String TXT_CANCEL = "Cancelar";
	private static final String TXT_ACEPT = "Borrar";
	private static final String TXT_ID_PRODUCT = "Digite el id del cliente  : ";
	private static final long serialVersionUID = 1L;
	private static final String PASSWORD_ERROR = "Contraseña incorrecta";
	private ZPanelTextField txPassword;
	private JPanel panelContent;
	private ZLabel labelError;

	
	public JPDeleteClient(ActionListener listener) throws IOException {
		setSize(Constants.SIZE.width,Constants.SIZE.height/4);
		setLocation((int)(Constants.SCREEN_SIZE.getWidth()/2)-(getWidth()/2), 
				(int)(Constants.SCREEN_SIZE.getHeight()/2)-(getHeight()/2));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Constants.PRIMARY_COLOR);
		init(listener);
	}

	public void init(ActionListener listener) throws IOException {
		removeAll();
		createContentPanel();
		addFieldList();
		addMargins(listener);
		createButtonsBar(listener);
		revalidate();
	}
	private void createContentPanel() {
		panelContent = new JPanel(new GridLayout(5,1));
		panelContent.setBackground(Constants.PRIMARY_COLOR);
	}
	private void addFieldList() {
		txPassword = new ZPanelTextField(TXT_ID_PRODUCT, "");
		panelContent.add(txPassword);				
	}
	private void addMargins(ActionListener listener) throws IOException {
		XPanel margins = new XPanel(1, 8, 1, 2, 7, 1,
				 new Dimension(Constants.SIZE.width, 350), listener);
		margins.center.add(panelContent);
		margins.addPanels();
		add(margins,BorderLayout.CENTER);
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
		ZButton btnOrder = new ZButton(listener, TXT_ACEPT,Event.DELETE_CLIENT.toString());
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
	
	public String getPassword(){
		return txPassword.getText();
	}
	
	public void placeDCError(String error) {
		if(error.equals(ModelConstants.NONE_ERROR.toString()))
			labelError.setText("");
		else if(error.equals(ModelConstants.EMPTY_ERROR.toString()))
			labelError.setText(Constants.EMPTY_FIELD_ERROR);
		else if(error.equals(ModelConstants.NUMBER_ERROR.toString()))
			labelError.setText(NUMBER_ERROR);
		else if(error.equals(ModelConstants.NOT_EXIST.toString()))
			labelError.setText(NOT_EXIST);

	}

}