package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	final static String USER_NAME="root";
	final static String PASSWORD="hp15p251nz";
	final static String URL="jdbc:mysql://localhost:3306/library";
	public static Connection getCon() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Class Not Found");
			e.printStackTrace();
		}
		return connection;
	}
}
