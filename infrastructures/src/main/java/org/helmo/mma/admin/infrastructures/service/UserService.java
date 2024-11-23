package org.helmo.mma.admin.infrastructures.service;

import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.storage.DataStorage;

import java.util.List;

public class UserService {

    private final DataStorage storage;

    public UserService(DataStorage storage) {
        this.storage = storage;
    }

    public List<User> findAll() {
        return storage.getUserDao().findAll();
    }
}
