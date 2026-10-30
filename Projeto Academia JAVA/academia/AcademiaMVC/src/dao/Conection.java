package dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conection {
 
	public Connection getConnection() {
 
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dbAcademia", "root", "1234");
 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
 
	}
}
