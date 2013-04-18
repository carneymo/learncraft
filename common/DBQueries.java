package mods.learncraft.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBQueries extends Database {

	public DBQueries() throws SQLException {
		super();
	}

	/**
	 * Inserts Player into database as having logged in.
	 * @param username
	 */
	public void insertPlayerLoggedIn(String username) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO users_log (`username`,`status`,`date_logged`)"
				+ "VALUES (?,?,?);";

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);

		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setInt(2, 1);
			stmt.setString(3, strDate);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public ResultSet getPlayerSpecItems(String username) {

		PreparedStatement stmt = null;
		String query = "SELECT * FROM spec_items WHERE username = ?";

		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			resultSet = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}

	public void removePlayerSpecItem(int id) {
		PreparedStatement stmt = null;
		String query = "DELETE FROM spec_items WHERE id = ?";

		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
