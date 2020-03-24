<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>UNO-Game</title>
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="../scripts/LogInManager.js"></script>
    <script src="../scripts/Animator.js"></script>
    <script src="../scripts/CookiesManager.js"></script>
    <script src="../scripts/index.js"></script>
    <link rel="stylesheet" type="text/css" href="../styles/lobby.css">
    <link rel="stylesheet" type="text/css" href="../styles/index.css">
</head>

<body>

    <div class="message">
        <h1 class="message" >Estamos preparando todo</h1>
        <br>
        <h2 class="message" id="loadingBox"></h2>
        <br>
        <h2 class="message">Tu oponente no esta listos.</h2>
    </div>

    <div id="errorBackScreen" class="backScreen">
        <div id="errorPop" class="popUp">
            <h1 id="errorMessage"></h1>
            <button class="formBtn" onclick="indexManager.hideError(); goBack();">Aceptar</button>
        </div>
    </div>

    <script>
        var goBack = function(){
            location = "index.jsp";
        }

        var indexManager = new IndexManager();
        var animator = new Animator();
        var cookiesManager = new CookiesManager();
        
        var name = cookiesManager.getCookie("name");
        var gameId = cookiesManager.getCookie("gameId");
        
        var logInManager = new LogInManager(name, 0, gameId);

        animator.writeMachine("Cargando...","#loadingBox");
        animator.splashText("#loadingBox");

        logInManager.getIn();



    </script>
</body>

</html>