package jdbcGame;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Created by Aaron Fernandes(300773526) on November 2015.
 */
public class DBConfig {

	private static final String USERNAME="aaron";
	private static final String PASSWORD = "aaaaaa";
	private static final String CONN_STRING = "jdbc:mysql://localhost/assignment5";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
	}

	public static void displayException(SQLException ex){
		System.err.println("Error Message: " + ex.getMessage());
		System.err.println("Error Code: " + ex.getErrorCode());
		System.err.println("SQL State: " + ex.getSQLState());

		ex.printStackTrace();
	}

}

