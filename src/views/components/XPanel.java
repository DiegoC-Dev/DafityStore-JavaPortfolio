package views.components;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import presenters.Event;
import views.Constants;

public class XPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public JPanel north;
	public JPanel center;
	public JPanel south;
	public JPanel west;
	public JPanel east;
	private Dimension size;
	public XPanel(int westW,int centerW,int eastW,int northH,int centerH,int southH,Dimension size,ActionListener listener) throws IOException {
		this.size = size;
		setLayout(new BorderLayout());
		assignData(size, Constants.PRIMARY_COLOR,this);
		init(westW,centerW,eastW,northH,centerH,southH,listener);
		setVisible(true);
	}
	private void assignData(Dimension size,Color color,JPanel panel){
		panel.setSize(size);
		panel.setPreferredSize(size);
		panel.setBackground(color);
	}
	private void init(int westW,int centerW,int eastW,int northH,int centerH,int southH,ActionListener listener) {
		createPanels(westW,centerW,eastW,northH,centerH,southH,listener,this);
	}
	private void createPanels(int westW,int centerW,int eastW,int northH,int centerH,int southH,ActionListener listener,JPanel panel) {
		Color northColor = Constants.PRIMARY_COLOR;
		Color southColor = Constants.PRIMARY_COLOR;
		Color eastColor = Constants.PRIMARY_COLOR;
		Color westColor = Constants.PRIMARY_COLOR;
		Color centerColor = Constants.PRIMARY_COLOR;
		Dimension dimension;

		int totalHeight = northH+centerH+southH;
		int totalWidth = westW+centerW+eastW;		
		
		north = new JPanel(new BorderLayout());
		dimension = new Dimension((int)size.getWidth(), 
				(int)size.getHeight()*northH/totalHeight);
		assignData(dimension, northColor, north);
		
		center = new JPanel(new BorderLayout());
		dimension = new Dimension((int)size.getWidth()*centerW/totalWidth,
				(int)size.getHeight()*centerH/totalHeight);
		assignData(dimension, centerColor, center);
		
		south = new JPanel(new BorderLayout());
		dimension = new Dimension((int)size.getWidth(), 
				(int)size.getHeight()*southH/totalHeight);
		assignData(dimension, southColor, south);
		
		west = new JPanel(new BorderLayout());
		dimension = new Dimension((int)size.getWidth()*westW/totalWidth, 
				(int)size.getHeight()*centerH/totalHeight);
		assignData(dimension, westColor, west);
		
		east = new JPanel(new BorderLayout());
		dimension = new Dimension((int)size.getWidth()*eastW/totalWidth,
				(int)size.getHeight()*centerH/totalHeight);
		assignData(dimension, eastColor, east);
	}
	public void addPanels() {
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		add(east,BorderLayout.EAST);
		add(west,BorderLayout.WEST);
		add(south,BorderLayout.SOUTH);
	}
}