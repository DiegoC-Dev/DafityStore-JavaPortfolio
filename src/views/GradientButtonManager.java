package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.JButton;

public class GradientButtonManager extends JButton implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JDmainMenuManager jDmainMenu;
	private int clicks;
	private Point2D location;
	public GradientButtonManager(Point2D point2d,ActionListener listener) {
		clicks = 1;
	 	this.addActionListener(this);
	 	location = point2d;
	 	jDmainMenu = new JDmainMenuManager((int)point2d.getX()+500, (int)point2d.getY()+500,
	 			listener);
	 	jDmainMenu.setVisible(true);
	}
	private Color color1 = Constants.PRIMARY_COLOR;
	private Color color2 = Constants.SECUNDARY_COLOR;

    @Override
    protected void paintComponent(Graphics g) {
    	int lineHeight =getHeight()/6;
    	int spacingWidth =getWidth()/6;
    	int cont1= 0;
    	
    	Graphics2D g2 = (Graphics2D) g.create();
    	
		g2.setColor(Constants.PRIMARY_COLOR);
    	g2.fillRect(0, 0, getWidth(), getHeight());
    	
    	for (int i = 0; i < getHeight(); i++) {
    		g2.setColor(cont1<=lineHeight?color1:color2);
    		g2.drawLine(spacingWidth, i, getWidth()-spacingWidth, i);
    		cont1=cont1==lineHeight*2?0:cont1;    		
    		cont1++;
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		clicks=clicks+1;
		jDmainMenu.setVisible(true);
		try {
			if(clicks%2==0) {
				for (int i = 0; i < 220; i++) {
					jDmainMenu.setX((int)location.getX());
					jDmainMenu.setY((int)location.getY());
					Thread.sleep(6);
					jDmainMenu.setWidth(i);
				}	
			}
			if(clicks%2!=0) {
				for (int i = 220; i > 0; i--) {
					jDmainMenu.setX((int)location.getX());
					jDmainMenu.setY((int)location.getY());
					Thread.sleep(5);
					jDmainMenu.setWidth(i);
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	public void sendCoordinates(Point point2d) {
		location=point2d;
		jDmainMenu.setX((int)location.getX());
		jDmainMenu.setY((int)location.getY());
	}

	public void hideMenu () {
		clicks--;
		jDmainMenu.setVisible(false);
	}
}