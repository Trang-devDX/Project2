package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.cj.jdbc.Driver;

public class ConnectionJDBCUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/estateadvance"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "27032003";
    
    public static Connection getConnection() {
    	Connection conn = null;
    	try {
    		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    		return conn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return conn;
    }
}
