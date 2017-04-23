<%-- 
    Document   : contact
    Created on : 24-Apr-2017, 01:41:41
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

        <!-- CSS Pages -->
        <link href="/Bookstore/CSS/theme.css" rel="stylesheet">
        <link href="/Bookstore/CSS/login.css" rel="stylesheet">

        <title>Contact Us</title>
    </head>
    <body>
        <header>
            <iframe frameborder='0' scrolling='no' id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp'>
                [Your user agent does not support frames or is currently configured not to display frames.]
            </iframe>
        </header>
        <!-- Navigation -->
        <div class='dropdown'>
            <button class='dropbtn'>MENU</button>
            <div class='dropdown-content'>
                <ul class='nav'>
                    <%
                        if (request.getSession(true) != null) {
                            out.println("              <li><a href='/Bookstore/logout.do'>Logout</a></li>");
                        } else {
                            out.println("              <li><a href='/Bookstore/browse.do'>Login</a></li>");
                        } %>
                    <li><a href='/Bookstore/browse.do'>Browse</a></li>
                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>
                    <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>
                </ul>
            </div>
        </div>
        <!--Begin Page-->
        <h1 >Contact us!</h1>
        <% if (request.getParameter("contactSubmit") == null || request.getParameter("contactSubmit") == "Submit another question") { %>
        <h2>Feel free to contact us if you have any questions.</h2>
        <h3 style='text-align: center'>Please fill out this form with your question and we will get back to you as soon as possible:</h3>
        <form name='contactForm' id='Form' class='modal-content animate' method='POST'>
            <div class='imgcontainer'>
                <img src='/Bookstore/IMG/contact.png' alt='sign up' class='loginimg'>
            </div>
            <div class='container'>
                <label><b>Name/Username</b></label>
                <input type='text' placeholder='YOUR NAME OR USERNAME' required>
                <label><b>Email</b></label>
                <input type='text' placeholder='YOUR EMAIL' required>
                <label><b>Your Question</b></label>
                <input type='text' placeholder='ENTER YOUR MESSAGE'  required>
                <button name='contactSubmit' style='width:100%; font-size:18px' type='submit'>Submit Question</button>
            </div>
        </form>
        <% } else { %>
        <h2>Thank you for submitting.</h2>
        <h2>We will be in contact with you shortly.</h2>
        <p>If you have any urgent questions, please call us:</p>
        <p>+852 3442 7654</p>
        <%
                if (request.getSession(true) != null) {
                    out.println("              <a class='button' style='float:left;' href='/Bookstore/browse.do'>Back to Browse</a>");
                } else {
                    out.println("              <a class='button' style='float:left;' href='/Bookstore/browse.do'>Login</a>");
                }
                out.println("              <a name='contactSubmit' class='button' href='/Bookstore/iframes/contact.jsp'>Submit another question</a>");
            }%>
        <footer>
            <iframe frameborder='0' scrolling='no' id='bookstorefooter' name='bookstorefooter' src='/Bookstore/iframes/bookstorefooter.jsp' width='100%' height='100px'>
                [Your user agent does not support frames or is currently configured not to display frames.]
            </iframe>
            <iframe frameborder='0' scrolling='no' id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp' width='100%'>
                [Your user agent does not support frames or is currently configured not to display frames.]
            </iframe>
        </footer>
    </body>
</html>
