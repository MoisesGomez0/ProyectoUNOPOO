package core;

public class Player {

	private String name;
	private int id;
	private Hand hand;
	
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
	public Player(String name, int id, Hand hand) {
		this.setName(name);
		this.setId(id);
		this.setHand(hand);
	}
	
	/**
	 * Constructor sobrecargado de la clase.
	 * @param name Nombre del jugador.
	 * @param hand Mano que administra las cartas del jugador.
	 * @param turn Estado booleano que especifica si el jugador está en turno.
	 */
	public Player(String name, Hand hand) {
		this.setName(name);
		this.setHand(hand);
	}
	
	/**
	 * Toma la primera carta de la baraja.
	 */
	public void takeCard() {
		this.hand.takeCard();
	}
	
	/**
	 * Toma las seis primeras cartas de la baraja.
	 */
	public void drawSix() {
		for (int i = 0; i < 6; i++) {
			this.hand.takeCard();
		}
	}
	
	/**
	 * Toma las cuatro primeras cartas de la baraja.
	 */
	public void drawFour() {
		for (int i = 0; i < 4; i++) {
			this.hand.takeCard();
		}
	}
	
	/**
	 * Toma las seis primeras cartas de la baraja.
	 */
	public void drawTwo() {
		for (int i = 0; i < 2; i++) {
			this.hand.takeCard();
		}
	}
	
	/**
	 * Suelta una carta en la pila de descarte.
	 * @param card Carta que soltará.
	 */
	public void dropCard(Card card) {
		try {
			this.hand.dropCard(card);
			
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("El jugador no tiene la carta: %s", card));
		}
	}
	
	/**
	 * Crea una representación JSON del objeto.
	 * @param tab Cantidad de tabs que tendrá al inicio.
	 * @return JSON del objeto.
	 */
	public String toJSON(int tab) {
		StringBuilder result = new StringBuilder("");
		
		result.append(String.format("%s{\n", "\t".repeat(tab)));
		result.append(String.format("%s\"id\":%s,\n", "\t".repeat(tab+1),this.id));
		result.append(String.format("%s\"name\":\"%s\",\n", "\t".repeat(tab+1),this.name));
		result.append(String.format("%s\"hand\":%s", "\t".repeat(tab+1),(this.hand.getCards().isEmpty())?"null\n":String.format("\n   %s\n", this.hand.toString(tab+2))));
		result.append(String.format("%s}", "\t".repeat(tab)));

		return result.toString();
	}
	
	/**
	 * Crea una representación JSON del objeto.
	 * @return JSON del objeto.
	 */	
	public String toJSON() {
		StringBuilder result = new StringBuilder("");
		
		result.append("{\n");
		result.append(String.format("\t\"id\":%s,\n", this.id));
		result.append(String.format("\t\"name\":\"%s\",\n", this.name));
		result.append(String.format("\t\"hand\":%s", (this.hand.getCards().isEmpty())?"null\n":String.format("\n   %s\n", this.hand.toString(2))));
		result.append("}");

		return result.toString();
	}
	/**
	 * Verifica si dos objetos son iguales.
	 * @param player Objeto a comparar.
	 * @return true si son iguales.
	 */
	public boolean equals(Player player) {
		if (this.name == player.name && this.id == player.id && this.hand.equals(player.hand)) {
			return true;
		}
		
		return false;
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
