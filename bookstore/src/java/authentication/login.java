/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

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
public class login extends HttpServlet {

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
            out.println(" <!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "    <head>"
                    + "        <!-- Meta attributes -->"
                    + "        <meta charset=\"utf-8\">"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
                    + "        <meta name=\"robots\" content=\"noindex, nofollow\">"
                    + "        <meta name=\"title\" content=\"Online Bookstore\">"
                    + "        <meta name=\"description\" content=\"An online marketplace for buying books.\">"
                    + "                            "
                    + "        <title>Welcome to our Online Bookstore!</title>"
                    + "                            "
                    + "        <!-- CSS Pages -->"
                    + "        <link href=\"/Bookstore/CSS/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>"
                    + "        <link href=\"/Bookstore/CSS/login.css\" rel=\"stylesheet\" type=\"text/css\"/>"
                    + "        <!-- JS Pages -->"
                    + "        <script src=\"/Bookstore/JS/basicFunctions.js\" type=\"text/javascript\"></script>"
                    + "        <script src=\"/Bookstore/JS/login.js\" type=\"text/javascript\"></script>"
                    + "    </head>"
                    + "    <body>"
                    + "        <header>"
                    + "            <iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/Bookstore/iframes/disclaimer.jsp\" width=\"100%\">"
                    + "                [Your user agent does not support frames or is currently configured not to display frames.]"
                    + "            </iframe>"
                    + "        </header>"
                    + "        "
                    + "        <!-- Navigation -->"
                    + "        <div class=\"dropdown\">"
                    + "            <button class=\"dropbtn\">MENU</button>"
                    + "            <div class=\"dropdown-content\">"
                    + "                <ul class=\"nav\">"
                    + "                    <li><a href=\"/Bookstore/browse.do\">Browse</a></li>"
                    + "                    <li><a href=\"/Bookstore/viewcart.do\">View Cart</a></li>"
                    + "                    <li><a href=\"/Bookstore/payment.do\">Pay Now</a></li>"
                    + "                </ul>"
                    + "            </div>"
                    + "        </div>"
                    + "        <h1>Welcome to our Online Bookstore!</h1>");
            //simple login form
            out.println("<form name='Form' id='Form' action='j_security_check' onsubmit='return validateLogin()' method='POST'>");
            out.println("  <p>User name: <input type='text' name='j_username' id='j_username' /></p>");
            out.println("  <p>Password: <input type='password' name='j_password' id='j_password' /></p>");
            out.println("  <p><button style='width:100%;font-size:18px' type='submit'>Login</button></p>");
            out.println("</form>");
            //sign up page
            out.println("<a href=\"/Bookstore/signup\" class=\"button\" style='float:left;'>Sign Up!</a>");
            //login as guest
            out.println("<form action='j_security_check' method='POST'> "
                    + "<input type='hidden' value='guest' name='j_username' id='j_username' />"
                    + "<input type='hidden' value='pwd' name='j_password' id='j_password' />"
                    + "<button class='button'  type='submit'>Continue without logging in >>></button>"
                    + "</form>");
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
