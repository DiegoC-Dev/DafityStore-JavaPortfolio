package views;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class BMainBorder extends TitledBorder{
	private static final long serialVersionUID = 1L;

	public BMainBorder(String title) {
		super(title);
		Border line = BorderFactory.createLineBorder(Constants.PRIMARY_COLOR, 1);
//		Border titledBorder = BorderFactory.createTitledBorder(line,title, TitledBorder.CENTER,
//				TitledBorder.TOP, null, Constants.SECUNDARY_FONT_COLOR);
//		Border t = BorderFactory.createTitledBorder(titledBorder, title, titleJustification,
//				titlePosition, titleFont, titleColor);
		setBorder(line);
		setTitleJustification(TitledBorder.CENTER);
		setTitlePosition(TitledBorder.TOP);
		setTitleColor(Constants.SECUNDARY_FONT_COLOR);
	}
}
