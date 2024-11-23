package org.helmo.mma.admin.infrastructures.factory;

import org.helmo.mma.admin.infrastructures.storage.SQLDataStorage;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDataSorageFactory {

    public SQLDataSorageFactory(String url) {

    }

    public SQLDataStorage createDataStorage() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:mma.db");
            return new SQLDataStorage(connection);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create the data storage", e);
        }
    }
}
