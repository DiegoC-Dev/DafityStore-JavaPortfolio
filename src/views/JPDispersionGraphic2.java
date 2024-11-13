package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;


public class JPDispersionGraphic2 extends JPanel{

	private static final long serialVersionUID = 1L;

	public static final double HEIGTH_OF_DIVISIONS_AXIS_X= 0.01;
	public static final double WIDTH_OF_DIVISIONS_AXIS_Y= 0.005;
	
	private Color backgroundColor = Constants.TERTIARY_COLOR;
	private Color gridColor = Constants.SECUNDARY_COLOR;
	private Color axesColor = Constants.SECUNDARY_COLOR;
	private Color sheetColor = Constants.PRIMARY_COLOR;


//	private Color backgroundColor = Color.gray;
//	private Color gridColor = Color.lightGray;
//	private Color sheetColor = Color.white;
//	private Color axesColor = Color.black;
	private Color valuesUseColor = Constants.TERTIARY_COLOR;
	
	private static String [] namesAxisX;
	private ArrayList<Integer> NamesAxisY;
	private static String [] namesOfAxes;
	private int [] values;
	int dividerValue;
	String[] titleOfTheGraphic;
	private int maxDataValY,maxValY,maxValYFinal,maxValX;
	private int X1_Axis_Y,Y1_Axis_Y,X2_Axis_Y,Y2_Axis_Y,X2_Axis_X,Y2_Axis_X;
	private int X1_DivisionLine,Y1_DivisionLine,X2_DivisionLine,Y2_DivisionLine;
	private int X1_FrameWork,Y1_FrameWork,X2_FrameWork,Y2_FrameWork;
	
	public JPDispersionGraphic2(String[] title,String[] nombresEjes,String[] nombresEjeX,int[]valores1){
		setBackground(backgroundColor);
		values =valores1;
		NamesAxisY = findYAxisValues();
		namesAxisX = nombresEjeX;
		namesOfAxes = nombresEjes;
		titleOfTheGraphic=title;
	}
	public void paintComponent(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		super.paintComponent(graphics);
		calculatePoints();
		addTitle(graphics2d);
		drawFramework(graphics2d);
		drawAxes(graphics2d);
		drawDivisionsInTheAxes(graphics2d);
		drawBars(graphics2d);
		drawGrid(graphics2d);
		addValuesLebels(graphics2d);
	}
	private void addTitle(Graphics2D graphics2d) {
		graphics2d.setColor(axesColor);
		for (int i = 0; i < titleOfTheGraphic.length; i++) {
			graphics2d.drawString(titleOfTheGraphic[i], 
			(int)(X2_Axis_Y+(X2_Axis_X-X2_Axis_Y)*0.47), 
			(int)((getHeight()*(0.1))+(i*15)));
		}
	}
	private void drawFramework(Graphics2D graphics2d) {
		graphics2d.setColor(sheetColor);
		graphics2d.fillRect(X1_FrameWork, Y1_FrameWork, X2_FrameWork-X1_FrameWork, Y2_FrameWork-Y1_FrameWork);
	}
	private void drawAxes(Graphics2D graphics2d) {
		graphics2d.setStroke(new BasicStroke(3f));
		graphics2d.setColor(axesColor);
		calculatePoints();
		graphics2d.drawLine(X1_Axis_Y,Y1_Axis_Y,X2_Axis_Y,Y2_Axis_Y);
		graphics2d.drawLine(X2_Axis_Y,Y2_Axis_Y,X2_Axis_X,Y2_Axis_X);
		graphics2d.drawString(namesOfAxes[0], X1_Axis_Y-((int)(getWidth()*(0.02+0.06))), Y1_Axis_Y+(Y2_Axis_Y-Y1_Axis_Y)/2);
		graphics2d.drawString(namesOfAxes[1], (int)(X2_Axis_Y+(X2_Axis_X-X2_Axis_Y)*0.47), Y2_Axis_Y+((int)(getHeight()*(0.02+0.05))));
		drawXTriangle(graphics2d);
		drawYTriangle(graphics2d);

	}
	private void drawXTriangle(Graphics2D graphics2d) {
		graphics2d.drawPolygon(new int[]{X2_Axis_X,X2_Axis_X,X2_FrameWork},	
				new int[]{Y2_Axis_Y+(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X),Y2_Axis_Y-(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X),
						Y2_Axis_Y}, 3);
		graphics2d.fillPolygon(new int[]{X2_Axis_X,X2_Axis_X,X2_FrameWork},	
				new int[]{Y2_Axis_Y+(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X),Y2_Axis_Y-(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X),
						Y2_Axis_Y}, 3);
	}
	private void drawYTriangle(Graphics2D graphics2d) {
		graphics2d.drawPolygon(new int[]{X1_Axis_Y+((int)(getWidth()*WIDTH_OF_DIVISIONS_AXIS_Y)),
				X1_Axis_Y-((int)(getWidth()*WIDTH_OF_DIVISIONS_AXIS_Y)),X1_Axis_Y},	
				new int[]{Y1_Axis_Y,Y1_Axis_Y,Y1_FrameWork}, 3);
		graphics2d.fillPolygon(new int[]{X1_Axis_Y+((int)(getWidth()*WIDTH_OF_DIVISIONS_AXIS_Y)),
				X1_Axis_Y-((int)(getWidth()*WIDTH_OF_DIVISIONS_AXIS_Y)),X1_Axis_Y},	
				new int[]{Y1_Axis_Y,Y1_Axis_Y,Y1_FrameWork}, 3);
	}
	private void calculatePoints(){
		X1_Axis_Y=(int) (getWidth()*0.2);
		Y1_Axis_Y=(int) (getHeight()*0.2);
		X2_Axis_Y=(int) (getWidth()*0.2);
		Y2_Axis_Y=(int) (getHeight()*0.8);
		X2_Axis_X=(int) (getWidth()*0.8);
		Y2_Axis_X=(int) (getHeight()*0.8);
		maxValY =(int)(Y2_Axis_Y-((Y2_Axis_Y-Y1_Axis_Y) *0.96));
		maxValX =(int)(X2_Axis_Y+((X2_Axis_X-X2_Axis_Y)*0.98));
		X1_FrameWork=X1_Axis_Y;
		X2_FrameWork=X2_Axis_X+((int)(getWidth()*HEIGTH_OF_DIVISIONS_AXIS_X));
		Y1_FrameWork=Y1_Axis_Y-((int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X));
		Y2_FrameWork=Y2_Axis_Y;
	}
	private void drawDivisionsInTheAxes(Graphics2D graphics2d) {
		drawDivisionsInTheAxisY(graphics2d);
		drawDivisionsInTheAxisX(graphics2d);
	}
	private double findMaxValue() {
		double maxValue=0;
		for (int i = 0; i < values.length; i++) {
			if(maxValue<values[i]) {
				maxValue=values[i];
			}
		}
		
		return maxValue;
	}
	private void findMaxYvalue(){ 
		double maxValue=findMaxValue();
		int cont= 0;
		while (maxValue/10>1) {
			maxValue = (int) (maxValue/10);
			cont++;
		}

		dividerValue = (int) Math.pow(10, cont);
		maxValue=(maxValue+1)*Math.pow(10, cont);
		maxDataValY=(int)maxValue;
	}
	private ArrayList<Integer> findYAxisValues(){ 
		findMaxYvalue();
		ArrayList<Integer> yAxisValues = new ArrayList<Integer>();
		for (int i = 0; i <= maxDataValY; i=i+dividerValue) {
			yAxisValues.add(i);
		}
		return yAxisValues;
	}
	private void drawDivisionsInTheAxisX(Graphics2D graphics2d) {
		for (int i = 1; i <= namesAxisX.length; i++) {
			X1_DivisionLine=X2_Axis_Y+((((maxValX-X2_Axis_Y)/(namesAxisX.length+1))*(i)));
			Y1_DivisionLine=Y2_Axis_Y+(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X);
			X2_DivisionLine=X1_DivisionLine;
			Y2_DivisionLine=Y2_Axis_Y-(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X);
				graphics2d.drawString(namesAxisX[i-1], X1_DivisionLine, Y1_DivisionLine+((int)(getHeight()*(0.015))));
		}
	}
	private void drawDivisionsInTheAxisY(Graphics2D graphics2d) {
		for (int i = 0; i < NamesAxisY.size(); i++) {
			X1_DivisionLine=X1_Axis_Y+((int)(getWidth()*WIDTH_OF_DIVISIONS_AXIS_Y));
			Y1_DivisionLine=Y2_Axis_Y-(((Y2_Axis_Y-maxValY)/(NamesAxisY.size()-1))*(i));
			X2_DivisionLine=X2_Axis_Y-((int)(getWidth()*WIDTH_OF_DIVISIONS_AXIS_Y));
			Y2_DivisionLine=Y1_DivisionLine;
			graphics2d.drawLine(X1_DivisionLine,Y1_DivisionLine,X2_DivisionLine,Y2_DivisionLine);
			graphics2d.drawString(toFormatInt(NamesAxisY.get(i)), X2_DivisionLine-((int)(getWidth()*(0.012))), Y1_DivisionLine-2);
		}
		maxValYFinal=Y1_DivisionLine;
	}
	private void drawGrid(Graphics2D graphics2d) {
		graphics2d.setStroke(new BasicStroke(0.5f));
		graphics2d.setColor(gridColor);
		yGrid(graphics2d);
		xGrid(graphics2d);
	}
	private void xGrid(Graphics2D graphics2d) {
		for (int i = 1; i <= namesAxisX.length; i++) {
			X1_DivisionLine=X2_Axis_Y+((((maxValX-X2_Axis_Y)/(namesAxisX.length+1))*(i)));
			Y1_DivisionLine=Y1_FrameWork;
			X2_DivisionLine=X1_DivisionLine;
			Y2_DivisionLine=Y2_Axis_Y;
			graphics2d.drawLine(X1_DivisionLine,Y1_DivisionLine,X2_DivisionLine,Y2_DivisionLine);
		}
	}	
	private void yGrid(Graphics2D graphics2d) {
		for (int i = 1; i < NamesAxisY.size(); i++) {
			X1_DivisionLine=X2_Axis_Y;
			Y1_DivisionLine=Y2_Axis_Y-(((Y2_Axis_Y-maxValY)/(NamesAxisY.size()-1))*(i));
			X2_DivisionLine=X2_FrameWork;
			Y2_DivisionLine=Y1_DivisionLine;
			graphics2d.drawLine(X1_DivisionLine,Y1_DivisionLine,X2_DivisionLine,Y2_DivisionLine);
		}
	}
	private void drawBars(Graphics2D graphics2d) {
		int point;
		for (int i = 1; i <= namesAxisX.length; i++) {
			X1_DivisionLine=X2_Axis_Y+((((maxValX-X2_Axis_Y)/(namesAxisX.length+1))*(i)));
			Y1_DivisionLine=Y2_Axis_Y+(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X);
			X2_DivisionLine=X1_DivisionLine;
			Y2_DivisionLine=Y2_Axis_Y-(int)(getHeight()*HEIGTH_OF_DIVISIONS_AXIS_X);
			point = calculatePoint(values[i-1]);
			graphics2d.setColor(valuesUseColor);
			graphics2d.drawOval (X1_DivisionLine-4,(Y2_Axis_Y-point)-4,
					8,
					8);
		}
	}
	private int calculatePoint(int value) {
		return value*(Y2_Axis_Y-maxValYFinal)/maxDataValY;
	}
	private void addValuesLebels(Graphics2D graphics2d){
		int point;
		for (int i = 1; i <= namesAxisX.length; i++) {
			X1_DivisionLine=X2_Axis_Y+((((maxValX-X2_Axis_Y)/(namesAxisX.length+1))*(i)));
			point = calculatePoint(values[i-1]);
			graphics2d.setColor(valuesUseColor);
			graphics2d.drawString(""+toFormatInt(values[i-1]), X1_DivisionLine+4,
					(Y2_Axis_Y-point)-4);
		}
	}
	public static String toFormatDouble(double number) {
		DecimalFormat df2 = new DecimalFormat("#,###.##");
		return df2.format(number);
	}
	public static String toFormatInt(int number) {
		DecimalFormat df2 = new DecimalFormat("#,###");
		return df2.format(number);
	}

}
