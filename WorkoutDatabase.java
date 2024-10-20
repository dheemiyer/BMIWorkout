import java.sql.*;

public class WorkoutDatabase {
    private Connection connection;

    public WorkoutDatabase(String dbUrl, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(dbUrl, user, password);
    }

    public String getWorkoutPlan(String bmiCategory) {
        String plan = "";
        String query = "SELECT workout FROM workouts WHERE category = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, bmiCategory);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                plan += rs.getString("workout") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plan;
    }
}
