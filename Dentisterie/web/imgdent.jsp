<%@page import="model.Dents"%>
<%@page import="java.util.List"%>
<%@page import="model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String p = String.valueOf(request.getAttribute("idclient"));
    String idclient = request.getParameter("idclient");
    String argent = request.getParameter("argent");
    List<Dents> etats = Dents.getAllEtat();
    List<Dents> situation = Dents.getSituation(Integer.parseInt(idclient));
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dents</title>
    <style>
        .container {
            position: relative;
            margin: auto;
            width: 400px;
            height: 21vh;
        }
        .image {
            display: block;
            width: 100%;
            height: auto;
        }
        .text-overlay {
            position: absolute;
            color: black;
            font-size: 24px;
            font-weight: initial;
            font-family: fantasy;
        }
    </style>
</head>
<body>
    <div class="container">
<!--        nify ambany-->
        <img class="image" src="da.jpg" alt="Your Image">
        <div class="text-overlay" id="1" style="top: 336px;left: 310px;"><%=situation.get(0).getId_etat()%></div> 
        <div class="text-overlay" id="2" style="top: 387px;left: 305px;"><%=situation.get(1).getId_etat()%></div> 
        <div class="text-overlay" id="3" style="top: 438px;left: 293px;"><%=situation.get(2).getId_etat()%></div>
        <div class="text-overlay" id="4" style="top: 480px;left: 285px;"><%=situation.get(3).getId_etat()%></div>
        <div class="text-overlay" id="5" style="top: 509px;left: 271px;"><%=situation.get(4).getId_etat()%></div>
        <div class="text-overlay" id="6" style="top: 535px;left: 253px;"><%=situation.get(5).getId_etat()%></div>
        <div class="text-overlay" id="7" style="top: 550px;left: 228px;"><%=situation.get(6).getId_etat()%></div>
        <div class="text-overlay" id="8" style="top: 557px;left: 204px;"><%=situation.get(7).getId_etat()%></div>
        <div class="text-overlay" id="9" style="top: 557px;left: 180px;"><%=situation.get(8).getId_etat()%></div>
        <div class="text-overlay" id="10" style="top: 551px;left: 157px;"><%=situation.get(9).getId_etat()%></div>
        <div class="text-overlay" id="11" style="top: 541px;left: 133px;"><%=situation.get(10).getId_etat()%></div>
        <div class="text-overlay" id="12" style="top: 511px;left: 114px;"><%=situation.get(11).getId_etat()%></div>
        <div class="text-overlay" id="13" style="top: 480px;left: 100px;"><%=situation.get(12).getId_etat()%></div>
        <div class="text-overlay" id="14" style="top: 442px;left: 88px;"><%=situation.get(13).getId_etat()%></div>
        <div class="text-overlay" id="15" style="top: 389px;left: 77px;"><%=situation.get(14).getId_etat()%></div>
        <div class="text-overlay" id="16" style="top: 336px;left: 70px;"><%=situation.get(15).getId_etat()%></div>
        
<!--        nify ambony-->
        <div class="text-overlay" id="21" style="top: 257px;left: 310px;"><%=situation.get(16).getId_etat()%></div> 
        <div class="text-overlay" id="22" style="top: 210px;left: 310px;"><%=situation.get(17).getId_etat()%></div>
        <div class="text-overlay" id="23" style="top: 165px;left: 301px;"><%=situation.get(18).getId_etat()%></div>
        <div class="text-overlay" id="24" style="top: 125px;left: 291px;"><%=situation.get(19).getId_etat()%></div>
        <div class="text-overlay" id="25" style="top: 95px;left: 277px;"><%=situation.get(20).getId_etat()%></div>
        <div class="text-overlay" id="26" style="top: 71px;left: 263px;"><%=situation.get(21).getId_etat()%></div>
        <div class="text-overlay" id="27" style="top: 45px;left: 243px;"><%=situation.get(22).getId_etat()%></div>
        <div class="text-overlay" id="28" style="top: 30px;left: 212px;"><%=situation.get(23).getId_etat()%></div>
        <div class="text-overlay" id="29" style="top: 30px;left: 173px;"><%=situation.get(24).getId_etat()%></div>
        <div class="text-overlay" id="30" style="top: 45px;left: 143px;"><%=situation.get(25).getId_etat()%></div>
        <div class="text-overlay" id="31" style="top: 71px;left: 124px;"><%=situation.get(26).getId_etat()%></div>
        <div class="text-overlay" id="32" style="top: 95px;left: 107px;"><%=situation.get(27).getId_etat()%></div>
        <div class="text-overlay" id="33" style="top: 125px;left: 93px;"><%=situation.get(28).getId_etat()%></div>
        <div class="text-overlay" id="34" style="top: 165px;left: 83px;"><%=situation.get(29).getId_etat()%></div>
        <div class="text-overlay" id="35" style="top: 210px;left: 75px;"><%=situation.get(30).getId_etat()%></div>
        <div class="text-overlay" id="36" style="top: 257px;left: 70px;"><%=situation.get(31).getId_etat()%></div>
    </div>

    <div>
        <form id="updateNumbersForm">
            <!-- Input fields for tooth number and damage level -->
            <input name="tooth_numero" id="toothNumeroInput" type="number" min="1" max="36" placeholder="numero" required />
            <select name="damage_level" id="damageLevelInput" required>
                <% for(int i = 0; i<etats.size(); i++){ %>
                    <option value="<%=etats.get(i).getId()%>"><%=etats.get(i).getEtat()%></option>
                <% } %>
            </select>
            <!-- Button to trigger the update -->
            <button type="submit">Update Number</button>
            <input type="hidden" name="argent" value="<%= argent %>" />
        </form>
    </div>
    
    <div style="margin: auto;">
        <!--<form onsubmit="sendUpdatedNumbers()">-->          
            <select name="priorite" id="priorite" required>
                <option value="sanitaire">Sanitaire</option>
                <option value="beaute">Beaute</option>
            </select><br/>
            <button onclick="sendUpdatedNumbers()">VALIDATE</button>
        <!--</form>-->
    </div>
    <script>
        // Get the form element
        const form = document.getElementById('updateNumbersForm');

        // Handle form submission
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent default form submission

            // Get tooth number and damage level values from the form inputs
            const toothNumero = document.getElementById('toothNumeroInput').value;
            const damageLevel = document.getElementById('damageLevelInput').value;

            // Update the corresponding number element based on tooth number
            // Replace `num1`, `num2`, etc., with actual IDs corresponding to the tooth numbers
            const correspondingNumElement = document.getElementById('' + toothNumero);
            
            // If the element exists, update its content with the new damage level value
            if (correspondingNumElement) {
                correspondingNumElement.textContent = damageLevel;
            }
        });
        
        function sendUpdatedNumbers() {
            // Get all text overlay elements
            const textOverlayElements = document.querySelectorAll('.text-overlay');
            const updatedTeeth = {};

            // Iterate through text overlay elements and store updated values
            textOverlayElements.forEach((element, index) => {
                const toothId = element.id;
                const damageLevel = element.textContent;
                updatedTeeth["teeth["+index+"]"] = toothId+","+damageLevel;
            });

            // Create a form and append input fields with updated values
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'DentController'; 

            // Add input fields with updated values to the form
            Object.entries(updatedTeeth).forEach(([key, value]) => {
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'dents';
                input.value = value;
                form.appendChild(input);
            });
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'idclient';
            input.value = '<%= idclient %>';
            form.appendChild(input);
            const argent = document.createElement('input');
            argent.type = 'hidden';
            argent.name = 'argent';
            argent.value = <%= argent %>;
            form.appendChild(argent);
//            alert(JSON.stringify(updatedTeeth)  );
            // Append the form to the document body and submit it
            document.body.appendChild(form);
            const selectedpriorite = document.getElementById("priorite");
            const inp = document.createElement('input');
            inp.type = 'hidden';
            inp.name = 'priorite';
            inp.value = selectedpriorite.value;
            form.appendChild(inp);        
            form.submit();
        }
        
    </script>
</body>
</html>
