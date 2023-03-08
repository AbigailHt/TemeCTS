package ro.ase.acs.datasqlmanager;

import java.sql.*;

public final class DbContext {
    private Connection connection;

    public DbContext() {
    }

    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        connection.setAutoCommit(false);
    }

    public void closeConnection() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public void commitChanges() throws SQLException {
        connection.commit();
    }

    public PreparedStatement prepareStatement(String command) throws SQLException {
        return connection.prepareStatement(command);
    }
}
