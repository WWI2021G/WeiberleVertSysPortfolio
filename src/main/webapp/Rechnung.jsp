<jsp:useBean class="beans.FormBean" id="form" scope="session" />
<jsp:setProperty name="form" property="tischNr" />

<!DOCTYPE html>
<html lang="de">

<head>
  <title>Bestellung 3000</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/style.css" rel="stylesheet">
</head>

<body>

  <h1>Rechnungen erstellen f&uuml;r Tisch
    <jsp:getProperty name='form' property='tischNr' />:
  </h1>

    <h2>Folgende Rechnung ist f&uuml;r Tisch <jsp:getProperty name='form' property='tischNr' /> offen:</h2>

    <h3>Getr&auml;nke</h3>

  <form action="${pageContext.request.contextPath}/rechnung">
    Die Rechnung wird
    <input type="radio" name="bezahlart" value="1">Bar</input>
    /
    <input type="radio" name="bezahlart" value="2">mit Karte</input>
    beglichen.<br>

    Die Kunden wollen
    <input type="radio" name="zsmOderGetrennt" value="1">zusammen</input>
    /
    <input type="radio" name="zsmOderGetrennt" value="2">getrennt</input>
    bezahlen.<br>

    Die Kunden haben einen Rabattcode:
    <input type="text" name="rabatt">

    <button type="submit">Best&auml;tigen</button>
  </form>

</body>

</html>
