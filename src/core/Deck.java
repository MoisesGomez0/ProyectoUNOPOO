package core;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 *
 * @author Leonardo
 */
public class Deck {
   /**Constantes. */
    final int COLORS = 5;
    final int VALUES = 15;
    
    
    /**Atributos.*/
 
    protected ArrayList<Card> cards = new ArrayList<>();  

    
    /**
     * Constructor de la clase.
     * 
     * Añade todas las cartas necesarias para el juego en un ArrayList de Cards.
     */
    public Deck(){
    	/**
    	 * Por cada color (excepto el negro) de EColor una carta con valor cero, dos del uno al nueve y dos comodines
    	 * de color (Draw two card, Reverse card, Skip card).
    	 */
    	for (EColor color: EnumSet.range(EColor.BLUE, EColor.YELLOW)) {
    		this.getCards().add(new Card(ENumber.CERO,color));/**Agrega una carta con el número cero a la baraja.*/
    		
    		for (int i = 0; i < 2; i++) {/**Dos de cada número.*/
				for (ENumber number: EnumSet.range(ENumber.ONE, ENumber.SKIP)) {
					this.getCards().add(new Card(number,color));/**Agrega la carta a la baraja.*/
				}
			}
        }
    	
    	for (int i = 0; i < 4; i++) {/**Cuatro cartas de especiales (de color negro).*/
    		this.getCards().add(new Card(ENumber.WILD,EColor.BLACK));/**Agrega cuatro cartas "cambiar color" a la baraja.*/
    		this.getCards().add(new Card(ENumber.DFOUR,EColor.BLACK));/**Agrega cuatro cartas "toma cuatro" a la baraja.*/
			
		}
    }
    
    /**Métodos*/
    
    /**
     * 
     * @return La primera carta de la baraja.
     */
    public Card giveCard() {
    	
    	if (this.getCards().isEmpty()) {
    		throw new IllegalArgumentException("No hay cartas en la baraja.");
    	}
    	Card result = this.getCards().get(0);
    	this.getCards().remove(0);
    	return result;
    	
    }
    
    /**
     * Busca una carta que se encuentre en el índice especificado.
     * @param cardIndex Indice de la carta a buscar.
     * @return Carta buscada.
     */
    public Card giveCard(int cardIndex) {
    	if (this.getCards().isEmpty()) {/**Si la baraja está vacía lanzará una excepción.*/
    		throw new IllegalArgumentException("No hay cartas en la baraja.");
    	}
    	/**Si el parametro es menor que cero o mayor o igual que la cantidad de cartas lanzará una excepción.*/
    	if (cardIndex < 0 || cardIndex >= this.getCards().size()) { 
    		throw new IllegalArgumentException("Parámetro fuera de rango.");
    	}
    	Card result = this.getCards().get(cardIndex);
    	this.getCards().remove(cardIndex);
    	return result;    	
    }
    
    public Card giveCard(Card card){
    	/**Resultado de la busqueda de la carta en la baraja. (false si no se encuntra la carta en la baraja.*/
    	int searchResult = this.searchCard(card.getValue(), card.getColor());
    	
        if (searchResult == -1) { /**Sí la carta no está lanzará una excepción.*/
            throw new IllegalArgumentException("La carta no se encuentra en la baraja");
        }
        
        Card result = this.getCards().get(searchResult);/**Guarda la carta requerida.*/
        this.getCards().remove(searchResult);/**Remueve la carta de la baraja.*/
        
        return result;
    	
    }
    
	/**
	 * Da una carta en especifica que se encuentra en la baraja.
	 * @param value Número o símbolo de la carta.
	 * @param color Color de la carta.
	 * @return Un objeto de tipo Card.
	 */
    public Card giveCard(ENumber value, EColor color){
    	/**Resultado de la busqueda de la carta en la baraja. (false si no se encuntra la carta en la baraja.*/
    	int searchResult = this.searchCard(value, color);
    	
        if (searchResult == -1) { /**Sí la carta no está lanzará una excepción.*/
            throw new IllegalArgumentException("La carta no se encuentra en la baraja");
        }
        
        Card result = this.getCards().get(searchResult);/**Guarda la carta requerida.*/
        this.getCards().remove(searchResult);/**Remueve la carta de la baraja.*/
        
        return result;
    }
    /**
     * Ordena la baraja aleatoriamente.
     * @return Un objeto Deck con las cartas desordenadas.
     */
    public Deck shuffle() {
    	ArrayList<Card> newCards = new ArrayList<>();

    	while (!this.getCards().isEmpty()) {/**Mientras la baraja no esté vacía.*/
    		/**Seleciona una carta aleatoria de la baraja y la añade a una nueva.*/
    		newCards.add(this.giveCard(RandomGenerator.randomInt(0, this.getCards().size()-1)));
    	}
    	this.cards = newCards;/**Se remplaza el arreglo de cartas por el nuevo ordenado aleatoriamente.*/ 
    	
    	Deck result = new Deck();
    	result.cards = this.cards;
    	return result;
    	
    }
    
    /**
     * Devuelve el índice de una carta especifica que se encuentra en la baraja.
     * @param value Número o símbolo de la carta a buscar.
     * @param color Color de la carta a buscar.
     * @return El inidce de la carta, -1 si no se encuentra la carta.
     */
    public int searchCard(ENumber value, EColor color) {
    	int result = -1;
    	
    	for (Card card : this.getCards()) {
			if (card.equals(new Card(value,color))) {
				result = this.getCards().indexOf(card); /**Encuentra la carta en la baraja.*/
				break;
			}
		}
    	
    	return result;
    }
    
    public int searchCard(Card card) {
    	return this.searchCard(card.getValue(),card.getColor());
    }
    
    
    /**
     * @return Una representación String de un arreglo de cartas.
     */
    @Override
    public String toString(){
    	if (this.cards.isEmpty()) {
    		return "null";
    	}
    	int size = this.cards.size();
    	StringBuilder result = new StringBuilder();
    	result.append("[\n");
    	if (this.cards.size() == 1) {
    		result.append(String.format("%s%s\n", "\t".repeat(1),this.cards.get(0)));
    	}else {
    		for (Card card : getCards().subList(0, this.cards.size()-2)) {
    			result.append(String.format("%s%s,\n", "\t".repeat(1),card));
    		}  		
    		result.append(String.format("%s%s\n", "\t".repeat(1),this.cards.get(size-1)));
    	}
    	result.append("]");  
    	
    	return result.toString();
    }
    /**
     * 
     * @param tab Cantidad de tabs que estará corrido.
     * @return Una representación String de un arreglo de cartas
     */
    public String toString(int tab){
    	if (this.cards.isEmpty()) {
    		return String.format("%snull", "\t".repeat(tab));
    	}
    	int size = this.cards.size();
    	StringBuilder result = new StringBuilder();
    	result.append(String.format("%s[\n", "\t".repeat(tab)));
    	if (this.cards.size() == 1) {
    		result.append(String.format("%s%s\n", "\t".repeat(tab+1),this.cards.get(0)));
    	}else {
    		for (Card card : getCards().subList(0, this.cards.size()-1)) {
    			result.append(String.format("%s%s,\n", "\t".repeat(tab+1),card));
    		}  		
    		result.append(String.format("%s%s\n", "\t".repeat(tab+1),this.cards.get(size-1)));
    	}
    	result.append(String.format("%s]", "\t".repeat(tab)));  
    	
    	return result.toString();
    }
    
    /**
     * Varifica que dos objetos Deck sean iguales.
     * @param deck Objeto deck a comparar.
     * @return true si son iguales.
     */
    
    public boolean equals(Deck deck) {
    	if (this.cards.equals(deck.getCards())) {
    		return true;
    	}
    	return false;
    }
      
    
    /**
	 * @return the cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**Pruebas con la clase.*/
    public static void main(String[] args){
        
    	ArrayList<String> a = new ArrayList<>();
    	ArrayList<String> b = new ArrayList<>();
    	
    	System.out.println(a.equals(b));
    	
    	b.add("buenas tardes.");
    	
    	System.out.println(a.equals(b));
    	
    	a.add("buenas tardes.");
    	
    	System.out.println(a.equals(b));
    	
    	Deck deck = new Deck();
    	
    	//deck.shuffle();
    	System.out.println("======Shuffle=====");
    	System.out.println(deck);

  

    }


}
