// Get the modal
var modal = document.getElementById('id01');
var modal = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function validateForm(){
    var a=document.forms["Form"]["uname"].value;
    var b=document.forms["Form"]["psw"].value;
    return (checkLogin(a, b));
}

// Check user login (fake)
localstorage=window.sessionStorage;
function checkLogin(user, pw) {
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