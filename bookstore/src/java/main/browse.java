/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lokheili3
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
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        String action = request.getParameter("action");
        String username = request.getRemoteUser();

        if (action != null) {
            // call different action depends on the action parameter
            if (action.equalsIgnoreCase("Add to Cart")) {
                this.addcartEntry(request, response);
            } else if (action.equalsIgnoreCase("change")) {
                this.dochangeEntry(request, response);
            } else if (action.equalsIgnoreCase("delete")) {
                this.dodeleteEntry(request, response);
            }
        }

        this.doRetrieveEntry(request, response);
    }

    private void doRetrieveEntry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
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
                out.println("              <li><a href='/Bookstore/browse.do'>Login</a></li>\n");
            }
            out.println("                  <li><a href='/Bookstore/browse.do'>Browse</a></li>"
                    + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>");
            if (!request.isUserInRole("guest")) {
                out.println("              <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>");
            }
            out.println("              </ul>"
                    + "            </div>"
                    + "        </div>"
                    + "<h1>Welcome! You can browse our books here!</h1>\n");
            // Begin Page
            if (request.isUserInRole("sprole") || request.isUserInRole("guest")) {
                //book list (customer)
                out.println("		<a href=\"/Bookstore/viewcart.do\" class=\"button\">View Cart</a>\n");
                if (!request.isUserInRole("guest")) {
                    out.println("           <a href='/Bookstore/viewdetail.do' class='button'>View Account Details</a></li>");
                }
                out.println("		<br>\n"
                        + "\n"
                        + "		<!-- Book List  -->\n"
                        + "		<table class=\"bookList\">\n"
                        + "			<col width=\"20%\">\n"
                        + "  		<col width=\"80%\">\n"
                        + "		  <tr>\n"
                        + "		    <th>Books</th>\n"
                        + "		    <th>Detail</th>\n"
                        + "\n");
                try {

                    // make connection to db and retrieve data from the table
                    String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                    String dbLoginId = "aiad034";
                    String dbPwd = "aiad034";

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = stmt.executeQuery("SELECT * FROM [book] ORDER BY [bookname] ASC");

                    while (rs != null && rs.next() != false) {
                        int id = rs.getInt("ID_book");
                        String name = rs.getString("bookname");
                        String author = rs.getString("author");
                        int price = rs.getInt("price");
                        int point = rs.getInt("loyalty");
                        int quantityAvailable = rs.getInt("stock");

                        out.println("</tr>\n"
                                + "		  <tr>\n"
                                + "		    <td style=\"text-align: center; vertical-align: middle;\"><img alt=\"Picture of a book\" src=\"/Bookstore/IMG/bookCover.png\"></td>\n"
                                + "		    <td >\n"
                                + "					<h3>" + name + "</h3>\n"
                                + "					<p>by " + author + "</p>\n"
                                + "					<p>Price: HKD " + price + "</p>\n"
                                + "					<p>Loyalty Point: " + point + "</p>\n"
                                + "					<form method='POST' class=\"addToCart\">\n"
                                + "                                             <input name='bookid' type='hidden' value='" + id + "' />"
                                + "						<label for=\"Quantity\">Quantity:</label>\n"
                                + "						<input type=\"number\" name=\"quantity\" value=\"1\" size=\"5\" min=\"1\" max='"+quantityAvailable+"'>\n"
                                + "						<input name='action' type=\"submit\" value=\"Add to Cart\">\n"
                                + "					</form>\n"
                                + "				</td>\n"
                                + "		  </tr>\n");
                    }

                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    out.println("</table>\n");
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
            } else if (request.isUserInRole("admin")) {
                //book list (manager)
                out.println("            		<a href=\"/Bookstore/refund.do\" class=\"button\">Refund Request</a>\n"
                        + "            		<a href=\"/Bookstore/addbooks.do\" class=\"button\">Add Books</a>\n"
                        + "            		<br>\n"
                        + "\n"
                        + "            		<!-- Book List  -->\n"
                        + "            		<table class=\"bookList\">\n"
                        + "            			<col width=\"20%\">\n"
                        + "              		<col width=\"80%\">\n"
                        + "            		  <tr>\n"
                        + "            		    <th>Books</th>\n"
                        + "            		    <th>Detail</th>\n"
                        + "\n");

                try {

                    // make connection to db and retrieve data from the table
                    /* Uncomment when connecting to DB!! */
                    String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                    String dbLoginId = "aiad034";
                    String dbPwd = "aiad034";

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = stmt.executeQuery("SELECT * FROM [book] ORDER BY [bookname] ASC");

                    while (rs != null && rs.next() != false) {
                        int id = rs.getInt("ID_book");
                        String name = rs.getString("bookname");
                        String author = rs.getString("author");
                        int price = rs.getInt("price");
                        int point = rs.getInt("loyalty");

                        out.println("            		  </tr>\n"
                                + "            		  <tr>\n"
                                + "            		    <td style=\"text-align: center; vertical-align: middle;\"><img alt=\"Picture of a book\" src=\"/Bookstore/IMG/bookCover.png\"></td>\n"
                                + "            		    <td >\n"
                                + "            					<h3>" + name + "</h3>\n"
                                + "            					<p>by " + author + "</p>\n"
                                + "            					<p>Price: HKD " + price + "</p>\n"
                                + "            					<p>Loyalty Point: " + point + "</p>\n"
                                + "            					<form method='POST' class=\"manageButton\">\n"
                                + "                                                     <input name='bookid' type='hidden' value='" + id + "' />\n"
                                + "            						<input name='action' type=\"submit\" value=\"Change\">\n"
                                + "            						<input name='action' type=\"submit\" value=\"Delete\">\n"
                                + "            					</form>\n"
                                + "            				</td>\n"
                                + "            		  </tr>\n");
                    }
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }

                    out.println("</table>\n");
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

    }

    private void addcartEntry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
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
                    + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>");
            if (!request.isUserInRole("guest")) {
                out.println("              <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>");
            }
            out.println("              </ul>"
                    + "            </div>"
                    + "        </div>");
            // Begin Page
            if (request.isUserInRole("sprole") || request.isUserInRole("guest")) {
                //book list (customer)
                out.println("<h1>Page to browse books (Customer)</h1>\n"
                        + "		<a href=\"/Bookstore/viewcart.do\" class=\"button\">View Cart</a>\n"
                        + "		<a href=\"/Bookstore/browse.do\" class=\"button\">Back to Browse</a>\n");
                if (!request.isUserInRole("guest")) {
                    out.println("           <a href='/Bookstore/viewdetail.do' class='button'>View Account Details</a></li>");
                }

                //add cart detail
                int bookid = 0;
                int quantity = 0;
                if (request.getParameter("bookid") != null && !request.getParameter("bookid").equalsIgnoreCase("")) {
                    bookid = Integer.parseInt(request.getParameter("bookid"));
                }
                if (request.getParameter("quantity") != null && !request.getParameter("quantity").equalsIgnoreCase("")) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }

                if (bookid != 0 && quantity != 0) {
                    // make connection to db and retrieve data from the table
                    String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
                    String dbLoginId = "aiad034";
                    String dbPwd = "aiad034";

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

                    //get added book info
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = stmt.executeQuery("SELECT * FROM [book] WHERE ID_book = " + bookid);

                    while (rs != null && rs.next() != false) {
                        int id = rs.getInt("ID_book");
                        String name = rs.getString("bookname");
                        String author = rs.getString("author");
                        int price = rs.getInt("price");
                        int point = rs.getInt("loyalty");

                        out.println("<fieldset>");

                        //check if book is already in cart
                        int exists = 0;
                        Statement stmt2 = con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery("SELECT * FROM purchased WHERE user_name = '" + request.getRemoteUser() +"' AND status = 'pending' AND bookname = '" + name + "'");
                        while (rs2 != null && rs2.next() != false) {
                            exists = 1;
                        }

                        // insert new book
                        if (exists == 0) {
                            out.println("<h3>Book added to cart</h3>");
                            //add to cart 
                            PreparedStatement pstmt = con.prepareStatement("INSERT INTO [purchased] ([user_name], [bookname], [quantity], [status], [refundable]) VALUES (?, ?, ?, ?, ?)");
                            pstmt.setString(1, request.getRemoteUser());
                            pstmt.setString(2, name);
                            pstmt.setInt(3, quantity);
                            pstmt.setString(4, "pending");
                            pstmt.setString(5, "yes");

                            Boolean result = pstmt.execute();

                            if (pstmt != null) {
                                pstmt.close();
                            }
                        } //update quantity
                        else {
                            out.println("<h3>The book quantity has been updated.</h3>");
                            
                            PreparedStatement pstmt2 = con.prepareStatement("UPDATE purchased SET quantity = ? WHERE user_name = ? AND status = ? AND bookname = ?");
                            pstmt2.setInt(1, quantity);
                            pstmt2.setString(2, request.getRemoteUser());
                            pstmt2.setString(3, "pending");
                            pstmt2.setString(4, name);
                            // execute the SQL statement
                            int rows = pstmt2.executeUpdate();
                            
                            if (pstmt2 != null) {
                                pstmt2.close();
                            }
                        }

                        out.println("<p>Book Name : " + name + "</p>");
                        out.println("<p>Author : " + author + "</p>");
                        out.println("<p>Price : " + price + "</p>");
                        out.println("<p>Quantity : " + quantity + "</p>");
                        out.println("<p>Loyalty : " + point + "</p>");
                        out.println("</fieldset>");

                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (stmt2 != null) {
                            stmt2.close();
                        }
                    }

                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }

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

            }

        } catch (java.lang.ClassNotFoundException | SQLException e) {
            out.println("<div style='color: red'>" + e.toString() + "</div>");
        } finally {
            out.close();
        }
    }

    private void dochangeEntry(HttpServletRequest request, HttpServletResponse response)
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
                    + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>");
            if (!request.isUserInRole("guest")) {
                out.println("              <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>");
            }
            out.println("              </ul>"
                    + "            </div>"
                    + "        </div>");
            // Begin Page
            out.println("<br/><a class='button' href='" + request.getRequestURI() + "'>Back to Browse</a>");
            // Register the JDBC driver, open a connection

            String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
            String dbLoginId = "aiad034";
            String dbPwd = "aiad034";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

            String bookid = request.getParameter("bookid");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            int availableQuantity = 0;
            int price = 0;
            int point = 0;
            if (request.getParameter("price") != null && !request.getParameter("price").equalsIgnoreCase("")) {
                price = Integer.parseInt(request.getParameter("price"));
            }
            if (request.getParameter("point") != null && !request.getParameter("point").equalsIgnoreCase("")) {
                point = Integer.parseInt(request.getParameter("point"));
            }
            if (request.getParameter("availableQuantity") != null && !request.getParameter("availableQuantity").equalsIgnoreCase("")) {
                    availableQuantity = Integer.parseInt(request.getParameter("availableQuantity")); 
                }

            if (title != null && !title.equalsIgnoreCase("")
                    && author != null && !author.equalsIgnoreCase("")
                    && price != 0 && point != 0 && availableQuantity != 0) {

                PreparedStatement pstmt = con.prepareStatement("UPDATE [book] SET bookname = ?, author = ?, price = ?, loyalty = ?, stock = ? WHERE ID_book = " + bookid);
                pstmt.setString(1, title);
                pstmt.setString(2, author);
                pstmt.setInt(3, price);
                pstmt.setInt(4, point);
                pstmt.setInt(5, availableQuantity);

                Boolean result = pstmt.execute();

                int count = 0;
                do {
                    if (result) {
                        out.println("<legend>The record is sucessfully updated.</legend>");
                        // display the information of the record just added including UID  
                    } else {
                        count = pstmt.getUpdateCount();
                        if (count >= 0) {
                            out.println("<legend>The record is sucessfully updated.</legend>");
                            out.println("<p>Book Name: " + title + "</p>");
                            out.println("<p>Author: " + author + "</p>");
                            out.println("<p>Price: " + price + "</p>");
                            out.println("<p>Loyalty: " + point + "</p>");
                            out.println("<p>Loyalty: " + availableQuantity + "</p>");
                        }
                    }
                    result = pstmt.getMoreResults();
                } while (result || count != -1);

                if (pstmt != null) {
                    pstmt.close();
                }
            } else {
                if (title == null) {
                    title = "";
                }
                if (author == null) {
                    author = "";
                }

                Statement stmt1 = con.createStatement();
                ResultSet rs1 = stmt1.executeQuery("SELECT * FROM book WHERE ID_book=" + bookid);

                if (rs1 != null && rs1.next() != false) {
                    title = rs1.getString("bookname");
                    author = rs1.getString("author");
                    price = rs1.getInt("price");
                    point = rs1.getInt("loyalty");
                    availableQuantity = rs1.getInt("stock");

                    out.println("<fieldset>\n");
                    out.println("<legend>Please fill in the form</legend>");
                    out.println("<form method='POST' action='" + request.getRequestURI() + "'>");
                    out.println("<input name='action' type='hidden' value='change' />");
                    out.println("<input name='bookid' type='hidden' value='" + bookid + "' />");
                    out.println("<label for='title'>Book Name: </label>");
                    out.println("<input name='title' type='text' size='25' maxlength='255' value='" + title + "' /></p>");
                    out.println("<label for='author'>Author: </label>");
                    out.println("<input name='author' type='text' size='25' maxlength='255' value='" + author + "' /></p>");
                    out.println("<label for='price'>Price: </label>");
                    out.println("<input name='price' type='number' size='8' maxlength='8' value='" + price + "' /></p>");
                    out.println("<label for='point'>Loyalty Point: </label>");
                    out.println("<input name='point' type='number' size='8' maxlength='8' value='" + point + "' /></p>");
                    out.println("<label for='point'>Quantity Available: </label>");
                    out.println("<input name='availableQuantity' type='number' size='8' maxlength='8' value='" + availableQuantity + "' /></p>");
                    out.println("<input style='float:right;' type='submit' value='Update!' />");
                    out.println("</form>\n");
                    out.println("</fieldset>\n");
                    rs1.close();

                }
                if (stmt1 != null) {
                    stmt1.close();
                }

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
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<div style='color: red'>" + e.toString() + "</div>");
        }

    }

    private void dodeleteEntry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
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
                    + "                    <li><a href='/Bookstore/viewcart.do'>View Cart</a></li>");
            if (!request.isUserInRole("guest")) {
                out.println("              <li><a href='/Bookstore/viewdetail.do'>Account Details</a></li>");
            }
            out.println("              </ul>"
                    + "            </div>"
                    + "        </div>");
            // Begin Page
            // Register the JDBC driver, open a connection 
            String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad034_db";
            String dbLoginId = "aiad034";
            String dbPwd = "aiad034";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, dbLoginId, dbPwd);

            int bookid = -1;
            if (request.getParameter("bookid") != null && !request.getParameter("bookid").equalsIgnoreCase("")) {
                bookid = Integer.parseInt(request.getParameter("bookid"));
            }
            if (bookid != -1) {

                PreparedStatement pstmt = con.prepareStatement("DELETE [book] WHERE ID_book = " + bookid);

                Boolean result = pstmt.execute();

                int count = 0;
                do {
                    if (result) {
                        out.println("<h2>The record is sucessfully deleted.</h2>");
                        // display the information of the record just added including UID
                    } else {
                        count = pstmt.getUpdateCount();
                        if (count >= 0) {
                            out.println("<h2>The record is sucessfully deleted.</h2>");
                        }
                    }
                    result = pstmt.getMoreResults();
                } while (result || count != -1);

                if (pstmt != null) {
                    pstmt.close();
                }
            } else {
            }
            out.println("<br/><a class='button' href='" + request.getRequestURI() + "'>Back to Browse</a>");
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
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<div style='color: red'>" + e.toString() + "</div>");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(browse.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(browse.class.getName()).log(Level.SEVERE, null, ex);
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
