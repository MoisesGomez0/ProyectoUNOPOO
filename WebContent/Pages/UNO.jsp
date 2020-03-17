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
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
        div#oponentCards {
            position: fixed;
            top: 0;
            left: 20vw;
            height: 10vh;
            width: 60vw;
            background-color: aqua;
        }

        div#hand {
            position: fixed;
            bottom: 0;
            left: 20vw;
            height: 20vh;
            width: 60vw;
            background-color: aqua;
        }

        div#deck {
            position: fixed;
            bottom: 30vh;
            height: 50vh;
            left: 25vw;
            width: 20vw;
            background-color: aqua;
        }

        div#discardPile {
            position: fixed;
            bottom: 30vh;
            height: 50vh;
            left: 55vw;
            width: 20vw;
            background-color: aqua;
        }

        div.card {
            position: fixed;
            height: 15vh;
            width: 10hw;
            background-color: bisque;
        }
    </style>
    <%out.print(String.format("<input type='hidden' value='%s' id='nameHidden'>",request.getParameter("name")));%>

</head>

<body>
    <div id="oponentCards"></div>
    <div id="deck">
        <div style="background-color: red; position: fixed; bottom: 30vh; height: 50vh; left: 25vw; width: 20vw;">
            Card
        </div>
    </div>
    <div id="discardPile"></div>
    <div id="hand"></div>

    <script>
    var game = new game();
    
    function update(){
    	$.get("getContent.jsp",{"file":"game.json"},function(data){
    		data = JSON(data.trim());
    		game.parse(data);
    	});
   	}
    	
    </script>
</body>

</html>