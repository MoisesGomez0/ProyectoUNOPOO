
function Deck(generated = false){
	
	this.cards = new LinkedList();/**Cartas que contiene la baraja.*/
		
		
	if (generated){/**Genera las cartas*/
		for (let keyColor in EColor) { /**Recorre todos los colores.*/
		
			if (EColor[keyColor] != EColor.BLACK){/**Excepto BLACK.*/
				/**Por cada color agrega: */
				this.cards.add(new Card(EValue.CERO,EColor[keyColor])); /**Un CERO.*/
				for (let keyValue in EValue) {  /**Dos cartas de cada color.*/
					if (EValue[keyValue] != EValue.WILD &&
							EValue[keyValue] != EValue.DFOUR &&
							EValue[keyValue] != EValue.CERO){
						
						this.cards.add(new Card(EValue[keyValue],EColor[keyColor]));
						this.cards.add(new Card(EValue[keyValue],EColor[keyColor]));  				
					}
				}		
			}
		}
		
		for (let i = 0; i < 4; i++) {
			this.cards.add(new Card(EValue.WILD,EColor.BLACK)); /**Cuatro cartas WILD.*/
			this.cards.add(new Card(EValue.DFOUR,EColor.BLACK)); /**Cuatro cartas DFOUR.*/ 
		}
	}			
	
	/**
	 * Da la carta requerida.
	 * @param {Card} card Carta requerida.
	 * @return La carta que se dará.
	 */
	this.giveCard = function(card=null){
		if(!card){
			return this.giveCardByIndex(0);
		}
		var indexCard = this.searchIndexCard(card);
		return this.giveCardByIndex(indexCard);
	
	}

	/**
	 * Da la carta que se encuentra en el índice especificado.
	 * @param {int} card Índice de la carta requerida.
	 * @return La carta que se dará.
	 */
	this.giveCardByIndex = function(card=0){
		if (this.cards.first == null){
			return false;
		}

		return this.cards.remove(card);
	}

	/**
	 * Ordena la baraja aleatoriamente.
	 */
	this.shuffle = function(){
		var newCards = new LinkedList();
		var r = new RandomGenerator();
    	while (this.cards.length() != 0) {/**Mientras la baraja no esté vacía.*/
			/**Seleciona una carta aleatoria de la baraja y la añade a una nueva.*/
			var card = this.giveCardByIndex(r.randomInt(0, this.cards.length()));
			if (card != false){
				newCards.add(card);
			}
		}

		this.cards = newCards;/**Se remplaza el arreglo de cartas por el nuevo ordenado aleatoriamente.*/ 
	}

	/**
	 * Busca una carta específica.
	 * @param {EValue} value Valor de la carta buscada.
	 * @param {EColor} color Color de la carta buscada.
	 * @return {int} Índice de la carta buscada.
	 * @return {false} Si no encontro la carta. 
	 */
	this.searchIndexCard = function(card){
		for (var i = 0; i < this.cards.length(); i++) {
			var currentCard = this.cards.get(i);
			/**Si es la carta buscada.*/
			if (currentCard.value.name == card.value.name && currentCard.color.name == card.color.name){
				return i;
			}
		}
		return false; /**Si no encontró la carta.*/
	}

	/**
	 * Imprime todas las cartas de la baraja.
	 * @param {int} tab Cantidad de tabulados que tendrá al inicio.
	 */
	this.toString = function(tab=0){
		if (!this.cards.first){
			return `${"\t".repeat(tab)}[]`;
		}

		var result = `${"\t".repeat(tab)}[\n`;
		for (let i = 0; i < this.cards.length()-1; i++) {
			result += `${"\t".repeat(tab+1)}\"${this.cards.get(i)}\",\n`;         
		}
		result += `${"\t".repeat(tab+1)}\"${this.cards.get(this.cards.length() - 1)}\"\n`;
		result += `${"\t".repeat(tab)}]`;
		return result;
	}
	
	this.parse=function(array){
    	var newCards = new LinkedList();
    	for ( var i in array) {
    		var banana = array[i].split("_");
			newCards.add(new Card(banana[0],banana[1]));
		}
    	
    	this.cards = newCards;
    	return this;
		
	}

    
}



