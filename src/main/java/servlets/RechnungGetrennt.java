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

@WebServlet("/rechnung/getrennt")
public class RechnungGetrennt extends HttpServlet {
  HttpSession session;
  private String[] productNames = FormBean.getProductNames();
  private double[] prices = FormBean.getPrices();
  private double gesamtPreis;

  // Das Rechnung-Servlet leitet auf diese Seite um, wenn eine getrennte Rechnung gewünscht ist.
  // Das Umleiten stellt eine GET-Anfrage, deshalb wird diese Methode nach dem Rechnung-Servlet
  // aufgerufen.
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    int rabatt = (int) session.getAttribute("rabatt");
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
    out.println("<h1>Offen stehende Rechnung f&uuml;r Tisch " + orderBean.getTischNr() + ":</h1>");
    // Die Tabelle mit den Produkten auf der Bestellung wird als Teil eines Formulars ausgegeben.
    // In der vierten Spalte kann ausgewählt werden, wie viele und welche der Produkte jeweils einzeln
    // gezahlt werden sollen.
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/getrennt\" method=\"POST\">");
    // Hier wird die splitOrder-Methode der Util-Klasse aufgerufen, um die bestellten Produkte
    // darzustellen.
    Util.splitOrder(out, orderBean, rabatt);
    // Beim Bestätigen der Auswahl wird dieses Servlet erneut aufgerufen, allerdings mit einer
    // POST-Anfrage.
    out.println("<button type=\"submit\">Bezahlen</button>");
    // Der folgende Button bietet die Möglichkeit den Vorgang abzubrechen.
    out.println("<a href=\"" + req.getContextPath() + "/index.jsp\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  // Diese Methode wird als Resultat der POST-Anfrage aufgerufen
  // In dieser Methode werden alle zuvor ausgewählten Produkte erneut aufgelistet und das Resultat
  // kann dann entweder Bar oder mit Karte bezahlt werden.
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
    // Hier werden alle Anfrageparameter in der Variable paramNames gespeichert.
    Enumeration<String> paramNames = req.getParameterNames();
    gesamtPreis = 0;
    // Hier wird über die Parameter iteriert.
    while ( paramNames.hasMoreElements() ) {
      String paramName = paramNames.nextElement();
      String[] paramValues = req.getParameterValues(paramName);
      // Im folgenden Abschnitt werden die zuvor ausgewählten Produkte und Anzahlen ausgegeben.
      for (int i = 0; i < paramValues.length; i++) {
        int paramValue = Integer.parseInt(paramValues[i]);
        // Hier sollen wieder nur die Produkte ausgegeben, die ungleich null sind.
        if ( paramValue != 0 ) {
          out.println("<td>" + productNames[Integer.parseInt(paramName)] + "</td>");
          session.setAttribute(paramName, paramValue);
          out.println("<td style=\"text-align: center;\">" + paramValue + "</td>");
          out.println("<td style=\"text-align: right;\">" + String.format("%.2f", paramValue * prices[Integer.parseInt(paramName)]) + "&euro;</td>");
        }
        // Hier wird der Gesamtpreis der ausgewählten Produkte berechnet.
        gesamtPreis += paramValue * prices[Integer.parseInt(paramName)];
      }
      out.println("</tr>");
    }
    out.println("<tr>");
    // Hier wird die Rabattzeile erstellt und der Rabatt berechnet.
    out.println("<td>Rabatt</td>");
    rabattAbsolut = (gesamtPreis * rabatt / 100);
    gesamtPreis = gesamtPreis - rabattAbsolut;
    session.setAttribute("gesamtPreis", gesamtPreis);
    out.println("<td style=\"text-align: center;\">" + rabatt + "&#37;</td>");
    out.println("<td style=\"text-align: right; color: green;\">" + String.format("%.2f", rabattAbsolut) + "&euro;</td>");
    out.println("</tr>");
    out.println("<tr>");
    // Hier wird die Gesamtpreiszeile erstellt.
    out.println("<td>Gesamt</td>");
    out.println("<td></td>");
    out.println("<td style=\"text-align:right; color: red;\">" + String.format("%.2f", gesamtPreis) + "&euro;</td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<br>");
    // Je nach dem ob der Betrag mit Karte oder bar bezahlt werden soll wird hier der Betrag entweder
    // als gezahlt (bei Kartenzahlung), oder der bar gegebene Betrag eingetragen.
    out.println("<h2>Der Kunde bezahlt mit Karte:</h2>");
    // Wenn der Betrag mit Karte bezahlt wird, wird direkt das RechnungGetrenntBezahlt-Servlet
    // aufgerufen.
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/getrennt/bezahlt\" method=\"POST\">");
    out.println("<button type=\"submit\">Betrag bezahlt</button>");
    out.println("<a href=\"" + req.getContextPath() + "/rechnung/getrennt\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("<h2>Der Kunde bezahlt bar:</h2>");
    // Wenn der Kunde bar bezahlt wird der gegebene Betrag abgefragt, und man wird an das
    // RechnungBarGetrennt-Servlet weitergeleitet.
    out.println("<h3>Der gegebene Betrag lautet:</h3>");
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/bar/getrennt\" method=\"POST\">");
    out.println("<input type=\"number\" name=\"gegebenesGeld\" min=\"" + gesamtPreis + "\" step=\"0.01\" required=\"required\">");
    out.println("<button type=\"submit\">Betrag bezahlt</button>");
    out.println("<a href=\"" + req.getContextPath() + "/rechnung/getrennt\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
