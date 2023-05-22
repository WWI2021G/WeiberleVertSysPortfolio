package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.FormBean;

// Dieses Servlet wird aufgerufen, wenn die Rechnung zusammen beglichen werden soll.
@WebServlet("/rechnung/zusammen")
public class RechnungZusammen extends HttpServlet {

  // Diese Methode wird aufgerufen, nachdem das Rechnung-Servlet zu diesem Servlet weitergeleitet hat.
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
    // Hier wird die displayOrder-Methode der Util-Klasse aufgerufen, um die offene Rechnung richtig
    // darzustellen.
    Util.displayOrder(out, orderBean, rabatt);
    // Hier gibt es wieder die Möglichkeit mit Karte oder bar zu bezahlen.
    // Wenn der Kunde mit Karte bezahlt wird er direkt an das RechnungZusammenBezahlt-Servlet
    // weitergeleitet.
    // Wenn der Kunde bar bezahlt wird der gegebene Betrag abgefragt, und eine POST-Anfrage an dieses
    // RechnungZusammen-Servlet gestellt.
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/zusammen/bezahlt\" method=\"POST\">");
    out.println("<h2>Der Kunde zahlt mit Karte:</h2>");
    out.println("<button type=\"submit\">Betrag beglichen</button>");
    out.println("<a href=\"" + req.getContextPath() + "/index.jsp\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/zusammen\" method=\"POST\">");
    out.println("<h2>Der Kunde zahlt bar:</h2>");
    out.println("<h3>Der gegebene Betrag lautet:</h3>");
    out.println("<input type=\"number\" name=\"gegebenesGeld\" min=\"" + Util.getGesamtPreis(out, orderBean, rabatt) + "\" step=\"0.01\" required=\"required\">");
    out.println("<button type=\"submit\">R&uuml;ckgeld berechnen</button>");
    out.println("<a href=\"" + req.getContextPath() + "/index.jsp\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  // Diese Methode wird aufgerufen, wenn der Kunde die Rechnung bar bezahlen will.
  // Sie errechnet das zu gebende Rückgeld.
  // Wenn bestätigt wurde, dass das Rückgeld gegeben wurde wird der Benutzer an das RechnungZusammenBezahlt-Servlet weitergeleitet.
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    int rabatt = (int) session.getAttribute("rabatt");
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Rechnungsservlet</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Die Rechnung f&uuml;r Tisch " + orderBean.getTischNr() + " wurde beglichen:</h1>");
    out.println("<h2>Bei einer Rechnung von " + String.format("%.2f", Util.getGesamtPreis(out, orderBean, rabatt)) + "&euro; wurden " + String.format("%.2f", Double.parseDouble(req.getParameter("gegebenesGeld"))) + "&euro; gezahlt.</h2>");
    out.println("<h2>Das ergibt ein R&uuml;ckgeld von " + String.format("%.2f", (Double.parseDouble(req.getParameter("gegebenesGeld")) - Util.getGesamtPreis(out, orderBean, rabatt))) + "&euro;</h2>");
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/zusammen/bezahlt\" method=\"POST\">");
    out.println("<button type=\"submit\">R&uuml;ckgeld gegeben</button>");
    out.println("<a href=\"" + req.getContextPath() + "/rechnung/zusammen\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
