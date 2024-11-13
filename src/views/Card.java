package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import models.Product;
import presenters.Event;

public class Card extends JPanel{

	private static final Color COLOR_LIGTH_GRAY = Color.decode("#969696");
	private static final Border BTN_LINE_BORDER = BorderFactory.createLineBorder(
			COLOR_LIGTH_GRAY, 1, true);
	private static final long serialVersionUID = 1L;
	private static final String TXT_BUY = "Comprar";
	private static final String BUY_ICON_PATH = "/img/Buy.png";
	
	public Card(ActionListener listener, Product componentList) {
		setLayout(new BorderLayout());
		setBackground(Constants.PRIMARY_COLOR);
		
		CentralComponents(componentList);
		createButtonBar(listener, componentList);
	}

	private void CentralComponents(Product componentList) {
		JPanel centralPanel = new JPanel(new GridLayout(2, 1));
		centralPanel.setBackground(Constants.PRIMARY_COLOR);
		
		JLabel lbImg = new JLabel();
		Image image= new ImageIcon(componentList.getImgPath()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		lbImg.setBackground(Constants.PRIMARY_COLOR);
		lbImg.setIcon(new ImageIcon(image));
		lbImg.setHorizontalAlignment(SwingConstants.CENTER);
		centralPanel.add(lbImg);
		
		createLabelsBar(componentList, centralPanel);
		

		add(centralPanel);
	}

	private void createLabelsBar(Product componentList, JPanel centralPanel) {
		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.setBackground(Constants.PRIMARY_COLOR);
		
		JLabel lbName = new JLabel(""+componentList.getName(),SwingConstants.CENTER);
		lbName.setFont(Constants.MAIN_FONT);
		lbName.setForeground(Constants.SECUNDARY_FONT_COLOR);
		panel.add(lbName,BorderLayout.CENTER);
		
		JLabel lbId = new JLabel(""+componentList.getPrice(),SwingConstants.CENTER);
		lbId.setFont(Constants.MAIN_FONT);
		lbId.setForeground(Constants.SECUNDARY_FONT_COLOR);
		panel.add(lbId);

		JLabel lbPath = new JLabel(""+componentList.getSize(),SwingConstants.CENTER);
		lbPath.setFont(Constants.MAIN_FONT);
		lbPath.setForeground(Constants.SECUNDARY_FONT_COLOR);
		panel.add(lbPath);

		centralPanel.add(panel);
	}

	private void createButtonBar(ActionListener listener, Product ComponentList) {
		JPanel panelButtons = new JPanel(new GridLayout(1, 2));
		
		JButton btnBuy = new JButton(new ImageIcon(getClass().getResource(BUY_ICON_PATH)));
//		JButton btnBuy = new JButton(TXT_BUY);
		btnBuy.addActionListener(listener);
		btnBuy.setToolTipText(TXT_BUY);
		btnBuy.setBackground(Constants.SECUNDARY_COLOR);
		btnBuy.setActionCommand(Event.BUY.toString());
		btnBuy.setName(String.valueOf(ComponentList.getName()));
		btnBuy.setFont(Constants.MAIN_FONT);
		btnBuy.setForeground(Constants.PRIMARY_FONT_COLOR);		
		btnBuy.setFocusable(false);
		btnBuy.setBorder(BTN_LINE_BORDER);
		panelButtons.add(btnBuy);
		
		add(panelButtons, BorderLayout.PAGE_END);
	}
}