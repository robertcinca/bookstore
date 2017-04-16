/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author robertcinca
 */
public class payment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
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
                    + "        <link href=\"/bookstore/CSS/login.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
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
                    + "                <ul class=\"nav\">\n");
            if (request.getSession(true) != null) {
                out.println("  <li><a href=\"/bookstore/logout.do\">Logout</a></li>\n");
            } else {
                out.println("  <li><a href=\"/bookstore/login.do\">Login</a></li>\n");
            }
            out.println("                    <li><a href=\"/bookstore/browse.do\">Browse</a></li>\n"
                    + "                    <li><a href=\"/bookstore/viewcart.do\">View Cart</a></li>\n"
                    + "                    <li><a href=\"/bookstore/payment.do\">Pay Now</a></li>\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "\n"
                    + "		<h1>Payment Page</h1>\n");

            String submitValue = request.getParameter("payPoints");
            String submitValue2 = request.getParameter("payCard");
            int submitValue3 = Integer.parseInt(request.getParameter("totalAmount"));
            int submitValue4 = Integer.parseInt(request.getParameter("totalLoyalty"));
            if (submitValue != null && !submitValue.equalsIgnoreCase("")) {
                String currentUser = request.getRemoteUser();
                int userLoyalty = 0;
                // make connection to db and retrieve data from the table
                String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                String dbLoginId = "aiad034";
                String dbPwd = "aiad034";

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                try (Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd); PreparedStatement stmt = con.prepareStatement("SELECT * FROM tomcat_users_loyalty WHERE user_name = ?")) {
                    stmt.setString(1, currentUser);
                    ResultSet rs = stmt.executeQuery();

                    while (rs != null && rs.next() != false) {
                        userLoyalty = Integer.parseInt(rs.getString("loyalty"));
                    }
                    if (rs != null) {
                        rs.close();
                    }

                }

                out.println("            <fieldset>\n"
                        + "                <legend>Pay for transaction (With Points)</legend>\n");
                if (userLoyalty >= submitValue4 * 10) {
                    out.println("                 <form action=\"/bookstore/confirmation.do\" method=\"post\">\n"
                            + "                 <h2> You have " + userLoyalty + " points. This transaction will use " + submitValue4 * 10 + " points.</h2>"
                            + "                 <h2> Please note: you will not gain any points during this transaction as you are already paying by points.</h2>"
                            + "                <h3>Enter delivery address</h3>\n"
                            + "                <label>Address Line 1:</label>\n"
                            + "				<input type=\"name\" name=\"addr1\" required>\n"
                            + "                <label>Address Line 2:</label>\n"
                            + "                             <input type=\"name\" name=\"addr2\" required>\n"
                            + "                <label>City:</label>\n"
                            + "				<input type=\"name\" name=\"city\" required>\n"
                            + "                <label>Country:</label>\n"
                            + "                             <input type=\"name\" name=\"country\" required>\n"
                            + "                <label>Post Code (if any):</label>\n"
                            + "                             <input type=\"name\" name=\"postcode\">\n"
                            + "<input type='hidden' value='paidPoints' name='paidPoints' id='paidPoints' />"
                            + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                            + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                            + "                <button style='width:100%; font-size:18px; border: 5px solid black;' name='pointsPaid' value='pointsPaid' type=\"submit\">Confirm Payment</button>\n"
                            + "                <a href='/bookstore/viewcart.do' class='cancelbtn' style='width:12%; border: 5px solid black;'>Return to Cart</a>\n"
                            + "       </fieldset>"
                            + "</form>\n");
                    out.println("<fieldset> Other Options:"
                            + "<form method='POST'>"
                            + "<input type='hidden' value='payCard' name='payCard' id='payCard' />"
                            + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                            + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                            + "<button type='submit' class='button' style='float:left;'>Pay by Card</button>"
                            + "</form>"
                            + "</fieldset>");
                } else {
                    out.println("<h2> Error: you do not have enough loyalty points to pay for this transaction. </h2>"
                            + "<h2> You have " + userLoyalty + " points. This transaction needs " + submitValue4 * 10 + " points.</h2>"
                            + "<h3>Please select another method of payment or return to cart:</h3>"
                            + "<form method='POST'>"
                            + "<input type='hidden' value='payCard' name='payCard' id='payCard' />"
                            + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                            + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                            + "<button type='submit' class='button'>Pay by Card</button>"
                            + "<a href='/bookstore/viewcart.do' class='cancelbtn' style='width:12%; border: 5px solid black;'>Return to Cart</a>"
                            + "</form>"
                            + "</fieldset>");
                }
            } else if (submitValue2 != null && !submitValue2.equalsIgnoreCase("")) {
                out.println("		<form action=\"/bookstore/confirmation.do\" method=\"post\">\n"
                        + "            <fieldset>\n"
                        + "                <legend>Pay for transaction (With Card)</legend>\n"
                        + "                 <h2> This transaction will cost HKD" + submitValue3 + ".00</h2>"
                        + "                 <h2> You will gain " + submitValue4 + " points during this transaction.</h2>"
                        + "                     <h3>Enter your card details</h3>\n"
                        + "                 <label>Card Name:</label>\n"
                        + "                             <input type=\"name\" name=\"cardName\" required>\n"
                        + "                <label>Card Number:</label>\n"
                        + "                             <input type=\"name\" name=\"cardNo\" required>\n"
                        + "                <label>Expiry Date:</label>\n"
                        + "                             <input type=\"date\" name=\"expiryDate\" required>\n"
                        + "                <label>Security Code:</label>\n"
                        + "                             <input type=\"name\" name=\"securityCode\" required>\n"
                        + "             <h3>Enter delivery address</h3>\n"
                        + "                <label>Address Line 1:</label>\n"
                        + "				<input type=\"name\" name=\"addr1\" required>\n"
                        + "                <label>Address Line 2:</label>\n"
                        + "                             <input type=\"name\" name=\"addr2\" required>\n"
                        + "                <label>City:</label>\n"
                        + "				<input type=\"name\" name=\"city\" required>\n"
                        + "                <label>Country:</label>\n"
                        + "                             <input type=\"name\" name=\"country\" required>\n"
                        + "                <label>Post Code (if any):</label>\n"
                        + "                             <input type=\"name\" name=\"postcode\">\n"
                        + "<input type='hidden' value='paidCard' name='paidCard' id='paidCard' />"
                        + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                        + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                        + "                <button style='width:100%; font-size:18px; border: 5px solid black;' name='pointsPaid' value='pointsPaid' type=\"submit\">Confirm Payment</button>\n"
                        + "                <a href='/bookstore/viewcart.do' class='cancelbtn' style='width:12%; border: 5px solid black;'>Return to Cart</a>\n"
                        + "            </fieldset>\n"
                        + "        </form>\n"
                        + "<fieldset> Other Options:"
                        + "<form method='POST'>"
                        + "<input type='hidden' value='payPoints' name='payPoints' id='payPoints' />"
                        + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                        + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                        + "<button type='submit' class='button' style='float:left;'>Pay by Points</button>"
                        + "</form>"
                        + "</fieldset>");

            } else {
                int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
                int totalLoyalty = Integer.parseInt(request.getParameter("totalLoyalty"));
                out.println("<h2> The total amount to pay is: HKD " + totalAmount + ".00 [OR] " + totalLoyalty * 10 + " loyalty points</h2>"
                        + "<h2> The total loyalty points you will gain: " + totalLoyalty + " points</h2>");
                out.println("\n"
                        + "<fieldset> Choose payment type:"
                        + "<form method='POST'>"
                        + "<input type='hidden' value='payCard' name='payCard' id='payCard' />"
                        + "<input type='hidden' value=" + totalAmount + " name='totalAmount' id='totalAmount' />"
                        + "<input type='hidden' value=" + totalLoyalty + " name='totalLoyalty' id='totalLoyalty' />"
                        + "<button type='submit' class='button' style='float:left;'>Pay by Card</button>"
                        + "</form>"
                        + "<form method='POST'>"
                        + "<input type='hidden' value='payPoints' name='payPoints' id='payPoints' />"
                        + "<input type='hidden' value=" + totalAmount + " name='totalAmount' id='totalAmount' />"
                        + "<input type='hidden' value=" + totalLoyalty + " name='totalLoyalty' id='totalLoyalty' />"
                        + "<button type='submit' class='button'>Pay by Points: </button>"
                        + "</form>"
                        + "</fieldset>");
            }

            out.println("		<footer>\n"
                    + "			<iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n"
                    + "            [Your user agent does not support frames or is currently configured not to display frames.]\n"
                    + "        	</iframe>\n"
                    + "        	<iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"/bookstore/iframes/bookstorefooter.jsp\" width=\"100%\" height=\"400px\">\n"
                    + "            [Your user agent does not support frames or is currently configured not to display frames.]\n"
                    + "        	</iframe>\n"
                    + "		</footer>\n"
                    + "	</body>\n"
                    + "</html>\n"
                    + "");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
        }
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
