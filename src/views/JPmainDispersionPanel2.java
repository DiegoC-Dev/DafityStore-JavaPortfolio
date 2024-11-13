package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import views.components.XPanel;
import views.components.ZButton;

public class JPmainDispersionPanel2 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPDispersionGraphic2 grafics;
	
	public JPmainDispersionPanel2(ActionListener listener,String[] title,String nombreEjes[],
			String CategoriasX[],int[] datos1,String event) throws IOException{
		setLayout(new BorderLayout());
		initialComponents(listener,title,nombreEjes, CategoriasX, datos1,event);
	}

	private void initialComponents(ActionListener listener,String[] title, String[] nombreEjes,
			String CategoriasX[],int[] datos1,String event) throws IOException {
		grafics = new JPDispersionGraphic2(title,nombreEjes, CategoriasX, datos1);
		add(grafics,BorderLayout.CENTER);
		createButtonBar(listener,event);
	}
	private void createButtonBar(ActionListener listener,String event) throws IOException {
		XPanel panelButtons = new XPanel(1, 8, 1, 1, 8, 1, 
				new Dimension(Constants.SIZE.width, 50), listener);
		ZButton btnOrder = new ZButton(listener, "Regresar",event);
		panelButtons.center.add(btnOrder);
		panelButtons.addPanels();
		add(panelButtons, BorderLayout.PAGE_END);
	}
}
