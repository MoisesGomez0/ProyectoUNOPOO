<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="core.*"%>
<%
	String gameId = request.getParameter("gameId"); /**ID de la partida.*/
	int currentPlayerId = Integer.parseInt(request.getParameter("currentPlayerId")); /**ID del jugador en turno.*/
	EColor currentColor = EColor.parse(request.getParameter("currentColor"));/**Último color de la discardPile.*/
	boolean playerChallenge = Boolean.getBoolean(request.getParameter("playerChallenge")); /**Si el el jugador retó al oponente.*/
	boolean clockWise = Boolean.parseBoolean(request.getParameter("clockWise"));/**Sentido del juego.*/
	
	String hostPlayerName = request.getParameter("hostPlayerName"); /**Nombre del jugador que crea la partida.*/
	int hostPlayerId = 0; /**ID del jugador que crea la partida. Siempre es 0.*/
	Hand hostPlayerHand = new Hand(request.getParameter("hostPLayerHand")); /**Mano del jugador que crea la partida.*/
	Player hostPlayer = new Player(hostPlayerName,hostPlayerId,hostPlayerHand);/**Jugador que crea la partida.*/
	
	
	String guestPlayerName = request.getParameter("guestPlayerName");/**Nombre del jugador que entra en la partida.*/
	int guestPlayerId = 1;/**ID del jugador que entra en la partida.*/
	Hand guestPlayerHand = new Hand(request.getParameter("guestPlayerHand"));/**Mano del jugador que entra en la partida.*/
	Player guestPlayer = new Player(guestPlayerName,guestPlayerId,guestPlayerHand);/**Jugador que entra en la partida.*/

	Deck deck = new Deck(request.getParameter("deck"));/**Baraja de la partida.*/
	DiscardPile discardPile = new DiscardPile(request.getParameter("discardPile"));/**Pila de descartes de la partida.*/
	
	Game game = new Game(gameId,currentPlayerId,currentColor,playerChallenge,clockWise,hostPlayer,guestPlayer,deck,discardPile);/**Partida.*/
	
	String action = request.getParameter("action");/**Acción requerida por el frontend.*/
	String droppedCard = request.getParameter("droppedCard"); /**Carta que soltó el jugador en turno.*/
	String selectedColor = request.getParameter("selectedColor"); /**Color que seleccionó el jugador en turno.*/

	if (action.equals("playerTakeCard")) { /**El jugador toma una carta.*/
		game.playerTakeCard();
		game.saveMemory();
	}else if(action.equals("playerDropCard")){ /**El jugador suelta una carta.*/
		Card card = new Card(droppedCard);
		EColor color = EColor.parse(selectedColor);
		game.playerDropCard(card, color);
		game.saveMemory();
	}
%>