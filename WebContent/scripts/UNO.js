function FrontManager(json) {

    this.json = json;


    this.updateCards = function () {
        var result = "";
        if (info.hostPlayer.name == name) {
        	if(info.hostPlayer.hand == null){
        		backScreenFinal.classList.add("active");
        		lastMessage.innerHTML = "Ganaste, Felicidades."
        		clearInterval(idSetIntervalUpdate);
        		return true;
        	}else if(info.guestPlayer.hand == null){
        		backScreenFinal.classList.add("active");
        		lastMessage.innerHTML = "Perdiste, Felicidades."
        		clearInterval(idSetIntervalUpdate);
        		return true;
        	}
            hand.innerHTML = this.handToHTML(info.hostPlayer.hand, info.hostPlayer.id);/**Cartas no ocultas. */
            oponentCards.innerHTML = this.handToHTML(info.guestPlayer.hand, info.guestPlayer.id, true);/**Cartas ocultas. */

        } else if (info.guestPlayer.name == name) {
        	if(info.guestPlayer.hand == null){
        		backScreenFinal.classList.add("active");
        		lastMessage.innerHTML = "Ganaste, Felicidades."
        		clearInterval(idSetIntervalUpdate);
        		return true;
        	}else if(info.hostPlayer.hand == null){
        		backScreenFinal.classList.add("active");
        		lastMessage.innerHTML = "Perdiste, Felicidades."
        		clearInterval(idSetIntervalUpdate);
        		return true;
        	}
            hand.innerHTML = this.handToHTML(info.guestPlayer.hand, info.guestPlayer.id);/**Cartas no ocultas. */
            oponentCards.innerHTML = this.handToHTML(info.hostPlayer.hand, info.hostPlayer.id, true);/**Cartas ocultas. */
        }

        var lastDiscardPileCard = am.getLastDiscard();

        discardPile.innerHTML = `<div id="discardPile" class="card" ><img class="card" src="../images/${lastDiscardPileCard}.png"></div>`;

        lastDiscardPileCard = lastDiscardPileCard.split("_");
        if(am.isOnPlay(name)){
        	if (lastDiscardPileCard[0] == "DFOUR" && info.onChallenge) {
        		clearInterval(idSetIntervalUpdate);
                backScreenDecision.classList.add("active");
            }else{
                updateFront();
            }
        }else{
            updateFront();
        }
        
    }

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

    this.cardToDiv = function (card, id, left, hidden = false) {

        if (hidden) {
            return `<div id="${card}" class="oponentCard" style="left:${left}vw;"><img class="oponentCard" src="../images/UNO.png"></div>`;
        }
        if (am.isOnPlay(name)) {
            if (am.isAvaiable(card)) {
                return `<div id="${card}" class="card" style="left:${left}vw; bottom:2vh;" ><img  onclick="am.dropCard(this.parentNode.id);" class="card" src="../images/${card}.png"></div>`;
            } else {
                return `<div id="${card}" class="card" style="left:${left}vw;" ><img  onclick="am.dropCard(this.parentNode.id)" style="opacity:0.8" class="card" src="../images/${card}.png"></div>`;
            }
        } else {
            return `<div id="${card}" class="card" style="left:${left}vw; opa" ><img  onclick="am.dropCard(this.parentNode.id)" class="card" src="../images/${card}.png"></div>`;

        }
    }


}

function ActionManager() {

    this.cardToDrop = null;

    
    this.dropConditionalCard = function(option){
    	if(option == "YES"){
    		this.dropCard(this.getLastCardOnHand());
    	}else if(option == "NO"){
    		dataManager.sendToBack("nextPlayer");
    	}
    	backScreenDrop.classList.remove("active");
    	
    }
    
    this.getLastCardOnHand = function(){
    	if(this.isOnPlay(info.guestPlayer.name)){
    		return info.guestPlayer.hand[info.guestPlayer.hand.length - 1];
    		
    	}else if(this.isOnPlay(info.hostPlayer.name)){
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

    this.playerTakeCard = function () {
        if (this.isOnPlay(name)) {
            dataManager.sendToBack("playerTakeCard");
        }
    }

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

    this.chooseColor = function (color) {
        backScreenColor.classList.remove("active");
        dataManager.sendToBack("playerDropCard", this.cardToDrop, color);

    }

    this.chooseDecision = function (decision) {
        if (decision == "YES") {
            console.log("Lo retó")
            dataManager.sendToBack("challenge", null, null, "true");
        } else if (decision == "NO") {
            dataManager.sendToBack("challenge", null, null, "false");
            console.log("No lo retó")


        }
        backScreenDecision.classList.remove("active");
        updateFront();

    }

    this.playerPressUNO = function () {
        if (info.guestPlayer.name == name && info.currentPlayerId == info.guestPlayer.id) {
            dataManager.sendToBack("playerPressUNO");
        } else if (info.hostPlayer.name == name && info.currentPlayerId == info.hostPlayer.id) {
            dataManager.sendToBack("playerPressUNO");
        }

    }
}

