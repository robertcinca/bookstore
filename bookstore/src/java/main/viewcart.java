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
import java.sql.Statement;
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
                    + "        <script src=\"/bookstore/JS/basicFunctions.js\" type=\"text/javascript\"></script>\n"
                    + "        <script src=\"/bookstore/JS/cartview.js\" type=\"text/javascript\"></script>\n"
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
                    + "		<!-- View Cart Headings-->\n");
            String currentUser = request.getRemoteUser();
            out.println("		<h1 style=\"text-align:left;float:left;\">Confirm Your Order, " + currentUser + "</h1>\n"
                    + "		<hr style=\"clear:both;\"/>\n"
                    + "		<h2>Your Shopping Cart</h2>\n"
                    + "\n"
                    + "		 <!-- Get Shopping Cart Information and Display It in a Table -->\n"
                    + "		<table>"
                    + "		  <tr>"
                    + "		    <th>ID</th>"
                    + "		    <th>Username</th>"
                    + " <th>Book Name </th>"
                    + " <th>Status </th>"
                    + " <th>Quantity </th>"
                    + " </tr>");
//                try {

            // make connection to db and retrieve data from the table
            String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
            String dbLoginId = "aiad034";
            String dbPwd = "aiad034";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM purchased WHERE user_name = ?");
            stmt.setString(1, currentUser);
            ResultSet rs = stmt.executeQuery();

            while (rs != null && rs.next() != false) {
                int id = rs.getInt("ID_purchased");
                String username = rs.getString("user_name");
                String name = rs.getString("bookname");
                String status = rs.getString("status");
                int quantity = rs.getInt("quantity");
                
                PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM book WHERE bookname = ?");
                stmt2.setString(1, name);
                ResultSet rs2 = stmt2.executeQuery();
                
                //TODO: table

                out.println("</tr>"
                        + "<td>" + id + "</td>"
                        + "<td>" + username + "</td>"
                        + "<td>" + name + "</td>"
                        + "<td>" + status + "</td>"
                        + "<td>" + quantity + "</td>"
                        + "</tr>");

            }

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
            out.println("</table>\n");

            out.println( "        <div align=\"center\" id=\"showData\"><script type=\"text/javascript\">javascript:getShoppingCart()</script></div>\n");
            out.println("        <br>\n"
                    + "\n"
                    + "		<a href=\"/bookstore/payment.do\" class=\"button\">Pay Now</a>\n"
                    + "		<a href=\"/bookstore/browse.do\" class=\"button\">Browse Books</a>\n"
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
            Logger.getLogger(viewcart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewcart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
