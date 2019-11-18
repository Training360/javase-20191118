package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationsServiceTest {

    @TempDir
    Path path;

    @Test
    public void testWriteToFile() throws IOException {
        System.out.println(path);

        Path file = path.resolve("test.csv");
        System.out.println(file);

        new LocationsService().writeToFile(new Location("Budapest", 10.0, 20.0),
                file);

        String content = Files.readString(file);
        assertEquals("Budapest,10.0,20.0", content);
    }
}
