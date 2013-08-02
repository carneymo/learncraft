package mods.learncraft.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database
{
    protected Connection conn;
    protected ResultSet resultSet;

    public static boolean loaded = false;

    public Database() throws SQLException
    {
        this.initConnect();
    }

    public void initConnect() throws SQLException
    {
        conn = DriverManager.getConnection(Configuration.DatabaseURL + Configuration.DatabaseName, Configuration.DatabaseUser, Configuration.DatabasePass);
        loaded = true;
    }

    public void executeQuery(String query) throws SQLException
    {
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet queryRows(String query) throws SQLException
    {
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
