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
import javax.servlet.http.HttpSession;

/**
 *
 * @author robertcinca
 */
public class logout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // invalidate the session
        HttpSession session=request.getSession(true);
        session.invalidate();
        
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
                    + "        <link href=\"/bookstore/CSS/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                    + "        <link href=\"/bookstore/CSS/login.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                    + "        <!-- JS Pages -->\n"
                    + "        <script src=\"/bookstore/JS/basicFunctions.js\" type=\"text/javascript\"></script>\n"
                    + "        <script src=\"/bookstore/JS/login.js\" type=\"text/javascript\"></script>\n"
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
                    + "        \n");
            out.println("<h1>Logout Page</h1>");
            out.println("<p>You have successfully logged out!</p>");
            out.println("<p>Return to <a href='browse.do'>index page</a> (re-login required)</p>");
            out.println("</form>");
            out.println("<footer>\n" +
"            <iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n" +
"                [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"            </iframe>\n" +
"            <iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"/bookstore/iframes/bookstorefooter.jsp\" width=\"100%\" height=\"400px\">\n" +
"                [Your user agent does not support frames or is currently configured not to display frames.]\n" +
"            </iframe>\n" +
"        </footer>");
            out.println("</body>");
            out.println("</html>");
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
