import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

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

    public Payment addPayment(Payment payment) {
        String sql = "INSERT INTO Payment (bookingReference, userID, paymentAmount, paymentMethod, paymentStatus, paymentDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, payment.getBookingReference());
            ps.setInt(2, payment.getUserID());
            ps.setDouble(3, payment.getPaymentAmount());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getPaymentStatus());
            ps.setTimestamp(6, payment.getPaymentDate());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedID = rs.getInt(1);
                payment.setPaymentID(generatedID); // update the Payment object
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

}
