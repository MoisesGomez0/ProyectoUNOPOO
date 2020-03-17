function Oponent(){
    /**Determina si el jugador se encuentra de turno o no. @type {boolean} */
    this.onPlay = null;

    /**Nombre del oponente @type {string} */
    this.name = null;

    /**ID del oponente @type {string} */
    this.ID = null;
    
    /**Número de cartas del oponente @type {integer} */
    this.handLength = null;

    /**Mano del oponente */
    this.hand = null;

    /**@method setName Establece el nombre del jugador oponente.
     * @param {string} name nombre del jugador oponente.
     */
    this.setName = function(name){
        this.name = name;
    }

    this.pick = function(deck){
        this.hand.push(deck.pop());
    }
}