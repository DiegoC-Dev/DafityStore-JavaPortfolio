package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import presenters.Event;
import views.components.XPanel;
import views.components.ZButton;

public class JPManagerMenu extends JPanel{
	private static final String TXT_EXIT = "Salir";
	private static final String MENU_CLIENT = "  Menu Administrador";
	public static final Dimension SIZE= new Dimension(1000, 1000);
	private static final long serialVersionUID = 1L;
	private GradientButtonManager gradientButton;
	private JPanel panelScroll;
	private JPanel panelContent;
	
	public JPManagerMenu(ActionListener listener,int sizeMargin, Color background, int width, int height, LayoutManager layout,Point2D point2d) throws IOException {
		setLayout(new BorderLayout());
		setSize(SIZE);
		setPreferredSize(SIZE);
		setBackground(Constants.PRIMARY_COLOR);
		init(listener,sizeMargin,background,width,height,layout,point2d);
		setVisible(true);
	}

	private void init(ActionListener listener,int sizeMargin, Color background, int width, int height, LayoutManager layout,Point2D point2d) throws IOException {
		gradientButtonPanel(listener, point2d);
		createReportPanel(listener,sizeMargin,background,width,height,layout);
		createButton(listener);
	}

	private void createReportPanel(ActionListener listener,int sizeMargin, Color background, int width, int height, LayoutManager layout) {
		panelScroll = new JPanel(new GridLayout());
		
		ReportPanel reportPanel = new ReportPanel(listener, sizeMargin, background, width, height, layout);
		panelScroll.add(reportPanel, BorderLayout.CENTER);
		
		add(new JScrollPane(panelScroll),BorderLayout.CENTER);
		
	}

	private void gradientButtonPanel(ActionListener listener, Point2D point2d) {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel buttonPanel = new JPanel(new BorderLayout());
		mainPanel.add(buttonPanel,BorderLayout.WEST);
		
		gradientButton = new GradientButtonManager(point2d,listener);
		buttonPanel.add(gradientButton,BorderLayout.CENTER);
		
		JPanel labelPanel = new JPanel(new BorderLayout());
		labelPanel.setBackground(Constants.PRIMARY_COLOR);
		
		JLabel lb = new JLabel(MENU_CLIENT);
		lb.setFont(Constants.MAIN_FONT);
		lb.setForeground(Constants.PRIMARY_FONT_COLOR);
		lb.setBackground(Constants.PRIMARY_COLOR);
		lb.setHorizontalAlignment(SwingConstants.LEFT);
		labelPanel.add(lb);
		
		mainPanel.add(labelPanel,BorderLayout.CENTER);
		
		add(mainPanel,BorderLayout.NORTH);
	}
	private void createButton(ActionListener listener) throws IOException {
		XPanel panelButtons = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		
		ZButton btnExit = new ZButton(listener,TXT_EXIT,Event.JP_LOGIN.toString());
		panelButtons.center.add(btnExit);
		
//		btnExit = new ZButton(listener, TXT_EXIT, Event.JP_LOGIN.toString());
//		modifyConstraints(c,1,8,11,1,0.5,btnExit);
//		add(btnExit,c);		

		
		panelButtons.addPanels();
		add(panelButtons, BorderLayout.PAGE_END);
	}
	public void sendCoordinates(Point point2d) {
		gradientButton.sendCoordinates(point2d);
	}
	public void hideMenu() {
		gradientButton.hideMenu();
	}
}