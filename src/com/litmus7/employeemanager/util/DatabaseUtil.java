package com.litmus7.employeemanager.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.employeemanager.constant.ErrorCodeConstants;
import com.litmus7.employeemanager.exception.DatabaseException;


public class DatabaseUtil {
	
	private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);
	
	private static final Properties props = new Properties();
    private static String url;
    private static String user;
    private static String password;
    
    private static final String propertiesFileName = "db.properties";
    
    static {
        try (FileInputStream fis = new FileInputStream(propertiesFileName)) {
            props.load(fis);
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (FileNotFoundException e) {
        	logger.error("Database Configuration file {} was not found", propertiesFileName);
        } catch (IOException e) {
        	logger.error("Failed to load Database Configuration values");
        }
    }

	
	public static Connection getConnection() throws DatabaseException{
		
		if (url == null || user == null || password == null) {
			logger.error("Database configuration is missing required values");
            throw new DatabaseException("Database configuration is missing required values", ErrorCodeConstants.DB_CONNECTION_ERROR_CODE);
        }
		
        try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.error("Failed to establish database connection", e);
			throw new DatabaseException("Failed to establish database connection", e, ErrorCodeConstants.DB_CONNECTION_ERROR_CODE);
		}
        

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
