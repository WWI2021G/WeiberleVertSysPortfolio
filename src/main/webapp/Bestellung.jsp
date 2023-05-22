<%-- Hier wird erstmals die Bean verwendet, um die Daten der Bestellungen zu speichern. Hier wird zunächst nur die Tischnummer festgelegt, da von ihr abhängig alle restlichen Daten gespeichert werden --%>
<jsp:useBean class="beans.FormBean" id="form" scope="session" />
<jsp:setProperty name="form" property="tischNr" />

<!DOCTYPE html>
<html lang="de">

<head>
  <title>Bestellung 3000</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%-- Formattierung der Tabelle, die in diesem jsp verwendet wird, um die Anzahl der einzelnen Produkte eingeben zu können --%>
  <style>
    table,
    th,
    td {
      border: 1px solid black;
      border-collapse: collapse;
    }
  </style>
</head>

<body>

  <%-- Aktuelle Tischnummer wird oben nochmal dargestellt zur Übersicht und zum Überprüfen --%>
  <h1>Bestellung f&uuml;r Tisch
    <jsp:getProperty name='form' property='tischNr' />:
  </h1>

  <%-- Zwei Tabellen einmal für Getränke und einmal für Essen, um es übersichtlich zu halten --%>
  <%-- Die erste Spalte beinhaltet die Anzahl. Wenn noch keine Bestellung aufgegeben wurde beginnen alle Werte mit 0, wenn bereits Produkte auf den Tisch gebucht sind sind die jeweiligen Werte der Anfangswert --%>
  <%-- Der minimale Input ist immer 0, da hier immer absolute Werte angezeigt werden gibt es keinen Grund negative Werte zuzulassen. Um etwas nachzubestellen oder zu korrigieren müssen einfach nur die absoluten Werte angepasst werden --%>
  <form action="UmleitungBestellung.jsp">
    <h2>Getr&auml;nke ausw&auml;hlen</h2>
    <table>
      <tr>
        <th>Anzahl</th>
        <th style="width: 300px;">Getr&auml;nk</th>
        <th>Einzelpreis</th>
      </tr>
      <tr>
        <td><input type="number" name="anzahlCola" value="<jsp:getProperty name='form' property='anzahlCola' />"
            min="0"></td>
        <td style="text-align: center;">Cola</td>
        <td style="text-align: right;">3,90&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlFanta" value="<jsp:getProperty name='form' property='anzahlFanta' />"
            min="0"></td>
        <td style="text-align: center;">Fanta</td>
        <td style="text-align: right;">3,80&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlSprite" value="<jsp:getProperty name='form' property='anzahlSprite' />"
            min="0"></td>
        <td style="text-align: center;">Sprite</td>
        <td style="text-align: right;">3,50&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlWasser" value="<jsp:getProperty name='form' property='anzahlWasser' />"
            min="0"></td>
        <td style="text-align: center;">Wasser</td>
        <td style="text-align: right;">1,80&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlApfel" value="<jsp:getProperty name='form' property='anzahlApfel' />"
            min="0"></td>
        <td style="text-align: center;">Apfelsaftschorle</td>
        <td style="text-align: right;">2,70&euro;</td>
      </tr>
    </table><br>

    <table>
      <h2>Essen ausw&auml;hlen</h2>
      <tr>
        <th>Anzahl</th>
        <th style="width: 300px;">Essen</th>
        <th>Einzelpreis</th>
      </tr>
      <tr>
        <td><input type="number" name="anzahlSchnitzel"
            value="<jsp:getProperty name='form' property='anzahlSchnitzel' />" min="0"></td>
        <td style="text-align: center;">Schnitzel mit Pommes</td>
        <td style="text-align: right;">18,70&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlKaesespaetzle"
            value="<jsp:getProperty name='form' property='anzahlKaesespaetzle' />" min="0"></td>
        <td style="text-align: center;">K&auml;sesp&auml;tzle</td>
        <td style="text-align: right;">16,40&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlSpaghetti"
            value="<jsp:getProperty name='form' property='anzahlSpaghetti' />" min="0"></td>
        <td style="text-align: center;">Spaghetti Bolognese</td>
        <td style="text-align: right;">17,50&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlChicken" value="<jsp:getProperty name='form' property='anzahlChicken' />"
            min="0"></td>
        <td style="text-align: center;">Chicken Nuggets</td>
        <td style="text-align: right;">12,30&euro;</td>
      </tr>
      <tr>
        <td><input type="number" name="anzahlPommes" value="<jsp:getProperty name='form' property='anzahlPommes' />"
            min="0"></td>
        <td style="text-align: center;">Pommes</td>
        <td style="text-align: right;">8,70&euro;</td>
      </tr>
    </table><br>
  <%-- Die aktualisierten Werte werden erst gespeichert, wenn der Bestellung absenden Knopf gedrückt wurde. Mit abbrechen werden alle Änderungen verworfen --%>
    <button type="submit">Bestellung absenden</button>
    <a href="index.jsp"><button type="button">Bestellung abbrechen</button></a>

  </form>

</body>

</html>
