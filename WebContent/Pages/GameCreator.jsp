<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="clases.FileManager"%>
<%
FileManager fm = new FileManager("/home/moises/Documentos/Prototipos/Prototipos/WebContent/Pages/");

fm.create("game.json", String.format("{\"ID\":\"%s\",\"j1\":\"%s\",\"j2\":\"%s\"}",
		request.getParameter("ID"),
		request.getParameter("j1"),
		request.getParameter("j2"))
		);

%>