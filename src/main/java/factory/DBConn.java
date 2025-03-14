package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	static Connection con;
	public static Connection con() {
		try {
			if(con==null) {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sub2_todo","root","");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}