package DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB2 {
	Connection con=null;
	Statement stmt=null;
	public void insert(String query) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "root");
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB2 : " + e);
		}
	}

}
