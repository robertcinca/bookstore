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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"    <head>\n" +
"        <!-- Meta attributes -->\n" +
"        <meta charset=\"utf-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"        <meta name=\"robots\" content=\"noindex, nofollow\">\n" +
"        <meta name=\"title\" content=\"Online Bookstore\">\n" +
"        <meta name=\"description\" content=\"An online marketplace for buying books.\">\n" +
"                            \n" +
"        <title>Welcome to our Online Bookstore!</title>\n" +
"                            \n" +
"        <!-- CSS Pages -->\n" +
"        <link href=\"/bookstore/CSS/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
"        <!-- JS Pages -->\n" +
"    </head>\n" +
"    <body>\n" +
"        <header>\n" +
"            <iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n" +
"                [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"            </iframe>\n" +
"        </header>\n" +
"        \n" +
"        <!-- Navigation -->\n" +
"        <div class=\"dropdown\">\n" +
"            <button class=\"dropbtn\">MENU</button>\n" +
"            <div class=\"dropdown-content\">\n" +
"                <ul class=\"nav\">\n" +
"                    <li><a href=\"/bookstore/pages/index.jsp\">Login</a></li>\n" +
"                    <li><a href=\"/bookstore/pages/browse.jsp\">Browse</a></li>\n" +
"                    <li><a href=\"/bookstore/pages/viewcart.jsp\">View Cart</a></li>\n" +
"                    <li><a href=\"/bookstore/pages/payment.jsp\">Pay Now</a></li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </div>\n" +
"		\n" +
"		<h1>Payment Confirmation Page</h1>\n" +
"		<p>Success! Your payment has been processed.</p>\n" +
"		<a href=\"/bookstore/pages/browse.jsp\" class=\"button\">Continue Browsing Bookstore...</a>\n" +
"                <a href=\"/bookstore/pages/viewdetail.jsp\" class=\"button\">View your member details</a>\n" +
"		<a href=\"/bookstore/pages/index.jsp\" class=\"button\">Sign Out</a>\n" +
"		<br>\n" +
"\n" +
"		<footer>\n" +
"			<iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n" +
"            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"        	</iframe>\n" +
"        	<iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"/bookstore/iframes/bookstorefooter.jsp\" width=\"100%\" height=\"400px\">\n" +
"            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"        	</iframe>\n" +
"		</footer>\n" +
"	</body>\n" +
"</html>");
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
