/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
public class editAccount extends HttpServlet {

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
            out.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "    <head>\n"
                    + "        <!-- Meta attributes -->\n"
                    + "        <meta charset=\"utf-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "        <meta name=\"robots\" content=\"noindex, nofollow\">\n"
                    + "        <meta name=\"title\" content=\"Online Bookstore\">\n"
                    + "        <meta name=\"description\" content=\"An online marketplace for buying books.\">\n"
                    + "                            \n"
                    + "        <title>Welcome to our Online Bookstore!</title>\n"
                    + "                            \n"
                    + "        <!-- CSS Pages -->\n"
                    + "        <link href=\"/bookstore/CSS/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                    + "        <link href=\"/bookstore/CSS/browse.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                    + "        <!-- JS Pages -->\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <header>\n"
                    + "            <iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n"
                    + "                [Your user agent does not support frames or is currently configured not to display frames.]\n"
                    + "            </iframe>\n"
                    + "        </header>\n"
                    + "        \n"
                    + "        <!-- Navigation -->\n"
                    + "        <div class=\"dropdown\">\n"
                    + "            <button class=\"dropbtn\">MENU</button>\n"
                    + "            <div class=\"dropdown-content\">\n"
                    + "                <ul class=\"nav\">\n"
                    + "                    <li><a href=\"/bookstore/login.do\">Login</a></li>\n"
                    + "                    <li><a href=\"/bookstore/browse.do\">Browse</a></li>\n"
                    + "                    <li><a href=\"/bookstore/viewcart.do\">View Cart</a></li>\n"
                    + "                    <li><a href=\"/bookstore/payment.do\">Pay Now</a></li>\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "\n"
                    + "		<h1>Account Detail</h1>\n"
                    + "		<a href=\"/bookstore/viewdetail.do\" class=\"button\">View Detail</a>\n"
                    + "		<br>\n"
                    + "\n"
                    + "		<!--user detail-->\n"
                    + "\n"
                    + "		<h2>Account Detail</h2>\n"
                    + "\n"
                    + "<fieldset>\n"
                    + "\n"
                    + "	<form>\n"
                    + "		<h3>User Info</h3>\n"
                    + "		<label for=\"username\">Username:</label>\n"
                    + "		<input type=\"text\" name=\"username\" >\n"
                    + "		<label for=\"password\">Password:</label>\n"
                    + "		<input type=\"text\" name=\"password\" >\n"
                    + "		<label for=\"password2\">Confirm Password:</label>\n"
                    + "		<input type=\"text\" name=\"password2\" >\n"
                    + "		<h3><br></h3>\n"
                    + "\n"
                    + "		<h3>Credit Card Info</h3>\n"
                    + "		<label for=\"cardname\">Card name:</label>\n"
                    + "		<input type=\"text\" name=\"cardname\" >\n"
                    + "		<label for=\"cardNumber\">Card Number:</label>\n"
                    + "		<input type=\"text\" name=\"cardNumber\" >\n"
                    + "		<label for=\"cardDate\">Expiry Date:</label>\n"
                    + "		<input type=\"date\" name=\"cardDate\" >\n"
                    + "		<h3><br></h3>\n"
                    + "\n"
                    + "		<h3>Address</h3>\n"
                    + "		<label for=\"address1\">Address Line 1:</label>\n"
                    + "		<input type=\"text\" name=\"address1\" >\n"
                    + "		<label for=\"address2\">Address Line 2:</label>\n"
                    + "		<input type=\"text\" name=\"address2\" >\n"
                    + "		<label for=\"city\">City:</label>\n"
                    + "		<input type=\"text\" name=\"city\" >\n"
                    + "		<label for=\"country\">Country:</label>\n"
                    + "		<input type=\"text\" name=\"country\" >\n"
                    + "		<label for=\"postCode\">Post Code (if any):</label>\n"
                    + "		<input type=\"text\" name=\"postCode\" >\n"
                    + "		<h3><br></h3>\n"
                    + "\n"
                    + "		<a href=\"/bookstore/viewdetail.do\" class=\"button\">Back to View Detail</a>\n"
                    + "		<a href=\"/bookstore/viewdetail.do\" class=\"button\">Confirm</a>\n"
                    + "\n"
                    + "\n"
                    + "	</form>\n"
                    + "</fieldset>\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "		<footer>\n"
                    + "			<iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n"
                    + "            [Your user agent does not support frames or is currently configured not to display frames.]\n"
                    + "        	</iframe>\n"
                    + "        	<iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"/bookstore/iframes/bookstorefooter.jsp\" width=\"100%\" height=\"400px\">\n"
                    + "            [Your user agent does not support frames or is currently configured not to display frames.]\n"
                    + "        	</iframe>\n"
                    + "		</footer>\n"
                    + "	</body>\n"
                    + "</html>");

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
