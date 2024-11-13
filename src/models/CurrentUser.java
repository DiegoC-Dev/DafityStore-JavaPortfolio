package models;

public class CurrentUser {
	private int idConnection;
	private int id;
	
	public CurrentUser(int idConnection, int id) {
		this.idConnection = idConnection;
		this.id= id;
	}

	public CurrentUser(int idConnection) {
		this.idConnection = idConnection;
	}

	public int getIdConnection() {
		return idConnection;
	}

	public void setIdConnection(int idConnection) {
		this.idConnection = idConnection;
	}

	public int getId() {
		return id;
	}

	public void setUser(int id) {
		this.id = id;
	}
	public String toString() {
		return ""+getIdConnection()+","+getId(); 
	}

}
