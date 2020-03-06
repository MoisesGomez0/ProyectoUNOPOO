package core;

/**
 * Pila de cartas descartadas de la partida.
 * Es donde los jugadores pondrán sus cartas a medida que avanza la partida.
 */
public class DiscardPile extends Deck{
	
	private Deck deck;
	/**
	 * Constructor de la clase. Se inicia con una carta no especial de la baraja que se está
	 * utilizando en la partida.
	 * @param deck Baraja que se utilizará en la partida.
	 */
	public DiscardPile(Deck deck) {
		this.cards.clear(); /**Inicia sin cartas.*/
		this.setDeck(deck);
		this.cards.add(deck.giveCard(RandomGenerator.noSpecialUNOCard()));/**Carta no especial*/
		
	}
	/**
	 * Recive una carta específica de la mano de un jugador.
	 * @param hand Mano del jugador que dará la carta.
	 * @param card Carta que el jugadoro dará.
	 */
	public void receiveCard(Hand hand, Card card) {
		try {
			this.cards.add(hand.giveCard(card));		
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
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
}
