package core;

import java.util.ArrayList;
/**
 * Maneja todas las acciones que se pueden hacer en una partida.
 * @author leonardo
 *
 */
public class Game {
	
	private String id;
	private int round = 1; /**Número de ronda actual. Esto ayuda a la ScoreBoard.*/
	private boolean clockWise = true;/**Sentido de juego horario por defecto.*/
	private ArrayList<Player> players = new ArrayList<>();/**Contiene los jugadores de la partida.*/
	private Deck deck = new Deck();
	private DiscardPile discardPile = new DiscardPile(deck);
	
	public Game(String id, ArrayList<Player> players) {
		this.id = id;
		this.players = players;
	}
	
	public Game(String id, ArrayList<Player> players, int round) {
		if (round < 1) {
			throw new IllegalArgumentException("El número de rondas no puede ser menor a 1.");
		}
		this.id = id;
		this.players = players;
		this.round = round;
	}
	
	/**
	 * Genera la partida.
	 * 	-Selecciona quien empieza.
	 * 	-Baraja la baraja.
	 * 	-Reparte las cartas (7 por cada jugador).
	 * 	-Agrega la primera carta a la DiscardPile.
	 */
	public void generateGame() {
		this.players.get(RandomGenerator.randomInt(0, players.size()-1)).setTurn(true);;/**Selecciona un jugador al azar para iniciar.*/
		this.deck.shuffle();/**Baraja la baraja.*/
		dealCards();
	}
	
	/**
	 * Reparte las cartas de cada jugador (7 por jugador). 
	 */
	private void dealCards() {
		for (Player player : players) {
			player.getHand().setDeck(this.deck);
			for (int i = 0; i < 7; i++) {
				player.takeCard();
			}
		}
	}
}
