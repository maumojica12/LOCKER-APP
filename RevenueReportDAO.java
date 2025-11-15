import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevenueReportDAO {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // --------------------------------------------------------
    // REVENUE GROUPED BY LOCKER SIZE
    // --------------------------------------------------------
    public List<RevenueReport> getRevenueByLockerSize(int year) {

        List<RevenueReport> list = new ArrayList<>();

        String sql =
            "SELECT LT.lockerTypeSize AS category, SUM(P.paymentAmount) AS totalRevenue " +
            "FROM Payment P " +
            "JOIN Booking B ON P.bookingReference = B.bookingReference " +
            "JOIN Locker L ON B.lockerID = L.lockerID " +
            "JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID " +
            "WHERE P.paymentStatus = 'Paid' " +
            "AND YEAR(P.paymentDate) = ? " +
            "GROUP BY LT.lockerTypeSize " +
            "ORDER BY LT.lockerTypeSize";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new RevenueReport(
                        rs.getString("category"),
                        rs.getDouble("totalRevenue")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // --------------------------------------------------------
    // REVENUE GROUPED BY LOCATION
    // --------------------------------------------------------
    public List<RevenueReport> getRevenueByLocation(int year) {

        List<RevenueReport> list = new ArrayList<>();

        String sql =
            "SELECT LOC.locationName AS category, SUM(P.paymentAmount) AS totalRevenue " +
            "FROM Payment P " +
            "JOIN Booking B ON P.bookingReference = B.bookingReference " +
            "JOIN Locker L ON B.lockerID = L.lockerID " +
            "JOIN Location LOC ON L.locationID = LOC.locationID " +
            "WHERE P.paymentStatus = 'Paid' " +
            "AND YEAR(P.paymentDate) = ? " +
            "GROUP BY LOC.locationName " +
            "ORDER BY LOC.locationName";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new RevenueReport(
                        rs.getString("category"),
                        rs.getDouble("totalRevenue")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}