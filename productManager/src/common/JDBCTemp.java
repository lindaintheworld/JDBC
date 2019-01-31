package common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import product.exception.ProductException;

public class JDBCTemp {

	public static Connection getConnection() throws ProductException {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("properties/dbserver.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
		
		return conn;
	}
	
	public static void close(Connection conn) throws ProductException {
		
		try {
			if(conn != null && conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
		
	}
	
	public static void close(Statement stmt) throws ProductException {
		
		try {
			if(stmt != null && stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public static void close(ResultSet rset) throws ProductException{
		try {
			if(rset != null && rset.isClosed())
				rset.close();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public static void commit(Connection conn) throws ProductException{
		try {
			if(conn != null && conn.isClosed())
				conn.commit();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
	
	public static void rollback(Connection conn) throws ProductException{
		try {
			if(conn != null && conn.isClosed())
				conn.rollback();
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
	}
}
