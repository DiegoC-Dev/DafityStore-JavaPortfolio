package util;

public class Convert {
	public static int[] StringToint(String[] list) {
	    int[] newList = new int[list.length];
		for (int j = 0; j < list.length; j++) {
	    	newList[j] = Integer.parseInt(list[j]);
	      }
	    return newList;
	}
//	public static int[] LocationNameToId(String[] list) {
//	    int[] newList = new int[list.length];
//		for (int j = 0; j < list.length; j++) {
//	    	newList[j] = Integer.parseInt(list[j]);
//	      }
//	    return newList;
//	}
}
