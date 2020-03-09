<%@page import="clases.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
	
    FileManager fm = new FileManager();
    
    fm.create("logIn.json",String.format("{\"gameID\":\"%s\",\"hostPlayer\":\"%s\",\"guestPlayer\":\"%s\"}",
    		request.getParameter("gameID"),
    		request.getParameter("hostPlayer"),
    		request.getParameter("guestPlayer")
    		));
%>