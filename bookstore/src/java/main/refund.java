/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
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
                out.println("              <li><a href='/Bookstore/login.do'>Login</a></li>\n");
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
                    + "		    <th>Price</th>\n"
                    + "				<th>Quantity</th>\n"
                    + "				<th></th>\n"
                    + "\n"
                    + "		  </tr>\n"
                    + "		  <tr>\n"
                    + "		    <td >Username</td>\n"
                    + "		    <td >Book Title</td>\n"
                    + "				<td >HKD 100</td>\n"
                    + "				<td >1</td>\n"
                    + "				<td >\n"
                    + "					<form class=\"refundButton\" style=\"float:right\">\n"
                    + "						<input type=\"button\" value=\"Accept\">\n"
                    + "						<input type=\"button\" value=\"Decline\">\n"
                    + "					</form>\n"
                    + "				</td>\n"
                    + "		  </tr>\n"
                    + "		  <tr>\n"
                    + "				<td >Username</td>\n"
                    + "		    <td >Book Title</td>\n"
                    + "				<td >HKD 100</td>\n"
                    + "				<td >1</td>\n"
                    + "				<td >\n"
                    + "					<form class=\"refundButton\" style=\"float:right\">\n"
                    + "						<input type=\"button\" value=\"Accept\">\n"
                    + "						<input type=\"button\" value=\"Decline\">\n"
                    + "					</form>\n"
                    + "				</td>\n"
                    + "		  </tr>\n"
                    + "		</table>\n");
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
