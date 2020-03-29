<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="author" content="Moises">
    <meta name="description" content="Página de inicio del juego">
    <title>UNO</title>
    <link rel="stylesheet" type="text/css" href="../styles/index.css">
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="../scripts/index.js"></script>
    <script src="../scripts/LogInManager.js"></script>
    <script src="../scripts/CookiesManager.js"></script>
    <script src="../scripts/SoundManager.js"></script>
    <script src="../scripts/Animator.js"></script>
    <script src="../scripts/RandomGenerator.js"></script>
    

    <script>
        var intervalID;
        var indexManager = new IndexManager();
        var cookiesManager = new CookiesManager();
        var sm = new SoundManager();
    </script>
</head>
</head>
</head>

<body>
    <div class="font"><img id="backgroundImg" src="../images/Font.png"></div>
    <div id="unoImgContainer">
        <img id="unoImg" src="../images/UNO.png">
    </div>
    <button id="newGame" class="initBtn" onmouseover="sm.playError();" onclick="indexManager.showInputHost();">Nuevo
        Juego</button>
    <br>
    <button id="getInToGame" class="initBtn" onclick="indexManager.showInputGuest();">Entrar a juego Existente</button>
    <br>
    <button id="scoreTable" class="initBtn" onclick="indexManager.showStadistics();">Estadísicas de puntuación</button>

    <div id="backScreenHost" class="backScreen">
        <div id="hostPopUp" class="popUp">
            <h1>UNO</h1>
            <h2>Crear un Nuevo Juego</h2>
            <input type="text" placeholder="Escribe tu nombre" id="hostName">
            <br>
            <button class="formBtn" onclick="indexManager.verifyAndRedirectHost();">Crear Juego</button>
            <br>
            <button class="formBtn closeWindow" onclick="indexManager.hideInputHost();return false;">Cancelar</button>
        </div>
    </div>

    <div id="backScreenGuest" class="backScreen">
        <div id="guestPopUp" class="popUp">
            <h1>UNO</h1>
            <h2>Entrar en un juego</h2>
            <input id="guestName" type="text" placeholder="Escribe tu nombre">
            <br>
            <input id="gameId" type="text" placeholder="Código del juego">
            <br>
            <button onclick="indexManager.verifyAndRedirectGuest()" class="formBtn">Entrar en juego</button>
            <br>
            <button class="formBtn closeWindow" onclick="indexManager.hideInputGuest();">Cancelar</button>
        </div>
    </div>

    <div id="backScreenScore" class="backScreen">
        <div class="popUp" id="tablePopUp">
            <h1>Estadísticas</h1>
            <div id="table"></div>
            <button class="closeWindow" onclick="indexManager.hideStadistics();">Cerrar</button>
        </div>
    </div>

    <div id="errorBackScreen" class="backScreen">
        <div id="errorPop" class="popUp">
            <h1 id="errorMessage">Debes llenar los datos necesarios</h1>
            <button class="formBtn" onclick="indexManager.hideError();">Aceptar</button>
        </div>
    </div>

    <div id="loadingScreenHost" class="backScreen">
        <div id="loadingPop" class="popUp">
            <div class="message">
                <h1 class="message">Comparte este código con tus amigos</h1>
                <br>
                <h2 class="message" id="code"></h2>
                <br>
                <h2 class="message" id="loadingBox"></h2>
                <br>
                <h2 class="message">Estamos preparando todo</h2>
            </div>
        </div>
    </div>

    <div id="loadingScreenGuest" class="backScreen">
        <div id="loadingPop" class="popUp">
            <div class="message">
                <h1 class="message">Estamos preparando todo</h1>
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
        </div>
    </div>

</body>

</html>