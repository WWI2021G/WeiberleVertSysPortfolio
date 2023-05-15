package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.FormBean;

@WebServlet("/rechnung/bar/getrennt/zahlen")
public class rechnungBarGetrenntZahlen extends HttpServlet {
  HttpSession session;
  double gesamtPreis;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    int rabatt = (int) session.getAttribute("rabatt");
    double rabattAbsolut = 0;
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Rechnungsservlet</title>");
    out.println("<style>");
    out.println("table, th, td {");
    out.println("border: 1px solid black;");
    out.println("border-collapse: collapse;");
    out.println("}");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Zu bezahlende Rechnung f&uuml;r Person von Tisch " + orderBean.getTischNr() + ":</h1>");
    out.println("<table>");
    out.println("<tr>");
    out.println("<th style=\"width: 220px\">Produkt</th>");
    out.println("<th style=\"width: 80px\">Menge</th>");
    out.println("<th style=\"width: 80px\">Preis</th>");
    out.println("</tr>");
    out.println("<tr>");
    Enumeration<String> paramNames = req.getParameterNames();
    gesamtPreis = 0;
    while ( paramNames.hasMoreElements() ) {
      String paramName = paramNames.nextElement();
      String[] paramValues = req.getParameterValues(paramName);
      for (int i = 0; i < paramValues.length; i++) {
        int paramValue = Integer.parseInt(paramValues[i]);
        if ( paramValue != 0 ) {
          out.println("<td>" + util.productNames[Integer.parseInt(paramName)] + "</td>");
          session.setAttribute(paramName, paramValue);
          out.println("<td style=\"text-align: center;\">" + paramValue + "</td>");
          out.println("<td style=\"text-align: right;\">" + String.format("%.2f", paramValue * util.prices[Integer.parseInt(paramName)]) + "&euro;</td>");
        }
        gesamtPreis += paramValue * util.prices[Integer.parseInt(paramName)];
      }
      out.println("</tr>");
    }
    out.println("<tr>");
    out.println("<td>Rabatt</td>");
    rabattAbsolut = (gesamtPreis * rabatt / 100);
    gesamtPreis = gesamtPreis - rabattAbsolut;
    session.setAttribute("gesamtPreis", gesamtPreis);
    out.println("<td style=\"text-align: center;\">" + rabatt + "&#37;</td>");
    out.println("<td style=\"text-align: right; color: green;\">" + String.format("%.2f", rabattAbsolut) + "&euro;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>Gesamt</td>");
    out.println("<td></td>");
    out.println("<td style=\"text-align:right; color: red;\">" + String.format("%.2f", gesamtPreis) + "&euro;</td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<br>");
    out.println("<h3>Der gegebene Betrag lautet:</h3>");
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/bar/getrennt/bezahlt\" method=\"POST\">");
    out.println("<input type=\"number\" name=\"gegebenesGeld\" min=\"" + gesamtPreis + "\" step=\"0.01\" required=\"required\">");
    out.println("<button type=\"submit\">Betrag bezahlt</button>");
    out.println("<a href=\"" + req.getContextPath() + "/rechnung/digital/getrennt\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
