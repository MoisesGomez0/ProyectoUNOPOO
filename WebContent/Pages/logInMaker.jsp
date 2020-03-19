<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% out.print(String.format("<input type='hidden' value='%s' id='nPlayers'>",request.getParameter("nPlayers").toString().trim())); %>
    <% out.print(String.format("<input type='hidden' value='%s' id='name'>",request.getParameter("name").toString().trim())); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="decription" content="Pantalla donde se preparará el juego y espera de los otros jugadores">
<link rel="stylesheet" type="text/css" href="../styles/logInMaker.css">
<script src="../scripts/LogInManager.js"></script>
<script src="RandomGenerator.js"></script>
<script src="../jquery-3.4.1.min.js"></script>
<title>UNO</title>
</head>
<body>
    <form action="gameMaker.jsp" method="POST" id="dataForm" style="visibility: hidden;">
        <input type="text" name="name" id="formName">
        <input type="text" name="nPlayers" id="formNPlayers">
        <input type="text" name="gameId" id="gameId">
    </form>
    <div id="message">
    <h1>Estamos preparando todo</h1>
    <h2>Comparte este código con tus amigos</h2>
    <h2 id="code"></h2>
    </div>
</body>
    <script>
        var name = document.querySelector("#name").value;
        var nPlayers = parseInt(document.querySelector("#nPlayers").value);
        var logInMaker = new LogInManager(name,nPlayers);
    
        logInMaker.generateGameId();
        logInMaker.generateData();
        console.log(logInMaker.generateData());
        logInMaker.saveData();
        logInMaker.setDataToSubmit();
        
        document.querySelector("#code").innerHTML = logInMaker.gameId;
        
        logInMaker.verifyLoginAndRedirect();
        
    </script>
</html>