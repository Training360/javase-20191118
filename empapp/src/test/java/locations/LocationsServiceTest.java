package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LocationsServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @TempDir
    Path path;

    @Test
    public void testWriteToFile() throws IOException {
        System.out.println(path);

        Path file = path.resolve("test.csv");
        System.out.println(file);

        new LocationsService(locationRepository).writeToFile(new Location("Budapest", 10.0, 20.0),
                file);

        String content = Files.readString(file);
        assertEquals("Budapest,10.0,20.0", content);
    }
}
