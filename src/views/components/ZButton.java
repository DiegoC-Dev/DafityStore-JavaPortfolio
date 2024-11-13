package views.components;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import views.Constants;

public class ZButton extends JButton{
	private static final long serialVersionUID = 1L;
	private static final Color COLOR_LIGTH_GRAY = Color.decode("#969696");
	private static final Border BTN_LINE_BORDER = BorderFactory.createLineBorder(
			COLOR_LIGTH_GRAY, 1, true);
	
	public ZButton(ActionListener listener,String text,String event) {	
		addActionListener(listener);
		setText(text);
		setToolTipText(text);
		setBackground(Constants.SECUNDARY_COLOR);
//		setBackground(Color.white);
		setActionCommand(event);
//		setFont(Constants.MAIN_FONT);
		setForeground(Constants.PRIMARY_FONT_COLOR);
//		setForeground(Color.black);
		setFocusable(false);
		setBorder(BTN_LINE_BORDER);

	}
}
