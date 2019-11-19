package locations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LocationsService {

    private LocationRepository locationRepository;

    public LocationsService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    private static Location createDefaultLocationByName(String name) {
        return new Location(UUID.randomUUID().toString(), name, 5.0, 5.0);
    }

    public void writeToFile(Location location, Path path) {
        try {
            Files.writeString(path,
                    String.join(",", location.getName(),
                            Double.toString(location.getLat()),
                            Double.toString(location.getLon())));
        }
        catch (IOException ioe) {
            throw new IllegalStateException("Can not write", ioe);
        }
    }

    public List<Location> saveLocations(String... names) {
        return Arrays
                .stream(names)
                .parallel()
                .filter(this::notExists)
                .map(LocationsService::createDefaultLocationByName)
                .peek(this::saveLocation)
                .collect(Collectors.toList());
    }

    private boolean notExists(String name) {
        return !locationRepository.isLocationWithName(name);
    }

    private void saveLocation(Location location) {
        locationRepository.save(location);
    }
}
