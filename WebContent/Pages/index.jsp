<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="author" content="Moises">
    <meta name="description" content="Página de inicio del juego">
    <script src="Game.js"></script>
    <script src="RandomGenerator.js"></script>
    <script src="Player.js"></script>
    <script src="Oponent.js"></script>
    <script src="docAdmin.js"></script>
    <script src="../jquery-3.4.1.min.js"></script>
    <script>
        var game = new Game();
        var player = new Player();
        var oponent = new Oponent();
        var doc = new DocAdmin();
        var intervalID;
    </script>
</head>
</head>
</head>

<body>
    <button id="newGame" onclick="makeGame();">Nuevo Juego</button>
    <br>
    <button id="getInToGame" onclick="getInGame();">Entrar a juego Existente</button>
    <br>
    <button id="scoreTable">Tabla de score</button>
</body>
<script>
    /**Este script es para el jugador anfitrión*/

    /** Esta función inicia la rutina de creación de una partidad,
     * y establece el id de juego y del jugador, tambien inserta en la 
     * página elementos necesarios para que el jugador configure su nombre.
    */
    function makeGame() {
        game.setID(5);
        player.setID(5);
        doc.addContent("body", "<div id='nameInput'>Introduce tu nombre<br><input type='text' id='name'><button id='sendNameInput' onclick='verifyLogin();'>Aceptar</button></div>")
        doc.disableByID([true, "newGame", "getInToGame", "scoreTable"]);
    }

    /**Ejecutada cuando el usario ha introducido su nombre,
     * muestra el código que debe compartir,
     * Usa un microservicio y ajax para verificar que el jugador oponente ingreso en la partida.
    */
    function verifyLogin() {
        player.name = document.querySelector("#name").value
        doc.removeByID("nameInput");
        doc.addContent("body", `<div id="gameID">Comparte este código con tu oponente<br>\${game.ID}</div>`);
        $.get("logInMaker.jsp", { "gameID": game.ID, "hostPlayer": player.name, "guestPlayer": "null" }, null);

        intervalID = setInterval(function () {
            $.get("getContent.jsp", {"file":"logIn.json"}, function (data) {
                data = data.trim();
                data = JSON.parse(data);
                console.log(data)
                if (data.guestPlayer == "null" || data.guestPlayer == null) {
                    //El jugador no ha entrado
                    console.log("El jugador no ha entrado")
                } else {
                    //El jugador entró
                    oponent.name = data.guestPlayer;
                    window.location=`gameMaker.jsp?gameID=\${game.ID}`
                    clearInterval(intervalID);
                }
            });
        },
            1000);

    }


</script>

<script>
    /**Este script es para jugador invitado*/

    /**Esta función se ejecuta cual el usuario selecciona entrar como invitado a una partida.
     * Inserta contenido html para que introdusca su nombre y el id de la sesión.
     * cuando este ingresa los datos ejecuta la función logIn()
    */
    function getInGame(){
        doc.addContent("body", "<div id='nameIDInput'>Introduce tu nombre<br><input type='text' id='name'><br>Escribe el código que te compartieron<br><input type='text' id='gameID'><button id='sendNameInput' onclick='logIn();'>Aceptar</button></div>");
        doc.disableByID([true, "newGame", "getInToGame", "scoreTable"]);
    }

    /**
     * Se encarga de entrar en la partida
     * 
    */
    function logIn(){
        player.name = document.querySelector("#name").value;
        game.ID = document.querySelector("#gameID").value;
        $.get("getContent.jsp",{"file":"logIn.json"},function(data){
            data = data.trim();
            data = JSON.parse(data);
            if(data.gameID == game.ID){
                oponent.name = data.hostPlayer;
                $.get("logInMaker.jsp", { "gameID": game.ID, "hostPlayer": oponent.name, "guestPlayer": player.name }, redirect);
            }else{
                doc.addContent("body","Ese es un ID de juego inválido");
            }

        });
    }
    /**Function encargada de redirigir a la pagina de juego*/
    var redirect = function(data){
        window.location = `lobby.jsp?gameID=\${game.ID}`

    }
</script>

</html>