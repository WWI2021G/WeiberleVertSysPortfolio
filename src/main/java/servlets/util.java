package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import beans.FormBean;

public class util {
  private static float gesamtPreis = 0;
  public static String[] products = {"Cola", "Fanta", "Sprite", "Wasser", "Apfel", "Schnitzel", "Kaesespaetzle", "Spaghetti", "Chicken", "Pommes"};
  public static String[] productNames = {"Cola", "Fanta", "Sprite", "Wasser", "Apfelsaftschorle", "Schnitzel mit Pommes", "K&auml;sesp&auml;tzle", "Spaghetti Bolognese", "Chicken Nuggets", "Pommes"};
  public static double[] prices = {3.9, 3.8, 3.5, 1.8, 2.7, 18.7, 16.4, 17.5, 12.3, 8.7};

  public static void displayOrder(PrintWriter out, FormBean orderBean) throws IOException {

    out.println("<table>");
    out.println("<tr>");
    out.println("<th>Produkt</th>");
    out.println("<th>Menge</th>");
    out.println("<th>Preis</th>");
    out.println("</tr>");
    out.println("<tr>");
    orderChecker(out, orderBean, false);
    out.println("</table>");
  }

  public static void splitOrder(PrintWriter out, FormBean orderBean) throws IOException {
    out.println("<table>");
    out.println("<tr>");
    out.println("<th>Produkt</th>");
    out.println("<th>Menge</th>");
    out.println("<th>Preis</th>");
    out.println("<th>Person zahlt</th>");
    out.println("</tr>");
    out.println("<tr>");
    orderChecker(out, orderBean, true);
    out.println("</table>");
  }

  public static void orderChecker(PrintWriter out, FormBean orderBean, boolean split) throws IOException {
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
          if ( split ) {
            out.println("<td><input type=\"number\" name=\"" + i + "\" min=\"0\" max=\"" + anzahl + "\" value=\"0\"></td>");
          }
          out.println("</tr>");
          gesamtPreis += (anzahl * prices[i]);
        }
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        out.println(e);
        e.printStackTrace();
      }
    }
    // TODO: Rabatt
    out.println("<tr>");
    out.println("<td>Gesamt</td>");
    out.println("<td></td>");
    out.println("<td>" + String.format("%.2f", gesamtPreis) + "&euro;</td>");
  }

  public static void payOne(PrintWriter out, FormBean orderBean, String product, int amount) {
    String setterCall = "setAnzahl" + product;
    String getterCall = "getAnzahl" + product;
    Method setter, getter;
    try {
      setter = FormBean.class.getMethod(setterCall, int.class);
      getter = FormBean.class.getMethod(getterCall);
      int newAnzahl = (int) getter.invoke(orderBean) - amount;
      setter.invoke(orderBean, newAnzahl);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      out.println(e);
      e.printStackTrace();
    }
  }

  public static float getGesamtPreis(PrintWriter out, FormBean orderBean) {
    gesamtPreis = 0;
    for (int i = 0; i < products.length; i++) {
      String getterCall = "getAnzahl" + products[i];
      Method getter;
      try {
        getter = FormBean.class.getMethod(getterCall);
        int anzahl = (int) getter.invoke(orderBean);
        gesamtPreis += (anzahl * prices[i]);
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        out.println(e);
        e.printStackTrace();
      }
    }
    gesamtPreis = Float.parseFloat(String.format("%.2f", gesamtPreis));
    return gesamtPreis;
  }
}
