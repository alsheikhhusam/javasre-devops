package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface ConnectionManager {
    /**
     * Method to initialize driver
     * @author Husam Alsheikh
     * @throws SQLException throws SQLException
     */
    void init() throws SQLException;

    /**
     * Method to set connection information
     * @author Husam Alsheikh
     * @param props properties type containing connection information
     * @throws SQLException throws SQLException
     */
    void init(Properties props) throws SQLException;

    /**
     * Method to call getConnection and pass connection information
     * @author Husam Alsheikh
     * @return returns the connection
     * @throws SQLException throws SQlException
     */
    Connection getConnection() throws SQLException;

    /**
     * Calls Drivers getConnection
     * @author Husam Alsheikh
     * @param username connection username
     * @param password connection password
     * @param url connection url
     * @return returns the connection
     * @throws SQLException throws SQLException
     */
    Connection getConnection(String username, String password, String url) throws SQLException;
}
