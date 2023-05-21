package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import beans.FormBean;

public class util {
  private static float gesamtPreis = 0;
  private static float rabattAbsolut = 0;
  private static String[] products = FormBean.getProducts();
  private static String[] productNames = FormBean.getProductNames();
  private static double[] prices = FormBean.getPrices();

  public static void displayOrder(PrintWriter out, FormBean orderBean, int rabatt) throws IOException {

    out.println("<table>");
    out.println("<tr>");
    out.println("<th style=\"width: 220px\">Produkt</th>");
    out.println("<th style=\"width: 80px\">Menge</th>");
    out.println("<th style=\"width: 80px\">Preis</th>");
    out.println("</tr>");
    out.println("<tr>");
    orderChecker(out, orderBean, rabatt, false);
    out.println("</table>");
  }

  public static void splitOrder(PrintWriter out, FormBean orderBean, int rabatt) throws IOException {
    out.println("<table>");
    out.println("<tr>");
    out.println("<th style=\"width: 220px\">Produkt</th>");
    out.println("<th style=\"width: 80px\">Menge</th>");
    out.println("<th style=\"width: 80px\">Preis</th>");
    out.println("<th>Person zahlt</th>");
    out.println("</tr>");
    out.println("<tr>");
    orderChecker(out, orderBean, rabatt, true);
    out.println("</table><br>");
  }

  public static void orderChecker(PrintWriter out, FormBean orderBean, int rabatt, boolean split) throws IOException {
    gesamtPreis = 0;
    rabattAbsolut = 0;

    for (int i = 0; i < products.length; i++) {
      String getterCall = "getAnzahl" + products[i];
      Method getter;
      try {
        getter = FormBean.class.getMethod(getterCall);
        int anzahl = (int) getter.invoke(orderBean);
        if ( anzahl != 0 ) {
          out.println("<td>" + productNames[i] + "</td>");
          out.println("<td style=\"text-align: center;\">" + anzahl + "</td>");
          out.println("<td style=\"text-align: right;\">" + String.format("%.2f", anzahl * prices[i]) + "&euro;</td>");
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
    out.println("<tr>");
    out.println("<td>Rabatt</td>");
    rabattAbsolut = (gesamtPreis * rabatt / 100);
    out.println("<td style=\"text-align: center;\">" + rabatt + "&#37;</td>");
    out.println("<td style=\"text-align: right; color: green;\">" + String.format("%.2f", rabattAbsolut) + "&euro;</td>");
    out.println("</tr>");
    gesamtPreis = gesamtPreis - rabattAbsolut;
    out.println("<tr>");
    out.println("<td>Gesamt</td>");
    out.println("<td></td>");
    out.println("<td style=\"text-align:right; color: red;\">" + String.format("%.2f", gesamtPreis) + "&euro;</td>");
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

  public static float getGesamtPreis(PrintWriter out, FormBean orderBean, int rabatt) {
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
    gesamtPreis = gesamtPreis - (gesamtPreis * rabatt / 100);
    return gesamtPreis;
  }
}
