package mods.learncraft.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

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
		String query = "SELECT * FROM spec_items WHERE username = ? AND itemstate = 'granted'";

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

	public void updateCurrentPlayers() {
		// TODO Auto-generated method stub
		
	}
	
	public void setPlayerOnline(String username) {
		PreparedStatement stmt = null;
		String query = "UPDATE users SET online = 1 WHERE username = (SELECT identikey FROM identikey_users WHERE username = ? LIMIT 1)";

		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
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

	public List getPlayerList() {
		PreparedStatement stmt = null;
		String query = "SELECT username FROM users WHERE 1=1";

		try {
			stmt = conn.prepareStatement(query);
			resultSet = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void updateSpecItemToTaken(int id) {
		PreparedStatement stmt = null;
		String query = "UPDATE spec_items SET itemstate = 'taken' WHERE id = ?";

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

	public void setAllToOffline() {

		PreparedStatement stmt = null;
		String query = "UPDATE users SET online = 0;";

		try {
			stmt = conn.prepareStatement(query);
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

	public ResultSet getTeamID(EntityPlayer player, String hostname) {
		PreparedStatement stmt = null;
		String query = "SELECT id FROM team_roster WHERE username = ? AND hostname = ?;";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, player.username);
			stmt.setString(2, hostname);
			
			resultSet = stmt.executeQuery();
			
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void addPlayerToTeam(EntityPlayer player, Team team) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO team_roster (username, teamname, hostname) VALUES (?, ?, ?);";

		String hostname = MinecraftServer.getServer().getWorldName();
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, player.username);
			stmt.setString(2, team.teamcolor);
			stmt.setString(3, hostname);
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
	
	public String getPlayerTeam(EntityPlayer player) {

		PreparedStatement stmt = null;
		String query = "SELECT teamname FROM team_roster WHERE username = ?;";
		
		String hostname = MinecraftServer.getServer().getHostname();
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, player.username);
			
			resultSet = stmt.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getString("teamname");
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
