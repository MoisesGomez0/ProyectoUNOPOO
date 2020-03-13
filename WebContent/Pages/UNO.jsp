<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="docAdmin.js"></script>
    <script src="Game.js"></script>
    <script src="Oponent.js"></script>
    <script src="Player.js"></script>
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

<body onload="update();">
    <div id="oponentCards"></div>
    <div id="deck" onclick="pick();">
        <div style="background-color: red; position: fixed; bottom: 30vh; height: 50vh; left: 25vw; width: 20vw;">
            Card
        </div>
    </div>
    <div id="discardPile"></div>
    <div id="hand"></div>

    <script>
        /**@type JSON objeto que almacena la información total del juego*/
        var data;
        var game = new Game();
        var oponent = new Oponent();
        var player = new Player();
        var doc = new DocAdmin();

        /**Trae la información de archivos al front y actualiza los objetos*/
        function update() {
            $.get("getContent.jsp", { "file": "game.json" }, upgrade);
        }

        var upgrade = function (gameJSON) {
            data = JSON.parse(gameJSON.trim());
            game.ID = data.id;
            game.clockWise = data.clockWise;
            game.deck = data.deck;
            game.discardPile = data.discardPile;

            if (data.player1.name == document.querySelector("#nameHidden").value) {
                player.name = data.player1.name;
                player.hand = data.player1.hand;
                player.ID = data.player1.id;

                oponent.name = data.player2.name;
                oponent.handLength = data.player2.hand.length;
                oponent.ID = data.player2.id;

            } else if (data.player2.name == document.querySelector("#nameHidden").value) {
                player.name = data.player2.name;
                player.hand = data.player2.hand;
                player.ID = data.player2.id;

                oponent.name = data.player1.name;
                oponent.handLength = data.player1.hand.length;
                oponent.ID = data.player1.id;

            }
            upgradeCards();


        }
        function upgradeCards() {
            hand.innerHTML = "";
            oponentCards.innerHTML = "";
            var pos = 60 / player.hand.length
            for (let i = 0; i < player.hand.length; i++) {
                hand.innerHTML += `<div style="background-color:red; border:1px solid black; position: fixed; width: 12.98vw; height: 20vh; left:\${20+i*pos}vw ; bottom: 0;"  id="\${player.hand[i]}" onclick="drop(this);">\${player.hand[i]}</div>`
            }
            pos = 60 / oponent.handLength;
            console.log("El handLength", oponent.handLength);
            for (let i = 0; i < oponent.handLength; i++) {
                console.log("agregue uno");
                oponentCards.innerHTML += `<div style="background-color:red; border:1px solid black; position: fixed; width: 8vw; height: 10vh; left:\${20+i*pos}vw ; top: 0;">\Card</div>`
            }
            discardPile.innerHTML = `<div style="background-color: red; border:1px solid black; position: fixed; bottom: 30vh; height: 50vh; left: 55vw; width: 20vw;">\${game.discardPile[ game.discardPile.length - 1]}</div>`

        }

        function drop(element) {
            var discard = game.discardPile[ game.discardPile.length - 1 ].split("_");
            var card = element.id.split("_");
            if (discard[0] == card[0] || discard[1] == card[1]) {
                doc.removeByID(element.id);
                game.discardPile.push(element.id)
                discardPile.innerHTML = element.innerHTML;
                player.dropCard(element.id);
                $.get("action.jsp", { "action": "drop", "playerID": player.ID, "card": element.id }, null);
                return element.id
            }
        }
        function pick(element) {
            console.log("la cantidad de cartas antes del poop", game.deck.length);
            var card = game.deck.pop();
            console.log("la cantidad de cartas despues del poop", game.deck.length);
            console.log(card);
            player.hand.push(card);
            upgradeCards();
        }
    </script>
</body>

</html>