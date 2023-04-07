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

  <h1>Bestellung</h1>
  <form action="Bestellung.jsp">
    <label for="tischNr">Geben Sie die gew&uuml;nschte Tischnumer ein:</label><br>
    <input type="number" id="tischNr" name="tischNr" min="1" max="5" required="required">
    <button type="submit">Bestellung bearbeiten</button>
  </form>

  <h1>Rechnung</h1>
  <form action="Rechnung.jsp">
    <label for="tischNr">Geben Sie die gew&uuml;nschte Tischnumer ein:</label><br>
    <input type="number" id="tischNr" name="tischNr" min="1" max="5" required="required">
    <button type="submit">Rechnung erstellen</button>
  </form>
</body>

</html>
