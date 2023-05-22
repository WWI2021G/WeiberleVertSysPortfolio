package beans;

// Diese Klasse wird verwendet, um die Daten jeder einzelnen Bestellung zu speichern. Dies findet im Rahmen der Session statt.
public class FormBean {
  private int tischNr;
  // Nützliche Variablen, die auf verschiedenen Seiten verwendet werden und hier an einer Stelle zusammen gefasst sind.
  // Um andere Essen oder Trinken anbieten zu können muss nur in dieser Klasse etwas geändert werden.
  // Die Reihenfolge der Arrays ist wichtig, da der Index jeweils mit der Position im tische-Array übereinstimmt.
  // Also die Nummer in tische[x][0] entspricht der Anzahl an bestellten Cola für Tisch x. Parallel dazu ist productNames[0] und prices[0] jeweils der Cola zugeordnet.
  private static final String[] products = {"Cola", "Fanta", "Sprite", "Wasser", "Apfel", "Schnitzel", "Kaesespaetzle", "Spaghetti", "Chicken", "Pommes"};
  private static final String[] productNames = {"Cola", "Fanta", "Sprite", "Wasser", "Apfelsaftschorle", "Schnitzel mit Pommes", "K&auml;sesp&auml;tzle", "Spaghetti Bolognese", "Chicken Nuggets", "Pommes"};
  private static final double[] prices = {3.9, 3.8, 3.5, 1.8, 2.7, 18.7, 16.4, 17.5, 12.3, 8.7};

  // Das zweidimensionale Array tische wird verwendet, um die Bestellungen zu speichern.
  // Die erste Dimension beinhaltet die 5 Tische, die zweite die jeweilige Anzahl der bestellten Produkte.
  // Zu beachten ist, dass Tischnummer 1 dem Index 0 in der ersten Dimension des tische-Arrays entspricht: Index = tischNr - 1
  int[][] tische = new int[5][10];

  // Getter und Setter für die Tischnummer
  public int getTischNr() {
    return tischNr;
  }

  public void setTischNr(int tischNr) {
    this.tischNr = tischNr;
  }

  // Im Folgenden sind getter und setter für die jeweiligen Produkte definiert
  // Die erste Dimension ist wie oben gezeigt abhängig von der gesetzten tischNr, in der zweiten Dimension hat jedes Produkt eine Stelle im tische-Array
  public int getAnzahlCola() {
    return tische[tischNr - 1][0];
  }

  public void setAnzahlCola(int anzahlCola) {
    tische[tischNr - 1][0] = anzahlCola;
  }

  public int getAnzahlFanta() {
    return tische[tischNr - 1][1];
  }

  public void setAnzahlFanta(int anzahlFanta) {
    tische[tischNr - 1][1] = anzahlFanta;
  }

  public int getAnzahlSprite() {
    return tische[tischNr - 1][2];
  }

  public void setAnzahlSprite(int anzahlSprite) {
    tische[tischNr - 1][2] = anzahlSprite;
  }

  public int getAnzahlWasser() {
    return tische[tischNr - 1][3];
  }

  public void setAnzahlWasser(int anzahlWasser) {
    tische[tischNr - 1][3] = anzahlWasser;
  }

  public int getAnzahlApfel() {
    return tische[tischNr - 1][4];
  }

  public void setAnzahlApfel(int anzahlApfel) {
    tische[tischNr - 1][4] = anzahlApfel;
  }

  public int getAnzahlSchnitzel() {
    return tische[tischNr - 1][5];
  }

  public void setAnzahlSchnitzel(int anzahlSchnitzel) {
    tische[tischNr - 1][5] = anzahlSchnitzel;
  }

  public int getAnzahlKaesespaetzle() {
    return tische[tischNr - 1][6];
  }

  public void setAnzahlKaesespaetzle(int anzahlKaesespaetzle) {
    tische[tischNr - 1][6] = anzahlKaesespaetzle;
  }

  public int getAnzahlSpaghetti() {
    return tische[tischNr - 1][7];
  }

  public void setAnzahlSpaghetti(int anzahlSpaghetti) {
    tische[tischNr - 1][7] = anzahlSpaghetti;
  }

  public int getAnzahlChicken() {
    return tische[tischNr - 1][8];
  }

  public void setAnzahlChicken(int anzahlChicken) {
    tische[tischNr - 1][8] = anzahlChicken;
  }

  public int getAnzahlPommes() {
    return tische[tischNr - 1][9];
  }

  public void setAnzahlPommes(int anzahlPommes) {
    tische[tischNr - 1][9] = anzahlPommes;
  }

  // Getter um die an verschiedenen Stellen verwendeten Variablen zugänglich zu machen
  public static String[] getProducts() {
    return products;
  }
  
  public static String[] getProductNames() {
    return productNames;
  }

  public static double[] getPrices() {
    return prices;
  }

  // Methode, die überprüft, ob für den Tisch bereits eine Bestellung aufgegeben wurde.
  public boolean isOrderOpen() {
    for (int i = 0; i < tische[tischNr - 1].length; i++) {
      if ( tische[tischNr - 1][i] != 0 ) {
        return true;
      }
    }
    return false;
  }

  // Methode, die aufgerufen wird, wenn eine Rechnung komplett beglichen wurde.
  // Sie setzt alle Produktanzahlen für die gegebene Tischnummer auf 0
  public void payFull() {
    for (int i = 0; i < tische[tischNr - 1].length; i++) {
      tische[tischNr - 1][i] = 0;
    }
  }
}
