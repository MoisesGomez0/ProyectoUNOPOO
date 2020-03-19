function Hand(deck,discardPile) {
    Deck.call(this,false);

    this.deck = deck;
    this.discardPile = discardPile;


    /**
     * Toma la primera carta de la baraja.
     * @param {Deck} deck Baraja donde se tomará la carta.
     */
    this.takeCard = function(deck = this.deck){
        return this.cards.add(deck.giveCard());
    }

    /**
     * @method dropCard Suelta una carta en la pila de descarte.
     * @param {Card} card Carta que soltará.
     * @param {DiscardPile} discardPIle Pila donde soltará la carta.
     */
    this.dropCard = function(card, discardPile = this.discardPile){
        var receivedCard = discardPile.receiveCard(this, card);
        receivedCard.div.moveToDiscardPile();
        return receivedCard;
    }

    /**
     * Verifica si tiene una carta del color especificado.
     * @param {EColor} color Color de la carta buscada.
     * @return false si no encuentra la carta.
     */
    this.checkColor = function(color){
        for (let i = 0; i < this.cards.length(); i++) {
            if (this.cards.get(i).color.name = color.name){
                return true
            }
        }
        return false;
    }

    /**
     * Verifica si tiene una carta con el número o símbolo especificado.
     * @param {EValue} value Número o símbolo de la carta buscada.
     * @return false si no encuentra la carta.
     */
    this.checkValue = function(value){
        for (let i = 0; i < this.cards.length(); i++) {
            if(this.cards.get(i).value.name == value.name){
                return true;
            }
        }

        return false;
    } 
}