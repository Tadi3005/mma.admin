package org.helmo.mma.admin.infrastructures.dao.user;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.dao.UserDao;
import org.helmo.mma.admin.infrastructures.dto.MySqlUserDto;
import org.helmo.mma.admin.infrastructures.mapper.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection;

    public UserDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Member";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resulSet = statement.executeQuery(query)) {
                while (resulSet.next()) {
//                    MySqlUserDto mySqlUserDto = new MySqlUserDto(resultSet.getString("matricule"), resultSet.getString("fullname"), resultSet.getString("email"));
//                    User user = UserMapper.INSTANCE.toUser(mySqlUserDto); TODO: Problem with the mapper
                    User user = new User(resulSet.getString("matricule"), resulSet.getString("fullname"), resulSet.getString("email"));
                    users.add(user);
                }

            }
        } catch (SQLException e) {
            LOGGER.error("Cannot find all users", e);
        }
        return users;
    }
}
