package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import presenters.Event;


public class JDmainMenuClient extends JDialog{
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private String[] nameList;
	private String[] eventList;

	public JDmainMenuClient(int x, int y,ActionListener listener) {
		eventList = new String[] {"SHOW_PROFILE","SHOW_UPDATE_CLIENT"}; 
		nameList = new String[] {"Ver perfil","Actualizar perfil"}; 
		setLayout(new GridLayout(nameList.length,1));
		setUndecorated(true);
		initComponents(listener);
		setAlwaysOnTop(true);
	}
	private void initComponents(ActionListener listener) {
		buttonSection(listener);
	}	
	private void buttonSection(ActionListener listener) {
		for (int i = 0; i < nameList.length; i++) {
			JButton button = new JButton(""+i);
			
			button.addActionListener(listener);
			button.setActionCommand(Event.valueOf(eventList[i]).toString());
			button.setBackground(Constants.TERTIARY_COLOR);
			button.setText(nameList[i]);
			button.setForeground(Constants.PRIMARY_FONT_COLOR);
//			button.setBorder(new RoundedBorder(0, Constants.PRIMARY_COLOR));
			button.setFont(Constants.MAIN_FONT);
			button.setName(""+i);
			button.setFocusable(false);
			add(button);
		}
	}
	public void setX(int x) {
		setLocation(x+3, y+64);
		this.x = x;
	}
	public void setY(int y) {
		setLocation(x+3, y+64);
		this.y = y;
	}
	public void setWidth(int width){
		setSize(width, 50*nameList.length);
	}
}