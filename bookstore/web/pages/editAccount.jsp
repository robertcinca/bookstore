<%-- 
    Document   : editAccount
    Created on : 08-Apr-2017, 20:51:52
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
        <link href="/bookstore/CSS/browse.css" rel="stylesheet" type="text/css"/>
        <!-- JS Pages -->
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

		<h1>Account Detail</h1>
		<a href="/bookstore/pages/viewdetail.jsp" class="button">View Detail</a>
		<br>

		<!--user detail-->

		<h2>Account Detail</h2>

<fieldset>

	<form>
		<h3>User Info</h3>
		<label for="username">Username:</label>
		<input type="text" name="username" >
		<label for="password">Password:</label>
		<input type="text" name="password" >
		<label for="password2">Confirm Password:</label>
		<input type="text" name="password2" >
		<h3><br></h3>

		<h3>Credit Card Info</h3>
		<label for="cardname">Card name:</label>
		<input type="text" name="cardname" >
		<label for="cardNumber">Card Number:</label>
		<input type="text" name="cardNumber" >
		<label for="cardDate">Expiry Date:</label>
		<input type="date" name="cardDate" >
		<h3><br></h3>

		<h3>Address</h3>
		<label for="address1">Address Line 1:</label>
		<input type="text" name="address1" >
		<label for="address2">Address Line 2:</label>
		<input type="text" name="address2" >
		<label for="city">City:</label>
		<input type="text" name="city" >
		<label for="country">Country:</label>
		<input type="text" name="country" >
		<label for="postCode">Post Code (if any):</label>
		<input type="text" name="postCode" >
		<h3><br></h3>

		<a href="/bookstore/pages/viewdetail.jsp" class="button">Back to View Detail</a>
		<a href="/bookstore/pages/viewdetail.jsp" class="button">Confirm</a>


	</form>
</fieldset>





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

