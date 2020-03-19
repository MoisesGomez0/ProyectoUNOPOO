function Oponent(name){
    /**Determina si el jugador se encuentra de turno o no. @type {boolean} */
    this.onPlay = null;

    /**Nombre del oponente @type {string} */
    this.name = name;

    /**ID del oponente @type {string} */
    this.ID = null;
    
    /**Número de cartas del oponente @type {integer} */
    this.handLength = null;

    /**@method setName Establece el nombre del jugador oponente.
     * @param {string} name nombre del jugador oponente.
     */
    this.setName = function(name){
        this.name = name;
    }
}