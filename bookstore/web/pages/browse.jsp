<%-- 
    Document   : browse.jsp
    Created on : 08-Apr-2017, 20:48:02
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
	<link href="../../css/browse.css" rel="stylesheet">

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

		<h1>Page to browse books (Customer)</h1>
		<a href="mbrowse.html" class="button">Manager View</a>
		<a href="viewcart.html" class="button">View Cart</a>
		<a href="viewDetail.html" class="button">View Account Detail</a>
		<br>

		<!-- Book List  -->
		<table class="bookList">
			<col width="20%">
  		<col width="80%">
		  <tr>
		    <th>Books</th>
		    <th>Detail</th>

		  </tr>
		  <tr>
		    <td style="text-align: center; vertical-align: middle;"><img alt="Picture of a book" src="../../img/bookCover.png"></td>
		    <td >
					<h3>Book Title</h3>
					<p>by Author</p>
					<p>Price: HKD 100</p>
					<p>Loyalty Point: 50</p>
					<form class="addToCart">
						<label for="Quantity">Quantity:</label>
						<input type="text" name="quantity" value="1" size="5">
						<input type="submit" value="Add to Cart">
					</form>
				</td>
		  </tr>
		  <tr>
		    <td style="text-align: center; vertical-align: middle;"><img alt="Picture of a book" src="../../img/bookCover.png"></td>
		    <td >
					<h3>Book Title</h3>
					<p>by Author</p>
					<p>Price: HKD 100</p>
					<p>Loyalty Point: 50</p>
					<form class="addToCart">
						<label for="Quantity">Quantity:</label>
						<input type="text" name="quantity" value="1" size="5">
						<input type="submit" value="Add to Cart">
					</form>
				</td>
		  </tr>
		</table>




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

