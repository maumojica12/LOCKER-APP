import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OccupancyReportDAO {

    // --- Database connection ---
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASS = "Auq_n49s.xq#";

    // --- Helper method to find connection ---
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // --- Get occupancy report grouped by locker size for a specific year ---
    public List<OccupancyReport> getOccupancyBySize(int year) {
        List<OccupancyReport> list = new ArrayList<>();

        String sql = """
            SELECT LT.lockerTypeSize, COUNT(B.bookingReference) AS totalBookings
            FROM Locker L
            JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID
            LEFT JOIN Booking B ON L.lockerID = B.lockerID AND YEAR(B.reservationDate) = ?
            GROUP BY LT.lockerTypeSize
            ORDER BY LT.lockerTypeSize
        """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new OccupancyReport(
                        0,
                        rs.getString("lockerTypeSize"),
                        "",
                        rs.getInt("totalBookings")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // --- Get occupancy report grouped by locker location for a specific year ---
    public List<OccupancyReport> getOccupancyByLocation(int year) {
        List<OccupancyReport> list = new ArrayList<>();

        String sql = """
            SELECT LOC.locationName, COUNT(B.bookingReference) AS totalBookings
            FROM Locker L
            JOIN Location LOC ON L.locationID = LOC.locationID
            LEFT JOIN Booking B ON L.lockerID = B.lockerID AND YEAR(B.reservationDate) = ?
            GROUP BY LOC.locationName
            ORDER BY LOC.locationName
        """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new OccupancyReport(
                        0,
                        "",
                        rs.getString("locationName"),
                        rs.getInt("totalBookings")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}