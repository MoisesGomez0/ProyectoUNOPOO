/**
 * @fileoverview Este archivo contiene la Clase Game con su información y sus métodos.
 * @version 0.1.0
 */

function Game(id=null,players = new LinkedList(),currentNodePlayer=null,currentColor=null,clockWise=true,deck=new Deck(true),discardPile = null){
    /**@type {string} Indetificador de la partida de juego.*/
    this.id = id;

    /**@type {boolean} true si el jugador tomó una decisión.*/
    this.playerDecision = false;

    /**@type {Node} Nodo que contiene al jugador con el turno actual en la partida.*/
    this.currentNodePlayer = currentNodePlayer;

    /**@type {EColor} Determina el color de la ultima carta en juego.*/
    this.currentColor = null;

    /**@type {boolean} Determina si la dirección del juego es en contra o a favor de
     * las agujas del reloj.
     */
    this.clockWise = clockWise;

    /**@type {LinkedList} Lista de jugadores en la partida.*/
    this.players = players;

    /**@type {Deck}  Baraja de la partida.*/
    this.deck = deck;

    /**@type {DiscardPile} Pila de cartas descartadas en la partida. */
    if(discardPile){
        this.discardPile = discardPile;
    }else{
        this.discardPile = new DiscardPile(deck);
    }
    /**@method setID Establece el ID del juego
     * @param {Integer} charactersNumber determina el número de caracteres
     * que debe tener el ID
     */
    this.setId = function(charactersNumber){
        random = new RandomGenerator()
        this.id = random.randomAlpha(10);
    }
    
    /**
     * @method generateGame Genera la partida:
     * -Selecciona quien empieza.
     * -Barajea la baraja.
     * -Agrega la primera carta a la discardPile.
     * -Reparte las cartas (7 por cada jugador).
     */
    this.generate = function(){
        var r = new RandomGenerator();
        this.currentNodePlayer = this.players.getNode(r.randomInt(0,this.players.length()));
        this.deck.shuffle();
        this.discardPile.receiveFirstCard();
        this.currentColor = this.discardPile.cards.get(0).color;
        this.dealCards();
    }
    /**@method dealCards Reparte las cartas de cada jugador(7 por jugador).*/
    this.dealCards = function() {
        for (let i = 0; i < this.players.length(); i++) {
            var current = this.players.get(i).hand = new Hand(this.deck,this.discardPile);
            for (let j = 0; j < 7; j++) {
                current.takeCard();               
            }  
        }
    }

    /**@method nextPlayer Decide quién es el siguiente jugador.
     * @param {boolean} loseTurn true si el siguiente jugador pierde turno.
    */
    this.nextNodePlayer = function(loseTurn = false){
        if (clockWise){
            if (loseTurn) {
                return this.currentNodePlayer.next.next;
            }
            return this.currentNodePlayer.next;
        }

        if(loseTurn){
            return this.currentNodePlayer.previus.previus;
        }
        return this.currentNodePlayer.previus;
    }

    /**@method playerTakeCard  El jugador actual toma una carta.*/
    this.playerTakeCard = function(){
        var card = this.currentNodePlayer.value.takeCard();
        if (card.color.name != EColor.Black.name &&
            (card.color.name != this.currentColor.name || card.value.name != lastCard.value.name)){
            this.currentNodePlayer = this.nextNodePlayer; /**Turno del siguiente jugador. */
        }else{
            doc.addContent("body",`<div id='takeCard'>Desea jugar esta carta?
            <button onclick="this.playerDropCard(${card.toString()}); doc.removeByID('takeCard');" >Si</button>            
            <button onclick="this.playerDropCard(${card.toString()}); doc.removeByID('takeCard');" >No</button>            
            </div>`)
            this.currentNodePlayer = this.nextNodePlayer;  /**Turno del siguiente jugador. */
        }
    }

    /**@method playerDropCard El jugador actual suelta una carta.*/
    this.playerDropCard = function(card, selectedColor = EColor.BLUE){
        
        if (typeof(card)==typeof("bananas")){
            var newCard = new Card();
            card = newCard.stringToCard(card);
        }

        var currentPlayer = this.currentNodePlayer.value;
        var nextPlayer = this.nextNodePlayer.value;
        var lastCard = this.discardPile.cards.last.value; /**Ultima carta en la discardPile.*/
        
        if (card.color.name != EColor.Black.name &&
            (card.color.name != this.currentColor.name || card.value.name != lastCard.value.name)){
            throw "El jugador no puede soltar esta carta."
        }

        currentPlayer.dropCard(card);/**Suelta la carta.*/
        switch (card.value) {
            case (EValue.DTWO):
                this.currentColor = card.color;/**Se actualiza el color.*/
                nextPlayer.drawTwo();/**El siguiente jugador toma dos cartas. */
                this.currentNodePlayer = this.nextNodePlayer(true);/**El siguiente jugador pierde turno. */
                
                break;
            
            case (EValue.REVERSE):
                this.currentColor = card.color;/**Se actualiza el color.*/
                this.clockWise = !this.clockWise; /**Se invierte el sentido del juego.*/
                this.currentNodePlayer = this.nextNodePlayer(); /**Turno del siguiente jugador. */
                break;
                    
            case (EValue.SKIP):
                this.currentColor = card.color;/**Se actualiza el color.*/
                this.currentNodePlayer = this.nextNodePlayer(true);/**El siguiente jugador pierde turno. */
                break;
                    
            case (EValue.WILD):
                console.log("Seleccione color.")
                this.currentColor = selectedColor; /**Se actualiza el color al seleccionado. */
                this.currentNodePlayer = this.nextNodePlayer(); /**Turno del siguiente jugador. */

                break;

            case (EValue.DFOUR):
                console.log("Seleccione color. (BLUE)")
                this.currentColor = selectedColor; /**Se actualiza el color al seleccionado. */
                console.log("Falta decición del challenge, por los momentos se hará sin decisión.")
                break;
                
            default:
                this.currentColor = card.color;/**Se actualiza el color.*/
                this.currentNodePlayer = this.nextNodePlayer(); /**Turno del siguiente jugador.*/
                break;
        }
    }

    this.toString=function(tab = 0){
        var tabs = "\t".repeat(tab);
        var result = `${tabs}{\n`;
        result += `${tabs}\t\"id\":\"${this.id}\",\n`;
        result += `${tabs}\t\"playerDecision\":${this.playerDecision},\n`;
        result += `${tabs}\t\"currentNodePlayer\":${this.currentNodePlayer.index},\n`;
        result += `${tabs}\t\"currentColor\":\"${this.currentColor.name}\",\n`;
        result += `${tabs}\t\"clockWise\":${this.clockWise},\n`;
        result += `${tabs}\t\"players\":\n${this.players.toString(tab+2)},\n`;
        result += `${tabs}\t\"deck\":\n${this.deck.toString(tab+2)},\n`;
        result += `${tabs}\t\"discardPile\":\n${this.discardPile.toString(tab+2)}\n`;
        result += `${tabs}}`;

        return result;
    }
    
    this.parse =function(json){
 
    	this.id = json["id"];
    	this.playerDecision = json["playerDecision"];
    	
    	var d = new Deck();
    	this.deck = d.parse(json["deck"]);
    	
    	var dp = new DiscardPile();
    	this.discardPile = dp.parse(json["discardPile"]);
    	this.discardPile.deck = this.deck;
    	
    	
    	var players = new LinkedList();
    	for ( var key in json["players"]) {
			var player = new Player();
			player.parse(json["players"][key]);
			player.hand.deck = this.deck;
			player.hand.discardPile = this.discardPile;
			players.add(player);
        }
        
        this.players=players;
        
    	this.currentNodePlayer = players.getNode(json["currentNodePlayer"]);
    	this.clockWise = json["clockWise"];
    	this.currentColor = new Card("CERO",json["currentColor"]).color;
    	
    	
    }
}