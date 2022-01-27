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

    @Override
    public void init() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    @Override
    public void init(Properties props) throws SQLException {
        this.username = props.getProperty("db.username");
        this.password = props.getProperty("db.password");
        this.url = props.getProperty("db.url");

        this.init();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(this.username, this.password, this.url);
    }

    @Override
    public Connection getConnection(String username, String password, String url) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
