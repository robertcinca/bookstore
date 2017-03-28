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
    if (Math.floor(Math.random()*3) == 0) {   
            localstorage.clear();     
            pageOpacity(); //call Opacity function
            window.alert("Username and/or password are wrong. Please try again.");
            return false;    
        }
    else {
            localstorage.setItem('user', user);
            return true;
    }
}

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
    return true;
}