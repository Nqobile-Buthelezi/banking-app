// onboarding.js

function passwordIsValid(password) {
    // Check if the password contains at least one special character
    let count = 0;

    // Iterating through the string
    for (let i = 0; i < password.length; i++) {
        // Checking the character for not being a letter, digit, or space
        if (!/[a-zA-Z0-9\s]/.test(password[i])) {
            count++;
        }
    }

    return count !== 0;
}


// Function to check password strength
function checkPasswordStrength(password) {
    // Your password strength logic goes here
    // For example, you can use a regular expression to check for certain criteria
    // This is a simple example, you can customize it based on your requirements
    if (password.length >= 8 && /[a-zA-Z]/.test(password) && /\d/.test(password) && passwordIsValid(password)) {
        return "Strong";
    } else if (password.length >= 6 && /[a-zA-Z]/.test(password)) {
        return "Medium";
    } else {
        return "Weak";
    }
}

// Function to update password strength feedback and indicator
function updatePasswordStrengthFeedback() {
    const passwordInput = document.getElementById('password');
    const feedbackDiv = document.getElementById('password-strength-feedback');
    const indicatorDiv = document.getElementById('password-indicator');

    const password = passwordInput.value;
    const strength = checkPasswordStrength(password);

    feedbackDiv.textContent = `Password Strength: ${strength}`;

    // Update the indicator based on strength
    if (strength === "Strong") {
        indicatorDiv.innerHTML = '<span style="color: green;"><i class="fas fa-check-circle"></i></span>'; // Green check mark
    } else if (strength === "Medium") {
        indicatorDiv.innerHTML = '<span style="color: orange;"><i class="fas fa-minus-circle"></i></span>'; // Orange check minus
    } else {
        indicatorDiv.innerHTML = '<span style="color: red;"><i class="fas fa-times-circle"></i></span>'; // Red cross
    }
}

// Attach event listeners
const passwordInput = document.getElementById('password');
passwordInput.addEventListener('input', updatePasswordStrengthFeedback);

var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
   return new bootstrap.Tooltip(tooltipTriggerEl);
});
