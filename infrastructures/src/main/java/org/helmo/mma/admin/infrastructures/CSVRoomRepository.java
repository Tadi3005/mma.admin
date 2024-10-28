package org.helmo.mma.admin.infrastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.repository.RoomRepository;
import org.helmo.mma.admin.infrastructures.dto.CSVRoomDto;
import org.helmo.mma.admin.infrastructures.mapper.RoomMapper;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVRoomRepository implements RoomRepository {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String path;
    private final RoomMapper roomMapper;

    public CSVRoomRepository(String path) throws FileNotFoundException {
        if (!Files.exists(Paths.get(path))) {
            LOGGER.error("The file {} does not exist", path);
            throw new FileNotFoundException("The file " + path + " does not exist");
        }
        this.path = path;
        this.roomMapper = new RoomMapper();
    }

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
        } catch (Exception e) {
            LOGGER.error("An error occurred while reading the file {}", path, e); // TODO: Ne pas renvoyer une liste vide en cas d'erreur
            return List.of();
        }
    }
}
