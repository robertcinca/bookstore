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
            out.println(" <!DOCTYPE html>\n"
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
                    + "        <link href=\"/Bookstore/CSS/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                    + "        <link href=\"/Bookstore/CSS/login.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                    + "        <!-- JS Pages -->\n"
                    + "        <script src=\"/Bookstore/JS/basicFunctions.js\" type=\"text/javascript\"></script>\n"
                    + "        <script src=\"/Bookstore/JS/login.js\" type=\"text/javascript\"></script>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <header>\n"
                    + "            <iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/Bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n"
                    + "                [Your user agent does not support frames or is currently configured not to display frames.]\n"
                    + "            </iframe>\n"
                    + "        </header>\n"
                    + "        \n"
                    + "        <!-- Navigation -->\n"
                    + "        <div class=\"dropdown\">\n"
                    + "            <button class=\"dropbtn\">MENU</button>\n"
                    + "            <div class=\"dropdown-content\">\n"
                    + "                <ul class=\"nav\">\n"
                    + "                    <li><a href=\"/Bookstore/browse.do\">Browse</a></li>\n"
                    + "                    <li><a href=\"/Bookstore/viewcart.do\">View Cart</a></li>\n"
                    + "                    <li><a href=\"/Bookstore/payment.do\">Pay Now</a></li>\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        \n"
                    + "        <h1>Please sign up here</h1>\n");

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
                    //Updating the tables
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
                                out.println("<a href=\"/Bookstore/browse.do\" class=\"button\">Click here to log in!</a>\n");
                                rs.close();
                            }
                        }
                        con.close();
                    }
                } else {
                    con.close();
                    out.println("<h2>Unfortunately, that username already exists. Please try again with another username.</h2>");
                    out.println("<a href=\"/Bookstore/signup\" class=\"button\">Click here to try again.</a>\n");
                }
            } else {
                out.println("<form id=\"Form2\" name=\"Form2\" class='modal-content animate' onsubmit=\"return validateSignUp()\" method=\"post\">\n"
                        + "                <div class=\"imgcontainer\">\n"
                        + "<span onClick=\"window.open('/Bookstore/browse.do','_self');\" class='close'>&times;</span>"
                        + "                    <img src=\"/Bookstore/IMG/welcome.png\" alt=\"sign up\" class=\"avatar\">\n"
                        + "                        </div>\n"
                        + "                \n"
                        + "                <div class=\"container\">\n"
                        + "                    <label><b>Enter a Username:</b></label>\n"
                        + "                    <input type=\"text\" placeholder=\"New Username\" name=\"uname\" required>\n"
                        + "                        \n"
                        + "                        <label><b>Enter a Password:</b></label>\n"
                        + "                        <input type=\"password\" placeholder=\"New Password\" name=\"psw\" required>\n"
                        + "                        <label><b>Re-enter password:</b></label>\n"
                        + "                        <input type=\"password\" placeholder=\"New Password\" name=\"psw2\" required>\n"
                        + "                            \n"
                        + "                            <button style='width:100%; font-size:18px' name='signupbutton' value='signup' type=\"submit\">Sign Up</button>\n"
                        + "                            </div>\n"
                        + "                <div class=\"container\" style=\"background-color:#f1f1f1\">\n"
                        + "<a href='/Bookstore/browse.do' class='cancelbtn' style='width:12%'>Cancel Signup</a>\n"
                        + "                </div>\n"
                        + "            </form>\n"
                        + "        </div>\n"
                        + "\n");
            }
            //footer
            out.println("       <br>"
                    + "         <footer>"
                    + "             <iframe id='bookstorefooter' name='bookstorefooter' src='/Bookstore/iframes/bookstorefooter.jsp' width='100%' height='100px'>"
                    + "                 [Your user agent does not support frames or is currently configured not to display frames.]"
                    + "             </iframe>"
                    + "             <iframe id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp' width='100%'>"
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
