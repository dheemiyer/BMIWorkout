document.getElementById('bmiForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    let weight = parseFloat(document.getElementById('weight').value);
    let height = parseFloat(document.getElementById('height').value);
    const bmi = (703 * weight / (height * height)).toFixed(2);

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

    document.getElementById('result').innerHTML = `Your BMI is ${bmi}, category: ${category}`;
    // Here, you would call your Java backend to get the workout plan based on the category.
});
