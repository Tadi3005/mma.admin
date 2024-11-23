package org.helmo.mma.admin.infrastructures.dao.user;

import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private final Connection connection;

    public UserDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            // TODO: log the error
        }
        return users;
    }
}
