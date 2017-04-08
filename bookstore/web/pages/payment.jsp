<%-- 
    Document   : payment
    Created on : 08-Apr-2017, 20:52:31
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
	<link href="../../css/theme.css" rel="stylesheet">

	<!-- JS Pages -->

	</head>
	<body>
		<header>
			<iframe id="disclaimer" name="disclaimer" src="../iframes/disclaimer.html" width="100%">
            [Your user agent does not support frames or is currently configured not to display frames.]
        	</iframe>
		</header>

        <!-- Navigation -->
        <div class="dropdown">
            <button class="dropbtn">MENU</button>
            <div class="dropdown-content">
                <ul class="nav">
                    <li><a href="../pages/index.html">Login</a></li>
                    <li><a href="../pages/browse.html">Browse</a></li>
                    <li><a href="../pages/viewcart.html">View Cart</a></li>
                    <li><a href="../pages/payment.html">Pay Now</a></li>

                </ul>
            </div>
        </div>

		<h1>Payment Page</h1>

		<form action="../corepages/accountdetail.html" method="post">
            <fieldset>
                <legend>Pay for transaction</legend>
                <label>Select payment method:</label>
                <select>
  					<option value="pointsPayment">Pay by Points</option>
  					<option value="cardPayment">Pay by Card</option>
				</select>
				<h3><br></h3>
				<h3>Enter your card details</h3>
				<label>Card Name:</label>
				<input type="name" name="cardName">
                <label>Card Number:</label>
                <input type="name" name="cardNo">
                <label>Expiry Date:</label>
                <input type="date" name="expiryDate">
                <label>Security Code:</label>
                <input type="name" name="securityCode">
                <h3><br></h3>
                <h3>Enter delivery address</h3>
                <label>Address Line 1:</label>
				<input type="name" name="addr1">
                <label>Address Line 2:</label>
                <input type="name" name="addr2">
                <label>City:</label>
				<input type="name" name="city">
                <label>Country:</label>
                <input type="name" name="country">
                <label>Post Code (if any):</label>
                <input type="name" name="postcode">
                <h3><br></h3>
                <a href="confirmation.html" class="button">Confirm Payment</a>
        		<a href="viewcart.html" class="button">Return to Cart</a>
                <!-- <input type="submit" value="Go!"> -->
            </fieldset>
        </form>


		<br>

		<footer>
			<iframe id="disclaimer" name="disclaimer" src="../iframes/disclaimer.html" width="100%">
            [Your user agent does not support frames or is currently configured not to display frames.]
        	</iframe>
        	<iframe id="bookstorefooter" name="bookstorefooter" src="../iframes/bookstorefooter.html" width="100%" height="400px">
            [Your user agent does not support frames or is currently configured not to display frames.]
        	</iframe>
		</footer>
	</body>
</html>
