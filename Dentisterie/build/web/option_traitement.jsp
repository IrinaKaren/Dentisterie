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
    <title>Option</title>
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
            background-color:  #1E90FF;
            padding: 100px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(23, 23, 23, 20.1);
            text-align: center; 
        }

        .btn {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            background-color: #dc3545;
            color: #fff;
            font-family: 'Cursive', sans-serif;
            font-size: 20px;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            display: block; /* or inline-block */
            text-decoration: none;
        }


        .btn:hover {
            background-color: #bd2130;
        }
    </style>
</head>
<body>

    <form>
        <a class="btn" href="TraitementController?option=sanitaire&idclient=<%=idclient%>&argent=<%=argent%>">Sanitaire</a>
        <a class="btn" href="TraitementController?option=beaute&idclient=<%=idclient%>&argent=<%=argent%>">Beaute</a>
    </form>

</body>
</html>



