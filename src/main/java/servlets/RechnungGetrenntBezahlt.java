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
    Enumeration<String> attributeNames = session.getAttributeNames();
    while ( attributeNames.hasMoreElements() ) {
      String attributeName = attributeNames.nextElement();
      Object attributeValue = session.getAttribute(attributeName);
      if ( !attributeName.equals("form") && !attributeName.equals("gesamtPreis") && !attributeName.equals("rabatt") ) {
        Util.payOne(out, orderBean, products[Integer.parseInt(attributeName)], (int) attributeValue);
        session.removeAttribute(attributeName);
      }
    }
    if ( orderBean.isOrderOpen() ) {
      resp.sendRedirect(req.getContextPath() + "/rechnung/getrennt");
    } else {
      resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }
}
