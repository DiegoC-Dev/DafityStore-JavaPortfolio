package views;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import presenters.Event;
public class JMmainMenu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private static final String TXT_FILE = "File";
	private static final String TXT_SHOW = "Show product list";
	private static final String TXT_DELETE = "Delete product";
	private static final String TXT_UPDATE = "Update product";
	private static final String TXT_ADD_PRODUCT = "Add product";
	
	
	private JMenu jmFile;
	private JMenuItem jmiAddProduct,jmiUpdateProduct,jmiDeleteProduct,jmiShowProductList;

	public JMmainMenu(ActionListener listener) {
		setBackground(Constants.TERTIARY_COLOR);
		initCompoenents(listener);	
	}
	private void initCompoenents(ActionListener listener) {
		jmFile = new JMenu(TXT_FILE);
		jmFile.setBackground(Constants.TERTIARY_COLOR);
		jmFile.setForeground(Constants.PRIMARY_FONT_COLOR);
		this.add(jmFile);
		
			jmiAddProduct = new JMenuItem(TXT_ADD_PRODUCT);
			jmiAddProduct.setBackground(Constants.TERTIARY_COLOR);
			jmiAddProduct.setForeground(Constants.PRIMARY_FONT_COLOR);
			jmiAddProduct.addActionListener(listener);
			jmiAddProduct.setActionCommand(Event.SHOW_ADD_PRODUCT.toString());
			jmiAddProduct.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
			jmFile.add(jmiAddProduct);
			
			jmiUpdateProduct = new JMenuItem(TXT_UPDATE);
			jmiUpdateProduct.setBackground(Constants.TERTIARY_COLOR);
			jmiUpdateProduct.setForeground(Constants.PRIMARY_FONT_COLOR);
			jmiUpdateProduct.addActionListener(listener);
			jmiUpdateProduct.setActionCommand(Event.SHOW_UPDATE_PRODUCT.toString());
			jmiUpdateProduct.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
			jmFile.add(jmiUpdateProduct);
			
			jmiDeleteProduct = new JMenuItem(TXT_DELETE);
			jmiDeleteProduct.setBackground(Constants.TERTIARY_COLOR);
			jmiDeleteProduct.setForeground(Constants.PRIMARY_FONT_COLOR);
			jmiDeleteProduct.addActionListener(listener);
			jmiDeleteProduct.setActionCommand(Event.SHOW_DELETE_PRODUCT.toString());
			jmiDeleteProduct.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
			jmFile.add(jmiDeleteProduct);

			jmiShowProductList = new JMenuItem(TXT_SHOW);
			jmiShowProductList.setBackground(Constants.TERTIARY_COLOR);
			jmiShowProductList.setForeground(Constants.PRIMARY_FONT_COLOR);
			jmiShowProductList.addActionListener(listener);
			jmiShowProductList.setActionCommand(Event.SHOW_PRODUCT_LIST.toString());
			jmiShowProductList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
			jmFile.add(jmiShowProductList);

	}
}