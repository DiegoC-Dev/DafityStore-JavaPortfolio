package models;

import java.util.Iterator;

public class Client {
	public static int idCounter = 1001;
	private int id;
	private String user;
	private String password;
	private String firstName;
	private String lastName;
	private String documentType;
	private int documentId;
	private String cityName;
	private MyDate birthDate;
	
	public Client(int docId) {
		this.documentId = docId;
	}
	 
	public Client(String user, String password, String firstName, String lastName, String documentType, int id,
			String cityName, MyDate birthDate) {
		this.id = idCounter++;
		this.user = user;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.documentType = documentType;
		this.documentId = id;
		this.cityName = cityName;
		this.birthDate = birthDate;
	}
	public Client(int id,String user, String password, String firstName, String lastName, String documentType, int documentId,
			String cityName, MyDate birthDate) {
		idCounter++;
		this.id = id;
		this.user = user;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.documentType = documentType;
		this.documentId = documentId;
		this.cityName = cityName;
		this.birthDate = birthDate;
	}
	public String hidePassword() {
		String hiddenPassword = "";
		for (int i = 0; i < password.length(); i++) {
			hiddenPassword += "*";
		}
		return hiddenPassword;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getUser() {
		return user;
	}



	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return firstName+" "+lastName;
	}
	
	public String getDocumentType() {
		return documentType;
	}


	public int getDocumentId() {
		return documentId;
	}


	public String getCityName() {
		return cityName;
	}
	public MyDate getBirthDate() {
		return birthDate;
	}
	public String getBirthDateSt() {
		return birthDate.toString();
	}

	public String toString() {
		return firstName+","+lastName+","+documentType+
				","+documentId+","+cityName+","+birthDate;
	}
	public String[] toStringVector() {
		String[] vector= {user,password,
				firstName+" "+lastName,
				documentType+" "+documentId,cityName,
				""+birthDate};
		return vector;
	}
	
}
