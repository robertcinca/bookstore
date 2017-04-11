package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class browse_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <!-- Meta attributes -->\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta name=\"robots\" content=\"noindex, nofollow\">\n");
      out.write("        <meta name=\"title\" content=\"Online Bookstore\">\n");
      out.write("        <meta name=\"description\" content=\"An online marketplace for buying books.\">\n");
      out.write("                            \n");
      out.write("        <title>Welcome to our Online Bookstore!</title>\n");
      out.write("                            \n");
      out.write("        <!-- CSS Pages -->\n");
      out.write("        <link href=\"/bookstore/CSS/theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"/bookstore/CSS/browse.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <!-- JS Pages -->\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <iframe id=\"disclaimer\" name=\"disclaimer\" src=\"/bookstore/iframes/disclaimer.jsp\" width=\"100%\">\n");
      out.write("                [Your user agent does not support frames or is currently configured not to display frames.]\n");
      out.write("            </iframe>\n");
      out.write("        </header>\n");
      out.write("        \n");
      out.write("        <!-- Navigation -->\n");
      out.write("        <div class=\"dropdown\">\n");
      out.write("            <button class=\"dropbtn\">MENU</button>\n");
      out.write("            <div class=\"dropdown-content\">\n");
      out.write("                <ul class=\"nav\">\n");
      out.write("                    <li><a href=\"/bookstore/pages/index.jsp\">Login</a></li>\n");
      out.write("                    <li><a href=\"/bookstore/pages/browse.jsp\">Browse</a></li>\n");
      out.write("                    <li><a href=\"/bookstore/pages/viewcart.jsp\">View Cart</a></li>\n");
      out.write("                    <li><a href=\"/bookstore/pages/payment.jsp\">Pay Now</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\t\t<h1>Page to browse books (Customer)</h1>\n");
      out.write("\t\t<a href=\"mbrowse.html\" class=\"button\">Manager View</a>\n");
      out.write("\t\t<a href=\"viewcart.html\" class=\"button\">View Cart</a>\n");
      out.write("\t\t<a href=\"viewDetail.html\" class=\"button\">View Account Detail</a>\n");
      out.write("\t\t<br>\n");
      out.write("\n");
      out.write("\t\t<!-- Book List  -->\n");
      out.write("\t\t<table class=\"bookList\">\n");
      out.write("\t\t\t<col width=\"20%\">\n");
      out.write("  \t\t<col width=\"80%\">\n");
      out.write("\t\t  <tr>\n");
      out.write("\t\t    <th>Books</th>\n");
      out.write("\t\t    <th>Detail</th>\n");
      out.write("\n");
      out.write("\t\t  </tr>\n");
      out.write("\t\t  <tr>\n");
      out.write("\t\t    <td style=\"text-align: center; vertical-align: middle;\"><img alt=\"Picture of a book\" src=\"../../img/bookCover.png\"></td>\n");
      out.write("\t\t    <td >\n");
      out.write("\t\t\t\t\t<h3>Book Title</h3>\n");
      out.write("\t\t\t\t\t<p>by Author</p>\n");
      out.write("\t\t\t\t\t<p>Price: HKD 100</p>\n");
      out.write("\t\t\t\t\t<p>Loyalty Point: 50</p>\n");
      out.write("\t\t\t\t\t<form class=\"addToCart\">\n");
      out.write("\t\t\t\t\t\t<label for=\"Quantity\">Quantity:</label>\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"quantity\" value=\"1\" size=\"5\">\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"Add to Cart\">\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t  </tr>\n");
      out.write("\t\t  <tr>\n");
      out.write("\t\t    <td style=\"text-align: center; vertical-align: middle;\"><img alt=\"Picture of a book\" src=\"../../img/bookCover.png\"></td>\n");
      out.write("\t\t    <td >\n");
      out.write("\t\t\t\t\t<h3>Book Title</h3>\n");
      out.write("\t\t\t\t\t<p>by Author</p>\n");
      out.write("\t\t\t\t\t<p>Price: HKD 100</p>\n");
      out.write("\t\t\t\t\t<p>Loyalty Point: 50</p>\n");
      out.write("\t\t\t\t\t<form class=\"addToCart\">\n");
      out.write("\t\t\t\t\t\t<label for=\"Quantity\">Quantity:</label>\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"quantity\" value=\"1\" size=\"5\">\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"Add to Cart\">\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t  </tr>\n");
      out.write("\t\t</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t<footer>\n");
      out.write("\t\t\t<iframe id=\"disclaimer\" name=\"disclaimer\" src=\"../iframes/disclaimer.html\" width=\"100%\">\n");
      out.write("            [Your user agent does not support frames or is currently configured not to display frames.]\n");
      out.write("        \t</iframe>\n");
      out.write("        \t<iframe id=\"bookstorefooter\" name=\"bookstorefooter\" src=\"../iframes/bookstorefooter.html\" width=\"100%\" height=\"400px\">\n");
      out.write("            [Your user agent does not support frames or is currently configured not to display frames.]\n");
      out.write("        \t</iframe>\n");
      out.write("\t\t</footer>\n");
      out.write("\t</body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
