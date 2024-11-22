package org.helmo.mma.admin.infrastructures.dao;

import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.dao.UserDao;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        return List.of();
    }
}
