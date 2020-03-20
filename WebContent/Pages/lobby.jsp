<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<% out.print(String.format("<input type='hidden' id='name' value='%s'>",request.getParameter("name").toString().trim())); %>
<%out.print(String.format("<input type='hidden' id='gameId' value='%s'>",request.getParameter("gameId").toString().trim()));%>

<head>
    <meta charset="UTF-8">
    <title>UNO-Game</title>
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="../scripts/LogInManager.js"></script>
    <script src="../scripts/Animator.js"></script>
    <link rel="stylesheet" type="text/css" href="../styles/lobby.css">
</head>

<body>
    <form action="UNO.jsp" method="POST" id="dataForm" style="visibility: hidden;">
        <input type="text" name="name" id="formName">
        <input type="text" name="nPlayers" id="formNPlayers">
        <input type="text" name="gameId" id="gameId">
    </form>

    <div class="message">
        <h1 class="message" >Estamos preparando todo</h1>
        <br>
        <h2 class="message" id="loadingBox"></h2>
        <br>
        <h2 class="message">Tus oponentes no estan listos.</h2>
    </div>


    <script>

        var playerName = document.querySelector("#name").value;
        var gameId = document.querySelector("#gameId").value;
        var animator = new Animator();
        var logInManager = new LogInManager(playerName, 0, gameId);

        animator.writeMachine("Cargando...","#loadingBox");
        animator.splashText("#loadingBox");

        logInManager.getIn();

        logInManager.verifyLoginAndRedirect();



    </script>
</body>

</html>