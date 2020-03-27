/**Objeto que se encarga de administrar lo que se muestra en la pantalla del jugador.*/
function FrontManager(json) {

    this.json = json;

    /**Actualiza las cartas que se muestran en la pantalla y acciones 
     * de juego que se necesitan ejecutar al mismo momento de actulizar las cartas
     * la infomación es tomada de las peticiones asincronas hechas al servidor.*/
    this.updateCards = function () {
        var result = "";

        /**Verifico si la partida terminó*/
        if (am.isEndGame(name)) {
            clearInterval(idSetIntervalUpdate);
            return true;
        }
        else {
            this.upgradeHands();
        }

        this.upgradeDiscardPile();

        if (am.isLastPlayable()) {
            clearInterval(idSetIntervalUpdate);
            backScreenDecision.classList.add("active");
        } else {
            console.log("im on play?",am.isOnPlay(name));
        	if(!am.isOnPlay(name)){
                console.log("im not on play and called to update")
                updateFront();
        	}
        }


    }


    /**Actualiza la carta mostrada en la discardPile.*/
    this.upgradeDiscardPile = function () {
        var lastFigure = am.getLastDiscard().split("_")[0];
        var currentColor = info.currentColor;
        document.querySelector("#discardPile").innerHTML = `<div id="discardPile" class="card" ><img class="card" src="../images/${lastFigure}_${currentColor}.png"></div>`;
    }

    /**Actualiza las cartas que los jugadores tiene en la mano.*/
    this.upgradeHands = function () {
        if (info.hostPlayer.name == name) {
            hand.innerHTML = this.handToHTML(info.hostPlayer.hand, info.hostPlayer.id);/**Cartas no ocultas. */
            oponentCards.innerHTML = this.handToHTML(info.guestPlayer.hand, info.guestPlayer.id, true);/**Cartas ocultas. */

        } else if (info.guestPlayer.name == name) {
            hand.innerHTML = this.handToHTML(info.guestPlayer.hand, info.guestPlayer.id);/**Cartas no ocultas. */
            oponentCards.innerHTML = this.handToHTML(info.hostPlayer.hand, info.hostPlayer.id, true);/**Cartas ocultas. */
        }
    }


    /**@param {list} hand Una lista con las cartas que un jugador tiene en la mano.
     * @param {int} id El id del jugador.
     * @param {boolean} hidden este determina si las cartas seran mostradas en la hand del jugador
     * si está en true o en la hand del oponente si está en false.
     * @return {string} result Un string con todos los divs que corresponden a todas las del jugador.
     * */
    this.handToHTML = function (hand, id, hidden = false) {

        var result = ""
        var left = 60 / hand.length;

        if (hidden) {
            for (let index in hand) {
                result += this.cardToDiv(hand[index], id, 20 + left * index, hidden);
            }
        } else {
            for (let index in hand) {
                result += this.cardToDiv(hand[index], id, 20 + left * index)
            }
        }
        return result;
    }

    /**
     * @param {string} card Una cadena con la nomenclatura de una carta.
     * @param {int} id Un número que representa el id del jugador.
     * @param {int} left Un valor numerico que representa la posición con respecto a la izquieda que debe tener la carta.
     * @param {boolean} hidden Determina si una carta está en la mano del oponente si es false,
     * o la mano del jugador si es true.
     * @return {string} result Un texto que representa un div de html con todos los atributos de una carta.
     * 
     * Este determina tambien si una carta se puede jugar y añade estilos diferentes a estas cartas.
     * */
    this.cardToDiv = function (card, id, left, hidden = false) {

        if (hidden) {
            return `<div id="${card}" class="oponentCard card" style="left:${left}vw;"><img class="oponentCard card" src="../images/UNO.png"></div>`;
        }
        if (am.isOnPlay(name)) {
            if (am.isAvaiable(card)) {
                return `<div id="${card}" class="playerCard card" style="left:${left}vw; bottom:2vh;" >
                            <img  onclick="am.dropCard(this.parentNode.id);" class="playerCard card" src="../images/${card}.png">
                        </div>`;

            } else {
                return `<div id="${card}" class="playerCard card" style="left:${left}vw;" >
                            <img  onclick="am.dropCard(this.parentNode.id)" style="opacity:0.8" class="card" src="../images/${card}.png">
                        </div>`;
            }
        } else {
            return `<div id="${card}" class="playerCard card" style="left:${left}vw; opa" >
                        <img  onclick="am.dropCard(this.parentNode.id)" class="card" src="../images/${card}.png">
                    </div>`;

        }
    }


}
/**Objeto encargado de administrar la acciones del usuario en la ejecución del juego*/
function ActionManager() {
	/**Atributo usado para establecer la carta a jugar.
	 * Usado por algunos métodos. */
    this.cardToDrop = null;

    /**Verifica si la partida terminó, de ser así retorna true y muestra un mensaje a 
     * los jugadores, tambien termina la rutina de actialización de los datos; si la
     * partida no ha terminado return false.
     *@param {string} name El nombre del jugador.
     * @return {boolean}*/
    this.isEndGame = function (name) {
        if (info.hostPlayer.name == name) {
            if (info.hostPlayer.hand == null) {
                backScreenFinal.classList.add("active");
                lastMessage.innerHTML = "Ganaste, Felicidades."
                return true;
            } else if (info.guestPlayer.hand == null) {
                backScreenFinal.classList.add("active");
                lastMessage.innerHTML = "Perdiste, Felicidades."
                return true;
            }
        } else if (info.guestPlayer.name == name) {
            if (info.guestPlayer.hand == null) {
                backScreenFinal.classList.add("active");
                lastMessage.innerHTML = "Ganaste, Felicidades."
                return true;
            } else if (info.hostPlayer.hand == null) {
                backScreenFinal.classList.add("active");
                lastMessage.innerHTML = "Perdiste, Felicidades."
                return true;
            }
        }
        return false;
    }

    /**Ejecutado cuando el usuario escoge si quiere conservar o soltar
     * la carta que acaba de tomar del deck.*/
    this.dropConditionalCard = function (option) {
        if (option == "YES") {
            this.dropCard(this.getLastCardOnHand());
        } else if (option == "NO") {
            dataManager.sendToBack("nextPlayer");
        }
        backScreenDrop.classList.remove("active");

    }


    /**@return {string} carta
     * Retorna la última carta que fue agregada a la mano de un jugador*/
    this.getLastCardOnHand = function () {
        if (this.isOnPlay(info.guestPlayer.name)) {
            return info.guestPlayer.hand[info.guestPlayer.hand.length - 1];

        } else if (this.isOnPlay(info.hostPlayer.name)) {
            return info.hostPlayer.hand[info.hostPlayer.hand.length - 1];

        }
    }


    /**@return {boolean}
     * @param {String} name El nombre de un jugador
     * Determina si el jugador está en turno.
     */
    this.isOnPlay = function (playerName) {
        if (info.guestPlayer.name == playerName && info.currentPlayerId == info.guestPlayer.id) {
            return true;
        } else if (info.hostPlayer.name == playerName && info.currentPlayerId == info.hostPlayer.id) {
            return true;
        }
        return false;
    }

    /**@return boolean
     * @param {String} cardTest cadena que cumpla con la nomenclatura de una carta.
     * Determina si una carta esta habilitada para ser jugada.
     *  */
    this.isAvaiable = function (cardTest) {
        cardTest = cardTest.split("_");
        var lastCard = this.getLastDiscard().split("_");

        return cardTest[0] == lastCard[0] || cardTest[1] == info.currentColor || cardTest[1] == "BLACK";

    }


    /**Se ejecuta cuando el jugador hace click en el deck.
     * Valida si el jugador está en turno.
     * Si está en turno inicia la comunicación con el servidor para que sea
     * sedida una carta al jugador.
     * */
    this.playerTakeCard = function () {
        if (this.isOnPlay(name)) {
            dataManager.sendToBack("playerTakeCard");
        }
    }

    /**@param card {string} carta con la nomenclatura correcta.
     * Método ejecutado cuando un jugador hace click en una carta de su mano.
     * Valida si el jugador está en turno.
     * Valida si la carta clickeada es válida para ser jugada.
     * Verifica si es una carta que necesite selecionar un cambio de color,
     * de ser asi hace cambios en la vista para que el jugador seleccione el color.
     * */
    this.dropCard = function (card) {
        if (this.isOnPlay(name)) {
            if (this.isAvaiable(card)) {
                type = card.split("_")[0];
                if (type == "WILD" || type == "DFOUR") {
                    backScreenColor.classList.add("active");
                    this.cardToDrop = card;
                } else {
                    dataManager.sendToBack("playerDropCard", card);
                }
            }
        }


    }


    /**@return {String} La última carta de la discard Pile.*/
    this.getLastDiscard = function () {
        return info.discardPile[info.discardPile.length - 1];
    }


    /**Se ejecuta cuando el jugador selecciona el color a seguir en jugarse cuando este
     *soltó una carta de +4 ó una de cambio de color, este inicia la comunicación con
     *el servidor para modificar el estado del juego. */
    this.chooseColor = function (color) {
        backScreenColor.classList.remove("active");
        dataManager.sendToBack("playerDropCard", this.cardToDrop, color);

    }

    /**Inicia la rutina de comunicación con el servidor cuando un usuario decide retar a
     * su oponente al momento de que el oponente jugó una carta de +4 */
    this.chooseDecision = function (decision) {
        if (decision == "YES") {
            dataManager.sendToBack("challenge", null, null, "true");
        } else if (decision == "NO") {
            dataManager.sendToBack("challenge", null, null, "false");
        }
        backScreenDecision.classList.remove("active");
        console.log("called to update on chooseDesition")
        updateFront();

    }
    /**Método ejecutado cuando el usuario presiona el botón de UNO, inicia la comunicación asíncrona con le
     * servidor para modificar el estado del juego */
    this.playerPressUNO = function () {
        if (this.isOnPlay(name)) {
            dataManager.sendToBack("playerPressUNO");
        }

    }

    /**@return {boolean}
     * Verifica si el jugador puede tirar la última carta tomada del deck
     * de ser así retorna true, lo contrario retorna false.
     * */
    this.isLastPlayable = function () {
        var lastDiscardPileCard = this.getLastDiscard().split("_");
        if (this.isOnPlay(name)) {
            if (lastDiscardPileCard[0] == "DFOUR" && info.onChallenge) {
                return true;
            }
        }
        return false;
    }
}

