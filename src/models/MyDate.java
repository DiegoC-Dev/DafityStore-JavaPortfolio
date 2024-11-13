package models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDate {
	private long day;
	private long month;
	private long year;
	
	public MyDate(long day, long month, long year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public MyDate(String date) {
		String list[] =date.split("[/]");
		this.year = 0;
		if(list.length==3) 
			if(list[0].length()<3 &&
			list[1].length()<3 && 
			list[2].length()==2) {
				try {
					this.day = Long.parseLong(list[0]);
					this.month = Long.parseLong(list[1]);
					this.year = Long.parseLong(list[2]);					
				} catch (Exception e) {
				}
			}
	}
	public MyDate() {
		Calendar c = new GregorianCalendar();
		this.day = c.get(Calendar.DATE);
		this.month = c.get(Calendar.MONTH);
		this.year = c.get(Calendar.YEAR);		
	}
	public boolean correctDate() {
		return year==0?false:true;
	}
	
	public long getDay() {
		return day;
	}
	public long getMonth() {
		return month;
	}
	public long getYear() {
		return year;
	}
	public String toString() {
		return day+"/"+month+"/"+year;
	}
	public String[] toStringVector() {
		String[] vector= {""+day,""+month,""+year};
		return vector;
	}
}
