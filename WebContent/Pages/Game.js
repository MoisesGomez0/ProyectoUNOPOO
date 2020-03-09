/**
 * @fileoverview Este archivo contiene la Clase Game con su información y sus métodos.
 * @author Moisés
 * @version 0.1.0
 */

function Game(){
    /**@type {string} Indetificador de la partida de juego*/
    this.ID = null;

    /** @type {list} Lista de cartas que fueron descartadas,
     * las cartas son @type {string} y siguen un formato "color_figura".
     * La última carta de la lista es la última carta que fue descartada, esta
     * debe estar en la parte superior.
    */
    this.discardPile = [];

    /** @type {list} Lista de cartas que aun estan ocultas y boca abajo,
     * las cartas son @type {string} y siguen un formato "color_figura".
     * La última carta de la lista en la cima de la pila.
    */
    this.deck = [];

    /**@type {string} guarda el nombre del jugador en turno. */
    this.turnOf = null;

    /**@type {Player} Objeto de tipo player que guarda su información, y
     * realiza acciónes propias de este.
     */
    this.player = new Player();

    /**@type {Oponent} Objeto de tipo Opente que guarda información necesaria */
    this.oponent = new Oponent();

    /**@method setID Establece el ID del juego
     * @param {Integer} charactersNumber determina el número de caracteres
     * que debe tener el ID
     */
    this.setID = function(charactersNumber){
        random = new RandomGenerator()
        this.ID = random.randomAlpha(10);
    }
}