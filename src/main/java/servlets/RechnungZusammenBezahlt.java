package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.FormBean;

// Dieses Servlet wird aufgerufen, wenn eine Rechnung zusammen bezahlt wurde.
@WebServlet("/rechnung/zusammen/bezahlt")
public class RechnungZusammenBezahlt extends HttpServlet {

  // Diese Methode ruft die Methode payFull der FormBean-Klasse auf und setzt somit die Bestellung zurück.
  // Danach wird der Benutzer an die Startseite zurückgeleitet.
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    orderBean.payFull();
    resp.sendRedirect(req.getContextPath() + "/index.jsp");
  }
}
