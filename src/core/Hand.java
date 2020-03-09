package core;


/**
 *Esta clase administra las cartas del jugador.
 * @author Leonardo
 */
public class Hand extends Deck{
    
    private Deck deck; /**Baraja de la que tomará cartas.*/
    private DiscardPile discardPile; /**Donde soltará las cartas.*/
	private boolean hiddenCards = false;
    
    
    /**Constructor vacío.*/
    public Hand() {
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    }
    
    public Hand(Deck deck) {
    	DiscardPile discardPile = new DiscardPile(this.deck);
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    	
    	this.setDeck(deck);
    	this.setDiscardPile(discardPile);
    }
    
    /**Métodos.*/
    
    /**
     * Toma la primera carta de la baraja.
     * 
     */
    public void takeCard(){
        this.cards.add(this.getDeck().giveCard());
    }
    
    /**
     * Suelta una carta en la pila de descarte.
     * @param card Carta que soltará.
     */
    public void dropCard(Card card) {
    	this.discardPile.receiveCard(this, card);
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
    
    public String toHand() {
    	StringBuilder result = new StringBuilder("");
    	
    	return result.toString();
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

	/**
	 * @return the discardPile
	 */
	public DiscardPile getDiscardPile() {
		return discardPile;
	}

	/**
	 * @param discardPile the discardPile to set
	 */
	public void setDiscardPile(DiscardPile discardPile) {
		this.discardPile = discardPile;
	}

	//Pruebas con la clase.
    public static void main(String[] args){}

}
