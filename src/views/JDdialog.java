package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

import views.components.ZLabel;

public class JDdialog extends JDialog{
	
	private static final int Y_DISTANCE_OUTSIDE = 
			(int) ((Constants.SIZE.getHeight()/2)-
					(Constants.SIZE_DIALOG.getHeight()/2));
	private static final int X_DISTANCE_OUTSIDE = 
			(int) ((Constants.SIZE.getWidth()/2)-
					(Constants.SIZE_DIALOG.getWidth()/2));;
	private static final String ICON_PATH = "/img/Icon.png";
	private static final String TITLE = "Alerta";
	private static final long serialVersionUID = 1L;
	private Point2D location;
	private int x;
	private int y;
	private ZLabel alert;
	public JDdialog() {
		setSize(Constants.SIZE_DIALOG);
		setLocation((int)(Constants.SCREEN_SIZE.getWidth()/2)-(getWidth()/2), 
				(int)(Constants.SCREEN_SIZE.getHeight()/2)-(getHeight()/2));
		setLayout(new BorderLayout());
		setTitle(TITLE);
		setIconImage(new ImageIcon(getClass().getResource(ICON_PATH)).getImage());
		setBackground(Constants.PRIMARY_COLOR);
		setAlwaysOnTop(true);
		setResizable(false);
		init();
	}
	private void init() {
		JPanel content = new JPanel(new GridLayout(1, 1));
		content.setBackground(Constants.SECUNDARY_COLOR);
		alert = new ZLabel("", Constants.PRIMARY_FONT_COLOR);
//		alert = new ZLabel("", Constants.PRIMARY_COLOR);
		alert.setFont(Constants.FIELD_FONT);
		content.add(alert);
		add(content,BorderLayout.CENTER);
	}
	public void sendCoordinates(Point point2d) {
		location = point2d;
		setX((int)location.getX());
		setY((int)location.getY());
	}
	public void setX(int x) {
		setLocation(x+X_DISTANCE_OUTSIDE, y+Y_DISTANCE_OUTSIDE);
		this.x = x;
	}
	public void setY(int y) {
		setLocation(x+X_DISTANCE_OUTSIDE, y+Y_DISTANCE_OUTSIDE);
		this.y = y;
	}
	public void setDialogText(String text) {
		alert.setText(text);
	}

}
