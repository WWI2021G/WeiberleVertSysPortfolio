<jsp:useBean class="beans.FormBean" id="form" scope="session" />
<jsp:setProperty name="form" property="*" />
<%-- Diese jsp existiert, um die auf der Bestellung.jsp-Seite eingegebenen Daten zu speichern --%>

<!DOCTYPE html>
<html lang="de">

<head>
  <title>In Bearbeitung</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%-- Nach dem die Seite fertig geladen hat, also alle Daten gespeichert wurden wird man zurück auf die index.jsp umgeleitet --%>
  <%-- Je nach Geschwindigkeit des Servers ist diese Seite überhaupt nicht sichtbar --%>
  <meta http-equiv="refresh" content="0.1; URL=index.jsp" />
</head>

</html>
