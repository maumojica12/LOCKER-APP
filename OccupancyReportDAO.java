import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OccupancyReportDAO {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASS = "22757205";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public List<OccupancyReport> getMonthlyOccupancy(int month, int year) {
        List<OccupancyReport> list = new ArrayList<>();

        String sql = "SELECT L.lockerID, LT.lockerTypeSize, LOC.locationName, COUNT(B.bookingReference) AS totalBookings " +
                     "FROM Locker L " +
                     "JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID " +
                     "JOIN Location LOC ON L.locationID = LOC.locationID " +
                     "LEFT JOIN Booking B ON L.lockerID = B.lockerID " +
                     "AND MONTH(B.bookingDate) = ? AND YEAR(B.bookingDate) = ? " +
                     "GROUP BY L.lockerID, LT.lockerTypeSize, LOC.locationName " +
                     "ORDER BY L.lockerID";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new OccupancyReport(
                        rs.getInt("lockerID"),
                        rs.getString("lockerTypeSize"),
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
