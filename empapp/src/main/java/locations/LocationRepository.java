package locations;

public interface LocationRepository {
    boolean isLocationWithName(String name);

    void save(Location location);
}
