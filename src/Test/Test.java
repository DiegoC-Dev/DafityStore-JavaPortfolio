package Test;

import java.sql.SQLException;
import java.util.ArrayList;

import net.ConnectionMySQL;

public class Test {
	public Test() throws SQLException {
		init();
	}
	private void init() throws SQLException {
		ConnectionMySQL mysql = new 
				ConnectionMySQL("jdbc:mysql://localhost/Dafity_DB?user=root&password=");
//				ConnectionMySQL("jdbc:mysql://localhost/Dafity_DB?user=user&password=123");

//		ArrayList<String[]> ClientList = mysql.getCientes();	
		ArrayList<String[]> saleList = mysql.getCompras();	
//		ArrayList<String[]> promotionList = mysql.getCompras();	
//		for (int i = 0; i < saleList.size(); i++) {
//				System.out.println("fecha..."+saleList.get(i)[2]);
//			}
//		System.out.println("codsd");
		
//		System.out.println("insercion..."+mysql.insertPromocion(500, 5, 10));
		
		String data;
		ArrayList<String []> topProducts = mysql.getTopProductos();
		for (int i = 0; i < topProducts.size(); i++) {
			for (int j = 0; j < topProducts.get(i).length; j++) {
				data = topProducts.get(i)[j]; 
				System.out.println(data);
			}
			System.out.println("------");
		}

	}
	public static void main(String[] args) {
		try {
			new Test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
