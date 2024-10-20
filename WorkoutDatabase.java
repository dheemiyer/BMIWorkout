import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDatabase {
    private Connection connection;

    public WorkoutDatabase(String dbUrl, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(dbUrl, user, password);
    }

    public String getWorkoutPlan(String bmiCategory) {
        StringBuilder plan = new StringBuilder();
        HashMap<String, List<String>> bodyPartFocus = new HashMap<>();

        // Workout focus areas based on BMI category
        if (bmiCategory.equals("underweight")) {
            bodyPartFocus.put("Chest", new ArrayList<>());
            bodyPartFocus.put("Shoulders", new ArrayList<>());
            bodyPartFocus.put("Triceps", new ArrayList<>());
        } else if (bmiCategory.equals("overweight") || bmiCategory.equals("obese")) {
            bodyPartFocus.put("Abdominals", new ArrayList<>());
            bodyPartFocus.put("Lats", new ArrayList<>());
        } else { // Normal weight
            String[] bodyParts = {"Abdominals", "Quadriceps", "Middle Back", "Lower Back", "Lats", 
                                  "Hamstrings", "Glutes", "Forearms", "Chest", "Calves", 
                                  "Biceps", "Abductors", "Shoulders", "Triceps"};
            for (String part : bodyParts) {
                bodyPartFocus.put(part, new ArrayList<>());
            }
        }

        // Query for workouts by body part
        for (String bodyPart : bodyPartFocus.keySet()) {
            String query = "SELECT Title, Type, Duration FROM workouts WHERE BodyPart = ? LIMIT 1";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, bodyPart);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String workoutName = rs.getString("Title");
                    String workoutType = rs.getString("Type");
                    int duration = rs.getInt("Duration");
                    plan.append(String.format("%s: %s for %d minutes\n", workoutName, workoutType, duration));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return plan.toString();
    }
}
