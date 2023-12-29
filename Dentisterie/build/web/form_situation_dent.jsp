<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String idclient = (String)request.getAttribute("idclient");
    String argent = (String)request.getAttribute("argent");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Situation dentaire</title>
    <style>
        body {
            font-family: 'Cursive', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 70px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 10.15);
            text-align: center; 
        }

        input, select, a, .add-button {
            width: calc(48% - 10px);
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            display: inline-block;
        }

        input[type="submit"], a, .add-button {
            font-family: 'Cursive', sans-serif;
            font-size: 18px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
        }

        .add-button {
            background-color: #dc3545;
            border-radius: 4px;
        }

        .add-button:hover {
            background-color: #bd2130;
        }

        .added-inputs {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .added-inputs input, .added-inputs select {
            width: calc(48% - 10px);
        }
    </style>
</head>

<script>
    function addInputFields() {
        var container = document.getElementById('added-inputs-container');
        var newInputs = container.cloneNode(true);

        var inputs = newInputs.getElementsByTagName('input');
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].value = '';
        }

        container.parentNode.insertBefore(newInputs, container.nextSibling);
    }
</script>

<body>
    <form action="FormSituationDentController" method="post">
        <p>Mettez votre situation dentaire ici:</p>
        <input type="hidden" name="idclient" value="<%=idclient%>">
        <input type="hidden" name="argent" value="<%=argent%>">
        <div id="added-inputs-container" class="added-inputs">
            <input type="number" name="numero_dent" placeholder="Numéro dent" required>
            <select id="type" name="degat">
                <option value="remplacement">Abimée</option>
                <option value="traitement">Cassée</option>
                <option value="traitement">Cari</option>
            </select>
            <select id="type" name="type">
                <option value="remplacement">Remplacement</option>
                <option value="traitement">Traitement</option>
            </select>
        </div>

        <button type="button" class="add-button" onclick="addInputFields()">+</button>
        <input type="submit" value="Valider">
    </form>
</body>
</html>