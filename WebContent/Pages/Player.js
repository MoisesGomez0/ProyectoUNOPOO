/**
 * 
 * @fileoverview Este archivo contiene la Clase player con sus métodos.
 * @author Moisés
 * @version 0.1.1
 */


 /**
  *@class Player, objeto que administra la información del jugador
  */
function Player(name=null,id = null,hand = new Hand()){

    /** @type {string} Identificador del jugador */
    this.id = id;

    /** @type {string} Nombre del jugador */
    this.name = name;

    /**
     * @type {Hand} Objeto que administra las cartas del jugador.
    */
    this.hand = hand;


    /**@method takeCard añade una carta a la mano del jugador
     * 
     */
    this.takeCard = function(){
        return this.hand.takeCard();
    }

    /**@method dropCard suelta o saca una carta de la mano del jugador.*/
    this.dropCard = function(card){
        return this.hand.dropCard(card);
    }
    
    /**@method drawTwo Toma las dos primeras cartas de la baraja. */
    this.drawTwo = function(){
        for (let i = 0; i < 2; i++) {
			this.hand.takeCard();
		}
    }
    /**@method drawFour Toma las cuatro primeras cartas de la baraja. */
    this.drawFour = function(){
        for (let i = 0; i < 4; i++) {
			this.hand.takeCard();
		}
    }
    /**@method drawSix Toma las seis primeras cartas de la baraja. */    
    this.drawSix = function(){
        for (let i = 0; i < 6; i++) {
			this.hand.takeCard();
		}
    }

    /**
     * @method toString Crea una representación JSON del objeto.
     * @param tab Cantidad de tabs que tendrá al inicio.
     * @return JSON del objeto.
     */
    this.toString = function(tab = 0){
        var tabs = "\t".repeat(tab);
        var result = `${tabs}{\n`
        result += `${tabs}\t\t\"id\":${this.id},\n`
        result += `${tabs}\t\t\"name\":\"${this.name}\",\n`
        result += `${tabs}\t\t\"hand\":\n${this.hand.toString(tab+1)}\n`
        result += `${tabs}}`

        return result;
    }
    
    this.parse = function(json){
    	this.id = json["id"];
    	this.name = json["name"];
    	this.hand.parse(json["hand"]);
    }

}