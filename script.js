document.getElementById('bmiForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    // Get weight, height, and calculate BMI
    let weight = parseFloat(document.getElementById('weight').value);
    let height = parseFloat(document.getElementById('height').value);
    const bmi = (703 * weight / (height * height)).toFixed(2);

    // Determine BMI category
    let category;
    if (bmi < 18.5) {
        category = "underweight";
    } else if (bmi < 24.9) {
        category = "normal weight";
    } else if (bmi < 29.9) {
        category = "overweight";
    } else {
        category = "obese";
    }

    // Get the selected intensity level
    let intensity = document.getElementById('intensity').value;

    // Get the selected equipment
    let equipment = document.getElementById('equipment').value;

    // Now send the BMI category, intensity, and equipment to the backend (or use locally)
    console.log(`Your BMI is ${bmi}, category: ${category}`);
    console.log(`Selected intensity: ${intensity}`);
    console.log(`Selected equipment: ${equipment}`);

    // Display the result
    document.getElementById('result').innerHTML = `Your BMI is ${bmi}, category: ${category}<br>
                                                   Intensity: ${intensity}<br>
                                                   Equipment: ${equipment}`;
});
