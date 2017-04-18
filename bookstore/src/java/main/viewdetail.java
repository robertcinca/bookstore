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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lokheili3
 */
public class viewdetail extends HttpServlet {

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
            try {
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
                        + "    </head>"
                        + "    <body>"
                        + "        <header>"
                        + "            <iframe id='disclaimer' name='disclaimer' src='/Bookstore/iframes/disclaimer.jsp' width='100%'>"
                        + "                [Your user agent does not support frames or is currently configured not to display frames.]"
                        + "            </iframe>"
                        + "        </header>"
                        // <!-- Navigation -->"
                        + "        <div class='dropdown'>"
                        + "            <button class='dropbtn'>MENU</button>"
                        + "            <div class='dropdown-content'>"
                        + "                <ul class='nav'>");
                if (request.getSession(true) != null) {
                    out.println("              <li><a href='/Bookstore/logout.do'>Logout</a></li>\n");
                } else {
                    out.println("              <li><a href='/Bookstore/login.do'>Login</a></li>\n");
                }
                out.println("                  <li><a href='/Bookstore/browse.do'>Browse</a></li>"
                        + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>"
                        + "                    <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>"
                        + "                </ul>"
                        + "            </div>"
                        + "        </div>");
                // Begin Page
                out.println("       <h1>Account Detail</h1>\n"
                        + "		<a href=\"/Bookstore/browse.do\" class=\"button\">Back to Browse</a>\n"
                        + "		<a href=\"/Bookstore/viewcart.do\" class=\"button\">View Cart</a>\n"
                        + "		<br>\n"
                        + "\n"
                        + "		<!--Purchased book-->\n"
                        + "\n"
                        + "		<h2>Purchased book</h2>\n"
                        + "		<table class=\"purchasedBook\">\n"
                        + "			<col width=\"40%\">\n"
                        + "  		<col width=\"10%\">\n"
                        + "			<col width=\"10%\">\n"
                        + "  		<col width=\"20%\">\n"
                        + "			<col width=\"20%\">\n"
                        + "\n"
                        + "		  <tr>\n"
                        + "				<th>Book Title</th>\n"
                        + "		    <th>Price</th>\n"
                        + "		    <th>Quantity</th>\n"
                        + "				<th>Status</th>\n"
                        + "				<th></th>\n</tr>"
                        + "\n");

                        //purchased book list
                        // make connection to db and retrieve data from the table
                        String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                        String dbLoginId = "aiad034";
                        String dbPwd = "aiad034";

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        String currentUser = request.getRemoteUser();

                        Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd); 
                        
                        String action = request.getParameter("action");
                        int purchase_id = 0;
                        if (request.getParameter("purchase_id") != null && !request.getParameter("purchase_id").equalsIgnoreCase("")) {
                            purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
                        }

                        //update refund request
                        if(action!=null && purchase_id!=0) {
                            PreparedStatement pstmt = con.prepareStatement("UPDATE [purchased] SET status = ? WHERE ID_purchased = " + purchase_id);
                            
                            if(action.equals("refund")){
                                pstmt.setString(1, "refund requested");
                            }
                            else if(action.equals("cancel")) {
                                pstmt.setString(1, "purchased");
                            }
                            Boolean result = pstmt.execute();
                            
                            if (pstmt != null) {
                                pstmt.close();
                            }
                        }
                        
            
                        PreparedStatement stmt = con.prepareStatement("SELECT * FROM [purchased] WHERE user_name = ? AND (status = 'purchased' OR status = 'refund requested' OR status = 'refund accepted' OR status = 'refund declined')");
                        stmt.setString(1, currentUser);

                        ResultSet rs = stmt.executeQuery();
                        while (rs != null && rs.next() != false) {
                            String bookname = rs.getString("bookname");
                            int quantity = rs.getInt("quantity");
                            String status = rs.getString("status");
                            purchase_id = rs.getInt("ID_purchased");
                            String refundable = rs.getString("refundable");   
                            
                            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM [book] WHERE bookname = ?");
                            stmt2.setString(1, bookname);
                            ResultSet rs2 = stmt2.executeQuery();

                            
                            while (rs2 != null && rs2.next() != false) {
                            
                                int price = rs2.getInt("price");
                                
                                    out.println("<tr>\n"
                                    + "		    <td >"+bookname+"</td>\n");
                                    
                                    if(refundable.equals("yes")){
                                        out.println("		                <td >HKD "+price+"</td>\n");
                                    }
                                    else {
                                        out.println("		                <td >"+price+" points</td>\n"); 
                                    }
                                    
                                    out.println("				<td >"+quantity+"</td>\n"
                                    + "				<td >"+status+"</td>\n"
                                    + "				<td >\n");
                                    if(status.equals("purchased") && refundable.equals("yes")){
                                        out.println("					<form method='POST' action='" + request.getRequestURI() + "' class=\"refundButton\" style=\"float:right\">\n"
                                        + "                                             <input name='purchase_id' type='hidden' value='"+purchase_id+"' />"
                                                + "                                     <input name='action' type='hidden' value='refund' />"
                                        + "						<input type='submit' value=\"Request Refund\">\n"
                                        + "					</form>\n");
                                    }
                                    else if(status.equals("refund requested")){
                                        out.println("					<form method='POST' action='" + request.getRequestURI() + "' class=\"refundButton\" style=\"float:right\">\n"
                                        + "                                             <input name='purchase_id' type='hidden' value='"+purchase_id+"' />"
                                                + "                                     <input name='action' type='hidden' value='cancel' />"
                                        + "						<input type='submit' value=\"Cancel Request\">\n"
                                        + "					</form>\n");
                                    }

                                    out.println("				</td>\n"
                                    + "		  </tr>\n");
                                    
                                    
                            }
                            if (rs2 != null) {
                                rs2.close();
                            }
                        }
                        if (rs != null) {
                            rs.close();
                        }
                        
                    //user detail
                    out.print("		</table>\n\n"
                    + "		<!--user detail-->\n"
                    + "\n"
                    + "		<h2>Account Detail</h2>\n"
                    + "\n"
                    + "<fieldset>\n"
                    + "\n");
                    
                    String currentuser = request.getRemoteUser();
                    PreparedStatement stmt3 = con.prepareStatement("SELECT * FROM [tomcat_users] WHERE user_name = ?");
                    stmt3.setString(1, currentuser);
                    ResultSet rs3 = stmt3.executeQuery();
                    
                    PreparedStatement stmt4 = con.prepareStatement("SELECT * FROM [tomcat_users_loyalty] WHERE user_name = ?");
                    stmt4.setString(1, currentuser);
                    ResultSet rs4 = stmt4.executeQuery();
                    
                    PreparedStatement stmt5 = con.prepareStatement("SELECT * FROM [tomcat_users_roles] WHERE user_name = ?");
                    stmt5.setString(1, currentuser);
                    ResultSet rs5 = stmt5.executeQuery();

                    while (rs3 != null && rs3.next() != false && rs4 != null && rs4.next() != false && rs5 != null && rs5.next() != false) {
                        
                        String username = rs3.getString("user_name");
                        String password = rs3.getString("password");

                        int loyalty = rs4.getInt("loyalty");
                        String role = rs5.getString("role_name");


                        out.print("		<h3>User Info</h3>\n"
                        + "		<p>Username: "+username+"</p>\n"
                        + "		<p>Password: "+password+"</p>\n"
                        + "		<p>Role: "+role+"</p>\n"
                        + "		<p>Loyalty Points: "+loyalty+"</p>\n"
                        + "		<br>\n"
                        + "\n"


                        /*+ "		<h3>Credit Card Info</h3>\n"
                        + "		<p>Card Name: </p>\n"
                        + "		<p>Card Number: </p>\n"
                        + "		<p>Expiry Date: </p>\n"
                        + "		<br>\n"
                        + "\n"
                        + "		<h3>Address</h3>\n"
                        + "		<p>Address: </p>\n"
                        + "		<p>City: </p>\n"
                        + "		<p>Country: </p>\n"
                        + "		<p>Post Code (if any): </p>\n"
                        + "		<br>\n"
                        + "\n"*/


                        + "		<a href=\"/Bookstore/editAccount.do\" class=\"button\">Edit Account</a>\n"
                        + "</fieldset>\n");
                    }
                    if (rs3 != null) {
                        rs3.close();
                    }
                    if (rs4 != null) {
                        rs4.close();
                    }
                    if (rs5 != null) {
                        rs5.close();
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
                           
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
            }catch (java.lang.ClassNotFoundException | SQLException e) {
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
