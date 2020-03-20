<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="core.Hand"%>
<%@page import="core.Player"%>
<%@page import="core.Game"%>
<%@page import="core.Deck"%>
<%@page import="clases.FileManager"%>
<%
	
	Game game = new Game(
			request.getParameter("gameId").toString().strip(),
			new Player(request.getParameter("hostPlayer").toString().trim(), new Hand()),
			new Player(request.getParameter("guestPlayer").toString().trim(), new Hand())
			);
	
	game.generateGame();
	game.saveMemory();

%>