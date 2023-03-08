package ro.ase.acs.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface IRepository {
    void createTable() throws SQLException;
    void insertData() throws SQLException;
    void readData() throws SQLException;
}
