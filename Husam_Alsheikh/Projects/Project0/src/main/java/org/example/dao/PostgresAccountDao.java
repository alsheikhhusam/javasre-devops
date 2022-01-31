package org.example.dao;

import org.example.database.ConnectionManager;
import org.example.dto.AccountDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresAccountDao implements Repository<Integer, AccountDTO> {
    private ConnectionManager connectionManager;

    public PostgresAccountDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Integer save(AccountDTO obj) {   //  Insert/Create Accounts
        try(Connection conn = this.connectionManager.getConnection()){  //  TEST ME
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
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<AccountDTO> getAll() {
        return null;
    }

    @Override
    public AccountDTO getById(Integer integer) {    //  Get account by ID
        try(Connection conn = this.connectionManager.getConnection()){
            String sql = "SELECT * FROM Accounts WHERE accountNum = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, integer);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return new AccountDTO(rs.getInt("userId"), rs.getString("username"), rs.getInt("balance"), rs.getInt("accountNum"));
        }catch (SQLException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void delete(AccountDTO obj) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void update(AccountDTO obj) {
        try(Connection conn = this.connectionManager.getConnection()){
            String sql = "update Accounts set balance = ? where accountNum = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getBalance());
            ps.setInt(2, obj.getAccountNum());

            ps.executeQuery();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Integer getId() {
        return null;
    }
}
