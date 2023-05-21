package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/rechnung")
public class rechnung extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    String zsmOderGetrennt = req.getParameter("zsmOderGetrennt");
    String rabatt = req.getParameter("rabatt");

    if ( rabatt.equals("Rabatt1") ) {
      session.setAttribute("rabatt", 10);
    } else if ( rabatt.equals("Rabatt2") ) {
      session.setAttribute("rabatt", 20);
    } else if ( rabatt.equals("Rabatt3") ) {
      session.setAttribute("rabatt", 30);
    } else {
      session.setAttribute("rabatt", 0);
    }

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
