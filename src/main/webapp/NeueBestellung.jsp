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

  <form action="">
    <h2>F&uuml;r welchen Tisch wollen Sie eine Rechnung &ouml;ffnen</h2>
    <input type="number" name="TischNr" min="1" max="5"><br>
    <h2>Getr&auml;nke ausw&auml;hlen</h2>
    <table>
      <tr>
        <th>Anzahl</th>
        <th>Getr&auml;nk</th>
        <th>Einzelpreis</th>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlCola" value="0" min="0"></td>
        <td style="text-align: center;">Cola</td>
        <td style="text-align: right;">3,90&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlFanta" value="0" min="0"></td>
        <td style="text-align: center;">Fanta</td>
        <td style="text-align: right;">3,80&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlSprite" value="0" min="0"></td>
        <td style="text-align: center;">Sprite</td>
        <td style="text-align: right;">3,50&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlWasser" value="0" min="0"></td>
        <td style="text-align: center;">Wasser</td>
        <td style="text-align: right;">1,80&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlApfel" value="0" min="0"></td>
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
        <td><input type="number" name="AnzahlSchnitzel" value="0" min="0"></td>
        <td style="text-align: center;">Schnitzel mit Pommes</td>
        <td style="text-align: right;">18,70&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlKäsespätzle" value="0" min="0"></td>
        <td style="text-align: center;">K&auml;sesp&auml;tzle</td>
        <td style="text-align: right;">16,40&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlSpaghetti" value="0" min="0"></td>
        <td style="text-align: center;">Spaghetti Bolognese</td>
        <td style="text-align: right;">17,50&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlChicken" value="0" min="0"></td>
        <td style="text-align: center;">Chicken Nuggets</td>
        <td style="text-align: right;">12,30&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="AnzahlPommes" value="0" min="0"></td>
        <td style="text-align: center;">Pommes</td>
        <td style="text-align: right;">8,70&euro;</td>
      </tr>
    </table><br>
    <button type="submit">Bestellung absenden</button>
  </form>

</body>

</html>
