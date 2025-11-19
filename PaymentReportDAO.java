import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentReportDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public List<PaymentReportEntry> getPaymentsByDateRange(Timestamp startDate, Timestamp endDate) {
        List<PaymentReportEntry> report = new ArrayList<>();
        String query = """
            SELECT p.paymentID, p.bookingReference, p.userID,
                   CONCAT(u.firstName, ' ', u.lastName) AS userName,
                   p.paymentAmount, p.paymentMethod, p.paymentStatus, p.paymentDate
            FROM Payment p
            JOIN User u ON p.userID = u.userID
            JOIN Booking b ON p.bookingReference = b.bookingReference
            WHERE p.paymentDate BETWEEN ? AND ?
            ORDER BY p.paymentDate ASC
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setTimestamp(1, startDate);
            ps.setTimestamp(2, endDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                report.add(new PaymentReportEntry(
                        rs.getInt("paymentID"),
                        rs.getString("bookingReference"),
                        rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getDouble("paymentAmount"),
                        rs.getString("paymentMethod"),
                        rs.getString("paymentStatus"),
                        rs.getTimestamp("paymentDate")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }
}
