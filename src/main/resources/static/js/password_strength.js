function updatePasswordStrength(password) {
    var password_strength = document.getElementById("password_strength");

    //TextBox left blank.
    if (password.length == 0) {
        password_strength.innerHTML = "";
        return;
    }

    let strengthValue = 0;

    if (password.length >= 8)
        strengthValue += 1;

    if (password.match(/(?=.*[0-9])/))
        strengthValue += 1;

    if (password.match(/(?=.*[^A-Za-z0-9])/))
        strengthValue += 1;

    if (password.match(/(?=.*[a-z])/))
        strengthValue += 1;

    if (password.match(/(?=.*[A-Z])/))
        strengthValue += 1;

    //Display status.
    var color = "";
    var strength = "";
    switch (passed) {
        case 0:
        case 1:
            strength = "Very Weak";
            color = "darkred";
            break;
        case 2:
        case 3:
            strength = "Weak";
            color = "red";
            break;
        case 4:
            strength = "Fair";
            color = "darkorange";
            break;
        case 5:
            strength = "Strong";
            color = "green";
            break;
    }

    password_strength.innerHTML = strength;
    password_strength.style.color = color;
}
