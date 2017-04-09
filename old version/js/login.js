// Get the modal
var modal = document.getElementById('id01');
var modal = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Check user login (fake)
localstorage=window.sessionStorage;
function validateLogin(){
    var user=document.forms["Form"]["uname"].value;
    var pw=document.forms["Form"]["psw"].value;
    if (checkLoginSyntax(user, pw) == false)
        return false;

    //TODO: create check with DB
    if (Math.floor(Math.random()*3) == 0) {   
            localstorage.clear();  
            window.alert("Username and/or password are wrong. Please try again.");
            return false;    
        }
    else {
            localstorage.setItem('user', user);
            if (user == "manager")
                document.Form.action ="mbrowse.html"; //give manager right of access to manager page
            return true;
    }
}

//Check user signup (fake)
function validateSignUp(){
    var user=document.forms["Form2"]["uname"].value;
    var pw=document.forms["Form2"]["psw"].value;
    var pw2=document.forms["Form2"]["psw2"].value;
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
    if (user.length < 5) {
        window.alert("Error: Username is too short: minimum length is 5 characters.");
        return false;
    }
    if (pw.length < 5) {
        window.alert("Error: Password is too short: minimum length is 5 characters.");
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

//redirect manager to right page
function pageDirect() {
    window.open("browse.html","_self")
}