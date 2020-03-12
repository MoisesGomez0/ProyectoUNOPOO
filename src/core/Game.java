package core;

/**
 * Maneja todas las acciones que se pueden hacer en una partida.
 * @author leonardo
 *
 */
public class Game {
	
	private String id;
	private int currentPlayerId; /**Id del jugador en turno.*/
	private boolean clockWise = true;/**Sentido de juego horario por defecto.*/
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Deck deck = new Deck();
	private DiscardPile discardPile = new DiscardPile(getDeck());
	
	public Game(String id, Player p1, Player p2) {
		this.setId(id);
		this.setPlayer1(p1);
		this.setPlayer2(p2);
		this.getPlayer1().setId(0);
		this.getPlayer2().setId(1);
	}
	
	/**
	 * Genera la partida.
	 * 	-Selecciona quien empieza.
	 * 	-Baraja la baraja.
	 * 	-Reparte las cartas (7 por cada jugador).
	 * 	-Agrega la primera carta a la DiscardPile.
	 */
	public void generateGame() {
		this.setCurrentPlayerId(RandomGenerator.randomInt(0, 1));
		dealCards();/**Reparte las cartas.*/
		this.getDeck().shuffle();/**Baraja la baraja.*/
		this.getDiscardPile().receiveFirstCard();/**Agrega la primera carta a la DiscardPile.*/
	}
	
	/**
	 * Reparte las cartas de cada jugador (7 por jugador). 
	 */
	private void dealCards() {
		this.deck.shuffle();
		this.getPlayer1().getHand().setDeck(this.getDeck());
		for (int i = 0; i < 7; i++) {
			this.getPlayer1().takeCard();
		}
		this.getPlayer2().getHand().setDeck(this.getDeck());
		for (int i = 0; i < 7; i++) {
			this.getPlayer2().takeCard();
		}
	}
	
	/**
	 * Genera una representación JSON del objeto.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		
		int tab = 1;
		
		result.append(String.format("{\n"));
		result.append(String.format("%s\"id\": \"%s\",\n","\t".repeat(tab),this.getId()));
		result.append(String.format("%s\"currentPlayerId\": \"%s\",\n","\t".repeat(tab),this.getCurrentPlayerId()));
		result.append(String.format("%s\"clockWise\": %s,\n","\t".repeat(tab),this.isClockWise()));
		result.append(String.format("%s\"player1\":\n%s,\n","\t".repeat(tab),this.getPlayer1().toJSON(tab+1)));
		result.append(String.format("%s\"player2\":\n%s,\n","\t".repeat(tab),this.getPlayer2().toJSON(tab+1)));
		result.append(String.format("%s\"deck\": \n%s,\n","\t".repeat(tab),this.getDeck().toString(tab+1)));
		result.append(String.format("%s\"discardPile\": \n%s\n","\t".repeat(tab),this.getDiscardPile().toString(tab+1)));
		result.append(String.format("}"));
		
		return result.toString();
	}
	
	/**
	 * 
	 * @param tab Número de tabulados
	 * @return Representación JSON del objeto.
	 */
	public String toString(int tab) {
		StringBuilder result = new StringBuilder("");
		
		result.append(String.format("{\n"));
		result.append(String.format("%s\"id\": \"%s\",\n","\t".repeat(tab),this.getId()));
		result.append(String.format("%s\"clockWise\": %s,\n","\t".repeat(tab),this.isClockWise()));
		result.append(String.format("%s\"player1\":\n%s,\n","\t".repeat(tab),this.getPlayer1().toJSON(tab+1)));
		result.append(String.format("%s\"player2\":\n%s,\n","\t".repeat(tab),this.getPlayer2().toJSON(tab+1)));
		result.append(String.format("%s\"deck\": \n%s,\n","\t".repeat(tab),this.getDeck().toString(tab+1)));
		result.append(String.format("%s\"discardPile\": \n%s\n","\t".repeat(tab),this.getDiscardPile().toString(tab+1)));
		result.append(String.format("}"));
		
		return result.toString();
	}
	
	public void saveMemory() {
		FileManager fm = new FileManager();
		fm.write("game.json", this.toString());
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the currentPlayerId
	 */
	public int getCurrentPlayerId() {
		return currentPlayerId;
	}

	/**
	 * @param currentPlayerId the currentPlayerId to set
	 */
	public void setCurrentPlayerId(int currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}

	/**
	 * @return the clockWise
	 */
	public boolean isClockWise() {
		return clockWise;
	}

	/**
	 * @param clockWise the clockWise to set
	 */
	public void setClockWise(boolean clockWise) {
		this.clockWise = clockWise;
	}

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(Player player2) {
		this.player2 = player2;
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

	/**Pruebas de la clase.*/
	public static void main(String[] args) {
		Player p1 = new Player("josn",new Hand());
		Player p2 = new Player("jon",new Hand());
		
		
		Game game = new Game("1a46bt", p1, p2);
		game.generateGame();
		//System.out.println(game);
		game.saveMemory();
		
		/**
		FileManager fm = new FileManager();
		System.out.println(fm.wpath());
		*/
		
	}
}