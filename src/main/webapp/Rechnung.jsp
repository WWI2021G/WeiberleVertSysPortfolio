<%-- Hier wird wieder die Tischnummer verwendet, um zu wissen auf welche Daten zugegriffen werden soll --%>
<jsp:useBean class="beans.FormBean" id="form" scope="session" />
<jsp:setProperty name="form" property="tischNr" />

<!DOCTYPE html>
<html lang="de">

<head>
  <title>Bestellung 3000</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

  <h1>Rechnungen erstellen f&uuml;r Tisch
    <jsp:getProperty name='form' property='tischNr' />:
  </h1>

  <%-- Prüft, ob für die angegebene Tischnummer eine Bestellung gemacht wurde --%>
  <%-- Wenn ja kann mit Radiobuttons festgelegt werden, ob die Kunden getrennt oder zusammen bezahlen wollen und, ob sie einen Rabattcode haben --%>
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

  <%-- Wenn für die angegebene Tischnummer noch keine Bestellung aufgegeben wurde gibt die Seite eine Fehlermeldung zurück und fordert auf auf die Startseite zurückzukehren --%>
  <% } else { %>

      <h3>F&uuml;r Tisch
        <jsp:getProperty name='form' property='tischNr' /> gibt es noch keine Rechnung, bitte kehren Sie zur Startseite
        zur&uuml;ck
      </h3>
      <a href="index.jsp"><button>Zur&uuml;ck zur Startseite</button></a>

  <% } %>

</body>

</html>
