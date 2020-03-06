package core;


/**
 *Esta clase administra las cartas del jugador.
 * @author Leonardo
 */
public class Hand extends Deck{
    
    private boolean hiddenCards = false;
    
    /**Constructor vacío.*/
    public Hand() {
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    }
    
    /**Métodos.*/
    
    /**
     * Toma la primera carta de una baraja específica.
     * @param deck Baraja de donde se tomará la carta.
     */
    public void takeCard(Deck deck){
        this.cards.add(deck.giveCard());
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

	//Pruebas con la clase.
    public static void main(String[] args){}

}
