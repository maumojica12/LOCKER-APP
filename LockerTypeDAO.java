import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LockerTypeDAO {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luggage_locker_db";
    private static final String USER = "root"; // MySQL username
    private static final String PASSWORD = ""; // MySQL password

    public List<LockerType> getAllLockerTypes() {
        List<LockerType> types = new ArrayList<>();
        String query = "SELECT * FROM LockerType";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while(rs.next()){
                LockerType lt = new LockerType(
                        rs.getInt("lockerTypeID"),
                        rs.getString("lockerTypeSize"),
                        rs.getDouble("lockerMaxWeight"),
                        rs.getDouble("lockerRate")
                );
                types.add(lt);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return types;
    }

    public LockerType getLockerTypeByID(int id) {
        String query = "SELECT * FROM LockerType WHERE lockerTypeID = ?";
        LockerType type = null;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                type = new LockerType(
                        rs.getInt("lockerTypeID"),
                        rs.getString("lockerTypeSize"),
                        rs.getDouble("lockerMaxWeight"),
                        rs.getDouble("lockerRate")
                );
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return type;
    }

    public List<LockerType> searchLockerTypes(String idText, String sizeText) {
        List<LockerType> results = new ArrayList<>();

        if (idText != null && !idText.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                LockerType lt = getLockerTypeByID(id);
                if (lt != null) results.add(lt);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else if (sizeText != null && !sizeText.isEmpty()) {
            List<LockerType> all = getAllLockerTypes();
            for (LockerType lt : all) {
                if (lt.getLockerTypeSize().equalsIgnoreCase(sizeText)) {
                    results.add(lt);
                }
            }
        }

        return results;
    }
}
