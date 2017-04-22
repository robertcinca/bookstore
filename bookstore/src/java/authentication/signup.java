/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author robertcinca
 */
public class signup extends HttpServlet {

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
                    + "                <ul class='nav'>"
                    + "                     <li><a href='/Bookstore/browse.do'>Login</a></li>"
                    + "                     <li><a href='/Bookstore/browse.do'>Browse</a></li>"
                    + "                     <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>"
                    + "                     <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>"
                    + "                </ul>"
                    + "            </div>"
                    + "        </div>");
            // Begin Page
            String user_name = request.getParameter("uname");
            String password = request.getParameter("psw");
            String role_name = "sprole";
            String loyalty = "0";

            if (user_name != null && password != null) {

                // Register the JDBC driver, open a connection
                String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                String dbLoginId = "aiad034";
                String dbPwd = "aiad034";

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

                boolean isUnique;
                try ( //Check whether username is unique
                        PreparedStatement stmt2 = con.prepareStatement("SELECT user_name FROM tomcat_users")) {
                    ResultSet rs2 = stmt2.executeQuery();
                    isUnique = true;
                    while (rs2 != null && rs2.next() != false) {
                        String username = rs2.getString("user_name");

                        if (user_name.equals(username)) {
                            isUnique = false;
                        }

                    }
                    if (rs2 != null) {
                        rs2.close();
                    }
                }

                if (isUnique) {
                    //Creating the user
                    // Create a preparedstatement to set the SQL statement			 
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO [tomcat_users] ([user_name], [password]) VALUES (?, ?)");
                    pstmt.setString(1, user_name);
                    pstmt.setString(2, password);

                    PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO [tomcat_users_roles] ([user_name], [role_name]) VALUES (?, ?)");
                    pstmt2.setString(1, user_name);
                    pstmt2.setString(2, role_name);

                    PreparedStatement pstmt3 = con.prepareStatement("INSERT INTO [tomcat_users_loyalty] ([user_name], [loyalty]) VALUES (?, ?)");
                    pstmt3.setString(1, user_name);
                    pstmt3.setString(2, loyalty);

                    // execute the SQL statement
                    int rows = pstmt.executeUpdate();
                    int rows2 = pstmt2.executeUpdate();
                    int rows3 = pstmt3.executeUpdate();

                    if (rows > 0 && rows2 > 0 && rows3 > 0) {
                        out.println("<legend>New Username is sucessfully created.</legend>");
                        try ( // display the information of the record just added including UID
                                Statement stmt = con.createStatement()) {
                            ResultSet rs = stmt.executeQuery("SELECT @@IDENTITY AS [@@IDENTITY]");
                            if (rs != null && rs.next() != false) {
                                out.println("<p>Username: " + user_name + "</p>");
                                out.println("<a href='/Bookstore/browse.do' class='button'>Click here to log in!</a>");
                                rs.close();
                            }
                        }
                        con.close();
                    }
                } else {
                    con.close();
                    out.println("<h2>Unfortunately, that username already exists. Please try again with another username.</h2>");
                    out.println("<a href='/Bookstore/signup' class='button'>Click here to try again.</a>");
                }
            } else {
                out.println("<form id='Form2' name='Form2' class='modal-content animate' onsubmit='return validateSignUp()' method='post'>"
                        + "                <div class='imgcontainer'>"
                        + "<span onClick='window.open('/Bookstore/browse.do','_self');' class='close'>&times;</span>"
                        + "                    <img src='/Bookstore/IMG/welcome.png' alt='sign up' class='avatar'>"
                        + "                        </div>"
                        + "                "
                        + "                <div class='container'>"
                        + "                    <label><b>Enter a Username:</b></label>"
                        + "                    <input type='text' placeholder='New Username' name='uname' required>"
                        + "                        "
                        + "                        <label><b>Enter a Password:</b></label>"
                        + "                        <input type='password' placeholder='New Password' name='psw' required>"
                        + "                        <label><b>Re-enter password:</b></label>"
                        + "                        <input type='password' placeholder='New Password' name='psw2' required>"
                        + "                            "
                        + "                            <button style='width:100%; font-size:18px' name='signupbutton' value='signup' type='submit'>Sign Up</button>"
                        + "                            </div>"
                        + "                <div class='container' style='background-color:#f1f1f1'>"
                        + "<a href='/Bookstore/browse.do' class='cancelbtn' style='width:12%'>Cancel Signup</a>"
                        + "                </div>"
                        + "            </form>"
                        + "        </div>"
                );
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
        } catch (SQLException e) {
            out.println("<div style='color: red'>" + e.toString() + "</div>");
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(JDBCServlet.class.getName()).log(Level.SEVERE, null, ex);
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
