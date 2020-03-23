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
	private Player hostPlayer = new Player();
	private Player guestPlayer = new Player();
	private Deck deck = new Deck();
	private DiscardPile discardPile = new DiscardPile(getDeck());
	
	public Game(String id, Player p1, Player p2) {
		this.setId(id);
		this.setHostPlayer(p1);
		this.setGuestPlayer(p2);
		this.getHostPlayer().setId(0);
		this.getGuestPlayer().setId(1);
	}
	
	public Game(String id,
				int currentPlayerId,
				EColor currentColor,
				boolean clockWise,
				Player p1,
				Player p2,
				Deck deck,
				DiscardPile discardPile ) {
			
		if (currentColor.getName() == EColor.BLACK.getName()) { /**Si es de color negro.*/
			throw new IllegalArgumentException("El color BLACK no puede ser currentColor");
		}
		
		this.setId(id);
		this.setCurrentColor(currentColor);
		this.currentPlayerId = currentPlayerId;
		this.setHostPlayer(p1);
		this.setGuestPlayer(p2);
		this.clockWise = clockWise;
		this.deck = deck;
		this.discardPile = discardPile;
		this.discardPile.setDeck(this.deck); 
		
		this.getHostPlayer().setId(0);
		this.getGuestPlayer().setId(1);
		
		this.hostPlayer.getHand().setDeck(this.deck);
		this.guestPlayer.getHand().setDeck(this.deck);
		this.hostPlayer.getHand().setDiscardPile(this.discardPile);
		this.guestPlayer.getHand().setDiscardPile(this.discardPile);
		
		
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
		this.currentColor = this.discardPile.cards.get(0).getColor();
	}
	
	/**
	 * Reparte las cartas de cada jugador (7 por jugador). 
	 */
	private void dealCards() {
		this.deck.shuffle();
		
		this.hostPlayer.getHand().setDeck(this.getDeck());
		for (int i = 0; i < 7; i++) {
			this.hostPlayer.takeCard();
		}
		
		this.guestPlayer.getHand().setDeck(this.getDeck());
		for (int i = 0; i < 7; i++) {
			this.guestPlayer.takeCard();
		}
	}
	
	private Player currentPlayer() {
		if (this.currentPlayerId == this.hostPlayer.getId()) {
			return this.hostPlayer;
		}else if(this.currentPlayerId == this.guestPlayer.getId()){
			return this.guestPlayer;
		}else {
			throw new IllegalArgumentException("No existen jugadores.");
		}
		
	}
	
	private void nextPlayer() {
		if (this.currentPlayerId == this.hostPlayer.getId()) {
			this.currentPlayerId = this.guestPlayer.getId();
		}else if(this.currentPlayerId == this.guestPlayer.getId()){
			this.currentPlayerId = this.hostPlayer.getId();
		}else {
			throw new IllegalArgumentException("No existen jugadores.");
		}
	}
	/**
	 * El jugador en turno toma una carta.
	 * @param playerId Id del jugador en turno.
	 */
	public void playerTakeCard() {
		this.currentPlayer().takeCard();
		this.nextPlayer();
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
			if (this.currentPlayerId == this.hostPlayer.getId()) {
				currentPlayer = this.hostPlayer;
				oponent=this.guestPlayer;
			}else{
				currentPlayer = this.guestPlayer;
				oponent = this.hostPlayer;
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
				this.currentPlayerId = oponent.getId();
			
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
			if (this.currentPlayerId == this.hostPlayer.getId()) {
				this.hostPlayer = currentPlayer;
				this.guestPlayer = oponent;
			}else{
				this.guestPlayer= currentPlayer;
				this.hostPlayer = oponent;
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
		//FileManager fm = new FileManager("src/memory/");
		FileManager fm = new FileManager("");
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
		result.append(String.format("%s\"currentColor\": \"%s\",\n","\t".repeat(tab),this.currentColor.getName()));
		result.append(String.format("%s\"clockWise\": %s,\n","\t".repeat(tab),this.isClockWise()));
		result.append(String.format("%s\"hostPlayer\":\n%s,\n","\t".repeat(tab),this.getHostPlayer().toJSON(tab+1)));
		result.append(String.format("%s\"guestPlayer\":\n%s,\n","\t".repeat(tab),this.getGuestPlayer().toJSON(tab+1)));
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
		result.append(String.format("%s\"currentColor\": \"%s\",\n","\t".repeat(tab),this.currentColor.getName()));
		result.append(String.format("%s\"clockWise\": %s,\n","\t".repeat(tab),this.isClockWise()));
		result.append(String.format("%s\"hostPlayer\":\n%s,\n","\t".repeat(tab),this.getHostPlayer().toJSON(tab+1)));
		result.append(String.format("%s\"guestPlayer\":\n%s,\n","\t".repeat(tab),this.getGuestPlayer().toJSON(tab+1)));
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
	 * @return the hostPlayer
	 */
	public Player getHostPlayer() {
		return hostPlayer;
	}

	/**
	 * @param hostPlayer the hostPlayer to set
	 */
	public void setHostPlayer(Player hostPlayer) {
		this.hostPlayer = hostPlayer;
	}

	/**
	 * @return the guestPlayer
	 */
	public Player getGuestPlayer() {
		return guestPlayer;
	}

	/**
	 * @param guestPlayer the guestPlayer to set
	 */
	public void setGuestPlayer(Player guestPlayer) {
		this.guestPlayer = guestPlayer;
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
		
		System.out.println(game.toString());
		
		/**
		FileManager fm = new FileManager();
		System.out.println(fm.wpath());
		*/
		
	}
}