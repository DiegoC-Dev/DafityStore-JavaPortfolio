package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Client;
import models.Location;

public class JPTableClientList extends JPanel{

	private static final String TXT_USER = "Usuario";
	private static final String TXT_BIRTH_DAY = "F. nacimiento";
	private static final String TXT_CITY = "Ciudad";
	private static final String TXT_ID = "Documento Id";
	private static final String TXT_PASSWORD = "Contraseña";
	private static final String TXT_NAME = "Nombre";
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements; 
	private JTable jtElements;
	private JScrollPane jsTable;

	public JPTableClientList(ActionListener listener){
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
		String[] headers = {TXT_USER,TXT_PASSWORD,TXT_NAME,TXT_ID,TXT_CITY,TXT_BIRTH_DAY};
//		String[] headers = {TXT_NAME,TXT_ID,TXT_CITY,TXT_BIRTH_DAY};
		dtmElements.setColumnIdentifiers(headers);
		repaint();
	}
	public void repaintTable(ArrayList<Client> list) {
		removeElements(list);

		addElements(list);
	}

	public void addElements(ArrayList<Client> list) {
		String data[];
		for (int i = 0; i < list.size(); i++) {
			data = list.get(i).toStringVector();
//			System.out.println("cuidad..."+data[2]);
////			data[2] = 
////				idToName(Integer.parseInt(data[2]));
			dtmElements.addRow(data);
//			dtmElements.addRow(list.get(i).toStringVector());
		}
	}
//	public String idToName(int id) {
//		String name = "";
//		Location location = null;
//		for (int i = 0; i < locationList.size(); i++) {
//			location = locationList.get(i);
//			if(location.getId()==id)
//				name = location.getName();
//		}
//		return name;
//	}

	private void removeElements(ArrayList<Client> list) {
		dtmElements.getDataVector().removeAllElements();
	}
}