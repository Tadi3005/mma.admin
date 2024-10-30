package org.helmo.mma.admin.domains.repository;

import org.helmo.mma.admin.domains.User;

import java.util.List;

/**
 * Repository to access the users.
 */
public interface UserRepository {
    /**
     * Find all the users.
     * @return the users
     */
    List<User> findAll();
}
