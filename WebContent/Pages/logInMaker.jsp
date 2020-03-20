<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<% out.print(String.format("<input type='hidden' value='%s' id='nPlayers'>",request.getParameter("nPlayers").toString().trim())); %>
<% out.print(String.format("<input type='hidden' value='%s' id='name'>",request.getParameter("name").toString().trim())); %>

<head>
    <meta charset="UTF-8">
    <meta name="decription" content="Pantalla donde se preparará el juego y espera de los otros jugadores">
    <link rel="stylesheet" type="text/css" href="../styles/logInMaker.css">
    <script src="../scripts/LogInManager.js"></script>
    <script src="RandomGenerator.js"></script>
    <script src="../scripts/Animator.js"></script>
    <script src="../jquery-3.4.1.min.js"></script>
    <title>UNO</title>
</head>

<body>
    <form action="gameMaker.jsp" method="POST" id="dataForm" style="visibility: hidden;">
        <input type="text" name="name" id="formName">
        <input type="text" name="nPlayers" id="formNPlayers">
        <input type="text" name="gameId" id="gameId">
    </form>
    
    <div class="message">
        <h1 class="message">Comparte este código con tus amigos</h1>
        <br>
        <h2 class="message" id="code"></h2>
        <br>
        <h2 class="message" id="loadingBox"></h2>
        <br>
        <h2 class="message">Estamos preparando todo</h2>
    </div>
</body>
<script>
    
    var name = document.querySelector("#name").value;
    var nPlayers = parseInt(document.querySelector("#nPlayers").value);
    var logInMaker = new LogInManager(name,nPlayers);
    var animator = new Animator();
    
    animator.writeMachine("Cargando...", "#loadingBox");
    animator.splashText("#loadingBox");

    logInMaker.generateGameId();
    logInMaker.generateData();

    console.log(logInMaker.generateData());
    logInMaker.saveData();
    logInMaker.setDataToSubmit();
    
    document.querySelector("#code").innerHTML = logInMaker.gameId;
    
    logInMaker.verifyLoginAndRedirect();

</script>

</html>