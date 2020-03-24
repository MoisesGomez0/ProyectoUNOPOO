function FrontManager(json) {

    this.json = json;


    this.updateCards = function () {
        var result = "";
        if (info.hostPlayer.name == name) {
            document.querySelector("#hand").innerHTML = this.handToHTML(this.json.hostPlayer.hand, this.json.hostPlayer.id);/**Cartas no ocultas. */
            document.querySelector("#oponentCards").innerHTML = this.handToHTML(this.json.guestPlayer.hand, this.json.guestPlayer.id, true);/**Cartas ocultas. */
        } else if (info.guestPlayer.name == name) {
            document.querySelector("#hand").innerHTML = this.handToHTML(this.json.guestPlayer.hand, this.json.guestPlayer.id);/**Cartas no ocultas. */
            document.querySelector("#oponentCards").innerHTML = this.handToHTML(this.json.hostPlayer.hand, this.json.hostPlayer.id, true);/**Cartas ocultas. */
        }
        var lastDiscardPileCard = am.getLastDiscard();

        document.querySelector("#discardPile").innerHTML = `<div id="discardPile" class="card" ><img class="card" src="../images/${lastDiscardPileCard}.png"></div>`;
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
        return `<div id="${card}" onclick="am.dropCard(this.id)" class="card" style="left:${left}vw;" ><img class="card" src="../images/${card}.png"></div>`;
    }

}

function ActionManager() {

    this.cardToDrop = null;

    this.playerTakeCard = function () {
        if (info.guestPlayer.name == name && info.currentPlayerId == info.guestPlayer.id) {
            dataManager.sendToBack("playerTakeCard");
        } else if (info.hostPlayer.name == name && info.currentPlayerId == info.hostPlayer.id) {
            dataManager.sendToBack("playerTakeCard");
        }
    }

    this.dropCard = function (card) {
        card = card.split("_");
        console.log("El ultimo en dropear",lastOnDrop)
        console.log("entre en dropcard");
        console.log("carta tocada", card);
        var lastCard = this.getLastDiscard().split("_");
        console.log("last", lastCard);
        if (info.guestPlayer.name == name && info.currentPlayerId == info.guestPlayer.id) {
            if (lastCard[0] == "DFOUR" && lastOnDrop == info.guestPlayer.id) {

                backScreenDecision.classList.add("active");
            
            }else if (card[0] == lastCard[0] || card[1] == info.currentColor || card[1] == "BLACK") {

                if (card[0] == "WILD" || card[0] == "DFOUR") {
                    console.log("Entre en especial");
                    backScreenColor.classList.add("active");
                    this.cardToDrop = card;

                }
                else {
                    console.log("solte", card);
                    dataManager.sendToBack("playerDropCard", card.join("_"));
                }
            }

        } else if (info.hostPlayer.name == name && info.currentPlayerId == info.hostPlayer.id) {
            if (lastCard[0] == "DFOUR" && lastOnDrop == info.hostPlayer.id) {

                backScreenDecision.classList.add("active");
            
            }else if (card[0] == lastCard[0] || card[1] == info.currentColor || card[1] == "BLACK") {

                if (card[0] == "WILD" || card[0] == "DFOUR") {
                    console.log("Entre en especial");
                    backScreenColor.classList.add("active");
                    this.cardToDrop = card;

                }
                else {
                    console.log("solte", card);
                    dataManager.sendToBack("playerDropCard", card.join("_"));
                }
            }
        }

    }

    this.getLastDiscard = function () {
        return info.discardPile[info.discardPile.length - 1];
    }

    this.chooseColor = function (color) {
        backScreenColor.classList.remove("active");
        dataManager.sendToBack("playerDropCard", this.cardToDrop.join("_"), color);

    }

    this.chooseDecision = function(decision){
        if(decision == "YES"){
            dataManager.sendToBack("challenge",null,null,"true");
        }else{
            dataManager.sendToBack("challenge",null,null,"false");

        }
        backScreenDecision.classList.remove("active");

    }
}

