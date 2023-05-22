package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Dieses Servlet wird verwendet, um den Benutzer an die richtige Stelle umzuleiten und, um den Rabatt zwischenzuspeichern
@WebServlet("/rechnung")
public class Rechnung extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    String zsmOderGetrennt = req.getParameter("zsmOderGetrennt");
    String rabatt = req.getParameter("rabatt");

    // Hier wird der Rabatt als Sessionvariable festgelegt und kann so in den restlichen Servlets gelesen werden.
    if ( rabatt.equals("Rabatt1") ) {
      session.setAttribute("rabatt", 10);
    } else if ( rabatt.equals("Rabatt2") ) {
      session.setAttribute("rabatt", 20);
    } else if ( rabatt.equals("Rabatt3") ) {
      session.setAttribute("rabatt", 30);
    } else {
      session.setAttribute("rabatt", 0);
    }

    // Hier findet das Routing statt, je nach dem, ob zusammen oder getrennt bezahlt wird.
    switch (zsmOderGetrennt) {
      case "zusammen":
        resp.sendRedirect(req.getContextPath() + "/rechnung/zusammen");
        break;
      case "getrennt":
        resp.sendRedirect(req.getContextPath() + "/rechnung/getrennt");
        break;
    }
  }
}
