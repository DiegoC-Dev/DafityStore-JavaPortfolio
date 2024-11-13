package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;
import views.components.XPanel;
import views.components.ZButton;

public class JPmainBarsPanel extends JPanel{
	private static final String TXT_RETURN = "Regresar";
	private static final long serialVersionUID = 1L;
	private JPBarsGraphic grafics;
	
	public JPmainBarsPanel(ActionListener listener,String[] title,String nombreEjes[],
		String CategoriasX[],int[] datos,String event) throws IOException{
		setLayout(new BorderLayout());
		initialComponents(listener,title,nombreEjes, CategoriasX, datos,event);
	}
	public JPmainBarsPanel(ActionListener listener,String event) throws IOException {
		setLayout(new BorderLayout());
		initialComponents(listener,
				new String[]{"","",""},
				new String[] {"",""},new String[] {"","",""}, 
				new int[] {1,1,1},event);
	}

	private void initialComponents(ActionListener listener,String[] title, String[] nombreEjes,
			String CategoriasX[],int[] datos,String event) throws IOException {
		grafics = new JPBarsGraphic(title,nombreEjes, CategoriasX, datos);
		add(grafics,BorderLayout.CENTER);
		createButtonBar(listener,event);
	}
	private void createButtonBar(ActionListener listener,String event) throws IOException {
		XPanel panelButtons = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnOrder = new ZButton(listener, TXT_RETURN,event);
		panelButtons.center.add(btnOrder);
		panelButtons.addPanels();
		add(panelButtons, BorderLayout.PAGE_END);
	}
	public void refreshBarGraphic(String[] title, String[] nombresEjes, String[] nombresEjeX, int[] valores) {
		grafics.init(title, nombresEjes, nombresEjeX, valores);
	}
	

}
