<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="core.*"%>
<%
	String gameId = request.getParameter("gameId");
	int currentPlayerId = Integer.parseInt(request.getParameter("currentPlayerId"));
	EColor currentColor = EColor.parse(request.getParameter("currentColor"));
	boolean clockWise = Boolean.parseBoolean(request.getParameter("clockWise"));
	
	String hostPlayerName = request.getParameter("hostPlayerName");
	int hostPlayerId = 0;
	Hand hostPlayerHand = new Hand(request.getParameter("hostPLayerHand"));
	Player hostPlayer = new Player(hostPlayerName,hostPlayerId,hostPlayerHand);
	
	
	String guestPlayerName = request.getParameter("guestPlayerName");
	int guestPlayerId = 1;
	Hand guestPlayerHand = new Hand(request.getParameter("guestPlayerHand"));
	Player guestPlayer = new Player(guestPlayerName,guestPlayerId,guestPlayerHand);

	Deck deck = new Deck(request.getParameter("deck"));
	DiscardPile discardPile = new DiscardPile(request.getParameter("discardPile"));
	
	Game game = new Game(gameId,currentPlayerId,currentColor,clockWise,hostPlayer,guestPlayer,deck,discardPile);
	
	String action = request.getParameter("action");

	if (action.equals("playerTakeCard")) {
		game.playerTakeCard();
		game.saveMemory();
	}
%>