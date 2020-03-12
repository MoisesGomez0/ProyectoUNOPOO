<%@page import="core.Hand"%>
<%@page import="core.Player"%>
<%@page import="core.Game"%>
<%@page import="core.Deck"%>
<%@page import="clases.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Game game = new Game(
			request.getParameter("gameID").toString().strip(),
			new Player(request.getParameter("hostPlayer").toString().trim(), new Hand()),
			new Player(request.getParameter("guestPlayer").toString().trim(), new Hand())
			);
	
	game.generateGame();
	game.saveMemory();
	response.sendRedirect(String.format("UNO.jsp?name=%s",request.getParameter("hostPlayer").toString().trim()));


%>