package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rechnung")
public class rechnung extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String bezahlart = req.getParameter("bezahlart");
    String zsmOderGetrennt = req.getParameter("zsmOderGetrennt");
    String combined = bezahlart + zsmOderGetrennt;

    switch (combined) {
      case "digitalzusammen":
        resp.sendRedirect(req.getContextPath() + "/rechnung/digital/zusammen");
        break;
      case "digitalgetrennt":
        resp.sendRedirect(req.getContextPath() + "/rechnung/digital/getrennt");
        break;
      case "barzusammen":
        resp.sendRedirect(req.getContextPath() + "/rechnung/bar/zusammen");
        break;
      case "bargetrennt":
        resp.sendRedirect(req.getContextPath() + "/rechnung/bar/getrennt");
        break;
    }
  }
}
