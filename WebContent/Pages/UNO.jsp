<%@page import="clases.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="../scripts/DataManager.js"></script>
    <script src="../scripts/UNO.js"></script>
    <script src="../scripts/CookiesManager.js"></script>
    <link rel="stylesheet" type="text/css" href="../styles/UNO.css">



</head>

<body>
    <div id="oponentCards"></div>
    <div id="deck" onclick="am.playerTakeCard();"> <img id="deck" src="../images/UNO.png"></div>
    <div id="discardPile" ></div>
    <div id="hand"></div>

    <div id="backScreenColor" class="backScreen">
        <div id="hostPopUp" class="popUp">
                <h1>UNO</h1>
                <h2>Elige el color</h2>
                <button id="RED" class="redBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                <button id="GREEN" class="greenBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                <br>
                <button id="BLUE" class="blueBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                <button id="YELLOW" class="yellowBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                
        </div>
    </div>

    <div id="backScreenDecision" class="backScreen">
        <div id="hostPopUp" class="popUp">
                <h1>UNO</h1>
                <h2>¿Retás a tu oponente?</h2>
                <button id="YES" class="decisionBtn" onclick="am.chooseDecision(this.id)">Si</button>
                <button id="NO" class="decisionBtn" onclick="am.chooseDecision(this.id)">NO</button>
        </div>
    </div>
    
    <div style="position:fixed; left:0; bottom:0;">
    	<button onclick="am.playerPressUNO()">UNO</button>
    </div>


</body>
    <script>
    	var lastOnDrop = "init";
    	var am = new ActionManager();
        var cookiesManager = new CookiesManager();
        var name = cookiesManager.getCookie("name");
        var info = {};
        var frontManager = new FrontManager(info);
        var dataManager = new DataManager();
        var idSetIntervalUpdate = setInterval(function(){
            dataManager.update();
        },500)
           	
    </script>

</html>