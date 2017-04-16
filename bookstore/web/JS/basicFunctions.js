//Put basic JS functions here

//Parsing URL to get name, value pair
function getParameterByName(name, url) {
    if (!url) {
        url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
    if (!results)
        return null;
    if (!results[2])
        return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

//Personal greeting with username (TODO: get uname from DataBase not from URL)
function formDataUsername() {
    var x = getParameterByName('uname');
    //use to redirect/display certain pages depending on if user is signed in or not
    if (x == null) {
        document.getElementById("welcomeMessage").innerHTML = " Guest";
        // x = checkLogin();
        // if (x == "false")
        // window.location = "login.html"; //redirect back to login page if no username present
    } else
        document.getElementById("welcomeMessage").innerHTML = " x";
}
