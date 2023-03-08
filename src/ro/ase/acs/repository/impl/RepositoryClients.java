package ro.ase.acs.repository.impl;

import ro.ase.acs.datasqlmanager.DbContext;
import ro.ase.acs.repository.IRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryClients implements IRepository {
    private final DbContext dbContext;


    public RepositoryClients(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public void createTable() throws SQLException {
        dbContext.openConnection();

        String sqlDrop = "DROP TABLE IF EXISTS clients";
        String sqlCreate = "CREATE TABLE clients(id INTEGER PRIMARY KEY,"
                + "name TEXT, address TEXT, salary REAL)";

        Statement statement = dbContext.createStatement();
        statement.executeUpdate(sqlDrop);
        statement.executeUpdate(sqlCreate);
        statement.close();
        dbContext.commitChanges();
        dbContext.closeConnection();
    }


    @Override
    public void insertData() throws SQLException {
        dbContext.openConnection();
        String sqlInsert = "INSERT INTO clients VALUES(1, 'Andreescu Alex', 'Bucharest', 4000)";
        Statement statement = dbContext.createStatement();
        statement.executeUpdate(sqlInsert);
        statement.close();

        String sqlInsertWithParams = "INSERT INTO clients VALUES (?,?,?,?)";
        PreparedStatement preparedStatement =
                dbContext.prepareStatement(sqlInsertWithParams);
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Mitrache Mircea");
        preparedStatement.setString(3, "Iasi");
        preparedStatement.setDouble(4, 3500);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        dbContext.commitChanges();
        dbContext.closeConnection();
    }

    @Override
    public void readData() throws SQLException {
        dbContext.openConnection();
        String sqlSelect = "SELECT * FROM clients";
        Statement statement = dbContext.createStatement();
        ResultSet rs = statement.executeQuery(sqlSelect);
        while(rs.next()) {
            int id = rs.getInt("id");
            System.out.println("id: " + id);
            String name = rs.getString(2);
            System.out.println("name: " + name);
            String address = rs.getString("address");
            System.out.println("address: " + address);
            double salary = rs.getDouble("salary");
            System.out.println("salary: " + salary);
        }
        rs.close();
        statement.close();
        dbContext.closeConnection();
    }
}
