<%-- 
    Document   : bookstorefooter
    Created on : 08-Apr-2017, 20:55:29
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
        <link href="/Bookstore/CSS/bookstore.css" rel="stylesheet">

        <!-- Open Links Outside iFrame -->
        <base target="_parent">

    </head>
    <body>
        <div id="fb-root"></div>
        <script>(function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.9";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));</script>
        <div class="bookstoreContainer">
            <div class="column-left">
                <a href="https://www.facebook.com/Amazon/" style="float:left;margin-right: 10px;" target="_blank"><img src="http://www.flamingopark.ro/wp-content/uploads/2016/07/facebook30.png" height="35"width="35"></a> 
                <a href="https://www.youtube.com/user/amazon" style="float:left;margin-right: 10px;" target="_blank"><img src="http://www.flamingopark.ro/wp-content/uploads/2016/07/youtube30.png" height="35"width="35"> </a> 
                <a href="https://www.instagram.com/amazon/" style="float:left;margin-right: 10px;" target="_blank"><img src="http://www.flamingopark.ro/wp-content/uploads/2016/07/instagram30.png" height="35"width="35"></a> 
                <a href="https://plus.google.com/+amazon" style="float:left;margin-right: 10px;" target="_blank"><img src="http://www.flamingopark.ro/wp-content/uploads/2016/07/google30.png" height="35"width="35"></a> 
                <!--<a href="http://www.flamingopark.ro/english/"><font color="#000000" size = "3">English</font></a>--> 
            </div>
            <div class="column-center">
                <p>©Copyright 2017 Bookstore.</p>
            </div>
            <div class="column-right">
                <div class="fb-like" data-href="https://www.facebook.com/Amazon/" data-layout="box_count" data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>
            </div>
        </div>
    </body>
</html>