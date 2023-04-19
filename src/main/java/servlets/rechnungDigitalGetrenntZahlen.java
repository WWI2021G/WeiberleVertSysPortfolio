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

@WebServlet("/rechnung/digital/getrennt/zahlen")
public class rechnungDigitalGetrenntZahlen extends HttpServlet {
  HttpSession session;

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
    double gesamtPreis = 0;
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
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/digital/getrennt/zahlen\" method=\"GET\">");
    out.println("<button type=\"submit\">Betrag bezahlt</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    session = req.getSession(true);
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    Enumeration<String> attributeNames = session.getAttributeNames();
    while ( attributeNames.hasMoreElements() ) {
      String attributeName = attributeNames.nextElement();
      Object attributeValue = session.getAttribute(attributeName);
      out.println("Session attribute name: " + attributeName);
      out.println("Session attribute value: " + attributeValue);
      if ( !attributeName.equals("form") ) {
        util.payOne(out, orderBean, util.products[Integer.parseInt(attributeName)], (int) attributeValue);
        session.removeAttribute(attributeName);
      }
    }
    if ( orderBean.isOrderOpen() ) {
      resp.sendRedirect(req.getContextPath() + "/rechnung/digital/getrennt");
    } else {
      resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }
}
