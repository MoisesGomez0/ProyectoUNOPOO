<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../jquery-3.4.1.min.js"></script>
<script src="docAdmin.js" ></script>
<script src="Game.js" ></script>
<script src="Oponent.js" ></script>
<script src="Player.js" ></script>
<script src="RandomGenerator.js" ></script>
<script>
    /** Instanciación de objetos necesarios para el juego */
    


</script>
<%out.print(String.format("<input type='hidden' value='%s' id='nameHidden'>",request.getParameter("name")));%>

</head>
<body onload="update(); printd(); goToBack();" >
<script>
    /**@type JSON objeto que almacena la información total del juego*/
    var data;
    var game = new Game();
    var oponent = new Oponent();
    var player = new Player();

    /**Trae la información de archivos al front y actualiza los objetos*/
   	function update(){
        $.get("getContent.jsp",{"file":"game.json"},upgrade);    
    }
    
    var upgrade = function(gameJSON) {
        data = JSON.parse(gameJSON.trim());
        game.ID = data.id;
        game.clockWise = data.clockWise;
        game.deck = data.deck;
        game.discardPile = data.discardPile;
        
        if(data.player1.name == document.querySelector("#nameHidden").value){
            player.name = data.player1.name;
            player.hand = data.player1.hand;
            player.ID = data.player1.id;

            oponent.name = data.player2.name;
            oponent.handLength = data.player2.hand.length;
            oponent.handLength = data.player2.id;

        }else if(data.player2.name == document.querySelector("#nameHidden").value){
            player.name = data.player2.name;
            player.hand = data.player2.hand;
            player.ID = data.player2.id;

            oponent.name = data.player1.name;
            oponent.handLength = data.player1.hand.length;
            oponent.handLength = data.player1.id;
        }


    }
    function printd(){
        console.log(game);
        console.log(player);
        console.log(oponent);
    }

    function goToBack(){
    }

</script>
</body>
</html>