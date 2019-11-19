package empapp;

import java.util.stream.Stream;

public class StreamSamples {

    public static void main(String[] args) {
        Stream.generate(() -> 1).count();
    }
}
