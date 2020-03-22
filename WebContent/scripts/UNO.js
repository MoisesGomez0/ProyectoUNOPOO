function FrontManager(json) {

    this.json = json;


    this.updateCards = function () {
        console.log(info);
        var result = "";
        if (info.hostPlayer.name == name) {
            document.querySelector("#hand").innerHTML = this.handToHTML(this.json.hostPlayer.hand, this.json.hostPlayer.id);/**Cartas no ocultas. */
            document.querySelector("#oponentCards").innerHTML = this.handToHTML(this.json.guestPlayer.hand, this.json.guestPlayer.id, true);/**Cartas ocultas. */
        } else if (info.guestPlayer.name == name) {
            document.querySelector("#hand").innerHTML = this.handToHTML(this.json.guestPlayer.hand, this.json.guestPlayer.id);/**Cartas no ocultas. */
            document.querySelector("#oponentCards").innerHTML = this.handToHTML(this.json.hostPlayer.hand, this.json.hostPlayer.id, true);/**Cartas ocultas. */
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
            return `<div id="${id}_${card}" class="oponentCard" style="left:${left}vw;"><img class="oponentCard" src="../images/UNO.png"></div>`;
        }
        return `<div id="${id}_${card}" class="card" style="left:${left}vw;" ><img class="card" src="../images/${card}.png"></div>`;
    }

}