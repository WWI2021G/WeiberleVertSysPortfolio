<!DOCTYPE html>
<html lang="de">

<head>
  <title>Bestellung 3000</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/style.css" rel="stylesheet">
</head>

<body>

  <h1>Neue Bestellung erstellen</h1>

  <form action="AusgabeNeueBestellung.jsp">
    <h2>F&uuml;r welchen Tisch wollen Sie eine Rechnung &ouml;ffnen</h2>
    <input type="number" name="tischNr" min="1" max="5" required="required"><br>
    <h2>Getr&auml;nke ausw&auml;hlen</h2>
    <table>
      <tr>
        <th>Anzahl</th>
        <th>Getr&auml;nk</th>
        <th>Einzelpreis</th>
      </tr>
      <tr>
        <td><input type="number" name="anzahlCola" value="0" min="0"></td>
        <td style="text-align: center;">Cola</td>
        <td style="text-align: right;">3,90&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlFanta" value="0" min="0"></td>
        <td style="text-align: center;">Fanta</td>
        <td style="text-align: right;">3,80&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlSprite" value="0" min="0"></td>
        <td style="text-align: center;">Sprite</td>
        <td style="text-align: right;">3,50&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlWasser" value="0" min="0"></td>
        <td style="text-align: center;">Wasser</td>
        <td style="text-align: right;">1,80&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlApfel" value="0" min="0"></td>
        <td style="text-align: center;">Apfelsaftschorle</td>
        <td style="text-align: right;">2,70&euro;</td>
      </tr>
    </table><br>

    <table>
      <h2>Essen ausw&auml;hlen</h2>
      <tr>
        <th>Anzahl</th>
        <th>Essen</th>
        <th>Einzelpreis</th>
      </tr>
      <tr>
        <td><input type="number" name="anzahlSchnitzel" value="0" min="0"></td>
        <td style="text-align: center;">Schnitzel mit Pommes</td>
        <td style="text-align: right;">18,70&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlKäsespätzle" value="0" min="0"></td>
        <td style="text-align: center;">K&auml;sesp&auml;tzle</td>
        <td style="text-align: right;">16,40&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlSpaghetti" value="0" min="0"></td>
        <td style="text-align: center;">Spaghetti Bolognese</td>
        <td style="text-align: right;">17,50&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlChicken" value="0" min="0"></td>
        <td style="text-align: center;">Chicken Nuggets</td>
        <td style="text-align: right;">12,30&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlPommes" value="0" min="0"></td>
        <td style="text-align: center;">Pommes</td>
        <td style="text-align: right;">8,70&euro;</td>
      </tr>
    </table><br>
    <button type="submit">Bestellung absenden</button>
  </form>

</body>

</html>
