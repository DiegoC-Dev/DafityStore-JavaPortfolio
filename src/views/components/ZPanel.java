package views.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ZPanel extends JPanel{
	private static final Dimension INITIAL_DIMENSION = new Dimension(0, 0);
	private	Dimension dimension;
	protected GridBagConstraints c; 
	public ZPanel(int sizeMargin, Color background, int width, int height, LayoutManager layout) {
		super();
		this.dimension = new Dimension(width, height);
		setSize(width, height);
		if(width!=0 || height!=0) 			
		setPreferredSize(new Dimension(dimension));
		setLayout(layout);
		setBackground(background);
		setBorder(new LineBorder(background, sizeMargin));
		if(layout.toString().equals("java.awt.GridBagLayout")){
			c = new GridBagConstraints();		
			generateBasicGrid(c);
		}
			
	}
	private void generateBasicGrid(GridBagConstraints c){
		c.fill = GridBagConstraints.BOTH;
//		modifyConstraints(c, 10, Abajo, 1, 0,Arriba,null);//margenes
//		modifyConstraints(c, 0, 0, 1, 1,0.1,null);
		modifyConstraints(c, 0, 7, 1, 1,0.15,null);
//		c.insets = new Insets(0,0,10,0);		
		c.insets = new Insets(0,0,0,0);		
		int columns = 13;
		for (int i = 0; i < columns; i++) {
			c.gridx = i;
//			add(new JLabel("" + i), c);
			add(new JLabel(""), c);
		}
	}
	protected void modifyConstraints(GridBagConstraints c,int x,int y,int weight,int height,double heightUnity,Component component) {
//		c.insets = new Insets(10,0,10,0);		
		c.insets = new Insets(2,0,2,0);	// margenes entre componentes	
		c.weightx = 1;	
		c.gridheight = height;	
		c.weighty = heightUnity;
		c.gridx = x;	
		c.gridy = y;	
		c.gridwidth = weight;
		if(component!=null) {
			Dimension initialDimension = new Dimension(0, 0);
			component.setPreferredSize(initialDimension);
		}
	}
	public Dimension getDimension() {
		return dimension;
	}
}
