document.getElementById('bmiForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    // Get weight, height, and calculate BMI
    let weight = parseFloat(document.getElementById('weight').value);
    let height = parseFloat(document.getElementById('height').value);
    const bmi = (703 * weight / (height * height)).toFixed(2);

    // Determine BMI category
    let category;
    if (bmi < 18.5) {
        category = "Underweight";
    } else if (bmi < 24.9) {
        category = "Normal weight";
    } else if (bmi < 29.9) {
        category = "Overweight";
    } else {
        category = "Obese";
    }

    // Get the selected intensity level
    let intensity = document.getElementById('intensity').value;

    // Get the selected equipment
    let equipment = document.getElementById('equipment').value;

    // Now send the BMI category, intensity, and equipment to the backend (or use locally)
    console.log(`Your BMI is ${bmi}`); 
    console.log(`Your category is: ${category}`);
    console.log(`Selected intensity: ${intensity}`);
    console.log(`Selected equipment: ${equipment}`);

    generalRecommendation = "Consider hitting a push pull legs routine or a arnold split (chest and back, shoulders and arms, and legs). Good luck in your fitness journey!";

    if (category === "Underweight") {
        exerciseRecommendation = "Focus on strength training and calorie-dense exercises to build muscle mass.";
    } else if (category === "Normal weight") {
        exerciseRecommendation = "Maintain a balanced workout routine including cardio and strength training.";
    } else if (category === "Overweight") {
        exerciseRecommendation = "Incorporate more cardio exercises and consider weight management strategies.";
    } else if (category === "Obese") {
        exerciseRecommendation = "Focus on low-impact cardio exercises and strength training to support weight loss.";
    }

    document.getElementById('result').innerHTML = `Your BMI is ${bmi} <br>
                                                   Category: ${category}<br>
                                                   Intensity: ${intensity}<br>
                                                   Equipment: ${equipment}<br>
                                                   Exercise Recommendation: ${exerciseRecommendation}<br>
                                                   General Recommendation: ${generalRecommendation}`;


});
