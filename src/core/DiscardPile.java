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
		this.cards.clear();
		this.setDeck(deck);
		
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
	 * Recive una carta aleatoria no especial para iniciar la partida.
	 */
	public void receiveFirstCard() {
		boolean x = false;
		
		while (!x) {
			try {
				this.cards.add(deck.giveCard(RandomGenerator.noActionUNOCard()));	
				x = true;
			} catch (Exception e) {
				x = false;
			}			
		}
	}
	
	/**
	 * Verifica si dos objetos son iguales.
	 * @param discardPile Objeto a comparar.
	 * @return true si son iguales.
	 */
	public boolean equals(DiscardPile discardPile) {
		if (this.deck.equals(discardPile.getDeck())&& this.cards == discardPile.getCards()) {
			return true;
		}
		
		return false;
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
	
	public static void main(String[] args) {
		Deck a = new Deck();
		DiscardPile ad = new DiscardPile(a);
		DiscardPile bd = ad;
		System.out.println(ad.equals(bd));
		
		bd = new DiscardPile(a.shuffle());
		System.out.println(ad.equals(bd));
		
		
	}
}
