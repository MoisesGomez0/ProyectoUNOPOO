/**
 * Pila de cartas descartadas de la paradida.
 * Es donde los jugadores pondrán sus cartas a medida que avanza el juego.
 * @param {Deck} deck Baraja asociada a la pila.
 */
function DiscardPile(deck=null,generated=false){
    Deck.call(this,generated);

    this.deck = deck;

	/**
	 * Recive una carta específica de la mano de un jugador.
	 * @param {Hand} hand Mano del jugador que dará la carta.
	 * @param {Card} card Carta que el jugadoro dará.
	 */
    this.receiveCard=function(hand,card){
        var cardIndex = hand.searchIndexCard(card);
        if (cardIndex == false){
            throw "La carta no se encuentra la mano.";
        }
        this.cards.add(hand.giveCardbyIndex(cardIndex));
    }

	/**
	 * Recive una carta aleatoria no especial para iniciar la partida.
     * @param {Deck} deck Baraja de donde se sacará la primera carta.
	 */
    this.receiveFirstCard = function(deck = this.deck){
        if (deck.cards.length() < 10){
            throw "Pocas cartas en la baraja.";
        }
        var r = new RandomGenerator();
        var card = deck.giveCard(r.noActionUNOCard());
        while(card == false){
            card = deck.giveCard(r.noActionUNOCard());
        }

        this.cards.add(card);
    }
}