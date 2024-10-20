import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkoutDatabase {

    private HashMap<String, HashMap<String, HashMap<String, String>>> workoutData;

    public WorkoutDatabase(String csvFilePath) {
        workoutData = new HashMap<>();
        loadWorkoutData(csvFilePath);
    }

    // Load data from CSV file
    private void loadWorkoutData(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String muscleGroup = values[0];
                String intensity = values[1]; // Beginner or Intermediate
                String equipment = values[2]; // Body Only, Dumbbells, Full Equipment
                String exercise = values[3]; // Exercise name

                // Populate the workoutData HashMap
                workoutData
                        .computeIfAbsent(muscleGroup, k -> new HashMap<>())
                        .computeIfAbsent(intensity, k -> new HashMap<>())
                        .put(equipment, exercise);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate workout plan based on BMI category, intensity, and equipment
    public String getWorkoutPlan(String bmiCategory, String intensity, String equipment) {
        StringBuilder plan = new StringBuilder();
        
        // Focus muscle groups based on BMI category
        String[] muscleGroups;
        if (bmiCategory.equals("underweight")) {
            muscleGroups = new String[]{"Chest", "Shoulders", "Triceps"};
        } else if (bmiCategory.equals("overweight") || bmiCategory.equals("obese")) {
            muscleGroups = new String[]{"Abdominals", "Lats"};
        } else { // Normal weight
            muscleGroups = new String[]{"Abdominals", "Quadriceps", "Middle Back", "Lower Back", "Lats", 
                                        "Hamstrings", "Glutes", "Forearms", "Chest", "Calves", 
                                        "Biceps", "Abductors", "Shoulders", "Triceps"};
        }

        // Loop through the muscle groups and add exercises based on intensity and equipment
        for (String muscleGroup : muscleGroups) {
            if (workoutData.containsKey(muscleGroup) && 
                workoutData.get(muscleGroup).containsKey(intensity) && 
                workoutData.get(muscleGroup).get(intensity).containsKey(equipment)) {

                String exercise = workoutData.get(muscleGroup).get(intensity).get(equipment);
                plan.append(muscleGroup).append(": ").append(exercise).append("\n");
            } else {
                plan.append(muscleGroup).append(": No exercise available for the selected options\n");
            }
        }

        return plan.toString();
    }
}
