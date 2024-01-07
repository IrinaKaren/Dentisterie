<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String idclient = request.getParameter("idclient");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Entrée votre argent</title>
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
            padding: 100px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 15.1);
            text-align: center; 
        }

        label {
            font-family: 'Cursive', sans-serif;
            font-size: 20px;
            display: block;
            margin-bottom: 8px;
            color: #333;
            text-align: left;
        }

        input, select {
            width: 100%; 
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            border: none;
            padding: 12px;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        input[type="submit"]:last-child {
            font-family: 'Cursive', sans-serif;
            font-size: 20px;
            background-color: #dc3545;
        }

        input[type="submit"]:last-child:hover {
            background-color: #bd2130;
        }
    </style>
</head>
<body>

    <form action="imgdent.jsp">
        <input type="hidden" name="idclient" value="<%=idclient%>">
        <label for="numeroDents">Entrée votre argent:</label>
        <input type="number" name="argent" required>

        <input type="submit" value="Suivant">
    </form>

</body>
</html>
