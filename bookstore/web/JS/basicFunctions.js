//Basic JS functions
/**
 *
 * @author robertcinca
 */

function checkSpend() {
    var x = document.forms["Form3"]["totalAmount"].value; //get sum
    if (x <= 0) {
        window.alert("Error: You have not added anything to cart.");
        return false;
    }
    return true;
}

// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Check user login (fake)
localstorage = window.sessionStorage;
function validateLogin() {
    var user = document.forms["Form"]["j_username"].value;
    var pw = document.forms["Form"]["j_password"].value;
    if (checkLoginSyntax(user, pw) == false)
        return false;
}

//Check user signup (fake)
function validateSignUp() {
    var user = document.forms["Form2"]["uname"].value;
    var pw = document.forms["Form2"]["psw"].value;
    var pw2 = document.forms["Form2"]["psw2"].value;
    for (var i = 0; i < pw.length; i++) {
        if (pw[i] != pw2[i]) {
            window.alert("Error: Passwords do not match.");
            return false;
        }
    }
    if (checkLoginSyntax(user, pw) == false)
        return false;

    //TODO: create user in DB
    localstorage.setItem('user', user);

    return true;
}

function checkLoginSyntax(user, pw) {
    if (user.length < 4) {
        window.alert("Error: Username is too short: minimum length is 4 characters.");
        return false;
    }
    if (pw.length < 3) {
        window.alert("Error: Password is too short: minimum length is 3 characters.");
        return false;
    }
    for (var i = 0; i < user.length; i++) {
        if (user[i] == " ") {
            window.alert("Error: There is an intervening space in the Username. Please try again.");
            return false;
        }
    }
    return true;
}