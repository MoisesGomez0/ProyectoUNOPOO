<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game maker</title>
<script src="../jquery-3.4.1.min.js"></script>
<script src = "RandomGenerator.js"></script>
	<script src = "LinkedList.js"></script>
	<script src = "EValue.js"></script>
	<script src = "EColor.js"></script>
	<script src = "Card.js"></script>
	<script src = "Deck.js"></script>
	<script src = "DiscardPile.js"></script>
	<script src = "Player.js"></script>
	<script src = "Game.js"></script>
	<script src = "Hand.js"></script>
</head>
<body>
<% out.print(String.format("<input type='hidden' id='gameId' value='%s'>",request.getParameter("gameId"))); %>
<% out.print(String.format("<input type='hidden' id='name' value='%s'>",request.getParameter("name"))); %>
<script>
	var id = document.querySelector("#gameId").value;
	var name = document.querySelector("#name").value;
	$.get("getContent.jsp",{"file":`logIn\${id}.json`},function(data){
		data = JSON.parse(data.trim());
		playersList = data.players;
		
		var players = new LinkedList();
		
		for ( var index in data.players) {
			players.add(new Player(data.players[index]));
		}
		var game = new Game(id,players);
		game.generate();/**Genera la partida.*/
		console.log(game.toString());
		$.get("write.jsp",{"file":`game\${id}.json`,"content":game.toString(),"override":true},function(){
			window.location = `UNO.jsp?name=\${name}`;
		})
	});
	
</script>
</body>

</html>