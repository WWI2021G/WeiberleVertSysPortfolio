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

// Dieses Servlet wird aufgerufen, um getrennte Rechnungen zu bezahlen.
@WebServlet("/rechnung/getrennt/bezahlt")
public class RechnungGetrenntBezahlt extends HttpServlet {
  private String[] products = FormBean.getProducts();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    session = req.getSession(true);
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    // Hier werden alle Session-Attribute zwischen gespeichert.
    Enumeration<String> attributeNames = session.getAttributeNames();
    // Hier wird über alle Session-Attribute iteriert.
    while ( attributeNames.hasMoreElements() ) {
      String attributeName = attributeNames.nextElement();
      Object attributeValue = session.getAttribute(attributeName);
      // Hier werden die Attribute aussortiert, die nicht Teil der Rechnung sind.
      if ( !attributeName.equals("form") && !attributeName.equals("gesamtPreis") && !attributeName.equals("rabatt") ) {
        // Hier wird die payOne-Methode der Util-Klasse aufgerufen, um das bezahlte Produkt zu aktualisieren.
        Util.payOne(out, orderBean, products[Integer.parseInt(attributeName)], (int) attributeValue);
        // Hier werdne die Attribute aus der Session entfernt, sobald die Daten übertragen und gespeichert wurden.
        session.removeAttribute(attributeName);
      }
    }
    // Hier wird geprüft, ob noch offene Produkte in der Rechnung sind.
    // Wenn ja wird der Benutzer zurück auf das RechnungGetrennt-Servlet umgeleitet, und die nächste Person kann zahlen.
    // Wenn alle Produkte beglichen wurden wird der Benutzer automatisch auf die Startseite zurückgebracht.
    if ( orderBean.isOrderOpen() ) {
      resp.sendRedirect(req.getContextPath() + "/rechnung/getrennt");
    } else {
      resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }
}
