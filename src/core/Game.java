package core;

/**
 * Maneja todas las acciones que se pueden hacer en una partida.
 * @author leonardo
 *
 */
public class Game {
	
	private String id;
	private int currentPlayerId; /**Id del jugador en turno.*/
	private EColor currentColor; /**Color de carta que se está jugando.*/
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
	
	public Game(String id, int currentPlayerId, EColor currentColor, boolean clockWise, Player p1, Player p2,  Deck deck, DiscardPile discardPile ) {
			
		if (currentColor.getName() == EColor.BLACK.getName()) { /**Si es de color negro.*/
			throw new IllegalArgumentException("El color BLACK no puede ser currentColor");
		}
		
		this.setId(id);
		this.setCurrentColor(currentColor);
		this.setPlayer1(p1);
		this.setPlayer2(p2);
		
		this.getPlayer1().setId(0);
		this.getPlayer2().setId(1);
		this.player1.getHand().setDeck(this.deck);
		this.player2.getHand().setDeck(this.deck);
		
		this.clockWise = clockWise;
		this.deck = deck;
		this.discardPile = discardPile;
		
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
	 * El jugador en turno toma una carta.
	 * @param playerId Id del jugador en turno.
	 */
	public void playerTakeCard() {
		if (this.currentPlayerId == this.player1.getId()) {
			this.player1.takeCard();
		}else if(this.currentPlayerId == this.player2.getId()){
			this.player2.takeCard();
		}
		
		this.saveMemory();
	}
	
	/**
	 * Establece las acciones según la carta que suelte el jugador en turno.
	 * @param playerId Id del jugador en turno.
	 * @param card Carta que quiere soltar.
	 * @param selectedColor El color que elige en caso de ser una carta especial.
	 * @param challenge Si el oponente lo retón en caso de que la carta sea un Draw Four.
	 */
	public void playerDropCard(int playerId, Card card, EColor selectedColor, boolean challenge) {
		
		Player currentPlayer = new Player();
		Player oponent = new Player();
		
		if (this.currentPlayerId != playerId) {
			throw new IllegalArgumentException("El jugador no puede soltar una carta si no es su turno.");
		}else {
			if (this.currentPlayerId == this.player1.getId()) {
				currentPlayer = this.player1;
				oponent=this.player2;
			}else{
				currentPlayer = this.player2;
				oponent = this.player1;
			}
		}
		
		
		int lastCardIndex = this.discardPile.getCards().size()-1;
		Card lastCard = this.discardPile.getCards().get(lastCardIndex); /**Ultima carta de la discardPile.*/
		
		if (card.getColor().equals(this.currentColor) || /**Tiene el mismo color que la última carta de la discardPile.*/
			card.getValue().equals(lastCard.getValue()) || /**Tiene el mismo número o símbolo de la última carta de la discardPile*/
			card.getColor().equals(EColor.BLACK)) { /**Es una carta especial.*/
			
			switch (card.getValue()) {
			case DFOUR: /**Si es una carta Draw Four.*/
				currentPlayer.dropCard(card);
				this.currentColor = selectedColor;
				
				/**
				 * Verifica que el jugador tenga una carta del mismo color o valor que la última carta de la discardPile.
				 */
				if (challenge) {
					if (currentPlayer.getHand().checkColor(lastCard.getColor())|| 
						currentPlayer.getHand().checkValue(lastCard.getValue())) {
						currentPlayer.drawSix(); /**Toma seis cartas.*/
						this.currentPlayerId = oponent.getId(); /**Será turno del otro jugador.*/
					}else {
						oponent.drawSix(); /**El retador toma seis cartas y pierde turno.*/
					}
				}
				break;
			
			case WILD: /**Si es una Carta Wild*/
				currentPlayer.dropCard(card); /**Suelta la carta.*/
				this.currentColor = selectedColor; /**Selecciona el color de la siguiente carta.*/
				this.currentPlayerId = oponent.getId(); /**Ahora es turno del oponente.*/
				
				break;
				
			case DTWO: /**Si es una carta Draw Two*/
				currentPlayer.dropCard(card); /**Suelta la carta.*/
				this.currentColor = card.getColor(); /**Cambia de color.*/
				oponent.drawTwo(); /**El oponente toma dos cartas y pierde turno.*/
				break;
				
			case REVERSE: /**Si es una carta Reverse*/
				currentPlayer.dropCard(card); /**Suelta la carta.*/
				this.clockWise = !this.clockWise; /**Cambia el sentido de juego.*/
				this.currentColor = card.getColor(); /**Cambia de color.*/
				this.currentPlayerId = oponent.getId(); /**Ahora es turno del oponente.*/
				break;
				
			case SKIP: /**Si es una carta Skip*/
				currentPlayer.dropCard(card); /**Suelta la carta y el oponente pierde turno.*/
				this.currentColor = card.getColor(); /**Cambia de color.*/
				break;

			default: /**Si es cualquier otro número.*/
				currentPlayer.dropCard(card); /**Suelta la carta*/
				this.currentColor = card.getColor(); /**Cambia de color.*/
				this.currentPlayerId = oponent.getId(); /**Ahora es turno del oponente*/
				break;
			}
			
			/**Actualiza los jugadores*/
			if (this.currentPlayerId == this.player1.getId()) {
				this.player1 = currentPlayer;
				this.player2 = oponent;
			}else{
				this.player2= currentPlayer;
				this.player1 = oponent;
			}
			
		}else {
			throw new IllegalArgumentException(String.format("No se puede soltar la carta: %s por que la ultima carta de la discardPile es: %s", card,lastCard));
		}
		
		this.saveMemory();
	}
	
	/**
	 * Guarda la partida en un archivo JSON en memoria.
	 */
	public void saveMemory() {
		FileManager fm = new FileManager("src/memory/");
		fm.write("game.json", this.toString());
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
		result.append(String.format("%s\"currentPlayerId\": %s,\n","\t".repeat(tab),this.getCurrentPlayerId()));
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
		result.append(String.format("%s\"currentPlayerId\": %s,\n","\t".repeat(tab),this.getCurrentPlayerId()));
		result.append(String.format("%s\"clockWise\": %s,\n","\t".repeat(tab),this.isClockWise()));
		result.append(String.format("%s\"player1\":\n%s,\n","\t".repeat(tab),this.getPlayer1().toJSON(tab+1)));
		result.append(String.format("%s\"player2\":\n%s,\n","\t".repeat(tab),this.getPlayer2().toJSON(tab+1)));
		result.append(String.format("%s\"deck\": \n%s,\n","\t".repeat(tab),this.getDeck().toString(tab+1)));
		result.append(String.format("%s\"discardPile\": \n%s\n","\t".repeat(tab),this.getDiscardPile().toString(tab+1)));
		result.append(String.format("}"));
		
		return result.toString();
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

	/**
	 * @return the currentColor
	 */
	public EColor getCurrentColor() {
		return currentColor;
	}

	/**
	 * @param currentColor the currentColor to set
	 */
	public void setCurrentColor(EColor currentColor) {
		this.currentColor = currentColor;
	}

	/**Pruebas de la clase.*/
	public static void main(String[] args) {
		Player p1 = new Player("josn",new Hand());
		Player p2 = new Player("jon",new Hand());
		
		
		Game game = new Game("1a46bt", p1, p2);
		game.generateGame();
		//System.out.println(game);
		game.saveMemory();
	}
}