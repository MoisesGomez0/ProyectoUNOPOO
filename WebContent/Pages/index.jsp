<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="author" content="MAL">
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
    <button id="newGame" class="initBtn" onmouseover="sm.playButton();" onclick="indexManager.showInputHost();sm.playPressButton();">Nuevo Juego</button>
    <br>
    <button id="getInToGame" class="initBtn" onmouseover="sm.playButton();" onclick="indexManager.showInputGuest();sm.playPressButton();">Entrar a juego Existente</button>
    <br>
    <button id="scoreTable" class="initBtn" onmouseover="sm.playButton();" onclick="indexManager.showStadistics();sm.playPressButton();">Estadísicas de puntuación</button>
    <br>
    <button id="credits" class="initBtn" onmouseover="sm.playButton();" onclick="sm.playPressButton();creditsScreen.classList.add('active');">Créditos</button>

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

    <div id="creditsScreen" class="backScreen">
        <div id="creditsPop" class="popUp" >
            <h1>Créditos</h1>
            <h2>Desarrolladores</h2>
            <p>Ana Hernández</p>
            <p>Leonardo Mass</p>
            <p>Moisés Gómez</p>
            <h2>Recursos de Audio</h2>
            <p><a href="https:\\www.zapsplat.com">ZapSplat.com</a></p>
            <br>
            <hr>
            <h6>UNAH-IS I PAC 2020</h6>
            <h6><a href="mailto:gmoises926@gmail.com">Contactar</a></h6>
            <button onclick="creditsScreen.classList.remove('active')" >Cerrar</button>
        </div>
    </div>

</body>

</html>