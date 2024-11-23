package org.helmo.mma.admin.infrastructures.factory;

import org.helmo.mma.admin.infrastructures.storage.FileDataStorage;

public class FileDataStorageFactory {

    private final String path;

    public FileDataStorageFactory(String path) {
        this.path = path;
    }

    public FileDataStorage createDataStorage() {
        try {
            return new FileDataStorage(path);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the data storage", e);
        }
    }
}
