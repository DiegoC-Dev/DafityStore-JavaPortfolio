package net;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionMySQL {

	private final String MySQL_CONNECTION = "jdbc:mysql://Dafity_DB?user=root&password=";
//	private final String MySQL_CONNECTION = "jdbc:mysql://Dafity_DB?user=user&password=123";

	private Connection connection;
	private Statement statement;
	private String stringConnection;

	/**
	 * Metodo contructor donde el parametro de entrada es un texto que representa la
	 * coneccion con la base de datos
	 * 
	 * @param stringConnection - texto de conneccion a la base de datos
	 */
	public ConnectionMySQL(String stringConnection) {
		this.stringConnection = stringConnection;
		connection = null;
		statement = null;
	}

	private void initConnection() throws SQLException {
		connection = DriverManager.getConnection(stringConnection);
		statement = connection.createStatement();
		statement.setQueryTimeout(10);
	}

	public ArrayList<String[]> getCientes() throws SQLException {
		ArrayList<String[]> clients = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement
				.executeQuery("SELECT * FROM personas p, ciudades c WHERE p.id_ciudad = c.id_ciudad;");
		while (resultSet.next()) {
			String[] client = new String[9];
			client[0] = Integer.toString(resultSet.getInt("id_persona"));
			client[1] = resultSet.getString("nombre_persona");
			client[2] = resultSet.getString("apellido_persona");
			client[3] = resultSet.getString("tipo_documento");
			client[4] = Integer.toString(resultSet.getInt("numero_documento"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			client[5] = sdf.format(resultSet.getDate("fecha_nacimiento"));
			client[6] = resultSet.getString("nombre_usuario");
			client[7] = resultSet.getString("contrasena");
			client[8] = resultSet.getString("nombre_ciudad");
			clients.add(client);
		}
		connection.close();
		return clients;
	}

	public ArrayList<String[]> getProducts() throws SQLException {
		ArrayList<String[]> products = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");
		while (resultSet.next()) {
			String[] product = new String[5];
			product[0] = Integer.toString(resultSet.getInt("id_producto"));
			product[1] = resultSet.getString("nombre_producto");
			product[2] = Integer.toString(resultSet.getInt("stock_producto"));
			product[3] = Integer.toString(resultSet.getInt("precio_producto"));
			product[4] = resultSet.getString("talla");
			products.add(product);
		}
		connection.close();
		return products;
	}

	public ArrayList<String[]> getCompras() throws SQLException {
		ArrayList<String[]> facturas = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement
				.executeQuery("SELECT * FROM personas c, facturas f, productos p, detalles_compra d, promociones s "
						+ "WHERE c.id_persona = f.id_persona AND f.id_factura = d.id_factura AND p.id_producto = d.id_producto AND d.id_promocion = s.id_promocion;");
		while (resultSet.next()) {
			String[] factura = new String[15];
			factura[0] = Integer.toString(resultSet.getInt("id_factura"));
			factura[1] = Integer.toString(resultSet.getInt("id_persona"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			factura[2] = sdf.format(resultSet.getDate("fecha_factura"));
			factura[3] = Integer.toString(resultSet.getInt("precio_completo"));
			factura[4] = Integer.toString(resultSet.getInt("descuento_completo"));
			factura[5] = Integer.toString(resultSet.getInt("pago_total"));
			factura[6] = Integer.toString(resultSet.getInt("id_producto"));
			factura[7] = Integer.toString(resultSet.getInt("id_promocion"));
			factura[8] = Integer.toString(resultSet.getInt("cantidad"));
			factura[9] = Integer.toString(resultSet.getInt("precio"));
			factura[10] = Integer.toString(resultSet.getInt("porcentaje"));
			factura[11] = Integer.toString(resultSet.getInt("stock_promocion"));
			factura[12] = resultSet.getString("nombre_persona");
			factura[13] = resultSet.getString("apellido_persona");
			factura[14] = resultSet.getString("nombre_producto");
			facturas.add(factura);
		}
		connection.close();
		return facturas;
	}

	public ArrayList<String[]> getUbicaciones() throws SQLException {
		ArrayList<String[]> ubicaciones = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM paises p, regiones r, ciudades c "
				+ "WHERE p.id_pais = r.id_pais AND r.id_region = c.id_region;");
		while (resultSet.next()) {
			String[] ubicacion = new String[17];
			ubicacion[0] = Integer.toString(resultSet.getInt("id_pais"));
			ubicacion[1] = resultSet.getString("nombre_pais");
			ubicacion[3] = Integer.toString(resultSet.getInt("id_region"));
			ubicacion[4] = resultSet.getString("nombre_region");
			ubicacion[5] = Integer.toString(resultSet.getInt("id_ciudad"));
			ubicacion[6] = resultSet.getString("nombre_ciudad");
			ubicaciones.add(ubicacion);
		}
		connection.close();
		return ubicaciones;
	}

	public boolean insertClient(int idPerson, String firstName, String secondName, String documentType,
			int documentNumber, Date birthday, String userName, String password, int idCity) throws SQLException {
		int acepted = 0;
		initConnection();
		acepted = statement.executeUpdate("INSERT INTO personas VALUES ('" + idPerson + "','" + firstName + "','"
				+ secondName + "','" + documentType + "','" + documentNumber + "','"
				+ new SimpleDateFormat("yyyy-MM-dd").format(birthday) + "', '" + userName + "', '" + password + "', '"
				+ idCity + "')");
		connection.close();
		return acepted == 1;
	}

	public boolean insertFactura(int idFactura, int idPersona, Date fechaFactura, double precioCompleto,
			int descuentoCompleto, double pagoTotal) throws SQLException {
		int acepted = 0;
		initConnection();
//		acepted = statement.executeUpdate("INSERT INTO facturas VALUES ('" + idFactura + "','"
//				+ new SimpleDateFormat("yyyy-MM-dd").format(fechaFactura) + "','" + (int) precioCompleto + "','"
//				+ descuentoCompleto + "','" + (int) pagoTotal + "','" + idPersona + "')");
		acepted = statement.executeUpdate("INSERT INTO facturas VALUES ('" + idFactura + "','"
				+ idPersona + "','" + new SimpleDateFormat("yyyy-MM-dd").format(fechaFactura) + "','" + (int) precioCompleto + "','"
				+ descuentoCompleto + "','" + (int) pagoTotal + "')");
		connection.close();
		return acepted == 1;
	}

	public boolean insertDetalle(int idFactura, int idProducto, int idPromocion, int idCantidad, double precio)
			throws SQLException {
		int acepted = 0;
		initConnection();
		acepted = statement.executeUpdate("INSERT INTO detalles_compra VALUES ('" + idFactura + "','" + idProducto
				+ "','" + idPromocion + "','" + idCantidad + "','" + precio + "')");
		connection.close();
		return acepted == 1;
	}

	public boolean insertPromocion(int idPromocion, int descuento, int stock) throws SQLException {
		int acepted = 0;
		initConnection();
		acepted = statement.executeUpdate(
				"INSERT INTO promociones VALUES ('" + idPromocion + "','" + descuento + "','" + stock + "')");
		connection.close();
		return acepted == 1;
	}

	public void deletUser(int idUser) throws SQLException {
		initConnection();
		statement.executeUpdate(
				"DELETE FROM detalles_compra WHERE id_factura = (SELECT id_factura FROM facturas WHERE id_persona = '"
						+ idUser + "');");
		statement.executeUpdate("DELETE FROM facturas WHERE id_persona = '" + idUser + "' ;");
		statement.executeUpdate("DELETE FROM personas WHERE id_persona = '" + idUser + "' ;");
		connection.close();
	}

	public boolean alterStockProd(int idProducto, int newStock) throws SQLException {
		int acepted = 0;
		initConnection();
		acepted = statement.executeUpdate(
				"UPDATE productos SET stock_producto = '" + newStock + "' WHERE id_producto = '" + idProducto + "' ;");
		connection.close();
		return acepted == 1;
	}

	public boolean alterProducto(int idProducto, String nombreProducto, int newStock, double precio, String talla)
			throws SQLException {
		int acepted = 0;
		initConnection();
		acepted = statement.executeUpdate("UPDATE productos SET nombre_producto = '" + nombreProducto
				+ "', stock_producto = '" + newStock + "', precio_producto = '" + precio + "',  talla = '" + talla
				+ "' WHERE id_producto = '" + idProducto + "' ;");
		connection.close();
		return acepted == 1;
	}

	public boolean alterUser(int idClient, String firstName, String secondName, String string, int i,
			Date date, String userName, String string2, int idCity) throws SQLException {
		int acepted = 0;
		initConnection();
		acepted = statement.executeUpdate(
				"UPDATE personas SET nombre_persona = '" + firstName + "', apellido_persona = '" + secondName
						+ "', tipo_documento = '" + string + "', numero_documento = '" + i
						+ "', fecha_nacimiento = '" + new SimpleDateFormat("yyyy-MM-dd").format(date)
						+ "', nombre_usuario = '" + userName + "', contrasena = '" + string2 + "', id_ciudad ='"
						+ idCity + "' WHERE id_persona = '" + idClient + "';");
		connection.close();
		return acepted == 1;
	}

	public ArrayList<String[]> getTopClients() throws SQLException {
		ArrayList<String[]> topClients = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement.executeQuery(
				"SELECT p.id_persona, p.nombre_persona, p.apellido_persona, count(f.id_factura) as cantidad FROM \r\n"
						+ "personas p, facturas f WHERE p.id_persona = f.id_persona \r\n"
						+ "GROUP BY p.id_persona, p.nombre_persona, p.apellido_persona  \r\n"
						+ "ORDER BY cantidad DESC LIMIT 5;");
		while (resultSet.next()) {
			String[] client = new String[2];
			client[0] = resultSet.getString("nombre_persona") + " " + resultSet.getString("apellido_persona");
			client[1] = Integer.toString(resultSet.getInt("cantidad"));
			topClients.add(client);
		}
		connection.close();
		return topClients;
	}

	public ArrayList<String[]> getTopProductos() throws SQLException {
		ArrayList<String[]> topProdc = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement
				.executeQuery("SELECT p.id_producto, p.nombre_producto, count(d.id_factura) as cantidad FROM \r\n"
						+ "productos p, detalles_compra d WHERE p.id_producto = d.id_producto\r\n"
						+ "GROUP BY p.id_producto, p.nombre_producto ORDER BY cantidad DESC LIMIT 5;");
		while (resultSet.next()) {
			String[] product = new String[2];
			product[0] = resultSet.getString("nombre_producto");
			product[1] = Integer.toString(resultSet.getInt("cantidad"));
			topProdc.add(product);
		}
		connection.close();
		return topProdc;
	}
	public boolean insertProducto(int idProducto, String nombre, int stock, double precio, String tallas) throws SQLException {
		int acepted = 0; 
		initConnection();
		acepted = statement.executeUpdate("INSERT INTO productos VALUES ('"+idProducto+"','"+nombre+"','"+
				stock+"','"+precio+"','"+tallas+"')");
		connection.close();
		return acepted == 1;
	}
	public ArrayList<String[]> getTopRegiones() throws SQLException{
		ArrayList<String[]> topProdc = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement.executeQuery("SELECT r.nombre_region, count(p.id_persona) as cantidad FROM personas p, ciudades c, regiones r \r\n"
				+ "	WHERE p.id_ciudad = c.id_ciudad AND c.id_region = r.id_region\r\n"
				+ "    GROUP BY r.nombre_region\r\n"
				+ "    ORDER BY cantidad DESC LIMIT 5;");
		while(resultSet.next()) {
			String[] product = new String[2];
			product[0] = resultSet.getString("nombre_region");
			product[1] = Integer.toString(resultSet.getInt("cantidad"));
			topProdc.add(product);
		}
		connection.close();
		return topProdc;
	}
	
	public ArrayList<String[]> getTopMaxPrecio() throws SQLException{
		ArrayList<String[]> topProdc = new ArrayList<>();
		initConnection();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM productos p\r\n"
				+ "    ORDER BY p.precio_producto DESC LIMIT 5;");
		while(resultSet.next()) {
			String[] product = new String[2];
			product[0] = resultSet.getString("nombre_producto");
			product[1] = Integer.toString(resultSet.getInt("precio_producto"));
			topProdc.add(product);
		}
		connection.close();
		return topProdc;
	}
}
