package org.helmo.mma.admin.infrastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.repository.UserRepository;
import org.helmo.mma.admin.infrastructures.dto.CSVUserDto;
import org.helmo.mma.admin.infrastructures.mapper.UserMapper;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Repository to access the users from a CSV file.
 */
public class CSVUserRepository implements UserRepository {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String path;
    private final UserMapper userMapper;

    /**
     * Create a user repository with a path to a CSV file.
     * @param path the path to the CSV file
     * @throws FileNotFoundException if the file does not exist
     */
    public CSVUserRepository(String path) throws FileNotFoundException {
        if (!Files.exists(Paths.get(path))) {
            LOGGER.error("The file {} does not exist", path);
            throw new FileNotFoundException("The file " + path + " does not exist");
        }
        this.path = path;
        this.userMapper = new UserMapper();
    }

    /**
     * Find all the users.
     * @return the users
     */
    @Override
    public List<User> findAll() {
        try (var reader = Files.newBufferedReader(Paths.get(path))) {
            return reader.lines()
                    .skip(1)
                    .map(line -> {
                        var parts = line.split(",");
                        CSVUserDto userDto = new CSVUserDto(parts[0], parts[1], parts[2]);
                        return userMapper.toUser(userDto);
                    })
                    .toList();
        } catch (Exception e) {
            LOGGER.error("An error occurred while reading the file {}", path, e);
            return List.of();
        }
    }
}
