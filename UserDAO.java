import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String URL = "jdbc:mysql://localhost:3306/luggage_locker_db";
    private final String USER = "root";          // change if you have a different MySQL username
    private final String PASSWORD = "22757205";          // set your MySQL password if any

    // --- Utility method for connecting to the database ---
   private Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // explicitly load MySQL driver
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException("MySQL Driver not found.");
    }
    return DriverManager.getConnection(URL + "?serverTimezone=UTC", USER, PASSWORD);
}

    // --- CREATE (Register User and return generated ID) ---
public int addUser(User user) {
    int generatedId = -1; // default if insert fails
    String sql = "INSERT INTO User (firstName, lastName, userContact, userEmail) VALUES (?, ?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, user.getFirstName());
        stmt.setString(2, user.getLastName());

        // Optional fields: set to null if empty
        if (user.getUserContact() != null && !user.getUserContact().isEmpty()) {
            stmt.setString(3, user.getUserContact());
        } else {
            stmt.setNull(3, java.sql.Types.VARCHAR);
        }

        if (user.getUserEmail() != null && !user.getUserEmail().isEmpty()) {
            stmt.setString(4, user.getUserEmail());
        } else {
            stmt.setNull(4, java.sql.Types.VARCHAR);
        }

        int rows = stmt.executeUpdate();

        if (rows > 0) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1); // get the auto-incremented userID
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return generatedId;
}

    // --- READ (Search User by ID) ---
    public User getUserById(int userID) {
        String sql = "SELECT * FROM User WHERE userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("userID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("userContact"),
                    rs.getString("userEmail")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // user not found
    }
    

    // --- SEARCH (Find Users by Name) ---
    public List<User> searchUsersByName(String nameKeyword) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User WHERE firstName LIKE ? OR lastName LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nameKeyword + "%");
            stmt.setString(2, "%" + nameKeyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(new User(
                    rs.getInt("userID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("userContact"),
                    rs.getString("userEmail")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

// --- UPDATE user ---
public boolean updateUser(User user) {
    String sql = "UPDATE User SET firstName=?, lastName=?, userContact=?, userEmail=? WHERE userID=?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, user.getFirstName());
        stmt.setString(2, user.getLastName());
        stmt.setString(3, user.getUserContact());
        stmt.setString(4, user.getUserEmail());
        stmt.setInt(5, user.getUserID());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean deleteUserByID(int userID) {
    String sql = "DELETE FROM User WHERE userID = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, userID);
        int rows = stmt.executeUpdate();
        return rows > 0; // true if deletion was successful

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
}
