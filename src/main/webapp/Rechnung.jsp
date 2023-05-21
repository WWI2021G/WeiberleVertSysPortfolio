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

  <% if (form.isOrderOpen()) { %>

    <h2>Folgende Informationen werden f&uuml;r die Rechnungsstellung ben&ouml;tigt:</h2>

    <form action="rechnung" method="post">
      Die Kunden wollen
      <input type="radio" name="zsmOderGetrennt" value="zusammen" required>zusammen</input>
      /
      <input type="radio" name="zsmOderGetrennt" value="getrennt">getrennt</input>
      bezahlen.<br><br>

      Die Kunden haben einen Rabattcode:
      <input type="text" name="rabatt"><br><br>

      <button type="submit">Best&auml;tigen</button>
    </form>

  <% } else { %>

      <h3>F&uuml;r Tisch
        <jsp:getProperty name='form' property='tischNr' /> gibt es noch keine Rechnung, bitte kehren Sie zur Startseite
        zur&uuml;ck
      </h3>
      <a href="index.jsp"><button>Zur&uuml;ck zur Startseite</button></a>

  <% } %>

</body>

</html>
