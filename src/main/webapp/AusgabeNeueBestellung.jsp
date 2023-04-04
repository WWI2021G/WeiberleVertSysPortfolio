<jsp:useBean class="beans.FormBean" id="form" scope="session" />
<jsp:setProperty name="form" property="*" />
<!DOCTYPE html>
<html lang="de">

<head>
  <title>TestAusgabe</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/style.css" rel="stylesheet">
</head>

<body>
  <h1>Bestellungs Übersicht:</h1>
  <h2>Die Bestellung wurde f&uml;r Tisch
    <jsp:getProperty name="form" property="tischNr" /> angelegt
  </h2>
  <table>
    <tr>
      <th>Getr&auml;nk/Gericht</th>
      <th>Anzahl</th>
    </tr>
    <tr>
      <td>Cola</td>
      <td>
        <jsp:getProperty name="form" property="anzahlCola" />
      </td>
    </tr>
    <tr>
      <td>Fanta</td>
      <td>
        <jsp:getProperty name="form" property="anzahlFanta" />
      </td>
    </tr>
    <tr>
      <td>Sprite</td>
      <td>
        <jsp:getProperty name="form" property="anzahlSprite" />
      </td>
    </tr>
    <tr>
      <td>Wasser</td>
      <td>
        <jsp:getProperty name="form" property="anzahlWasser" />
      </td>
    </tr>
    <tr>
      <td>Apfelsaftschorle</td>
      <td>
        <jsp:getProperty name="form" property="anzahlApfel" />
      </td>
    </tr>
    <tr>
      <td>Schnitzel</td>
      <td>
        <jsp:getProperty name="form" property="anzahlSchnitzel" />
      </td>
    </tr>
    <tr>
      <td>K&auml;sesp&auml;tzle</td>
      <td>
        <jsp:getProperty name="form" property="anzahlKaesespaetzle" />
      </td>
    </tr>
    <tr>
      <td>Spaghetti Bolognese</td>
      <td>
        <jsp:getProperty name="form" property="anzahlSpaghetti" />
      </td>
    </tr>
    <tr>
      <td>Chicken Nuggets</td>
      <td>
        <jsp:getProperty name="form" property="anzahlChicken" />
      </td>
    </tr>
    <tr>
      <td>Pommes</td>
      <td>
        <jsp:getProperty name="form" property="anzahlPommes" />
      </td>
    </tr>
  </table>

  <a href="index.jsp"><button type="button">Best&auml;tigen</button></a>
  <a href="NeueBestellung.jsp"><button type="button">Bestellung wiederholen</button></a>
</body>

</html>
