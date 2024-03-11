<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des clients</title>
    <style>
        .formList {
            display: flex;
            flex-direction: row;
            justify-content: center;
            gap: 20px;
            align-items: center;
        }

        form {
            display: flex;
            flex-direction: column;
            padding: 16px;
            border: solid black;
            border-radius: 20px;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<%
    String clientList = request.getAttribute("clients").toString();
    int nbClients = (int) request.getAttribute("nbClients");
%>
<div class="formList">
    <form method="post" action="${pageContext.request.contextPath}/action">
        <h2>Enregistrer un nouveau client :</h2>
        <label for="name">Nom du client à enregistrer :</label>
        <input type="text" id="name" name="name">
        <label for="city">Ville du client à enregistrer :</label>
        <input type="text" id="city" name="city">
        <input type="hidden" id="function" name="function" value="registerClient">
        <input type="submit" value="Enregistrer le client">
    </form>
    <form method="post" action="${pageContext.request.contextPath}/action">
        <h2>Rechercher un client par :</h2>
        <label for="oidClient">Identifiant :</label>
        <input type="text" id="oidClient" name="oidClient">
        <label for="city">Ville :</label>
        <input type="text" id="city" name="city">
        <input type="hidden" id="function" name="function" value="findClientByCity">
        <input type="submit" value="Rechercher">
    </form>
    <form method="post" action="${pageContext.request.contextPath}/action">
        <input type="hidden" id="function" name="function" value="cleanFilters">
        <input type="submit" value="Effacer les filtres">
    </form>
</div>
<br><br>
<table>
    <caption>
        <%="Affichage de " + nbClients + " client(s)."%>
    </caption>
    <tr>
        <th>Identifiant - Nom - Ville</th>
    </tr>
    <%=clientList%>
</table>
</body>
</html>
