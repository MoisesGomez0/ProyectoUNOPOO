package core;


/**
 *Esta clase administra las cartas del jugador.
 * @author Leonardo
 */
public class Hand extends Deck{
    
    private Deck deck; /**Baraja de la que tomará cartas.*/
	private boolean hiddenCards = false;
    
    
    /**Constructor vacío.*/
    public Hand(Deck deck) {
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    	this.setDeck(deck);
    }
    
    /**Métodos.*/
    
    /**
     * Toma la primera carta de una baraja específica.
     * @param deck Baraja de donde se tomará la carta.
     */
    public void takeCard(){
        this.cards.add(this.getDeck().giveCard());
    }
    
    /**
     * Cambiar el estado oculto del las cards en la mano.
     * @param state true para ocultar las cartas.
     */
    public void hideCards(boolean state){
        setHiddenCards(state);
        
        for (Card card : cards) {
            card.setHidden(state);
        }
    }
    
    /**
	 * @return the hiddenCards
	 */
	public boolean isHiddenCards() {
		return hiddenCards;
	}

	/**
	 * @param hiddenCards the hiddenCards to set
	 */
	public void setHiddenCards(boolean hiddenCards) {
		this.hiddenCards = hiddenCards;
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	//Pruebas con la clase.
    public static void main(String[] args){}

}
