<%-- 
    Document   : viewcart
    Created on : 08-Apr-2017, 20:53:34
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
        <!-- JS Pages -->
        <script src="/bookstore/JS/basicFunctions.js" type="text/javascript"></script>
        <script src="/bookstore/JS/cartview.js" type="text/javascript"></script>
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
		
		<!-- View Cart Headings-->
		<h1 style="text-align:left;float:left;">Confirm Your Order, </h1>
		<h1 style="text-align:left;float:left;" id="welcomeMessage"><script>javascript:formDataUsername();</script></h1>
		<hr style="clear:both;"/>
		<h2>Your Shopping Cart</h2>

		 <!-- Get Shopping Cart Information and Display It in a Table -->
        <div align="center" id="showData"><script type="text/javascript">javascript:getShoppingCart()</script></div>
        <br>

		<a href="payment.html" class="button">Pay Now</a>
		<a href="browse.html" class="button">Browse Books</a>
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