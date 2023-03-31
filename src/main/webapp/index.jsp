<!DOCTYPE html>
<html lang="de">

<head>
  <title>Bestellung 3000</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/style.css" rel="stylesheet">
</head>

<body>
  <h1>Bestellung 3000</h1>

  <p>
    Hierbei handelt es sich um den ultimativen Bestellungsmangager f&uuml;r den Gastronomiebereich.<br>
    Sie befinden sich aktuell im Hauptmen&uuml;. Von hier aus k&ouml;nnen Sie einen neuen Tisch anlegen, einen
    bestehenden Tisch ver&auml;ndern und die Rechnung erstellen lassen.<br>
  </p>

  <form action="NeueBestellung.jsp">
    <button type="submit">Neue Bestellung</button>
  </form>

  <form action="AlteBestellung.jsp">
    <input type="number" name="nummerBearbeitenBestellung">Die Bestellung welcher Tischnummer soll bearbeitet
    werden?</input>
    <button type="submit">Bestellung bearbeiten</button>
  </form>

  <form action="RechnungGen.jsp">
    <input type="number" name="nummerBearbeitenBestellung">F&uuml;r welchen Tisch soll eine Rechnung erstellt
    werden?</input>
    <input type="text" name="rechnungsart">Wollen die G&auml;ste zusammen oder separat zahlen?</input>
    <button type="submit">Rechnung erstellen</button>
  </form>
</body>

</html>
