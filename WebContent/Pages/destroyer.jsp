<%@page import="core.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
    
    if(request.getParameter("destroy") != null){
    	FileManager fm = new FileManager();
    	fm.create("logIn.json", "");
    	fm.create("game.json", "");
    	out.print(String.format("{\"status\":%s}",fm.deleteFile("logIn.json") == fm.deleteFile("game.json")));
    }else{
    	out.print("{\"status\":false}");
    }
 %>