<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="version" content="0.1.22">
<meta name="autor" content="@gmoises926">
<title>Prototipo</title>
<script>
    function RandomGenerator(){
    this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    this.randomASCII = function(length){
        var result = "";        
        for(let i=0; i<length; i++){
            result += this.alphabet[this.randomInt(0,this.alphabet.length)];
        }
        return result;
    };
    this.randomInt = function(min,max){
        return parseInt(Math.random()*(max-min) + min)
    }
}
</script>
<script src = "../jquery-3.4.1.min.js"></script>
</head>
<body>
    <button id="newGame" onclick="makeGame();">Nuevo Juego</button>
    <br>
    <button id="getInToGame" onclick="getInGame();">Entrar a juego Existente</button>
    <br>
    <button id="scoreTable">Tabla de score</button>
    
    
</body>
<script>
    //Valores necesarios para instaciar un juego #SC
    var playerName;
    var adversaryName;
    var playerID;
    var randomGenerator = new RandomGenerator();
    var gameID;
    var verifyLoginID;

    //Esta funcion genera el id del jugador y llama a la función getNameAsVisitor, función de visitante.
    function getInGame(){
        playerID = randomGenerator.randomASCII(10);
        getNameAsVisitor();
    }

    //Esta función inserta el html donde el usuario ingresa su nombre, deshabilita los botones principales y en boton que ejecuta la función getGameID(), función de visitante.
    function getNameAsVisitor() {
        document.querySelector("body").innerHTML += 
        "<div id='nameInput'>Introduce tu nombre<br><input type='text' id='name'><button id='sendNameInput' onclick=' getGameID();'>Aceptar</button></div>";
        disableByID([true,"newGame","getInToGame","scoreTable"]);
    }
    
    //Esta función establece el nombre del usuario en los atributos y remueve el html insertado para que el usuario ingrese su nombre, luego inserta el html para que el usuario ingrese el id de la partida con ello el boton que ejecuta el metodo setIDGame() y deshabilita los botones principales, función de visitante, función de visitante.
    function getGameID() {
        playerName = document.querySelector("#name").value;
        removeNodeByID("nameInput");
        document.querySelector("body").innerHTML += 
        "<div id='gameIDInput'>Introduce el ID del juego<br><input type='text' id='IDGame'><button id='sendIDInput' onclick=' setIDGame();'>Aceptar</button></div>";
        disableByID([true,"newGame","getInToGame","scoreTable"]);
    }

    //Valida que el ID ingresado por el usuario sea igual al creado por el visitante, si esto es cierto modifica el archivo .json del juego para que ambos jugadores se conecten, de no ser asi pide el id de nuevo.
    var getInGameJSON = function (data) {
        data = JSON.parse(data);
        data.j2 = playerName;
        if(data.ID != gameID){
            document.querySelector("body").innerHTML += "Ese es un ID invalido";
            document.querySelector("body").innerHTML += 
            "<div id='gameIDInput'>Introduce el ID del juego<br><input type='text' id='IDGame'><button id='sendIDInput' onclick=' setIDGame();'>Aceptar</button></div>";
        }else{
            document.querySelector("body").innerHTML = "Se entro en la partida";
            $.get("GameCreator.jsp",data,null);
        }
    }

    //Establece el idGame ingresado por el usuario en los atributos, y remueve el html insertado para ello, tambien obtiene el contenido del archivo game.json que son los atributos del juego que ya deberian estar creados por el jugador estrella y lo envia a la función getInGameJSON, función de visitante.
    function setIDGame(){
        gameID = document.querySelector("#IDGame").value;
        removeNodeByID("gameIDInput");
        $.get("getContent.jsp",{"file":"game.json"},getInGameJSON)

    }

    //Función usada por la funcíon verifyLogin para establece el nombre del jugador adversario
    var setAdversayName = function (data){
        data = JSON.parse(data);
        adversaryName = data.j2;
    }
    
    //Esta función revisa cada segundo si ya ingreso el jugador visitante
    function verifyLogin(){
        $.get("getContent.jsp",{"file":"game.json"},setAdversayName);
        var id = setInterval( function(){
            $.get("getContent.jsp",{"file":"game.json"},setAdversayName);
            if(adversaryName != "null"){
                document.querySelector("body").innerHTML = "<div>Entro otro usuario aqui se debe de cambiar a la pestaña de juego</div>"
                clearInterval(id);
                document.querySelector("#showGameID").remove()
            }
        },1000
        )
    };


    //function que se ejecuta con el boton de juego nuevo, esta crea el ID del player estrella y el ID del juego, tambien llama a la función getName().
    function makeGame(){
        playerID = randomGenerator.randomASCII(10);
        gameID = randomGenerator.randomASCII(5);
        getName();

    }

    //Esta funcion es llamada cuando ya se tiene el id del jugador y del juego y se espera que se conecte el visitante.
    function showGameID(){
        document.querySelector("body").innerHTML = `<div id='showGameID'><p>Debes compartir este código con un amigo</p><p>\${gameID}</p><p>Esperando conexión de tu amigo...</p></div>`;
    }

    //Inserta el HTML para ingresar el nombre del jugador estrella y un boton que al presionarse ejecuta la función setName(), tambien deshabilita los botones principales
    function getName(){
        document.querySelector("body").innerHTML += 
        "<div id='nameInput'>Introduce tu nombre<br><input type='text' id='name'><button id='sendNameInput' onclick=' setName();'>Aceptar</button></div>";
        disableByID([true,"newGame","getInToGame","scoreTable"]);
        
    }

    // esta función establece el nombre del jugador estrella y llamar al microservicio GameCreator.jso este crea un json para establecer un juego, ese microservicio es precario y provicional, debe ser remplazado, tambien llama a la función que muestra el código del juego, y la función que verifica cuando que otro jugador se conecte
    function setName(){
        playerName = document.querySelector("#name").value;
        $.get("GameCreator.jsp",{"ID":gameID,"j1":playerName},null);
        removeNodeByID("nameInput");
        showGameID();
        verifyLogin();
    }
    
    //Recibe un arreglo donde el primer elemento es un boobleano y los demas son los ids de los objetos html que se quieren deshabilitar o habilitar, si se quiere deshabilitar el bool debe ser true
    function disableByID(parameters){
        for(let i=1; i<parameters.length; i++){
            document.querySelector("#"+parameters[i]).disabled=parameters[0];
        }
    }


    //Este elemento elimina objetos html por su id
    function removeNodeByID(nodeID){
        document.querySelector("#"+nodeID).remove();

    }

    //funcion hecha solo para ocultar los objetos insertados para pedir el nombre del usuario
    function hideGetName(){
        document.querySelector("#nameInput").remove();
        disableByID([false,"newGame","getInToGame","scoreTable"]);

    }


</script>

</html>