<%@page import="clases.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
FileManager fm = new FileManager("/home/moises/Documentos/Prototipos/Prototipos/WebContent/Pages/");
out.print(fm.read(request.getParameter("file")));

%>