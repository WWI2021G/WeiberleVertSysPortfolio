package beans;

public class FormBean {
  private int tischNr;

  int[][] tische = new int[5][10];

  public int getTischNr() {
    return tischNr;
  }

  public void setTischNr(int tischNr) {
    this.tischNr = tischNr;
  }

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
}
