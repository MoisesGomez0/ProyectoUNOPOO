package core;

public class Player {

	private String name;
	private int id;
	private Hand hand = new Hand();
	private boolean turn = false; /**Al momento de generar el juego se manejaran los estados de turn.*/
	
	
	/**Construnctor vacío de la clase.*/
	public Player() {
		
	}
	/**
	 * Constructor sobrecargado de la clase.
	 * @param name Nombre del jugador.
	 * @param id Identificación del jugador.
	 * @param hand Mano que administra las cartas del jugador.
	 * @param turn Estado booleano que especifica si el jugador está en turno.
	 */
	public Player(String name, int id, Hand hand, boolean turn) {
		this.setName(name);
		this.setId(id);
		this.setHand(hand);
		this.setTurn(turn);
	}
	
	/**
	 * Toma la primera carta de la baraja.
	 */
	public void takeCard() {
		this.hand.takeCard();
	}
	
	/**
	 * Suelta una carta en la pila de descarte.
	 * @param card Carta que soltará.
	 */
	public void dropCard(Card card) {
		this.hand.dropCard(card);
	}
	
	public boolean challenge(Player player) {
		return true;
	}
	
	public String toJSON(int tab) {
		StringBuilder result = new StringBuilder("");
		
		result.append(String.format("%s{\n", "\t".repeat(tab)));
		result.append(String.format("%s\"id\":%s,\n", "\t".repeat(tab),this.id));
		result.append(String.format("%s\"name\":%s,\n", "\t".repeat(tab),this.name));
		result.append(String.format("%s\"turn\":%s,\n", "\t".repeat(tab),this.turn));
		result.append(String.format("%s\"hand\":%s", "\t".repeat(tab),(this.hand.getCards().isEmpty())?"null\n":String.format("\n   %s\n", this.hand.toString(tab+2))));
		result.append(String.format("%s}\n", "\t".repeat(tab)));

		return result.toString();
	}
	
	public String toJSON() {
		StringBuilder result = new StringBuilder("");
		
		result.append("{\n");
		result.append(String.format("\t\"id\":%s,\n", this.id));
		result.append(String.format("\t\"name\":%s,\n", this.name));
		result.append(String.format("\t\"turn\":%s,\n", this.turn));
		result.append(String.format("\t\"hand\":%s", (this.hand.getCards().isEmpty())?"null\n":String.format("\n   %s\n", this.hand.toString(2))));
		result.append("}\n");

		return result.toString();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the hand
	 */
	public Hand getHand() {
		return hand;
	}
	/**
	 * @param hand the hand to set
	 */
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	/**
	 * @return the turn
	 */
	public boolean isTurn() {
		return turn;
	}
	/**
	 * @param turn the turn to set
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	/**Pruebas de la clase.*/
	public static void main(String[] args) {
		Player p = new Player();
		p.getHand().getCards().add(RandomGenerator.UNOCard());
		p.getHand().getCards().add(RandomGenerator.UNOCard());
		p.getHand().getCards().add(RandomGenerator.UNOCard());
		p.getHand().getCards().add(RandomGenerator.UNOCard());
		System.out.println(p.toJSON(1));
	}
}
