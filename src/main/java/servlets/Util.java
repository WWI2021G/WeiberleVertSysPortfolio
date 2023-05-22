package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import beans.FormBean;

// Diese Klasse wird verwendet, um Methoden bereitzustellen, die an mehreren Stellen Anwendung
// finden.
public class Util {
  // Deklaration verschiedener Variablen, die in verschiedenen Methoden benötigt werden.
  private static double gesamtPreis = 0;
  private static double rabattAbsolut = 0;
  private static String[] products = FormBean.getProducts();
  private static String[] productNames = FormBean.getProductNames();
  private static double[] prices = FormBean.getPrices();

  // Diese Methode wird verwendet, um eine Tabelle zu erstellen, die Produktname, Menge und Preis der
  // bestellten Produkte auflistet.
  // Sie ist stark von der orderChecker-Methode abhängig.
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

  // Diese Methode funktioniert ähnlich, wie die displayOrder-Methode, wird aber verwendet, wenn die
  // Rechnung getrennt beglichen werden soll.
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

  // Diese Methode überprüft welche Produkte bestellt wurden und gibt dann nur diese aus
  public static void orderChecker(PrintWriter out, FormBean orderBean, int rabatt, boolean split) throws IOException {
    // Zurücksetzen der Variablen, um die Funktionsweise auch bei mehreren Durchläufen zu garantieren
    gesamtPreis = 0;
    rabattAbsolut = 0;

    // Hier wird mit Java Reflection, um nicht jede Methode der Bean-Klasse individuell ausschreiben zu
    // müssen. Außerdem muss an dieser Methode nichts angepasst werden, wenn die Produkte verändert
    // werden.
    // Jedes einzelne Produkt wird hier durchlaufen, um zu überprüfen, ob es bestellt wurde oder nicht.
    for (int i = 0; i < products.length; i++) {
      // Hier wird eine Variable erstellt, die einen String mit dem Namen der Getter-Methode beinhaltet.
      String getterCall = "getAnzahl" + products[i];
      Method getter;
      // In diesem try-catch-Block wird mit Hilfe des getterCall-Strings die Methode aufgerufen und
      // geprüft, ob sie ungleich 0 ist.
      // Wenn ja wird das Produkt ausgegeben, wenn nein wird es übersprungen.
      try {
        // Hier wird mit Hilfe des getterCalls die jeweilige Methode in der Bean-Klasse rausgesucht.
        getter = FormBean.class.getMethod(getterCall);
        // Hier wird die Anzahl des jeweiligen Produkts, was der Returnwert der Get-Methode ist
        // zwischengespeichert.
        int anzahl = (int) getter.invoke(orderBean);
        if ( anzahl != 0 ) {
          out.println("<td>" + productNames[i] + "</td>");
          out.println("<td style=\"text-align: center;\">" + anzahl + "</td>");
          out.println("<td style=\"text-align: right;\">" + String.format("%.2f", anzahl * prices[i]) + "&euro;</td>");
          // Wenn eine getrennte Rechnung gewollt ist muss hier eine weitere Spalte für alle Produkte
          // ausgegeben werden.
          if ( split ) {
            out.println("<td><input type=\"number\" name=\"" + i + "\" min=\"0\" max=\"" + anzahl + "\" value=\"0\"></td>");
          }
          out.println("</tr>");
          gesamtPreis += (anzahl * prices[i]);
        }
        // Die folgenden Exceptions werden benötigt, wenn Methoden mit Hilfe von Java Reflections aufgerufen
        // werden
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        out.println(e);
        e.printStackTrace();
      }
    }
    out.println("<tr>");
    // Hier wird eine Zeile mit dem Rabatt eingefügt. Der gutgeschriebene Betrag wird in grün dargestellt.
    out.println("<td>Rabatt</td>");
    rabattAbsolut = (gesamtPreis * rabatt / 100);
    out.println("<td style=\"text-align: center;\">" + rabatt + "&#37;</td>");
    out.println("<td style=\"text-align: right; color: green;\">" + String.format("%.2f", rabattAbsolut) + "&euro;</td>");
    out.println("</tr>");
    gesamtPreis = gesamtPreis - rabattAbsolut;
    out.println("<tr>");
    // Hier wird eine Zeile mit dem Gesamtpreis eingefügt. Der zu zahlende Betrag wird in rot dargestellt.
    out.println("<td>Gesamt</td>");
    out.println("<td></td>");
    out.println("<td style=\"text-align:right; color: red;\">" + String.format("%.2f", gesamtPreis) + "&euro;</td>");
  }

  // Diese Methode wird verwendet, wenn die Rechnung getrennt bezahlt wird, um einzelne Produkte zu aktualisieren.
  // Hier wird ebenfalls mit Hilfe von Java Reflections gearbeitet, um die Setter und Getter-Methoden zu verwenden ohne sie ausschreiben zu müssen.
  public static void payOne(PrintWriter out, FormBean orderBean, String product, int amount) {
    String setterCall = "setAnzahl" + product;
    String getterCall = "getAnzahl" + product;
    Method setter, getter;
    try {
      setter = FormBean.class.getMethod(setterCall, int.class);
      getter = FormBean.class.getMethod(getterCall);
      // Hier wird die noch offene Anzahl des jeweiligen Produkts berechnet.
      int newAnzahl = (int) getter.invoke(orderBean) - amount;
      // Hier wird die neue Anzahl mit der jeweiligen Setter-Methode gespeichert.
      setter.invoke(orderBean, newAnzahl);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      out.println(e);
      e.printStackTrace();
    }
  }

  // Diese Methode wird verwendet, um den Gesamtpreis der Bestellung zu errechnen.
  // Sie bezieht den Rabatt direkt mit ein und formattiert das Ergebnis passend für Preise.
  // Auch in dieser Methode werden Java Reflections verwendet.
  public static double getGesamtPreis(PrintWriter out, FormBean orderBean, int rabatt) {
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
    // Hier wird der Rabatt mit eingerechnet.
    gesamtPreis = gesamtPreis - (gesamtPreis * rabatt / 100);
    // Hier wird das Ergebnis formattiert.
    gesamtPreis = Double.parseDouble(String.format("%.2f", gesamtPreis));
    return gesamtPreis;
  }
}
