import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Raeka.101482";

    // VIEW ALL PAYMENTS
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM Payment";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Payment p = new Payment(
                        rs.getInt("paymentID"),
                        rs.getString("bookingReference"),
                        rs.getInt("userID"),
                        rs.getDouble("paymentAmount"),
                        rs.getString("paymentMethod"),
                        rs.getString("paymentStatus"),
                        rs.getTimestamp("paymentDate")
                );
                payments.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    // SEARCH PAYMENT BY ID
    public Payment getPaymentById(int paymentID) {
        String query = "SELECT * FROM Payment WHERE paymentID = ?";
        Payment payment = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, paymentID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                payment = new Payment(
                        rs.getInt("paymentID"),
                        rs.getString("bookingReference"),
                        rs.getInt("userID"),
                        rs.getDouble("paymentAmount"),
                        rs.getString("paymentMethod"),
                        rs.getString("paymentStatus"),
                        rs.getTimestamp("paymentDate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

    // 💾 ADD NEW PAYMENT (Process Payment & Release Locker)
    public boolean insertPayment(Payment payment) {
        String query = "INSERT INTO Payment (bookingReference, userID, paymentAmount, paymentMethod, paymentStatus, paymentDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, payment.getBookingReference());
            ps.setInt(2, payment.getUserID());
            ps.setDouble(3, payment.getPaymentAmount());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getPaymentStatus());
            ps.setTimestamp(6, payment.getPaymentDate());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🧾 UPDATE PAYMENT STATUS (optional)
    public boolean updatePaymentStatus(int paymentID, String status) {
        String query = "UPDATE Payment SET paymentStatus = ? WHERE paymentID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, paymentID);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
