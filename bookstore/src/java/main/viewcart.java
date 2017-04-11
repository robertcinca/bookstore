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
            throws ServletException, IOException {
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
                    + "                <ul class=\"nav\">\n"
                    + "                    <li><a href=\"/bookstore/login.do\">Login</a></li>\n"
                    + "                    <li><a href=\"/bookstore/browse.do\">Browse</a></li>\n"
                    + "                    <li><a href=\"/bookstore/viewcart.do\">View Cart</a></li>\n"
                    + "                    <li><a href=\"/bookstore/payment.do\">Pay Now</a></li>\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "		\n"
                    + "		<!-- View Cart Headings-->\n"
                    + "		<h1 style=\"text-align:left;float:left;\">Confirm Your Order, </h1>\n"
                    + "		<h1 style=\"text-align:left;float:left;\" id=\"welcomeMessage\"><script>javascript:formDataUsername();</script></h1>\n"
                    + "		<hr style=\"clear:both;\"/>\n"
                    + "		<h2>Your Shopping Cart</h2>\n"
                    + "\n"
                    + "		 <!-- Get Shopping Cart Information and Display It in a Table -->\n"
                    + "        <div align=\"center\" id=\"showData\"><script type=\"text/javascript\">javascript:getShoppingCart()</script></div>\n"
                    + "        <br>\n"
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
