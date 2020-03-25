<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="decription" content="Pantalla donde se preparará el juego y espera de los otros jugadores">
    <link rel="stylesheet" type="text/css" href="../styles/logInMaker.css">
    <script src="../scripts/LogInManager.js"></script>
    <script src="../scripts/CookiesManager.js"></script>
    <script src="../scripts/RandomGenerator.js"></script>
    <script src="../scripts/Animator.js"></script>
    <script src="../jquery-3.4.1.min.js"></script>
    <title>UNO</title>
</head>

<body>

    
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
    var cookiesManager = new CookiesManager();

    var name = cookiesManager.getCookie("name");
    nPlayers = 2;
    var logInMaker = new LogInManager(name,nPlayers);
    var animator = new Animator();
    
    animator.writeMachine("Cargando...", "#loadingBox");
    animator.splashText("#loadingBox");

    logInMaker.generateGameId();
    logInMaker.generateData();

    document.querySelector("#code").innerHTML = logInMaker.gameId;

    logInMaker.saveData();
    
    logInMaker.verifyLoginAndRedirec();

</script>

</html>