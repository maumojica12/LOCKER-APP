import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LockerTransferDAO {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "22757205";

    // --- Get all transfers ---
    public static List<LockerTransfer> getAllTransfers() {
        List<LockerTransfer> transfers = new ArrayList<>();
        String query = "SELECT * FROM LockerTransfer";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                LockerTransfer transfer = new LockerTransfer(
                        rs.getInt("transferID"),
                        rs.getString("bookingReference"),
                        rs.getTimestamp("transferDate").toLocalDateTime(),
                        rs.getDouble("adjustmentAmount"),
                        rs.getInt("oldLockerID"),
                        rs.getInt("newLockerID")
                );
                transfers.add(transfer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transfers;
    }

    // --- Get transfer by ID ---
    public static LockerTransfer getTransferByID(int id) {
        String query = "SELECT * FROM LockerTransfer WHERE transferID = ?";
        LockerTransfer transfer = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                transfer = new LockerTransfer(
                        rs.getInt("transferID"),
                        rs.getString("bookingReference"),
                        rs.getTimestamp("transferDate").toLocalDateTime(),
                        rs.getDouble("adjustmentAmount"),
                        rs.getInt("oldLockerID"),
                        rs.getInt("newLockerID")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transfer;
    }

    // --- Get latest transfer for a booking ---
    public static LockerTransfer getLatestTransferForBooking(String bookingReference) {
        String query = "SELECT * FROM LockerTransfer WHERE bookingReference = ? ORDER BY transferDate DESC LIMIT 1";
        LockerTransfer transfer = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, bookingReference);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                transfer = new LockerTransfer(
                        rs.getInt("transferID"),
                        rs.getString("bookingReference"),
                        rs.getTimestamp("transferDate").toLocalDateTime(),
                        rs.getDouble("adjustmentAmount"),
                        rs.getInt("oldLockerID"),
                        rs.getInt("newLockerID")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transfer;
    }

    // --- Add new transfer and update booking ---
    public static int addTransfer(LockerTransfer transfer) {
        String insertQuery = "INSERT INTO LockerTransfer (bookingReference, transferDate, adjustmentAmount, oldLockerID, newLockerID) "
                           + "VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // --- Insert into LockerTransfer ---
            try (PreparedStatement ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, transfer.getBookingReference());
                ps.setTimestamp(2, Timestamp.valueOf(transfer.getTransferDate()));
                ps.setDouble(3, transfer.getAdjustmentAmount());
                ps.setInt(4, transfer.getOldLockerID());
                ps.setInt(5, transfer.getNewLockerID());

                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }

            // --- Update Booking with new lockerID ---
            String updateBookingSQL = "UPDATE Booking SET lockerID = ? WHERE bookingReference = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(updateBookingSQL)) {
                ps2.setInt(1, transfer.getNewLockerID());
                ps2.setString(2, transfer.getBookingReference());
                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    // --- Update transfer ---
    public static boolean updateTransfer(LockerTransfer transfer) {
        String query = "UPDATE LockerTransfer SET bookingReference = ?, transferDate = ?, adjustmentAmount = ?, oldLockerID = ?, newLockerID = ? WHERE transferID = ?";
        boolean success = false;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, transfer.getBookingReference());
            ps.setTimestamp(2, Timestamp.valueOf(transfer.getTransferDate()));
            ps.setDouble(3, transfer.getAdjustmentAmount());
            ps.setInt(4, transfer.getOldLockerID());
            ps.setInt(5, transfer.getNewLockerID());
            ps.setInt(6, transfer.getTransferID());

            success = ps.executeUpdate() > 0;

            // --- Also update booking lockerID if transfer updated ---
            if (success) {
                String updateBookingSQL = "UPDATE Booking SET lockerID = ? WHERE bookingReference = ?";
                try (PreparedStatement ps2 = conn.prepareStatement(updateBookingSQL)) {
                    ps2.setInt(1, transfer.getNewLockerID());
                    ps2.setString(2, transfer.getBookingReference());
                    ps2.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // --- Delete transfer ---
    public static boolean deleteTransfer(int id) {
        String query = "DELETE FROM LockerTransfer WHERE transferID = ?";
        boolean success = false;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            success = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
