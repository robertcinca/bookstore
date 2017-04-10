/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lokheili3
 */
public class addbooks extends HttpServlet {

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
                "        <link href=\"/bookstore/CSS/mbrowse.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
                "        </div>\n" +
                "\n" +
                "		<h1>Page to browse books (Manager)</h1>\n" +
                "		<a href=\"/bookstore/browse\" class=\"button\">Back to Browse</a>\n" +
                "\n" +
                "		<br>\n" +
                "\n" +
                "		<fieldset>\n");
            
                try {
                //addbooks input field
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                int price = 0;
                int point = 0;
                if(request.getParameter("price")!=null && !request.getParameter("price").equalsIgnoreCase("")){
                    price = Integer.parseInt(request.getParameter("price"));
                }
                if(request.getParameter("point")!=null && !request.getParameter("point").equalsIgnoreCase("")){
                    point = Integer.parseInt(request.getParameter("point"));
                }
                
                
                
                if (title != null && !title.equalsIgnoreCase("") &&
                    author != null && !author.equalsIgnoreCase("") &&
                    price != 0 && point!= 0) {
                    
                    // Register the JDBC driver, open a connection
                           
                    String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                    String dbLoginId = "aiad034";
                    String dbPwd = "aiad034";

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO [book] ([bookname], [author], [price], [loyalty]) VALUES (?, ?, ?, ?)");
                    pstmt.setString(1, title);
                    pstmt.setString(2, author);
                    pstmt.setInt(3, price);
                    pstmt.setInt(4, point);
                    
                    int rows = pstmt.executeUpdate();


                    if (rows > 0) {
                        out.println("<legend>New record is sucessfully created.</legend>");
                        // display the information of the record just added including UID

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT @@IDENTITY AS [@@IDENTITY]");
                        if (rs != null && rs.next() != false) {
                                out.println("<p>UID: " + rs.getInt(1) + "</p>");
                                rs.close();
                        }
                        if (stmt != null) {
                                stmt.close();
                        }

                        out.println("<p>Title:" + title + "</p>");
                        out.println("<p>Author:" + author + "</p>");
                        out.println("<p>Price:" + price + "</p>");
                        out.println("<p>Loyalty points:" + point + "</p>");
                    }
                    else {
                        out.println("<legend>ERROR: New record is failed to create.</legend>");
                    }
                    
                }
                else {
                    out.println(
                "			<legend>Add New Books</legend>\n" +
                "			<h3>Fill in book detail</h3>\n" +
                "			<form method='POST' class=\"addBooks\">\n" +
                "				<label for=\"title\">Book Title:</label>\n" +
                "				<input type=\"text\" name=\"title\" >\n" +
                "				<label for=\"author\">Author:</label>\n" +
                "				<input type=\"text\" name=\"author\" >\n" +
                "				<label for=\"price\">Price:</label>\n" +
                "				<input type=\"number\" name=\"price\" >\n" +
                "				<label for=\"point\">Loyalty Points:</label>\n" +
                "				<input type=\"number\" name=\"point\" >\n" +
                "				<input style=\"float:right;\" type=\"submit\" value=\"Add book\">\n" +
                "			</form>\n");
                }
                
                out.println("</fieldset>\n"+"<footer>\n" +
                "			<iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n" +
                "            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
                "        	</iframe>\n" +
                "        	<iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"/bookstore/iframes/bookstorefooter.jsp\" width=\"100%\" height=\"400px\">\n" +
                "            [Your user agent does not support frames or is currently configured not to display frames.]\n" +
                "        	</iframe>\n" +
                "		</footer>\n" +
                "	</body>\n" +
                "</html>");
                } catch (ClassNotFoundException e) {
                    out.println("<div style='color: red'>" + e.toString() + "</div>");
                } catch (SQLException e) {
                    out.println("<div style='color: red'>" + e.toString() + "</div>");
                } catch(InputMismatchException e) {
                    out.println("<div style='color: red'>" + e.toString() + "</div>");
                }finally {
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
