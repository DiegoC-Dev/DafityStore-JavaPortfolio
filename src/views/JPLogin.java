package views;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import models.ModelConstants;
import presenters.Event;
import views.components.ZButton;
import views.components.ZLabel;
import views.components.ZPanelTextField;
import views.components.ZPanel;

public class JPLogin extends ZPanel {
	private static final String TXT_REGISTRATION = "Registrarse";
	private static final String TXT_ACCESS = "Acceder";
	private static final String TXT_PASSWORD = "Contraseña";
	private static final String TITLE_PASSWORD = "Contraseña : ";
	private static final String TXT_USUARIO = "Usuario";
	private static final String TITLE_USER = "Usuario :";
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "Datos incorrectos";
	private ZLabel lbImage;
	private ZPanelTextField txUser;
	private ZPanelTextField txPassword;
	private ZLabel labelError;
	private ZButton btnAccept;
	private ZButton btnRegistration;
	private static final String MAIN_IMG = "/img/MainImage.png";

	public JPLogin(ActionListener listener, int sizeMargin, Color background, int width, int height,
			LayoutManager layout) {
		super(sizeMargin, background, width, height, layout);
		init(listener);
	}

	private void init(ActionListener listener) {
		lbImage = new ZLabel(MAIN_IMG, 500, 200);
		modifyConstraints(c, 1, 1, 11, 1, 2.5, lbImage);
		add(lbImage, c);

		txUser = new ZPanelTextField(TITLE_USER,"");
//		txUser = new ZPanelTextField(TITLE_USER, TXT_USUARIO);
//		txUser = new ZPanelTextField(TITLE_USER, TXT_USUARIO+"0");
		modifyConstraints(c, 4, 2, 5, 1, 0.5, txUser);
		add(txUser, c);

//	    txPassword = new ZPanelTextField(TITLE_PASSWORD,"a100");
	    txPassword = new ZPanelTextField(TITLE_PASSWORD,"");
//		txPassword = new ZPanelTextField(TITLE_PASSWORD, TXT_PASSWORD);
		modifyConstraints(c, 4, 3, 5, 1, 0.5, txPassword);
		add(txPassword, c);

		labelError = new ZLabel("", Color.red);
		modifyConstraints(c, 4, 4, 5, 1, 0.5, labelError);
		add(labelError, c);

		btnAccept = new ZButton(listener, TXT_ACCESS, Event.JP_ACCESS.toString());
		modifyConstraints(c, 1, 5, 11, 1, 0.5, btnAccept);
		add(btnAccept, c);

		btnRegistration = new ZButton(listener, TXT_REGISTRATION, Event.SHOW_REGISTRATION.toString());
		modifyConstraints(c, 1, 6, 11, 1, 0.5, btnRegistration);
		add(btnRegistration, c);
	}

	public String getUser() {
		return txUser.getText();
	}

	public String getPassword() {
		return txPassword.getText();
	}

	public void placeLoginError(String access) {
		if (access.equals(ModelConstants.ACC_DENIED.toString()))
			labelError.setText(ERROR_MESSAGE);
		else if (access.equals(ModelConstants.NUMBER_ERROR.toString()))
			labelError.setText("Usuario debe ser numerico");
		else
			labelError.setText("");

	}

	public void resetForm() {
//		txUser.setText(TXT_USUARIO);
//		txPassword.setText(TXT_PASSWORD);
		txUser.setText("");
		txPassword.setText("");
		revalidate();
	}

}
