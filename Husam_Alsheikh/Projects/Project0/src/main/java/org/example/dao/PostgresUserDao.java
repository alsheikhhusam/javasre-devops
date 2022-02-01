package org.example.dao;

import org.example.database.ConnectionManager;
import org.example.dto.TransactionDTO;
import org.example.models.Roles;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostgresUserDao implements UserRepository{
    private ConnectionManager connectionManager;

    public PostgresUserDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Method to save transactions
     * @author Husam Alsheikh
     * @param obj updated user
     * @return returns transaction id
     */
    @Override
    public Integer save(User obj) { //  Insert transaction
        try(Connection conn = this.connectionManager.getConnection()){
            TransactionDTO transactionDTO = obj.getTransactions().get(obj.getTransactions().size() - 1);
            String sql = "insert into Transactions(username, date, amount, description, accountNum, userId) values (?, ?, ?, ?, ?, ?) returning transactionId";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, transactionDTO.getUsername());
            ps.setString(2, transactionDTO.getDate());
            ps.setInt(3, transactionDTO.getAmount());
            ps.setString(4, transactionDTO.getDescription());
            ps.setInt(5, transactionDTO.getAccountNum());
            ps.setInt(6, transactionDTO.getUserid());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @return returns null
     */
    @Override
    public List<User> getAll() {
        return null;
    }

    /**
     * Method to get user from user id
     * @author Husam Alsheikh
     * @param integer user id
     * @return returns user
     */
    @Override
    public User getById(Integer integer) {
        try(Connection conn = this.connectionManager.getConnection()){
            List<Integer> accountsId = new ArrayList<>();
            Set<Roles> roles = new HashSet<>();
            List<TransactionDTO> transactionDTOS = new ArrayList<>();

            String sql = "select * from users u where u.userid = ?";
            String sql2 = "select * from accounts a where a.userid = ?";
            String sql3 = "select role from users u left join roles r on u.roleid = r.id where u.userid = ?";
            String sql4 = "select * from transactions t where t.userid = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, integer);

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, integer);

            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setInt(1, integer);

            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setInt(1, integer);

            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            ResultSet rs3 = ps3.executeQuery();
            ResultSet rs4 = ps4.executeQuery();

            rs.next();

            //  Get all accountsId
            while(rs2.next()){
                accountsId.add(rs2.getInt("accountNum"));
            }

            //  Get all roles
            while(rs3.next()){
                roles.add(Roles.valueOf(rs3.getString("role")));
            }

            while(rs4.next()){
                transactionDTOS.add(new TransactionDTO(rs4.getString("date"), rs4.getInt("userid"), rs4.getString("username"),
                        rs4.getInt("accountNum"), rs4.getInt("amount"), rs4.getString("description")));
            }

            return new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), accountsId, roles, transactionDTOS);

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param obj user
     */
    @Override
    public void delete(User obj) {

    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param integer user id
     */
    @Override
    public void deleteById(Integer integer) {

    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param obj user
     */
    @Override
    public void update(User obj) {
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @return returns null
     */
    @Override
    public Integer getId() {
        return null;
    }

    /**
     * @author Husam Alsheikh
     * @param username username
     * @return returns user
     */
    @Override
    public User getByUsername(String username) {
        try(Connection conn = this.connectionManager.getConnection()){
            Set<Roles> roles = new HashSet<>();
            List<Integer> accountsId = new ArrayList<>();

            //  Get user
            String sql = "SELECT * FROM Users WHERE username = ?";

            //  Get accounts
            String sql2 = "select a.accountNum from users u left join accounts a on u.userid = a.userid where a.userid notnull and u.username = ?";

            //  Get roles
            String sql3 = "select role from users u left join roles r on u.roleid = r.id where u.username = ?";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, username);

            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setString(1, username);

            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            ResultSet rs3 = ps3.executeQuery();

            rs.next();

            //  Get all accountsId
            while(rs2.next()){
                accountsId.add(rs2.getInt("accountNum"));
            }

            //  Get all roles
            while(rs3.next()){
                 roles.add(Roles.valueOf(rs3.getString("role")));
            }

            return new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), accountsId, roles);
        }catch (SQLException ex){
            System.out.println(ex);
            return null;
        }
    }
}
