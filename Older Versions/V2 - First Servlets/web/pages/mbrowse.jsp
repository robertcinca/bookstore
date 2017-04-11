<%-- 
    Document   : mbrowse
    Created on : 08-Apr-2017, 20:52:13
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
        <link href="/bookstore/CSS/mbrowse.css" rel="stylesheet" type="text/css"/>
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

		<h1>Page to browse books (Manager)</h1>
		<a href="/bookstore/pages/browse.jsp" class="button">Customer View</a>
		<a href="/bookstore/pages/refund.jsp" class="button">Refund Request</a>
		<a href="/bookstore/pages/addBooks.jsp" class="button">Add Books</a>
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
		    <td style="text-align: center; vertical-align: middle;"><img alt="Picture of a book" src="/bookstore/IMG/bookCover.png"></td>
		    <td >
					<h3>Book Title</h3>
					<p>by Author</p>
					<p>Price: HKD 100</p>
					<p>Loyalty Point: 50</p>
					<form class="manageButton">
						<input type="button" value="Change">
						<input type="button" value="Delete">
					</form>
				</td>
		  </tr>
		  <tr>
		    <td style="text-align: center; vertical-align: middle;"><img alt="Picture of a book" src="/bookstore/IMG/bookCover.png"></td>
		    <td >
					<h3>Book Title</h3>
					<p>by Author</p>
					<p>Price: HKD 100</p>
					<p>Loyalty Point: 50</p>
					<form class="manageButton">
						<input type="button" value="Change">
						<input type="button" value="Delete">
					</form>
				</td>
		  </tr>
		</table>




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
