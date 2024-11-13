package views.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JTextField;

import views.Constants;

public class ZTextField extends JTextField{
	private static final long serialVersionUID = 1L;

	public ZTextField(String text) {
		setText(text);
		setBorder(null);
		setColumns(10);
		setHorizontalAlignment(JTextField.CENTER);
		createToolTip();
		setFont(Constants.MAIN_FONT);
		setBackground(Constants.PRIMARY_COLOR);
		setForeground(Constants.PRIMARY_FONT_COLOR);
		setFont(Constants.FIELD_FONT);
//		setForeground(Color.black);
		setOpaque(true);
	}
	@Override 
	protected void paintComponent(Graphics g) { 
	     super.paintComponent(g); 
		g.setColor(Constants.SECUNDARY_COLOR);//
//		g.drawRoundRect(2, 2, getWidth()-4, getHeight()-4, 20, 20);
//		g.drawRect(2, 2, getWidth()-4, getHeight()-4);
		g.drawLine(0,getHeight()-2, getWidth(), getHeight()-2);
		g.drawLine(0,getHeight()-4, getWidth(), getHeight()-4);
	} 

}
