package org.helmo.mma.admin.domains.repository;

import org.helmo.mma.admin.domains.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
