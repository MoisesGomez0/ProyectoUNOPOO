/**
 * 
 * @fileoverview Este archivo contiene la Clase player con sus métodos.
 * @author Moisés
 * @version 0.1.0
 */


 /**
  *@class Player, objeto que administra la información del jugador
  */
function Player(){
    /** @type {boolean} Determina si el jugador se encuentra de turno o no.*/
    this.onPlay = null;

    /** @type {string} Identificador del jugador */
    this.ID = null;

    /** @type {string} Nombre del jugador */
    this.name = null;

    /**@type {list}  Lista que guarda las cartas que el jugador tiene en la mano
     * cada elemento debe ser @type {string} de la forma "color_figura"
    */
    this.hand = [];
    
    /**@method setID Establece el ID del jugador
     * @param {Integer} charactersNumber determina el número de caracteres
     * que debe tener el ID
     */
    this.setID = function(charactersNumber){
        random = new RandomGenerator()
        this.ID = random.randomAlpha(10);
    }

    /**@method setName Establece el nombre del jugador.
     * @param {string} name nombre del jugador.
     */
    this.setName = function(name){
        this.name = name;
    }

    /**@method addCard añade una carta a la mano del jugador
     * @param  {string} card es la carta a añadir.
     */
    this.addCard = function(card){
        this.hand.push(card);
    }

    /**@method dropCard suelta o saca una carta de la mano del jugador */
    this.dropCard = function(card){
        var tempHand = [];
        this.hand.forEach(element => {
            if(element != card){
                tempHand.push(element)
            }
        });
        this.hand = tempHand;
    }

}