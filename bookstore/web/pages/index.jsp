<%-- 
    Document   : index
    Created on : 08-Apr-2017, 20:44:53
    Author     : robertcinca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Meta attributes -->
        <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
                <meta name="robots" content="noindex, nofollow">
                    <meta name="title" content="Online Bookstore">
                        <meta name="description" content="An online marketplace for buying books.">
                            
                            <title>Welcome to our Online Bookstore!</title>
                            
                            <!-- CSS Pages -->
                            <link href="/bookstore/CSS/theme.css" rel="stylesheet" type="text/css"/>
                            <link href="/bookstore/CSS/login.css" rel="stylesheet" type="text/css"/>
                                    <!-- JS Pages -->
                                    <script src="/bookstore/JS/basicFunctions.js" type="text/javascript"></script>
                                    <script src="/bookstore/JS/login.js" type="text/javascript"></script>
                                    </head>
    <body>
        <header>
            <iframe id="disclaimer" name="disclaimer" src="/bookstore/iframes/disclaimer.jsp" width="100%">
                [Your user agent does not support frames or is currently configured not to display frames.]
            </iframe>
        </header>
        
        <!-- Navigation -->
        <div class="dropdown">
            <button class="dropbtn">MENU</button>
            <div class="dropdown-content">
                <ul class="nav">
                    
                    <li><a href="/bookstore/pages/index.jsp">Login</a></li>
                    <li><a href="/bookstore/pages/browse.jsp">Browse</a></li>
                    <li><a href="/bookstore/pages/viewcart.jsp">View Cart</a></li>
                    <li><a href="/bookstore/pages/payment.jsp">Pay Now</a></li>

                </ul>
            </div>
        </div>
        
        <h1>Welcome to our Online Bookstore!</h1>
        <p>Please select one of the following options:</p>
        
        <button onclick="document.getElementById('id01').style.display='block'" class="button">Login or Sign Up</button>
        
        <!-- Login Form -->
        <div id="id01" class="modal">
            
            <form id="Form" name="Form" class="modal-content animate" onsubmit="return validateLogin()" action="/bookstore/pages/browse.jsp" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                    <img src="/bookstore/IMG/welcome.png" alt="welcome" class="avatar">
                        </div>
                
                <div class="container">
                    <label><b>Username</b></label>
                    <input type="text" placeholder="Enter Username (Use manager for manager login)" name="uname" required>
                        
                        <label><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="psw" required>
                            
                            <button style="width:100%" type="submit">Login</button>
                            <input type="checkbox" checked="checked"> Remember me
                            </div>
                <div class="container" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
                    <button type="button" onclick="document.getElementById('id01').style.display='none';document.getElementById('id02').style.display='block'" class="signup">Sign Up</button>
                </div>
            </form>
        </div>

		<!-- SignUp Form -->
        <div id="id02" class="modal">
            
            <form id="Form2" name="Form2" class="modal-content animate" onsubmit="return validateSignUp()" action="/bookstore/pages/browse.jsp" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
                    <img src="/bookstore/IMG/newuser.png" alt="sign up!" class="avatar">
                        </div>
                
                <div class="container">
                    <label><b>Enter a Username:</b></label>
                    <input type="text" placeholder="New Username" name="uname" required>
                        
                        <label><b>Enter a Password:</b></label>
                        <input type="password" placeholder="New Password" name="psw" required>
                        <label><b>Re-enter password:</b></label>
                        <input type="password" placeholder="New Password" name="psw2" required>
                            
                            <button style="width:100%" type="submit">Sign Up</button>
                            <input type="checkbox" checked="checked"> Remember me
                            </div>
                <div class="container" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
                    <button type="button" onclick="document.getElementById('id02').style.display='none';document.getElementById('id01').style.display='block'" class="signup">Login</button>
                </div>
            </form>
        </div>

        <a href="/bookstore/pages/browse.jsp" class="button">Continue without logging in >>></a>
        <br>
        
        <footer>
            <iframe id="disclaimer" name="disclaimer" src="/bookstore/iframes/disclaimer.jsp" width="100%">
                [Your user agent does not support frames or is currently configured not to display frames.]
            </iframe>
            <iframe id="bookstorefooter" name="bookstorefooter" src="/bookstore/iframes/bookstorefooter.jsp" width="100%" height="400px">
                [Your user agent does not support frames or is currently configured not to display frames.]
            </iframe>
        </footer>
    </body>
</html>
