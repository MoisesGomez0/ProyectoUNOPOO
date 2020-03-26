package core;

/**
 * Pila de cartas descartadas de la partida.
 * Es donde los jugadores pondrán sus cartas a medida que avanza la partida.
 */
public class DiscardPile extends Deck{
	
	private Deck deck;
	/**
	 * Constructor de la clase.
	 * @param deck Baraja que se utilizará en la partida.
	 */
	public DiscardPile(Deck deck) {
		this.cards.clear();
		this.setDeck(deck);
		
	}
    /**
     * Instancia la DiscardPile con las cartas especificadas.
     * 
     * @param card Cartas requeridas en la DiscardPile.
     * Se debe validar con la expreción regula contenida en ERegex.DECK.
     */	
	public DiscardPile (String cards) {
		super(cards);
	}
	/**
	 * Recive una carta específica de la mano de un jugador.
	 * @param hand Mano del jugador que dará la carta.
	 * @param card Carta que el jugadoro dará.
	 */
	public void receiveCard(Hand hand, Card card) {
		this.cards.add(hand.giveCard(card));		
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
