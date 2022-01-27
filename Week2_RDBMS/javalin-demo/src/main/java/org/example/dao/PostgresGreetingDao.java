package org.example.dao;

import org.example.database.ConnectionManager;
import org.example.models.Greeting;
import org.example.models.Roles;
import org.example.models.User;

import java.sql.*;
import java.util.*;

public class PostgresGreetingDao implements Repository<Integer, Greeting>{

    private ConnectionManager connectionManager;

    public PostgresGreetingDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Integer save(Greeting obj) {
        try(Connection conn = this.connectionManager.getConnection()) {

            // create the sql statement
            String sql = "insert into greeting (greeting_text, user_id) values (?, ?) returning id";
            // create the statement object
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getGreeting());
            ps.setInt(2, obj.getUser().getId());

            // execute the statement and get the resultset
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();

            keys.next();
            return keys.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<Greeting> getAll() {
        System.out.println("Getting all greetings");
        try {
            //get a connection
            Connection conn = this.connectionManager.getConnection();
            List<Greeting> greetings = new ArrayList<>();

            // create the sql statement
            String sql = "select g.id as greeting_id, g.greeting_text, u.id as user_id, u.username, r.id, r.role from greeting g " +
                    "left join greeting_user u on g.user_id = u.id " +
                    "left join greeting_user_role gur on gur.user_id = u.id " +
                    "left join greeting_role r on gur.role_id = r.id " +
                    "order by g.id asc";
            // create the statement object
            PreparedStatement ps = conn.prepareStatement(sql);

            // execute the statement and get the resultset
            ResultSet rs = ps.executeQuery();

            // iterator over the resultset and create the objects
            rs.next();
            Greeting tempGreeting = newGreeting(rs);

            do {
                if(tempGreeting.getId() != rs.getInt("greeting_id")) {
                    // working with a new record
                    // add the old temp to the return
                    // and create a new temp
                    greetings.add(tempGreeting);
                    tempGreeting = newGreeting(rs);
                } else {
                    // add new roles to the user
                    tempGreeting.getUser().getRoles().add(Roles.valueOf(rs.getString("role")));
                }
            }while(rs.next());
            greetings.add(tempGreeting);

            // return the Objects
            return greetings;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public Greeting getById(Integer id) {
            //get a connection
        try(Connection conn = this.connectionManager.getConnection()) {
            Greeting greeting = null;

            // create the sql statement
            String sql = "select g.id as greeting_id, g.greeting_text, u.id as user_id, u.username, r.id, r.role from greeting g " +
                    "left join greeting_user u on g.user_id = u.id " +
                    "left join greeting_user_role gur on gur.user_id = u.id " +
                    "left join greeting_role r on gur.role_id = r.id " +
                    "group by g.id, g.greeting_text, u.id, u.username, r.id, r.role " +
                    "having g.id = ? " +
                    "order by greeting_text asc";
            // create the statement object
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // execute the statement and get the resultset
            ResultSet rs = ps.executeQuery();

            // iterator over the resultset and create the objects
            rs.next();
            greeting = newGreeting(rs);

            do {
                // add new roles to the user
                greeting.getUser().getRoles().add(Roles.valueOf(rs.getString("role")));
            }while(rs.next());

            // return the Objects
            return greeting;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void delete(Greeting obj) {
        this.deleteById(obj.getId());
    }

    @Override
    public void deleteById(Integer id) {
        try(Connection conn = this.connectionManager.getConnection()) {

            // create the sql statement
            String sql = "delete from greeting where id = ?";
            // create the statement object
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // execute the statement and get the resultset
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private Greeting newGreeting(ResultSet rs) throws SQLException {
        Greeting tempGreeting = new Greeting();
        tempGreeting.setId(rs.getInt("greeting_id"));
        tempGreeting.setGreeting(rs.getString("greeting_text"));

        User tempUser = new User();
        tempUser.setId(rs.getInt("user_id"));
        tempUser.setUsername(rs.getString("username"));

        Set<Roles> tempRoles = new HashSet<>();
        tempRoles.add(Roles.valueOf(rs.getString("role")));

        tempUser.setRoles(tempRoles);
        tempGreeting.setUser(tempUser);

        return tempGreeting;
    }
}
