package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import beans.FormBean;

public class util {
  private static float gesamtPreis = 0;

  public static void displayOrder(PrintWriter out, FormBean orderBean) throws IOException {

    out.println("<table>");
    out.println("<tr>");
    out.println("<th>Produkt</th>");
    out.println("<th>Menge</th>");
    out.println("<th>Preis</th>");
    out.println("</tr>");
    out.println("<tr>");
    orderChecker(out, orderBean);

    // TODO: Implement Rabatt
    out.println("<tr>");
    out.println("<td>Gesamt</td>");
    out.println("<td></td>");
    out.println("<td>" + String.format("%.2f", gesamtPreis) + "</td>");
    out.println("</table>");
  }

  public static void orderChecker(PrintWriter out, FormBean orderBean) throws IOException {
    String[] products = {"Cola", "Fanta", "Sprite", "Wasser", "Apfel", "Schnitzel", "Kaesespaetzle", "Spaghetti", "Chicken", "Pommes"};
    String[] productNames = {"Cola", "Fanta", "Sprite", "Wasser", "Apfelsaftschorle", "Schnitzel mit Pommes", "K&auml;sesp&auml;tzle", "Spaghetti Bolognese", "Chicken Nuggets", "Pommes"};
    double[] prices = {3.9, 3.8, 3.5, 1.8, 2.7, 18.7, 16.4, 17.5, 12.3, 8.7};
    gesamtPreis = 0;

    for (int i = 0; i < products.length; i++) {
      String getterCall = "getAnzahl" + products[i];
      Method getter;
      try {
        getter = FormBean.class.getMethod(getterCall);
        int anzahl = (int) getter.invoke(orderBean);
        if ( anzahl != 0 ) {
          out.println("<td>" + productNames[i] + "</td>");
          out.println("<td>" + anzahl + "</td>");
          out.println("<td>" + String.format("%.2f", anzahl * prices[i]) + "&euro;</td>");
          out.println("</tr>");
          gesamtPreis += (anzahl * prices[i]);
        }
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        out.println(e);
        e.printStackTrace();
      }
    }
  }
}
