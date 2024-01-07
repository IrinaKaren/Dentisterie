<%@page import="model.Operation"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Operation> listdents = (List<Operation>)request.getAttribute("listdents");
    double cout_total = (double)request.getAttribute("cout_total");
    double reste = (double)request.getAttribute("reste");
    double argent = (double)request.getAttribute("argent");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <style>
        body {
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            position: relative;
            margin: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .content {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 20.15);
            width: 80%;
            padding: 20px;
            margin-top: 210px;
            overflow: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            font-family: 'Cursive', sans-serif;
            font-size: 18px;
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #dc3545;
            color: #fff;
        }

        h2 {
            font-family: 'Cursive', sans-serif;
            font-size: 18px;
            color: #333;
/*            text-align: center;*/
            margin-bottom: 20px;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            text-decoration: none;
            background-color: chocolate;
            color: #fff;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: rgb(203, 117, 55);
        }
    </style>
</head>
<body>
    <div class="content">
        <table>
            <thead>
                <tr>
                    <th>Dents</th>
                    <th>Date</th>
                    <th>Numéro</th>
                    <th>Etat</th>
                    <th>Type</th>
                    <th>Cout</th>
                </tr>
            </thead>
            <tbody>
                <% for (int i = 0; i < listdents.size(); i++) { %>
                    <tr>
                        <td></td>
                        <td><%=listdents.get(i).getDate_rdv()%></td>
                        <td><%=listdents.get(i).getNumeroDent()%></td>
                        <td><%=listdents.get(i).getId_etat()%></td>
                        <td><%=listdents.get(i).getType()%></td>
                        <td><%=listdents.get(i).getCout()%></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <h2>Budget : <%=argent%></h2>
        <h2>Cout Total : <%=cout_total%></h2>
        <h2>Reste : <%=reste%></h2>
    </div>
</body>
</html>
