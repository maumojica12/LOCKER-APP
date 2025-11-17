import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CancellationDAO {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root"; // MySQL username
    private static final String PASSWORD = "Auq_n49s.xq#"; // MySQL password

    public boolean addCancellation(String bookingRef, String date, String reason, double refundFee) {

        String sql = "INSERT INTO Cancellation (bookingReference, cancelDate, reason, refundFee) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingRef);
            stmt.setString(2, date);
            stmt.setString(3, reason);
            stmt.setDouble(4, refundFee);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Cancellation> getAllCancellations() {
        List<Cancellation> list = new ArrayList<>();
        String sql = "SELECT * FROM cancellation";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cancellation c = new Cancellation(
                        rs.getInt("cancellationID"),
                        rs.getString("bookingReference"),
                        rs.getObject("cancelDate", LocalDateTime.class),
                        rs.getString("reason"),
                        rs.getDouble("refundFee")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<CanceledBookingsReport> getCancellationReport(int month, int year){
        List<CanceledBookingsReport> report = new ArrayList<>();

        String sql = "SELECT C.cancellationID, B.bookingReference, " +
                "CONCAT(U.firstName, ' ', U.lastName) AS userName, " +
                "LT.lockerTypeSize AS lockerSize, LOC.locationName AS lockerLocation, " +
                "C.cancelDate, C.reason, C.refundFee " +
                "FROM Cancellation C " +
                "JOIN Booking B ON C.bookingReference = B.bookingReference " +
                "JOIN User U ON B.userID = U.userID " +
                "JOIN Locker L ON B.lockerID = L.lockerID " +
                "JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID " +
                "JOIN Location LOC ON L.locationID = LOC.locationID " +
                "WHERE MONTH(C.cancelDate) = ? AND YEAR(C.cancelDate) = ? " +
                "ORDER BY C.cancelDate DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("cancelDate");
                LocalDateTime cancelDate = ts != null ? ts.toLocalDateTime() : null;

                report.add(new CanceledBookingsReport(
                        rs.getInt("cancellationID"),
                        rs.getString("bookingReference"),
                        rs.getString("userName"),
                        rs.getString("lockerSize"),
                        rs.getString("lockerLocation"),
                        cancelDate,
                        rs.getString("reason"),
                        rs.getDouble("refundFee")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }
}