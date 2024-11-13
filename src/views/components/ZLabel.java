package views.components;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import views.Constants;

public class ZLabel extends JLabel{
	private static final long serialVersionUID = 1L;

	public ZLabel(String path,int Width,int Height) {
		Image image= new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(image));
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	public ZLabel(String text,Color color) {
		setText(text);
		setForeground(color);
		setBackground(Constants.PRIMARY_COLOR);
		setHorizontalAlignment(JLabel.CENTER);
	}

}
