package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Product;

public class JPTableProductList extends JPanel{
	private static final String TXT_STOCK = "Stock";
	private static final String TXT_SIZE = "Talla";
	private static final String TXT_DISCOUNT = "Descuento %";
	private static final String TXT_PRICE = "Precio";
	private static final String TXT_NAME = "Nombre";
	private static final String TXT_ID = "Id";
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements; 
	private JTable jtElements;
	private JScrollPane jsTable;

	public JPTableProductList(ActionListener listener){
		initComponents();
		setVisible(true);
	}

	public JPTableProductList(){

	}

	private void initComponents(){
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		dtmElements = new DefaultTableModel();
		changeLanguageColunmJtable();

		jtElements = new JTable();
		jtElements.setModel(dtmElements);
		jtElements.getTableHeader().setReorderingAllowed(false);
		jtElements.getTableHeader().setBackground(Constants.TERTIARY_COLOR);
		jtElements.getTableHeader().setForeground(Color.white);
		jtElements.getTableHeader().setFont(Constants.MAIN_FONT);
		jtElements.setBackground(Constants.PRIMARY_COLOR);
		jtElements.setBorder(null);

		jsTable = new JScrollPane(jtElements);
		jsTable.setFont(Constants.MAIN_FONT);
		jsTable.setForeground(Constants.PRIMARY_FONT_COLOR);
		jsTable.setBorder(null);
		jsTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(jsTable, BorderLayout.PAGE_END);
		this.setBorder(null);
	}
	public void changeLanguageColunmJtable(){
		String[] headers = {TXT_NAME,TXT_PRICE,TXT_DISCOUNT,TXT_SIZE,TXT_STOCK};
		dtmElements.setColumnIdentifiers(headers);
		repaint();
	}
	public void repaintTable(ArrayList<Product> list) {
		removeElements(list);
		addElements(list);
	}

	public void addElements(ArrayList<Product> list) {
		for (int i = 0; i < list.size(); i++) {
			dtmElements.addRow(list.get(i).toStringVector());
		}
	}

	private void removeElements(ArrayList<Product> list) {
		dtmElements.getDataVector().removeAllElements();
	}
}