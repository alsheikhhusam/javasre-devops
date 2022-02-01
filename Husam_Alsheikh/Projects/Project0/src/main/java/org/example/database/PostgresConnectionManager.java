package org.example.database;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnectionManager implements ConnectionManager{
    private String username;
    private String password;
    private String url;

    public PostgresConnectionManager() {
    }

    public PostgresConnectionManager(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    /**
     * Registers driver
     * @author Husam Alsheikh
     * @throws SQLException throws SQLException
     */
    @Override
    public void init() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    /**
     * Sets connection information
     * @author Husam Alsheikh
     * @param props properties type containing connection information
     * @throws SQLException throws SQLException
     */
    @Override
    public void init(Properties props) throws SQLException {
        this.username = props.getProperty("db.username");
        this.password = props.getProperty("db.password");
        this.url = props.getProperty("db.url");

        this.init();
    }

    /**
     * calls this.getConnection
     * @author Husam Alsheikh
     * @return returns the connection
     * @throws SQLException throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        return this.getConnection(this.username, this.password, this.url);
    }

    /**
     * calls driver's getConnection
     * @author Husam Alsheikh
     * @param username connection username
     * @param password connection password
     * @param url      connection url
     * @return returns connection
     * @throws SQLException throws SQLException
     */
    @Override
    public Connection getConnection(String username, String password, String url) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
