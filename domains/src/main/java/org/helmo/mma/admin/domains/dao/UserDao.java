package org.helmo.mma.admin.domains.dao;

import org.helmo.mma.admin.domains.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
