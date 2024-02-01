package com.sergioramos.dao;

import com.sergioramos.models.User;
import com.sergioramos.util.DateUtil;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final HikariDataSource dataSource;

    public UserDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from forum.users";
        List<User> users = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Date birth = resultSet.getDate("birth");
                Character sex = resultSet.getString("sex").charAt(0);
                User user = new User(id, firstName, lastName, email, password, birth, sex);
                users.add(user);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error fetching users", e);
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        String sql = "select * from forum.users u where u.id = ?";
        User user = null;
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Integer userId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Date birth = resultSet.getDate("birth");
                Character sex = resultSet.getString("sex").charAt(0);
                user = new User(userId, firstName, lastName, email, password, birth, sex);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error fetching user", e);
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        boolean success;
        String sql = "insert into users (firstname, lastname, email, password, birth, sex) values (?,?,?,?,?,?)";
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, DateUtil.toSqlDate(user.getBirth()));
            preparedStatement.setString(6, String.valueOf(user.getSex()));
            success = preparedStatement.executeUpdate() > 0;
        } catch(SQLException e) {
            success = false;
        }
        return success;
    }

    @Override
    public boolean edit(User user) {
        boolean success;
        Integer id = user.getId();
        String newFirstName = user.getFirstName();
        String newLastName = user.getLastName();
        String newEmail = user.getEmail();
        String newPassword = user.getPassword();
        String sql = "update users u set firstname = ?, lastname = ?, email = ?, password = ? where u.id = ?";
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newLastName);
            preparedStatement.setString(3, newEmail);
            preparedStatement.setString(4, newPassword);
            preparedStatement.setInt(5, id);
            success = preparedStatement.executeUpdate() > 0;
        } catch(SQLException e) {
            success = false;
        }
        return success;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean success;
        String sql = "delete from users u where u.id = ?";
        try(Connection conn = this.dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            success = preparedStatement.executeUpdate() > 0;
        } catch(SQLException e) {
            success = false;
        }
        return success;
    }
}
