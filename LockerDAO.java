import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LockerDAO {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root"; // MySQL username
    private static final String PASSWORD = "Auq_n49s.xq#"; // MySQL password

    //Get List of All Lockers
    public List<Locker> getAllLockers() {
        List<Locker> lockers = new ArrayList<>();
        String query = "SELECT * FROM Locker";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lockers.add(new Locker(
                        rs.getInt("lockerID"),
                        rs.getInt("lockerTypeID"),
                        rs.getInt("locationID"),
                        rs.getString("lockerStatus")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lockers;
    }
    //Search for Locker by ID
    public Locker getLockerByID(int lockerID) {
        String query = "SELECT * FROM Locker WHERE lockerID = ?";
        Locker locker = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, lockerID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                locker = new Locker(
                        rs.getInt("lockerID"),
                        rs.getInt("lockerTypeID"),
                        rs.getInt("locationID"),
                        rs.getString("lockerStatus")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locker;
    }

    //Get All Available Lockers
    public List<Locker> getAvailableLocker() {
        List<Locker> lockers = new ArrayList<>();
        String query = "SELECT * FROM Locker WHERE lockerStatus = 'Available'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lockers.add(new Locker(
                        rs.getInt("lockerID"),
                        rs.getInt("lockerTypeID"),
                        rs.getInt("locationID"),
                        rs.getString("lockerStatus")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lockers;
    }

    //Get All Occupied Lockers
    public List<Locker> getOccupiedLocker() {
        List<Locker> lockers = new ArrayList<>();
        String query = "SELECT * FROM Locker WHERE lockerStatus = 'Occupied'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lockers.add(new Locker(
                        rs.getInt("lockerID"),
                        rs.getInt("lockerTypeID"),
                        rs.getInt("locationID"),
                        rs.getString("lockerStatus")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lockers;
    }
    //get Reserved Lockers
    public List<Locker> getReservedLocker() {
        List<Locker> lockers = new ArrayList<>();
        String query = "SELECT * FROM Locker WHERE lockerStatus = 'Reserved'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lockers.add(new Locker(
                        rs.getInt("lockerID"),
                        rs.getInt("lockerTypeID"),
                        rs.getInt("locationID"),
                        rs.getString("lockerStatus")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lockers;
    }

    //Update Locker Status
    public boolean updateLockerStatus(int lockerID, String lockerStatus){
        String query = "UPDATE Locker SET lockerStatus = ? WHERE lockerID = ?";
        boolean success = false;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, lockerStatus);
            ps.setInt(2, lockerID);
            success = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Get Available Lockers by Location
    public List<Locker> getAvailableLockersByLocation(int locationID) {
        List<Locker> lockers = new ArrayList<>();

        String query = """
            SELECT * FROM Locker 
            WHERE lockerStatus = 'Available' AND locationID = ?
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, locationID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lockers.add(new Locker(
                        rs.getInt("lockerID"),
                        rs.getInt("lockerTypeID"),
                        rs.getInt("locationID"),
                        rs.getString("lockerStatus")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lockers;
    }

    // Add Locker
    public boolean addLocker(Locker locker) {
        String sql = """
            INSERT INTO Locker (lockerTypeID, locationID, lockerStatus) 
            VALUES (?, ?, 'Available')
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, locker.getLockerTypeID());
            ps.setInt(2, locker.getLocationID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update Locker
    public boolean updateLocker(Locker locker) {
        String sql = """
            UPDATE Locker 
            SET lockerTypeID=?, locationID=?, lockerStatus=? 
            WHERE lockerID=?
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, locker.getLockerTypeID());
            ps.setInt(2, locker.getLocationID());
            ps.setString(3, locker.getLockerStatus());
            ps.setInt(4, locker.getLockerID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Locker
    public boolean deleteLocker(int lockerID) {
        String sql = "DELETE FROM Locker WHERE lockerID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lockerID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}