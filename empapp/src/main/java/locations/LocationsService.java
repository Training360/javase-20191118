package locations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LocationsService {

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
}
