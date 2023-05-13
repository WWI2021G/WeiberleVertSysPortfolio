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

@WebServlet("/rechnung/bar/getrennt")
public class rechnungBarGetrennt extends HttpServlet {

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
    out.println("<form action=\"" + req.getContextPath() + "/rechnung/bar/getrennt/zahlen\" method=\"POST\">");
    util.splitOrder(out, orderBean, rabatt);
    out.println("<button type=\"submit\">Bezahlen</button>");
    out.println("<a href=\"" + req.getContextPath() + "/index.jsp\"><button type=\"button\">Abbrechen</button></a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}

