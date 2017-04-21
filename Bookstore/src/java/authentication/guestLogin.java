/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class guestLogin extends HttpServlet {

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
                    + "    </head>");
            // Begin Page
            //login as guest, create random id
            // Register the JDBC driver, open a connection
            String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
            String dbLoginId = "aiad034";
            String dbPwd = "aiad034";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String user_name;
            //Creating the user
            try (Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd)) {
                //Creating the user
                user_name = "guest"; //random user
                // Create a preparedstatement to set the SQL statement
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO [tomcat_users] ([user_name], [password]) VALUES (?, ?)");
                pstmt.setString(1, user_name);
                pstmt.setString(2, "pwd");
                PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO [tomcat_users_roles] ([user_name], [role_name]) VALUES (?, ?)");
                pstmt2.setString(1, user_name);
                pstmt2.setString(2, "guest");
                PreparedStatement pstmt3 = con.prepareStatement("INSERT INTO [tomcat_users_loyalty] ([user_name], [loyalty]) VALUES (?, ?)");
                pstmt3.setString(1, user_name);
                pstmt3.setString(2, "0");
                // execute the SQL statement
                int rows = pstmt.executeUpdate();
                int rows2 = pstmt2.executeUpdate();
                int rows3 = pstmt3.executeUpdate();
            }

            out.println("<form id='guestUserLogin' action='j_security_check' method='POST'> "
                    + "<input type='hidden' value=" + user_name + " name='j_username' id='j_username' />"
                    + "<input type='hidden' value='pwd' name='j_password' id='j_password' />"
                    + "</form>");

            //Submit form for guest without user input
            out.println("<script type='text/javascript'>"
                    + "  document.getElementById('guestUserLogin').submit();"
                    + "</script>");

            out.println("</body>"
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
