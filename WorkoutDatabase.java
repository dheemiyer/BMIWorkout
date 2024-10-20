import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WorkoutDatabase {
    private HashMap<String, HashMap<String, HashMap<String, String>>> workoutData;

    public WorkoutDatabase(String csvFilePath) {
        workoutData = new HashMap<>();
        loadWorkoutData(csvFilePath);
    }

    private void loadWorkoutData(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String muscleGroup = values[0];
                String intensity = values[1]; 
                String equipment = values[2]; 
                String exercise = values[3]; 

                workoutData
                        .computeIfAbsent(muscleGroup, k -> new HashMap<>())
                        .computeIfAbsent(intensity, k -> new HashMap<>())
                        .put(equipment, exercise);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWorkoutPlan(String bmiCategory, String intensity, String equipment) {
        StringBuilder plan = new StringBuilder();
        
        String[] muscleGroups;
        if (bmiCategory.equals("Underweight")) {
            muscleGroups = new String[]{"Chest", "Shoulders", "Triceps"};
        } else if (bmiCategory.equals("Overweight") || bmiCategory.equals("Obese")) {
            muscleGroups = new String[]{"Abdominals", "Lats"};
        } else { 
            muscleGroups = new String[]{"Abdominals", "Quadriceps", "Middle Back", "Lower Back", "Lats", 
                                        "Hamstrings", "Glutes", "Forearms", "Chest", "Calves", 
                                        "Biceps", "Abductors", "Shoulders", "Triceps"};
        }

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
