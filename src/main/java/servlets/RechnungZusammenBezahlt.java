package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.FormBean;

@WebServlet("/rechnung/zusammen/bezahlt")
public class RechnungZusammenBezahlt extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    FormBean orderBean = (FormBean) session.getAttribute("form");
    orderBean.payFull();
    resp.sendRedirect(req.getContextPath() + "/index.jsp");
  }
}