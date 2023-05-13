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

@WebServlet("/rechnung/bar/getrennt/bezahlt")
public class rechnungBarGetrenntBezahlt extends HttpServlet {

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
    out.println("<h2>Bei einer Rechnung von " + String.format("%.2f", gesamtPreis) + "&euro; wurden " + String.format("%.2f", Float.parseFloat(req.getParameter("gegebenesGeld"))) + "&euro; gezahlt.</h2>");
    out.println("<h2>Das ergibt ein R&uuml;ckgeld von " + String.format("%.2f", (Float.parseFloat(req.getParameter("gegebenesGeld")) - gesamtPreis)) + "&euro;</h2>");
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/bar/getrennt/bezahlt\" method=\"GET\">");
    out.println("<button type=\"submit\">R&uuml;ckgeld gegeben</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        util.payOne(out, orderBean, util.products[Integer.parseInt(attributeName)], (int) attributeValue);
        session.removeAttribute(attributeName);
      }
    }
    if ( orderBean.isOrderOpen() ) {
      resp.sendRedirect(req.getContextPath() + "/rechnung/bar/getrennt");
    } else {
      resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
  }
}
