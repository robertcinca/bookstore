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
public class refund extends HttpServlet {

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
                    out.println("              <li><a href='/Bookstore/browse.do'>Login</a></li>\n");
                }
                out.println("                  <li><a href='/Bookstore/browse.do'>Browse</a></li>"
                        + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>"
                        + "                    <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>"
                        + "                </ul>"
                        + "            </div>"
                        + "        </div>");
                // Begin Page
                out.println("       <h1>Refund Requests</h1>\n"
                        + "\n"
                        + "		<a href=\"/Bookstore/browse.do\" class=\"button\">Back to browse</a>\n"
                        + "		<br>\n"
                        + "\n"
                        + "		<!--Refund Requests-->\n"
                        + "\n"
                        + "		<table class=\"refundRequest\">\n"
                        + "			<col width=\"20%\">\n"
                        + "  		<col width=\"40%\">\n"
                        + "			<col width=\"15%\">\n"
                        + "  		<col width=\"10%\">\n"
                        + "			<col width=\"15%\">\n"
                        + "\n"
                        + "		  <tr>\n"
                        + "				<th>User</th>\n"
                        + "		    <th>Book Title</th>\n"
                        + "				<th>Quantity</th>\n"
                        + "				<th></th>\n"
                        + "\n");

                // make connection to db and retrieve data from the table
                String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                String dbLoginId = "aiad034";
                String dbPwd = "aiad034";

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

                //update refund request
                String action = request.getParameter("action");

                int purchase_id = 0;
                if (request.getParameter("purchase_id") != null && !request.getParameter("purchase_id").equalsIgnoreCase("")) {
                    purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
                }

                if (action != null && purchase_id != 0) {
                    PreparedStatement pstmt = con.prepareStatement("UPDATE [purchased] SET status = ? WHERE ID_purchased = " + purchase_id);

                    if (action.equalsIgnoreCase("accept")) {
                        pstmt.setString(1, "refund accepted");
                    } else if (action.equalsIgnoreCase("decline")) {
                        pstmt.setString(1, "refund declined");
                    }
                    Boolean result = pstmt.execute();

                    if (pstmt != null) {
                        pstmt.close();
                    }
                }

                //refund list
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM [purchased] WHERE status = 'refund requested' AND refundable = 'yes'");

                ResultSet rs = stmt.executeQuery();
                while (rs != null && rs.next() != false) {
                    String username = rs.getString("user_name");
                    String bookname = rs.getString("bookname");
                    int quantity = rs.getInt("quantity");
                    purchase_id = rs.getInt("ID_purchased");
                    String refundable = rs.getString("refundable");

                    

                        out.println("		  </tr>\n"
                                + "		  <tr>\n"
                                + "		    <td >" + username + "</td>\n"
                                + "		    <td >" + bookname + "</td>\n"
                                + "				<td >" + quantity + "</td>\n"
                                + "				<td >\n"
                                + "					<form class=\"refundButton\" style=\"float:right\">\n"
                                + "                                             <input name='purchase_id' type='hidden' value='" + purchase_id + "' />"
                                + "						<input name='action' type=\"submit\" value=\"Accept\">\n"
                                + "						<input name='action' type=\"submit\" value=\"Decline\">\n"
                                + "					</form>\n"
                                + "				</td>\n"
                                + "		  </tr>\n");
                    
                }

                out.println("		</table>\n");

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
            } catch (java.lang.ClassNotFoundException | SQLException e) {
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
