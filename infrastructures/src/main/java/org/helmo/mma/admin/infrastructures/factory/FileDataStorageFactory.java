package org.helmo.mma.admin.infrastructures.factory;

import org.helmo.mma.admin.domains.storage.DataStorageFactory;
import org.helmo.mma.admin.infrastructures.storage.FileDataStorage;

public class FileDataStorageFactory implements DataStorageFactory {

    private final String path;

    public FileDataStorageFactory(String path) {
        this.path = path;
    }

    @Override
    public FileDataStorage createDataStorage() {
        return new FileDataStorage(path);
    }
}
