package ro.ase.acs.sql;

import ro.ase.acs.datasqlmanager.DbContext;
import ro.ase.acs.repository.IRepository;
import ro.ase.acs.repository.impl.RepositoryClients;
import ro.ase.acs.repository.impl.RepositoryEmployees;


public class Main {

    public static void main(String[] args) {
        try {
            DbContext dbContext = new DbContext();

            IRepository repository = new RepositoryEmployees(dbContext);

            repository.createTable();
            repository.insertData();
            repository.readData();

            System.out.println("---------------");

            repository = new RepositoryClients(dbContext);
            repository.createTable();
            repository.insertData();
            repository.readData();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}