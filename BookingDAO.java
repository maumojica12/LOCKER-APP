import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "IwillSuccess12:)";

    // --- Generate unique booking reference in format BKG-0001 ---
    public String generateBookingReference() {
        String lastRef = null;
        String sql = "SELECT bookingReference FROM Booking ORDER BY bookingReference DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                lastRef = rs.getString("bookingReference");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int newNumber = 1; // default if no bookings exist
        if (lastRef != null) {
            String numberPart = lastRef.substring(4); // Extract number from "BKG-0003" -> 3
            try {
                newNumber = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
                newNumber = 1; // fallback
            }
        }

        return String.format("BKG-%04d", newNumber);
    }

    public boolean addBooking(Booking booking) {
    String sql = "INSERT INTO Booking (bookingReference, userID, lockerID, reservationFee, reservationDate, selectedReservationDate, bookingStatus) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Set parameters
        stmt.setString(1, booking.getBookingReference());   // set generated bookingRef
        stmt.setInt(2, booking.getUserID());
        stmt.setInt(3, booking.getLockerID());
        stmt.setDouble(4, booking.getReservationFee());
        stmt.setString(5, booking.getReservationDate());
        stmt.setString(6, booking.getSelectedReservationDate());
        stmt.setString(7, booking.getBookingStatus());

        // Execute INSERT
        int rows = stmt.executeUpdate();

        return rows > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    // --- Update booking status and times ---
    public boolean updateBooking(Booking booking) {
        String sql = "UPDATE Booking SET bookingStatus = ?, checkInTime = ?, checkOutTime = ? WHERE bookingReference = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingStatus());
            stmt.setString(2, booking.getCheckInTime());
            stmt.setString(3, booking.getCheckOutTime());
            stmt.setString(4, booking.getBookingReference()); // String

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- Get booking by reference ---
    public Booking getBookingByReference(String bookingReference) {
        String sql = "SELECT * FROM Booking WHERE bookingReference = ?";
        Booking booking = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingReference); // String
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                        rs.getString("bookingReference"),
                        rs.getInt("userID"),
                        rs.getInt("lockerID"),
                        rs.getDouble("reservationFee"),
                        rs.getString("reservationDate"),
                        rs.getString("selectedReservationDate"),
                        rs.getString("bookingStatus"),
                        rs.getString("checkInTime"),
                        rs.getString("checkOutTime")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }

    public List<Booking> getPendingCheckInBookings() {
    List<Booking> bookings = new ArrayList<>();
    String sql = "SELECT * FROM Booking WHERE bookingStatus = 'Pending Check-in'";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Booking booking = new Booking(
                rs.getString("bookingReference"),
                rs.getInt("userID"),
                rs.getInt("lockerID"),
                rs.getDouble("reservationFee"),
                rs.getString("reservationDate"),
                rs.getString("selectedReservationDate"),
                rs.getString("bookingStatus"),
                rs.getString("checkInTime"),
                rs.getString("checkOutTime")
            );
            bookings.add(booking);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return bookings;
}

public List<Booking> getCheckedInBookings() {
    List<Booking> bookings = new ArrayList<>();
    String sql = "SELECT * FROM Booking WHERE bookingStatus = 'Checked-in'";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Booking booking = new Booking(
                rs.getString("bookingReference"),
                rs.getInt("userID"),
                rs.getInt("lockerID"),
                rs.getDouble("reservationFee"),
                rs.getString("reservationDate"),
                rs.getString("selectedReservationDate"),
                rs.getString("bookingStatus"),
                rs.getString("checkInTime"),
                rs.getString("checkOutTime")
            );
            bookings.add(booking);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return bookings;
}

    public boolean updateCheckOutTime(String bookingReference) {
        String sql = "UPDATE Booking SET checkOutTime = NOW() WHERE bookingReference = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bookingReference);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBookingStatus(String bookingReference, String newStatus) {
        String sql = "UPDATE Booking SET bookingStatus = ? WHERE bookingReference = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setString(2, bookingReference);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}