package com.litmus7.employeemanager.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
	private static final Properties props = new Properties();
    private static String url;
    private static String user;
    private static String password;
    
    static {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            props.load(fis);
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] db.properties not found");
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to load db.properties due to an I/O error");
        }
    }

	
	public static Connection getConnection() throws SQLException{
		
		if (url == null || user == null || password == null) {
            throw new SQLException("Database configuration not loaded properly.");
        }
		
        return DriverManager.getConnection(url, user, password);
        

//      try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
//          if (input != null) {
//              props.load(input);
//          } else {
//              System.out.println("[ERROR] db.properties not found");
//          }
//      } catch (IOException e) {
//          System.out.println("[ERROR] Failed to load db.properties due to an I/O error");
//      }

	}
}
