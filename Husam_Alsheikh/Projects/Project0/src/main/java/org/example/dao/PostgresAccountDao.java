package org.example.dao;

import org.example.database.ConnectionManager;
import org.example.dto.AccountDTO;

import java.sql.*;
import java.util.List;

public class PostgresAccountDao implements Repository<Integer, AccountDTO> {
    private ConnectionManager connectionManager;

    public PostgresAccountDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Saves account to database
     * @author Husam Alsheikh
     * @param obj Account
     * @return returns account number
     */
    @Override
    public Integer save(AccountDTO obj) {   //  Insert/Create Accounts
        try(Connection conn = this.connectionManager.getConnection()){
            String sql = "insert into Accounts(balance, username, userId) values (?, ?, ?) returning accountNum";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getBalance());
            ps.setString(2, obj.getUsername());
            ps.setInt(3, obj.getUserid());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
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
    public List<AccountDTO> getAll() {
        return null;
    }

    /**
     * Returns account based on account number
     * @author Husam Alsheikh
     * @param integer Account number
     * @return returns account
     */
    @Override
    public AccountDTO getById(Integer integer) {
        try(Connection conn = this.connectionManager.getConnection()){
            String sql = "SELECT * FROM Accounts WHERE accountNum = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, integer);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return new AccountDTO(rs.getInt("userId"), rs.getString("username"), rs.getInt("balance"), rs.getInt("accountNum"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param obj Account
     */
    @Override
    public void delete(AccountDTO obj) {

    }

    /**
     * No Implementation
     * @author Husam Alsheikh
     * @param integer account number
     */
    @Override
    public void deleteById(Integer integer) {

    }

    /**
     * Updates account balance
     * @author Husam Alsheikh
     * @param obj Account
     */
    @Override
    public void update(AccountDTO obj) {
        try(Connection conn = this.connectionManager.getConnection()){
            String sql = "update Accounts set balance = ? where accountNum = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getBalance());
            ps.setInt(2, obj.getAccountNum());

            ps.executeQuery();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
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
}
