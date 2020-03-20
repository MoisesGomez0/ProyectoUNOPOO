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
    <script src="../scripts/CookiesManager.js"></script>
    
    <script>
        var intervalID;
        var indexManager = new IndexManager();
        var cookiesManager = new CookiesManager();
    </script>
</head>
</head>
</head>

<body>
    <div id="unoImgContainer">
        <img id="unoImg" src="../images/UNO.jpg">
    </div>
    <button id="newGame" class="initBtn" onclick="indexManager.showInputHost()">Nuevo Juego</button>
    <br>
    <button id="getInToGame" class="initBtn" onclick="indexManager.showInputGuest()">Entrar a juego Existente</button>
    <br>
    <button id="scoreTable" class="initBtn" >Estadísicas de puntuación</button>

    <div id="backScreenHost" class="backScreen">
        <div id="hostPopUp" class="popUp">
                <h1>UNO</h1>
                <h2>Crear un Nuevo Juego</h2>
                <input type="text" placeholder="Escribe tu nombre" id="hostName">
                <br>
                <button class="formBtn" onclick="indexManager.verifyAndRedirectHost();">Crear Juego</button>
                <br>
                <button class="formBtn" onclick="indexManager.hideInputHost();return false;">Cancelar</button>
        </div>
    </div>

    <div id="backScreenGuest" class="backScreen">
        <div id="guestPopUp" class="popUp">
            <h1>UNO</h1>
            <h2>Entrar en un juego</h2>
            <form action="lobby.jsp" method="POST">
            <input id="guestName" type="text" placeholder="Escribe tu nombre">
            <br>
            <input id="gameId" type="text" placeholder="Código del juego">
            <br>
            <button onclick="indexManager.verifyAndRedirectGuest()" class="formBtn">Entrar en juego</button>
            <br>
            <button class="formBtn" onclick="indexManager.hideInputGuest();return false;">Cancelar</button>
            </form>
        </div>
    </div>

    <div id="errorBackScreen" class="backScreen">
        <div id="errorPop" class="popUp">
            <h1 id="errorMessage"></h1>
            <button class="formBtn" onclick="indexManager.hideError();">Aceptar</button>
        </div>
    </div>

</body>
</html>