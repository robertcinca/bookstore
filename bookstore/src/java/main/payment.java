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
            //Begin Header
            out.println(" <!DOCTYPE html>"
                    + "<html lang='en'>"
                    + "    <head>"
                    // <!-- Meta attributes -->"
                    + "        <meta charset='utf-8'>"
                    + "        <meta name='viewport' content='width=device-width, initial-scale=1'>"
                    + "        <meta name='robots' content='noindex, nofollow'>"
                    + "        <meta name='title' content='Online Bookstore'>"
                    + "        <meta name='description' content='An online marketplace for buying books.'>"
                    // <!-- Page Title -->"
                    + "        <title>Welcome to our Online Bookstore!</title>"
                    // <!-- CSS Pages -->"
                    + "        <link href='/Bookstore/CSS/theme.css' rel='stylesheet' type='text/css'/>"
                    + "        <link href='/Bookstore/CSS/login.css' rel='stylesheet' type='text/css'/>"
                    // <!-- JS Pages -->"
                    + "    </head>"
                    + "    <body>"
                    + "        <header>"
                    + "            <iframe frameborder='0' scrolling='no' id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp' width='100%'>"
                    + "                [Your user agent does not support frames or is currently configured not to display frames.]"
                    + "            </iframe>"
                    + "        </header>"
                    // <!-- Navigation -->"
                    + "        <div class='dropdown'>"
                    + "            <button class='dropbtn'>MENU</button>"
                    + "            <div class='dropdown-content'>"
                    + "                <ul class='nav'>");
            if (request.getSession(true) != null) {
                out.println("              <li><a href='/Bookstore/logout.do'>Logout</a></li>");
            } else {
                out.println("              <li><a href='/Bookstore/browse.do'>Login</a></li>");
            }
            out.println("                  <li><a href='/Bookstore/browse.do'>Browse</a></li>"
                    + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>");
            if (!request.isUserInRole("guest")) {
                out.println("              <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>");
            }
            out.println("              </ul>"
                    + "            </div>"
                    + "        </div>");
            // Begin Page
            out.println("       <h1>Payment Page</h1>");

            String submitValue = request.getParameter("payPoints");
            String submitValue2 = request.getParameter("payCard");
            int submitValue3 = Integer.parseInt(request.getParameter("totalAmount"));
            int submitValue4 = Integer.parseInt(request.getParameter("totalLoyalty"));
            String currentUser = request.getRemoteUser();

            if (submitValue != null && !submitValue.equalsIgnoreCase("")) {
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

                out.println("            <fieldset>"
                        + "                <legend>Pay for transaction (With Points)</legend>");
                if (userLoyalty >= submitValue4 * 10) {
                    out.println("                 <form action='/Bookstore/confirmation.do' method='post'>"
                            + "                 <h2> You have " + userLoyalty + " points. This transaction will use " + submitValue4 * 10 + " points.</h2>"
                            + "                 <p> Note: you will not gain loyalty points if you pay with loyalty points.</p>"
                            + "                 <p>You will also not be able to refund your purchase.</p>"
                            + "                <h3>Enter delivery address</h3>"
                            + "                <label>Address Line 1:</label>"
                            + "				<input type='name' name='addr1' required>"
                            + "                <label>Address Line 2:</label>"
                            + "                             <input type='name' name='addr2' required>"
                            + "                <label>City:</label>"
                            + "				<input type='name' name='city' required>"
                            + "                <label>Country:</label>"
                            + "                             <input type='name' name='country' required>"
                            + "                <label>Post Code (if any):</label>"
                            + "                             <input type='name' name='postcode'>"
                            + "<input type='hidden' value='paidPoints' name='paidPoints' id='paidPoints' />"
                            + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                            + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                            + "                <button style='width:100%; font-size:18px; border: 5px solid black;' name='pointsPaid' value='pointsPaid' type='submit'>Confirm Payment</button>"
                            + "                <a href='/Bookstore/viewcart.do' class='cancelbtn' style='width:12%; border: 5px solid black;'>Return to Cart</a>"
                            + "       </fieldset>"
                            + "</form>");
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
                            + "<a href='/Bookstore/viewcart.do' class='cancelbtn' style='width:12%; border: 5px solid black;'>Return to Cart</a>"
                            + "</form>"
                            + "</fieldset>");
                }
            } else if (submitValue2 != null && !submitValue2.equalsIgnoreCase("")) {
                out.println("		<form action='/Bookstore/confirmation.do' method='post'>"
                        + "            <fieldset>"
                        + "                <legend>Pay for transaction (With Card)</legend>"
                        + "                 <h2> This transaction will cost HKD" + submitValue3 + ".00</h2>");
                if (!"guest".equals(currentUser)) {
                    out.println("                 <h2> You will gain " + submitValue4 + " points during this transaction.</h2>");
                }
                out.println("                     <h3>Enter your card details</h3>"
                        + "                 <label>Card Name:</label>"
                        + "                             <input type='name' name='cardName' required>"
                        + "                <label>Card Number:</label>"
                        + "                             <input type='name' name='cardNo' required>"
                        + "                <label>Expiry Date:</label>"
                        + "                             <input type='date' name='expiryDate' required>"
                        + "                <label>Security Code:</label>"
                        + "                             <input type='name' name='securityCode' required>"
                        + "             <h3>Enter delivery address</h3>"
                        + "                <label>Address Line 1:</label>"
                        + "				<input type='name' name='addr1' required>"
                        + "                <label>Address Line 2:</label>"
                        + "                             <input type='name' name='addr2' required>"
                        + "                <label>City:</label>"
                        + "				<input type='name' name='city' required>"
                        + "                <label>Country:</label>"
                        + "                             <input type='name' name='country' required>"
                        + "                <label>Post Code (if any):</label>"
                        + "                             <input type='name' name='postcode'>"
                        + "<input type='hidden' value='paidCard' name='paidCard' id='paidCard' />"
                        + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                        + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                        + "                <button style='width:100%; font-size:18px; border: 5px solid black;' name='pointsPaid' value='pointsPaid' type='submit'>Confirm Payment</button>"
                        + "                <a href='/Bookstore/viewcart.do' class='cancelbtn' style='width:12%; border: 5px solid black;'>Return to Cart</a>"
                        + "            </fieldset>"
                        + "        </form>");
                if (!"guest".equals(currentUser)) {
                    out.println("<fieldset> Other Options:"
                            + "<form method='POST'>"
                            + "<input type='hidden' value='payPoints' name='payPoints' id='payPoints' />"
                            + "<input type='hidden' value=" + submitValue3 + " name='totalAmount' id='totalAmount' />"
                            + "<input type='hidden' value=" + submitValue4 + " name='totalLoyalty' id='totalLoyalty' />"
                            + "<button type='submit' class='button' style='float:left;'>Pay by Points</button>"
                            + "</form>"
                            + "</fieldset>");
                }

            } else {
                int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
                int totalLoyalty = Integer.parseInt(request.getParameter("totalLoyalty"));
                out.println("<h2> The total amount to pay is: HKD " + totalAmount + ".00</h2>");
                if (!"guest".equals(currentUser)) {
                    out.println("<h2> The total loyalty points you will gain: " + totalLoyalty + " points</h2>"
                            + "<h2>Alternatively, the total amount to pay using loyalty points: " + totalLoyalty * 10 + "</h2>");
                }
                out.println(""
                        + "<fieldset> Choose payment type:"
                        + "<form method='POST'>"
                        + "<input type='hidden' value='payCard' name='payCard' id='payCard' />"
                        + "<input type='hidden' value=" + totalAmount + " name='totalAmount' id='totalAmount' />"
                        + "<input type='hidden' value=" + totalLoyalty + " name='totalLoyalty' id='totalLoyalty' />"
                        + "<button type='submit' class='button' style='float:left;'>Pay by Card</button>"
                        + "</form>");
                if (!"guest".equals(currentUser)) {
                    out.println("<form method='POST'>"
                            + "<input type='hidden' value='payPoints' name='payPoints' id='payPoints' />"
                            + "<input type='hidden' value=" + totalAmount + " name='totalAmount' id='totalAmount' />"
                            + "<input type='hidden' value=" + totalLoyalty + " name='totalLoyalty' id='totalLoyalty' />"
                            + "<button type='submit' class='button'>Pay by Points: </button>"
                            + "</form>");
                }
                out.println("</fieldset>");

            }
            //footer
            out.println("       <br>"
                    + "         <footer>"
                    + "             <iframe frameborder='0' scrolling='no' id='bookstorefooter' name='bookstorefooter' src='/Bookstore/iframes/bookstorefooter.jsp' width='100%' height='100px'>"
                    + "                 [Your user agent does not support frames or is currently configured not to display frames.]"
                    + "             </iframe>"
                    + "             <iframe frameborder='0' scrolling='no' id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp' width='100%'>"
                    + "                 [Your user agent does not support frames or is currently configured not to display frames.]"
                    + "             </iframe>"
                    + "         </footer>"
                    + "    </body>"
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
