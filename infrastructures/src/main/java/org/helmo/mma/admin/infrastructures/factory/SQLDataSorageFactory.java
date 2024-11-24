package org.helmo.mma.admin.infrastructures.factory;

import org.helmo.mma.admin.domains.exception.ConnexionException;
import org.helmo.mma.admin.domains.storage.DataStorageFactory;
import org.helmo.mma.admin.infrastructures.storage.SQLDataStorage;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDataSorageFactory implements DataStorageFactory {
    private final String connectionUrl;

    public SQLDataSorageFactory(String url) {
        try {
            String[] parts = url.split("@");
            String db = parts[0];
            String[] parts2 = parts[1].split(":");
            String host = parts2[0];
            String[] parts3 = parts2[1].split(";");
            String port = parts3[0];
            String[] parts4 = parts3[1].split("=");
            String user = parts4[1];
            String pwd = parts3[2].split("=")[1];
            this.connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pwd; // TODO: Check array bounds
        } catch (Exception e) {
            throw new ConnexionException("Cannot parse connection url");
        }
    }

    @Override
    public SQLDataStorage createDataStorage() {
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            return new SQLDataStorage(connection);
        } catch (Exception e) {
            throw new ConnexionException("Cannot connect to database");
        }
    }
}
