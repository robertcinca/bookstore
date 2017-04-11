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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lokheili3, robertcinca
 */
public class browse extends HttpServlet {

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
            "        <link href=\"/bookstore/CSS/browse.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
            "                    <li><a href=\"/bookstore/index\">Login</a></li>\n" +
            "                    <li><a href=\"/bookstore/browse\">Browse</a></li>\n" +
            "                    <li><a href=\"/bookstore/viewcart\">View Cart</a></li>\n" +
            "                    <li><a href=\"/bookstore/payment\">Pay Now</a></li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "        </div>\n");

            //book list (customer)
            out.println("<h1>Page to browse books (Customer)</h1>\n" +
            "		<a href=\"/bookstore/mbrowse\" class=\"button\">Manager View</a>\n" +
            "		<a href=\"/bookstore/viewcart\" class=\"button\">View Cart</a>\n" +
            "		<a href=\"/bookstore/viewdetail\" class=\"button\">View Account Detail</a>\n" +
            "		<br>\n" +
            "\n" +
            "		<!-- Book List  -->\n" +
            "		<table class=\"bookList\">\n" +
            "			<col width=\"20%\">\n" +
            "  		<col width=\"80%\">\n" +
            "		  <tr>\n" +
            "		    <th>Books</th>\n" +
            "		    <th>Detail</th>\n" +
            "\n");
            try{


                // make connection to db and retrieve data from the table
                
                /* Uncomment when connecting to DB!! */

//                String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
//                String dbLoginId = "aiad034";
//                String dbPwd = "aiad034";
//
//                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);
//
//                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = stmt.executeQuery("SELECT * FROM [book] ORDER BY [bookname] ASC");
//
//                while (rs != null && rs.next() != false) {

//                    int id = rs.getInt("ID_book");
//                    String name =  rs.getString("bookname");
//                    String author =  rs.getString("author");
//                    int price = rs.getInt("price");
//                    int point = rs.getInt("loyalty");

                    //comment out
                    String name = "test"; String author = "test2"; int price = 20; int point = 10; int id= 0;

                    out.println("</tr>\n" +
                    "		  <tr>\n" +
                    "		    <td style=\"text-align: center; vertical-align: middle;\"><img alt=\"Picture of a book\" src=\"/bookstore/IMG/bookCover.png\"></td>\n" +
                    "		    <td >\n" +
                    "					<h3>"+name+"</h3>\n" +
                    "					<p>by "+author+"</p>\n" +
                    "					<p>Price: HKD "+price+"</p>\n" +
                    "					<p>Loyalty Point: "+point+"</p>\n" +
                    "					<form method='POST' class=\"addToCart\">\n" +
                                                                "<input name='bookid' type='hidden' value='"+id+"' />" +
                    "						<label for=\"Quantity\">Quantity:</label>\n" +
                    "						<input type=\"number\" name=\"quantity\" value=\"1\" size=\"5\">\n" +
                    "						<input type=\"submit\" value=\"Add to Cart\">\n" +
                    "					</form>\n" +
                    "				</td>\n" +
                    "		  </tr>\n");
//                } //uncomment!!
                

                //book list (manager)
                out.println("<h1>Page to browse books (Manager)</h1> <a href=\"/bookstore/pages/browse.jsp\" class=\"button\">Customer View</a>\n" +
"            		<a href=\"/bookstore/pages/refund.jsp\" class=\"button\">Refund Request</a>\n" +
"            		<a href=\"/bookstore/pages/addBooks.jsp\" class=\"button\">Add Books</a>\n" +
"            		<br>\n" +
"\n" +
"            		<!-- Book List  -->\n" +
"            		<table class=\"bookList\">\n" +
"            			<col width=\"20%\">\n" +
"              		<col width=\"80%\">\n" +
"            		  <tr>\n" +
"            		    <th>Books</th>\n" +
"            		    <th>Detail</th>\n" +
"\n" +
"            		  </tr>\n" +
"            		  <tr>\n" +
"            		    <td style=\"text-align: center; vertical-align: middle;\"><img alt=\"Picture of a book\" src=\"/bookstore/IMG/bookCover.png\"></td>\n" +
"            		    <td >\n" +
"            					<h3>Book Title</h3>\n" +
"            					<p>by Author</p>\n" +
"            					<p>Price: HKD 100</p>\n" +
"            					<p>Loyalty Point: 50</p>\n" +
"            					<form class=\"manageButton\">\n" +
"            						<input type=\"button\" value=\"Change\">\n" +
"            						<input type=\"button\" value=\"Delete\">\n" +
"            					</form>\n" +
"            				</td>\n" +
"            		  </tr>\n" +
"            		  <tr>\n" +
"            		    <td style=\"text-align: center; vertical-align: middle;\"><img alt=\"Picture of a book\" src=\"/bookstore/IMG/bookCover.png\"></td>\n" +
"            		    <td >\n" +
"            					<h3>Book Title</h3>\n" +
"            					<p>by Author</p>\n" +
"            					<p>Price: HKD 100</p>\n" +
"            					<p>Loyalty Point: 50</p>\n" +
"            					<form class=\"manageButton\">\n" +
"            						<input type=\"button\" value=\"Change\">\n" +
"            						<input type=\"button\" value=\"Delete\">\n" +
"            					</form>\n" +
"            				</td>\n" +
"            		  </tr>\");" );
            		

                out.println("</table>\n");

                out.println("<footer>\n" +
                "			<iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n" +
                "            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
                "        	</iframe>\n" +
                "        	<iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"/bookstore/iframes/bookstorefooter.jsp\" width=\"100%\" height=\"400px\">\n" +
                "            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
                "        	</iframe>\n" +
                "		</footer>\n" +
                "	</body>\n" +
                "</html>\n" +
                "");

//            if (rs != null) {
//                rs.close();
//            }
//            if (stmt != null) {
//                stmt.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//
//            } catch (java.lang.ClassNotFoundException | SQLException e) {
//                out.println("<div style='color: red'>" + e.toString() + "</div>");
            } 
            finally {
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