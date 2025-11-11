import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentManager {

    private static final String URL = "jdbc:mysql://localhost:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Raeka.101482";

    public static class Payment {
        int paymentID;
        String bookingReference;
        int userID;
        double paymentAmount;
        String paymentMethod;
        String paymentStatus;
        Timestamp paymentDate;

        public Payment(int paymentID, String bookingReference, int userID, double paymentAmount, String paymentMethod, String paymentStatus, Timestamp paymentDate) {
            this.paymentID = paymentID;
            this.bookingReference = bookingReference;
            this.userID = userID;
            this.paymentAmount = paymentAmount;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
            this.paymentDate = paymentDate;
        }

        @Override
        public String toString() {
            return "PaymentID: " + paymentID +
                    ", BookingRef: " + bookingReference +
                    ", UserID: " + userID +
                    ", Amount: ₱" + paymentAmount +
                    ", Method: " + paymentMethod +
                    ", Status: " + paymentStatus +
                    ", Date: " + paymentDate;
        }

    }

    // VIEW ALL PAYMENTS
    public static List<Payment> viewAllPayments(){
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM Payment";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

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

        payments.forEach(System.out::println);
        return payments;
    }

    // SEARCH PAYMENT BY ID
    public static Payment searchPaymentById(int paymentID){
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
                System.out.println("Record found:\n" + payment);
            } else {
                System.out.println("No payment found with ID " + paymentID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    // PROCESS LOCKER RELEASE
}
