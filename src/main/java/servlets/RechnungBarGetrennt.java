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

// Dieses Servlet wird aufgerufen, wenn eine getrennte Rechnung bar bezahlt wird.
@WebServlet("/rechnung/bar/getrennt")
public class RechnungBarGetrennt extends HttpServlet {

  // Infolge der POST-Anfrage aus dem RechnungGetrennt-Servlet wird hier das Rückgeld berechnet.
  // Sobald bestätigt wurde, dass das Rückgeld ausgezahlt wurde, wird das RechnungGetrenntBezahlt-Servlet aufgerufen.
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    double gesamtPreis = (double) session.getAttribute("gesamtPreis");
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Rechnungsservlet</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Die Rechnung f&uuml;r Tisch " + orderBean.getTischNr() + " wurde beglichen:</h1>");
    out.println("<h2>Bei einer Rechnung von " + String.format("%.2f", gesamtPreis) + "&euro; wurden " + String.format("%.2f", Double.parseDouble(req.getParameter("gegebenesGeld"))) + "&euro; gezahlt.</h2>");
    out.println("<h2>Das ergibt ein R&uuml;ckgeld von " + String.format("%.2f", (Double.parseDouble(req.getParameter("gegebenesGeld")) - gesamtPreis)) + "&euro;</h2>");
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/getrennt/bezahlt\" method=\"POST\">");
    out.println("<button type=\"submit\">R&uuml;ckgeld gegeben</button>");
    // Mit diesem Button kann der Zahlvorgang abgebrochen werden.
    out.println("<a href=\"" + req.getContextPath() + "/rechnung/getrennt\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
