package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

public class LocationsServiceTest {

    @TempDir
    Path path;

    @Test
    public void testWriteToFile() {
        System.out.println(path);
    }
}
