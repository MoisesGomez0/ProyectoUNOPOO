<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="author" content="Moises">
    <meta name="description" content="Página de inicio del juego">
    
    <script src="RandomGenerator.js"></script>
    <script src="docAdmin.js"></script>
    <script src="../jquery-3.4.1.min.js"></script>
    
    <script>
        var gameId;
        var Nplayers;
        var playerName;
        var oponents = [];
        var doc = new DocAdmin();
        var intervalID;
        var random = new RandomGenerator();
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
        gameId = random.randomAlpha(4);
        doc.addContent("body", "<div id='nameInput'>Introduce tu nombre<br><input type='text' id='name'><button id='sendNameInput' onclick='verifyLogin();'>Aceptar</button></div>")
        doc.addContent("#nameInput",
        `<select id='NPlayers'>
        <option value=\${2}>2 Jugadores</option>
        <option value=3>3 Jugadores</option>
        <option value=4>4 jugadores</option>
        </select>`);
        doc.disableByID([true, "newGame", "getInToGame", "scoreTable"]);
    }

    /**Ejecutada cuando el usario ha introducido su nombre,
     * muestra el código que debe compartir,
     * Usa un microservicio y ajax para verificar que el jugador oponente ingreso en la partida.
    */
    function verifyLogin() {
        playerName = document.querySelector("#name").value;
        NPlayers = document.querySelector("#NPlayers").value;

        doc.removeByID("nameInput");
        doc.addContent("body", `<div id="gameID">Comparte este código con tu oponente<br>\${gameId}</div>`);

        var data = {"gameID":gameId,"players":[playerName],"NPlayers":NPlayers};

        $.get("write.jsp",{"file":"logIn.json","content":JSON.stringify(data),"override":false},null);

        intervalID = setInterval(function () {
            $.get("getContent.jsp", {"file":"logIn.json"}, function (data) {
                data = data.trim();
                data = JSON.parse(data);
                console.log(data)
                if (data.players.length != NPlayers) {
                    //El jugador no ha entrado
                    console.log("No han entrado los jugadores necesarios")
                } else {
                    //El jugador entró
                    console.log("entro")
                    window.location=`gameMaker.jsp?gameId=\${gameId}&name=\${playerName}`;
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
        playerName = document.querySelector("#name").value;
        gameId = document.querySelector("#gameID").value;
        $.get("getContent.jsp",{"file":"logIn.json"},function(data){
            data = data.trim();
            data = JSON.parse(data);
            if(data.gameID == gameId){
                data.players.push(playerName);
                NPlayers = data.NPlayers;
                $.get("write.jsp",{"file":"logIn.json","content":JSON.stringify(data),"override":true}, redirect);
            }else{
                doc.addContent("body","Ese es un ID de juego inválido");
            }

        });
    }
    /**Function encargada de redirigir a la pagina de juego*/
    var redirect = function(data){
        window.location = `lobby.jsp?name=\${playerName}`

    }
</script>

</html>