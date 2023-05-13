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
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Rechnungsservlet</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Zu bezahlende Rechnung f&uuml;r Person von Tisch " + orderBean.getTischNr() + ":</h1>");
    out.println("<table>");
    out.println("<tr>");
    out.println("<th>Produkt</th>");
    out.println("<th>Menge</th>");
    out.println("<th>Preis</th>");
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
          out.println("<td>" + paramValue + "</td>");
          out.println("<td>" + String.format("%.2f", paramValue * util.prices[Integer.parseInt(paramName)]) + "&euro;</td>");
        }
        gesamtPreis += paramValue * util.prices[Integer.parseInt(paramName)];
        session.setAttribute("gesamtPreis", gesamtPreis);
      }
      out.println("</tr>");
    }
    out.println("<tr>");
    out.println("<td>Gesamt</td>");
    out.println("<td></td>");
    out.println("<td>" + String.format("%.2f", gesamtPreis) + "&euro;</td>");
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
