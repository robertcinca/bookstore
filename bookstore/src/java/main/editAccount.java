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
import java.sql.SQLException;
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
            try {
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
                        + "        <link href='/Bookstore/CSS/browse.css' rel='stylesheet' type='text/css'/>"
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
                        + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>"
                        + "                    <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>"
                        + "                </ul>"
                        + "            </div>"
                        + "        </div>");
                // Begin Page
                out.println("       <h1>Edit Account Details</h1>"
                        + "		<a href='/Bookstore/viewdetail.do' class='button'>View Details</a>"
                        + "		<h3></h3>");

                String username = request.getRemoteUser();
                String password = request.getParameter("password");

                if (username != null && !username.equalsIgnoreCase("")
                        && password != null && !password.equalsIgnoreCase("")) {

                    // Register the JDBC driver, open a connection
                    String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                    String dbLoginId = "aiad034";
                    String dbPwd = "aiad034";

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

                    PreparedStatement pstmt = con.prepareStatement("UPDATE tomcat_users SET password = ? WHERE user_name = '" + username + "'");
                    pstmt.setString(1, password);

                    Boolean result = pstmt.execute();

                    out.println("<fieldset>");
                    out.println("<legend>The record is sucessfully updated.</legend>");

                    out.println("<p>Username: " + username + "</p>");
                    out.println("<p>Password: " + password + "</p>");
                    out.println("</fieldset>");

                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }

                } else {
                    out.println("<fieldset>"
                            + "	<form method='POST' id='editAccount' action='" + request.getRequestURI() + "' onsubmit='return validatepassword()' >"
                            + "		<h3>You can change your password here:</h3>"
                            + "		<label for='username'>Username: </label>"
                            + "		<input type='text' name='username' value ='" + request.getRemoteUser() + "' disabled>"
                            + "		<label for='password'>Password:</label>"
                            + "		<input type='password' name='password' required>"
                            + "		<label for='password2'>Confirm Password:</label>"
                            + "		<input type='password' name='password2' required>"
                            + "		<h3><br></h3>"
                            + "		<a href='/Bookstore/viewdetail.do' class='button'>Back to View Details</a>"
                            + "		<input class='button' type='submit' value='Confirm' />"
                            + "	</form>"
                            + "</fieldset>");
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
            } catch (ClassNotFoundException | SQLException e) {
                out.println("<div style='color: red'>" + e.toString() + "</div>");
            } finally {
                out.close();
            }
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
