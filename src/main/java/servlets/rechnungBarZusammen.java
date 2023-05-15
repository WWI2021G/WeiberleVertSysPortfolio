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

@WebServlet("/rechnung/bar/zusammen")
public class rechnungBarZusammen extends HttpServlet {

  @Override
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
    util.displayOrder(out, orderBean, rabatt);
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/bar/zusammen\" method=\"POST\">");
    out.println("<h3>Der gegebene Betrag lautet:</h3>");
    out.println("<input type=\"number\" name=\"gegebenesGeld\" min=\"" + util.getGesamtPreis(out, orderBean, rabatt) + "\" step=\"0.01\" required=\"required\">");
    out.println("<button type=\"submit\">R&uuml;ckgeld berechnen</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

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
    out.println("<h2>Bei einer Rechnung von " + String.format("%.2f", util.getGesamtPreis(out, orderBean, rabatt)) + "&euro; wurden " + String.format("%.2f", Float.parseFloat(req.getParameter("gegebenesGeld"))) + "&euro; gezahlt.</h2>");
    out.println("<h2>Das ergibt ein R&uuml;ckgeld von " + String.format("%.2f", (Float.parseFloat(req.getParameter("gegebenesGeld")) - util.getGesamtPreis(out, orderBean, rabatt))) + "&euro;</h2>");
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/bar/zusammen/bezahlt\" method=\"POST\">");
    out.println("<button type=\"submit\">R&uuml;ckgeld gegeben</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
