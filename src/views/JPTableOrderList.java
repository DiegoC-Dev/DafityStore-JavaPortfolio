package views;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Order;

public class JPTableOrderList extends JPanel{

	private static final String TXT_DISCOUNT_VALUE = "Descuento";
	private static final String TXT_BASE_VALUE = "Valor base";
	private static final String TXT_TOTAL_VALUE = "Valor Final";
	private static final String TXT_DATE = "Fecha";
	private static final String TXT_QUANTITY = "Cantidad";
	private static final String TXT_CLIENT = "Cliente";
	private static final String TXT_PRODUCT = "Producto";
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements; 
	private JTable jtElements;
	private JScrollPane jsTable;

	public JPTableOrderList(ActionListener listener){
		initComponents();
		setVisible(true);
	}

	private void initComponents(){
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		dtmElements = new DefaultTableModel();
		changeLanguageColunmJtable();

		jtElements = new JTable();
		jtElements.setModel(dtmElements);
		jtElements.getTableHeader().setReorderingAllowed(false);
		jtElements.getTableHeader().setBackground(Constants.TERTIARY_COLOR);
		jtElements.getTableHeader().setForeground(Constants.PRIMARY_FONT_COLOR);
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
		String[] headers = {TXT_PRODUCT,TXT_CLIENT,TXT_QUANTITY,TXT_DATE,
				TXT_BASE_VALUE,TXT_DISCOUNT_VALUE,TXT_TOTAL_VALUE};
		dtmElements.setColumnIdentifiers(headers);
		repaint();
	}

	public void repaintTable(ArrayList<Order> list) {
		removeElements(list);
		addElements(list);
	}

	public void addElements(ArrayList<Order> list) {
		for (int i = 0; i < list.size(); i++) {
			dtmElements.addRow(list.get(i).toStringVector());
		}
	}

	private void removeElements(ArrayList<Order> list) {
		dtmElements.getDataVector().removeAllElements();
	}

}