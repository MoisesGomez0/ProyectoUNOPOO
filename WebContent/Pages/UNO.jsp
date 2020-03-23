<%@page import="clases.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="docAdmin.js"></script>
	<script src = "LinkedList.js"></script>
	<script src = "EValue.js"></script>
	<script src = "EColor.js"></script>
	<script src = "Card.js"></script>
	<script src = "Deck.js"></script>
	<script src = "DiscardPile.js"></script>
	<script src = "Player.js"></script>
	<script src = "Game.js"></script>
	<script src = "Hand.js"></script>
	<script src="Div.js"></script>
    <script src="../scripts/DataManager.js"></script>
    <script src="../scripts/UNO.js"></script>
    <script src="../scripts/CookiesManager.js"></script>
    <link rel="stylesheet" type="text/css" href="../styles/UNO.css">



</head>

<body>
    <div id="oponentCards"></div>
    <div id="deck" onclick="playerTakeCard();"> <img id="deck" src="../images/UNO.png"></div>
    <div id="discardPile" ></div>
    <div id="hand"></div>

</body>
    <script>
        var cookiesManager = new CookiesManager();
        var name = cookiesManager.getCookie("name");
        var info = {};
        var frontManager = new FrontManager(info);
        var dataManager = new DataManager();
        var idSetIntervalUpdate = setInterval(function(){
            dataManager.update();
        },1000)
           	
    </script>

</html>