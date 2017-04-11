/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lokheili3
 */
public class viewdetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"    <head>\n" +
"        <!-- Meta attributes -->\n" +
"        <meta charset=\"utf-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"        <meta name=\"robots\" content=\"noindex, nofollow\">\n" +
"        <meta name=\"title\" content=\"Online Bookstore\">\n" +
"        <meta name=\"description\" content=\"An online marketplace for buying books.\">\n" +
"                            \n" +
"        <title>Welcome to our Online Bookstore!</title>\n" +
"                            \n" +
"        <!-- CSS Pages -->\n" +
"        <link href=\"/bookstore/CSS/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
"        <link href=\"/bookstore/CSS/browse.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
"        <!-- JS Pages -->\n" +
"    </head>\n" +
"    <body>\n" +
"        <header>\n" +
"            <iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n" +
"                [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"            </iframe>\n" +
"        </header>\n" +
"        \n" +
"        <!-- Navigation -->\n" +
"        <div class=\"dropdown\">\n" +
"            <button class=\"dropbtn\">MENU</button>\n" +
"            <div class=\"dropdown-content\">\n" +
"                <ul class=\"nav\">\n" +
"                    <li><a href=\"/bookstore/pages/index.jsp\">Login</a></li>\n" +
"                    <li><a href=\"/bookstore/pages/browse.jsp\">Browse</a></li>\n" +
"                    <li><a href=\"/bookstore/pages/viewcart.jsp\">View Cart</a></li>\n" +
"                    <li><a href=\"/bookstore/pages/payment.jsp\">Pay Now</a></li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </div>\n" +
"\n" +
"		<h1>Account Detail</h1>\n" +
"		<a href=\"/bookstore/pages/browse.jsp\" class=\"button\">Back to Browse</a>\n" +
"		<a href=\"/bookstore/pages/viewcart.jsp\" class=\"button\">View Cart</a>\n" +
"		<br>\n" +
"\n" +
"		<!--Purchased book-->\n" +
"\n" +
"		<h2>Purchased book</h2>\n" +
"		<table class=\"purchasedBook\">\n" +
"			<col width=\"40%\">\n" +
"  		<col width=\"10%\">\n" +
"			<col width=\"10%\">\n" +
"  		<col width=\"20%\">\n" +
"			<col width=\"20%\">\n" +
"\n" +
"		  <tr>\n" +
"				<th>Book Title</th>\n" +
"		    <th>Price</th>\n" +
"		    <th>Quantity</th>\n" +
"				<th>Status</th>\n" +
"				<th></th>\n" +
"\n" +
"		  </tr>\n" +
"		  <tr>\n" +
"		    <td >Book Title</td>\n" +
"		    <td >HKD 100</td>\n" +
"				<td >1</td>\n" +
"				<td >purchased</td>\n" +
"				<td >\n" +
"					<form class=\"refundButton\" style=\"float:right\">\n" +
"						<input type=\"button\" value=\"Request Refund\">\n" +
"					</form>\n" +
"				</td>\n" +
"		  </tr>\n" +
"			<tr>\n" +
"		    <td >Book Title</td>\n" +
"		    <td >HKD 100</td>\n" +
"				<td >1</td>\n" +
"				<td >refund accepted</td>\n" +
"				<td >\n" +
"\n" +
"				</td>\n" +
"		  </tr>\n" +
"		</table>\n" +
"\n" +
"		<!--user detail-->\n" +
"\n" +
"		<h2>Account Detail</h2>\n" +
"\n" +
"<fieldset>\n" +
"\n" +
"		<h3>User Info</h3>\n" +
"		<p>Username: </p>\n" +
"		<p>Password: </p>\n" +
"		<p>Role: </p>\n" +
"		<p>Loyalty Points: </p>\n" +
"		<br>\n" +
"\n" +
"		<h3>Credit Card Info</h3>\n" +
"		<p>Card Name: </p>\n" +
"		<p>Card Number: </p>\n" +
"		<p>Expiry Date: </p>\n" +
"		<br>\n" +
"\n" +
"		<h3>Address</h3>\n" +
"		<p>Address: </p>\n" +
"		<p>City: </p>\n" +
"		<p>Country: </p>\n" +
"		<p>Post Code (if any): </p>\n" +
"		<br>\n" +
"\n" +
"		<a href=\"/bookstore/pages/editAccount.jsp\" class=\"button\">Edit Account</a>\n" +
"</fieldset>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"		<footer>\n" +
"			<iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n" +
"            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"        	</iframe>\n" +
"        	<iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"/bookstore/iframes/bookstorefooter.jsp\" width=\"100%\" height=\"400px\">\n" +
"            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"        	</iframe>\n" +
"		</footer>\n" +
"	</body>\n" +
"</html>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
