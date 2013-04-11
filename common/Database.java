package mods.learncraft.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
	
	private String url = "";
	private String user = "mcforge";
	private String pass = "walrus";
	
	public void updateDB() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, pass);
		String query = "INSERT INTO learncraft.users (username,status) VALUES ('carneymo',1);";
		PreparedStatement sampleQueryStatement = conn.prepareStatement(query);
		sampleQueryStatement.executeUpdate();
		sampleQueryStatement.close();
		conn.close();
	}
	
}
