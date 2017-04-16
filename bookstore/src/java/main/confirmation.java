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
import javax.servlet.http.HttpSession;

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
                    + "		\n"
                    + "		<h1>Payment Confirmation Page</h1>\n"
                    + "		<h1>Success! Your payment has been processed.</h1>");

            //TODO: list actions that have occurred
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
            Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM tomcat_users_loyalty WHERE user_name = ?");
            stmt.setString(1, currentUser);
            ResultSet rs = stmt.executeQuery();

            while (rs != null && rs.next() != false) {
                userLoyalty = Integer.parseInt(rs.getString("loyalty"));
                if (confirmationValue != null) {
                    userLoyalty -= 10 * totalLoyalty;
                } else {
                    userLoyalty += totalLoyalty;
                }
            }

            if (confirmationValue != null) {
                out.println("<h2> You paid by points</h2>");
                out.println("<h2> The following loyalty points have been deducted from your account: " + totalLoyalty * 10 + "</h2>");
                out.println("<h2> Your new total loyalty points: " + userLoyalty + " points</h2>");
                out.println("<h2> You have purchased the following books: </h2>");
            } else {
                out.println("<h2> You paid by card</h2>");
                out.println("<h2> The following amount has been deducted from your card: HKD" + totalAmount + ".00</h2>");
                out.println("<h2> The following loyalty points have been added to your account: " + totalLoyalty + " points</h2>");
                out.println("<h2> Your new total loyalty points: " + userLoyalty + " points</h2>");
                out.println("<h2> You have purchased the following books: </h2>");
            }

            out.println("<table>"
                    + "	<tr>"
                    + "	<th>Book Name</th>"
                    + "	<th>Author</th>"
                    + " <th>Quantity </th>"
                    + " </tr>");

            PreparedStatement stmt3 = con.prepareStatement("SELECT * FROM purchased WHERE user_name = ?");
            stmt3.setString(1, currentUser);
            ResultSet rs3 = stmt3.executeQuery();

            while (rs3 != null && rs3.next() != false) {
                String bookname = rs3.getString("bookname");
                String status = rs3.getString("status");
                int quantity = rs3.getInt("quantity");
                out.println("<h1>" + status + "</h1>");
                if ("pending".equals(status)) {
                    PreparedStatement pstmt = con.prepareStatement("UPDATE tomcat_users_loyalty SET loyalty = ? WHERE user_name = ?");
                    pstmt.setInt(1, userLoyalty);
                    pstmt.setString(2, currentUser);

                    Boolean result = pstmt.execute();
                    
                    status = "purchased";
                    PreparedStatement pstmt2 = con.prepareStatement("UPDATE purchased SET status = ? WHERE user_name = ?");
                    pstmt2.setString(1, status);
                    pstmt2.setString(2, currentUser);

                    PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM book WHERE bookname = ?");
                    stmt2.setString(1, bookname);
                    ResultSet rs2 = stmt2.executeQuery();

                    while (rs2 != null && rs2.next() != false) {

                        String author = rs2.getString("author");

                        out.println("</tr>"
                                + "<td>" + bookname + "</td>"
                                + "<td>" + author + "</td>"
                                + "<td>" + quantity + "</td>"
                                + "</tr>");
                    }
                    if (stmt2 != null) {
                        stmt2.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (pstmt2 != null) {
                        pstmt2.close();
                    }
                }
            }

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }

            if (rs3 != null) {
                rs3.close();
            }
            if (stmt3 != null) {
                stmt3.close();
            }
            if (con != null) {
                con.close();
            }
            out.println("</table>"
                    + "<h3>Please expect us to deliver your books in the next few days.</h3>"
                    + "<h3>Please select one of the following options:</h3>");
            out.println("		<a href=\"/bookstore/browse.do\" class=\"button\">Continue Browsing Bookstore...</a>\n"
                    + "                <a href=\"/bookstore/viewdetail.do\" class=\"button\">View your member details</a>\n"
                    + "		<a href=\"/bookstore/logout.do\" class=\"button\">Sign Out</a>\n"
                    + "		<br>\n"
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(confirmation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(confirmation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
