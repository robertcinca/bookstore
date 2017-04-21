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
public class confirmation extends HttpServlet {

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
                    // <!-- JS Pages -->"
                    + "    </head>"
                    + "    <body>"
                    + "        <header>"
                    + "            <iframe  scrolling='no' id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp' width='100%'>"
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
            out.println("       <h1>Payment Confirmation Page</h1>"
                    + "		<h1>Success! Your payment has been processed.</h1>");

            String confirmationValue = request.getParameter("paidPoints");
            String confirmationValue2 = request.getParameter("paidCard");

            int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
            int totalLoyalty = Integer.parseInt(request.getParameter("totalLoyalty"));
            int userLoyalty = 0;
            String currentUser = request.getRemoteUser();

            // make connection to db and retrieve data from the table
            String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
            String dbLoginId = "aiad034";
            String dbPwd = "aiad034";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd)) {
                PreparedStatement stmt3;
                ResultSet rs3;
                try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM tomcat_users_loyalty WHERE user_name = ?")) {
                    stmt.setString(1, currentUser);
                    ResultSet rs = stmt.executeQuery();
                    ResultSet rs4;
                    try (PreparedStatement stmt4 = con.prepareStatement("SELECT * FROM purchased WHERE user_name = ? AND status = ?")) {
                        stmt4.setString(1, currentUser);
                        stmt4.setString(2, "pending");
                        rs4 = stmt4.executeQuery();
                        while (rs != null && rs.next() != false) {
                            userLoyalty = Integer.parseInt(rs.getString("loyalty"));
                            while (rs4 != null && rs4.next() != false) {
                                if (confirmationValue != null) {
                                    userLoyalty -= 10 * totalLoyalty;
                                } else {
                                    userLoyalty += totalLoyalty;
                                }
                            }
                        }
                    }
                    if (rs4 != null) {
                        rs4.close();
                    }
                    if (confirmationValue != null) {
                        out.println("<h2> You paid by points</h2>");
                        out.println("<h2> The following loyalty points have been deducted from your account: " + totalLoyalty * 10 + "</h2>");
                        out.println("<h2> Your new total loyalty points: " + userLoyalty + " points</h2>");
                    } else {
                        out.println("<h2> You paid by card</h2>");
                        out.println("<h2> The following amount has been deducted from your card: HKD" + totalAmount + ".00</h2>");
                        if (!"guest".equals(currentUser)) {
                            out.println("<h2> The following loyalty points have been added to your account: " + totalLoyalty + " points</h2>");
                            out.println("<h2> Your new total loyalty points: " + userLoyalty + " points</h2>");
                        }
                    }
                    out.println("<h2> You have purchased the following books: </h2>");
                    out.println("<table>"
                            + "	<tr>"
                            + "	<th>Book Name</th>"
                            + "	<th>Author</th>"
                            + " <th>Quantity </th>"
                            + " </tr>");
                    stmt3 = con.prepareStatement("SELECT * FROM purchased WHERE user_name = ? AND status = ?");
                    stmt3.setString(1, currentUser);
                    stmt3.setString(2, "pending");
                    rs3 = stmt3.executeQuery();
                    while (rs3 != null && rs3.next() != false) {
                        String bookname = rs3.getString("bookname");
                        int quantity = rs3.getInt("quantity");

                        try (PreparedStatement pstmt2 = con.prepareStatement("UPDATE purchased SET status = ?, refundable = ? WHERE user_name = ? AND status = ?")) {
                            pstmt2.setString(1, "purchased");
                            if (confirmationValue == null) {
                                pstmt2.setString(2, "yes");
                            } else {
                                pstmt2.setString(2, "no");
                            }
                            pstmt2.setString(3, currentUser);
                            pstmt2.setString(4, "pending");

                            Boolean result2 = pstmt2.execute();
                            if (!"guest".equals(currentUser)) {
                                try (PreparedStatement pstmt = con.prepareStatement("UPDATE tomcat_users_loyalty SET loyalty = ? WHERE user_name = ?")) {
                                    pstmt.setInt(1, userLoyalty);
                                    pstmt.setString(2, currentUser);

                                    Boolean result = pstmt.execute();
                                }

                            }
                        }

                        // Update quantity if changed
                        PreparedStatement pstmt3 = con.prepareStatement("UPDATE book SET stock = stock - ? WHERE bookname = ?");
                        pstmt3.setInt(1, quantity);
                        pstmt3.setString(2, bookname);
                        // execute the SQL statement
                        int rows = pstmt3.executeUpdate();

                        ResultSet rs2;
                        try (PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM book WHERE bookname = ?")) {
                            stmt2.setString(1, bookname);
                            rs2 = stmt2.executeQuery();
                            while (rs2 != null && rs2.next() != false) {
                                String author = rs2.getString("author");

                                out.println("</tr>"
                                        + "<td>" + bookname + "</td>"
                                        + "<td>" + author + "</td>"
                                        + "<td>" + quantity + "</td>"
                                        + "</tr>");
                            }
                        }

                        if (rs2 != null) {
                            rs2.close();
                        }

                    }
                    if (rs != null) {
                        rs.close();
                    }
                }

                if (rs3 != null) {
                    rs3.close();
                }

                stmt3.close();
            }

            out.println("</table>"
                    + "<h3>Please expect us to deliver your books in the next few days.</h3>"
                    + "<h3>Please select one of the following options:</h3>");
            out.println("		<a href='/Bookstore/browse.do' class='button'>Continue Browsing Bookstore...</a>");
            if (!request.isUserInRole("guest")) {
                out.println("         <a href='/Bookstore/viewdetail.do' class='button'>View your member details</a>");
            }
            out.println("		<a href='/Bookstore/logout.do' class='button'>Sign Out</a>"
                    + "		<br>");
            //footer
            out.println("       <br>"
                    + "         <footer>"
                    + "             <iframe  scrolling='no' id='bookstorefooter' name='bookstorefooter' src='/Bookstore/iframes/bookstorefooter.jsp' width='100%' height='100px'>"
                    + "                 [Your user agent does not support frames or is currently configured not to display frames.]"
                    + "             </iframe>"
                    + "             <iframe  scrolling='no' id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp' width='100%'>"
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
            Logger.getLogger(confirmation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(confirmation.class.getName()).log(Level.SEVERE, null, ex);
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
