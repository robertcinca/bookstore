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
public class viewcart extends HttpServlet {

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
                    + "        <title>Your Cart</title>"
                    // <!-- CSS Pages -->"
                    + "        <link href='/Bookstore/CSS/theme.css' rel='stylesheet' type='text/css'/>"
                    + "        <link href='/Bookstore/CSS/viewcart.css' rel='stylesheet' type='text/css'/>"
                    // <!-- JS Pages -->"
                    + "        <script src='/Bookstore/JS/basicFunctions.js' type='text/javascript'></script>"
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
            out.println("		<!-- View Cart Headings-->");
            String currentUser = request.getRemoteUser();
            if ("admin".equals(currentUser)) {
                out.println("<h1>This page displays the user's cart in a table format.</h1>"
                        + "		<a href='/Bookstore/browse.do' class='button'>Back to main admin page</a>"
                        + "             <br>");
            } else {
                if (!request.isUserInRole("guest")) {
                    out.println("<h1>Confirm Your Order, " + currentUser + "</h1>");
                } else {
                    out.println("<h1>Confirm Your Order, guest</h1>");
                }
                out.println("		<h2>Your Shopping Cart</h2>"
                        + "		 <!-- Get Shopping Cart Information and Display It in a Table -->"
                        + "	<table>"
                        + "	<tr>"
                        + " <th></th>"
                        + " <th>Book Name</th>"
                        + " <th>Author</th>"
                        + " <th>Quantity </th>"
                        + " <th>Unit Points </th>"
                        + " <th>Unit Price </th>"
                        + " <th>Total Price </th>"
                        + " </tr>");

                // make connection to db and retrieve data from the table
                String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                String dbLoginId = "aiad034";
                String dbPwd = "aiad034";

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Delete the book if action called
                String deleteBook = request.getParameter("bookname");
                if (deleteBook != null) {
                    try (Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd)) {
                        PreparedStatement pstmt = con.prepareStatement("DELETE FROM purchased WHERE user_name = ? AND status = ? AND bookname = ?");
                        pstmt.setString(1, currentUser);
                        pstmt.setString(2, "pending");
                        pstmt.setString(3, deleteBook);
                        // execute the SQL statement
                        int rows = pstmt.executeUpdate();
                    }
                }

                // Update quantity if changed
                String updateQuantity = request.getParameter("quantity");
                String updateBook = request.getParameter("bookname2");
                if (updateQuantity != null) {
                    try (Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd)) {
                        PreparedStatement pstmt2 = con.prepareStatement("UPDATE purchased SET quantity = ? WHERE user_name = ? AND status = ? AND bookname = ?");
                        pstmt2.setString(1, updateQuantity);
                        pstmt2.setString(2, currentUser);
                        pstmt2.setString(3, "pending");
                        pstmt2.setString(4, updateBook);
                        // execute the SQL statement
                        int rows = pstmt2.executeUpdate();
                    }
                }

                //Create view cart table
                int totalAmount;
                int totalLoyalty;
                try (Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd); PreparedStatement stmt = con.prepareStatement("SELECT * FROM purchased WHERE user_name = ? AND status = ?")) {
                    stmt.setString(1, currentUser);
                    stmt.setString(2, "pending");
                    ResultSet rs = stmt.executeQuery();
                    totalAmount = 0;
                    totalLoyalty = 0;
                    while (rs != null && rs.next() != false) {
                        String bookname = rs.getString("bookname");
                        int quantity = rs.getInt("quantity");
                        try (PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM book WHERE bookname = ?")) {
                            stmt2.setString(1, bookname);
                            ResultSet rs2 = stmt2.executeQuery();

                            while (rs2 != null && rs2.next() != false) {

                                String author = rs2.getString("author");
                                int loyalty = rs2.getInt("loyalty");
                                int price = rs2.getInt("price");
                                int quantityAvailable = rs2.getInt("stock");
                                int totalPrice = price * quantity;
                                int totalPoints = loyalty * quantity;
                                totalAmount += totalPrice;
                                totalLoyalty += totalPoints;
                                out.println("</tr>"
                                        + "<td>"
                                        + "     <form method='POST'>"
                                        + "     <input type='hidden' value='" + bookname + "' name='bookname' id='bookname'  />"
                                        + "     <input type='submit' value='Delete'/>"
                                        + "     </form>"
                                        + "</td>"
                                        + "<td>" + bookname + "</td>"
                                        + "<td>" + author + "</td>"
                                        + "<td>"
                                        + "     <form name='AddToCartForm' id='AddToCartForm' onsubmit='return validateAddToCart()' method='POST'>"
                                        + "     <input type='hidden' value='" + bookname + "' name='bookname2' id='bookname2'  />"
                                        + "     <input type='number' name='quantity' id='quantity' value='" + quantity + "' min='1' max='" + quantityAvailable + "'/>"
                                        + "     <input type='submit' value='Change'>"
                                        + "     </form>"
                                        + "</td>"
                                        + "<td>" + loyalty + "</td>"
                                        + "<td>" + price + "</td>"
                                        + "<td>" + totalAmount + "</td>"
                                        + "</tr>"
                                );
                            }
                        }

                    }
                    if (rs != null) {
                        rs.close();
                    }
                }

                out.println("<tfoot>"
                        + " <tr>"
                        + "	<td></td>"
                        + "	<td></td>"
                        + "	<td></td>"
                        + " <td colspan='3' style='text-align:right'>Total Price (HKD):</td>"
                        + " <td>" + totalAmount + ".00</td>"
                        + " </tr>"
                        + "  </tfoot>");
                out.println("</table>");

                out.println("        <br>"
                        + "<form name='Form3' action='/Bookstore/payment.do' onsubmit='return checkSpend()' method='POST'>"
                        + "<input name='totalAmount' type='hidden' value=" + totalAmount + ">"
                        + "<input name='totalLoyalty' type='hidden' value=" + totalLoyalty + ">"
                        + "<button class='button' style='float:left;' name='proceedPayment' value='proceedPayment' type='submit'>Pay Now</button>"
                        + "</form>"
                        + "		<a href='/Bookstore/browse.do' class='button'>Browse Books</a>");
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
            Logger.getLogger(viewcart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(viewcart.class.getName()).log(Level.SEVERE, null, ex);
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
