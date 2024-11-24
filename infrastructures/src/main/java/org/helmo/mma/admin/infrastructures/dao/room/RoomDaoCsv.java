package org.helmo.mma.admin.infrastructures.dao.room;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.dao.RoomDao;
import org.helmo.mma.admin.infrastructures.dto.csv.CSVRoomDto;
import org.helmo.mma.admin.infrastructures.mapper.csv.RoomMapperCsv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RoomDaoCsv implements RoomDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String path;
    private final RoomMapperCsv roomMapper;

    /**
     * Create a room repository with a path to a CSV file.
     * @param path the path to the CSV file
     * @throws FileNotFoundException if the file does not exist
     */
    public RoomDaoCsv(String path, RoomMapperCsv mapper) {
        try {
            if (!Files.exists(Paths.get(path))) {
                LOGGER.error("The file {} does not exist", path);
                throw new FileNotFoundException("The file " + path + " does not exist");
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("An error occurred while creating the file {}", path);
        }
        this.path = path;
        this.roomMapper = mapper;
    }

    /**
     * Find all the rooms.
     * @return the rooms
     */
    @Override
    public List<Room> findAll() {
        try (var reader = Files.newBufferedReader(Path.of(path))) {
            return reader.lines()
                    .skip(1)
                    .map(line -> {
                        var parts = line.split(",");
                        CSVRoomDto roomDto = new CSVRoomDto(parts[0], parts[1], parts[2]);
                        return roomMapper.toRoom(roomDto);
                    })
                    .toList();
        } catch (IOException e) {
            LOGGER.error("An error occurred while reading the file {}", path, e);
            return List.of();
        }
    }
}
