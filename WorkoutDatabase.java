import java.sql.*;

public class WorkoutDatabase {
    private Connection connection;

    public WorkoutDatabase(String dbUrl, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(dbUrl, user, password);
    }

    public String getWorkoutPlan(String bmiCategory) {
    StringBuilder plan = new StringBuilder();
    String query = "SELECT name, type, intensity, duration, calories_burned FROM workouts WHERE category = ?";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, bmiCategory);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String workoutName = rs.getString("name");
            String workoutType = rs.getString("type");
            int duration = rs.getInt("duration");
            int calories = rs.getInt("calories_burned");
            plan.append(String.format("%s: %s for %d minutes (Burns %d calories)\n", workoutName, workoutType, duration, calories));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return plan.toString();
}
