package com.services.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectService {

	private String username = "root";
	private String password = "eclipse";
	private String db = "";
	private String host = "localhost";
	
	private Connection conn = null;
	private ResultSet res = null;
	
	public ConnectService(String dbname) {
		this.db = dbname;
		connect();
		// TODO Auto-generated constructor stub
	}
	
	public void connect(){
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella ricerca della classe com.mysql.jdbc.Driver");
			e.printStackTrace();
		}
		try {
			this.conn = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.host+":3306/"+this.db,this.username,this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public ResultSet executeQuery(String sql) throws SQLException{
	    this.res = this.conn.createStatement().executeQuery(sql);
		return res;
	}
	
}
