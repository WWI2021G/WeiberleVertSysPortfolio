package enums;

public enum Orderable {
  COLA("Cola", 3.9), FANTA("Fanta", 3.8), SPRITE("Sprite", 3.5), WASSER("Wasser", 1.8), APFEL("Apfel", 2.7), SCHNITZEL("Schnitzel mit Pommes", 18.7), KAESESPAETZLE("K&auml;sesp&auml;tzle", 16.4), SPAGHETTI("Spaghetti Bolognese", 17.5), CHICKEN("Chicken Nuggets", 12.3), POMMES("Pommes", 8.7);

  final private String productName;
  final private double price;

  Orderable(String productName, double price) {
    this.productName = productName;
    this.price = price;
  }
}

