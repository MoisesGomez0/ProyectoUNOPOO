package core;


/**
 *Esta clase administra las cartas del jugador.
 * @author Leonardo
 */
public class Hand extends Deck{
    
    private Deck deck = new Deck(); /**Baraja de la que tomará cartas.*/
    private DiscardPile discardPile = new DiscardPile(this.deck); /**Donde soltará las cartas.*/
	private boolean hiddenCards = false;
    
    
    /**Constructor vacío.*/
    public Hand() {
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    }
    
    public Hand(Deck deck) {
    	this.discardPile = new DiscardPile(this.deck);
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    	this.setDeck(deck);
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
    
    /**
     * Verifica si dos objetos son iguales.
     * @param hand Objeto a comparar.
     * @return true si son iguales.
     */
    public boolean equals(Hand hand) {
    	if(this.deck.equals(hand.getDeck())&&this.discardPile.equals(hand.getDiscardPile())&&this.cards.equals(hand.getCards())) {
    		return true;
    	}
    	return false;
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
	private void setHiddenCards(boolean hiddenCards) {
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
    public static void main(String[] args){
    	Deck deck = new Deck();
    	DiscardPile discardPile = new DiscardPile(deck);
    	
    	deck.shuffle();
    	discardPile.receiveFirstCard();
    	
    	Hand a = new Hand(deck);
    	Hand b = new Hand(deck);
    	
    	System.out.println(a.equals(b));
    }

}
