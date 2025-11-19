import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Raeka.101482";

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM Location";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Location loc = new Location(
                        rs.getInt("locationID"),
                        rs.getString("locationName"),
                        rs.getString("locationCity"),
                        rs.getString("locationPostalCode"),
                        rs.getString("contact")
                );
                locations.add(loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
