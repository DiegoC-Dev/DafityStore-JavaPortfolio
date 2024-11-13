package views.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import views.BMainBorder;
import views.Constants;

public class ZPanelTextField extends JPanel{
	private static final long serialVersionUID = 1L;
	private ZTextField textField;

	public ZPanelTextField(String text) {
		setLayout(new BorderLayout());
		textField = new ZTextField(text);
		add(textField, BorderLayout.CENTER);
	}
	public ZPanelTextField(String borderText,String text) {
		setLayout(new BorderLayout());
		textField = new ZTextField(text);
		textField.setBorder(new BMainBorder(borderText));
		add(textField, BorderLayout.CENTER);
	}
	
	public String getText() {
		return textField.getText();
	}
	public void setText(String text){
		textField.setText(text);
	}
}