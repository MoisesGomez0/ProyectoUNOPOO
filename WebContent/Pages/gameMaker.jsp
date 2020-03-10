<%@page import="core.Deck"%>
<%@page import="clases.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UNO-Game</title>
</head>
<body>
<%
	FileManager fm = new FileManager();
	Deck deck = new Deck();
	fm.create(String.format("%sHand.json",request.getParameter("gameID").toString().trim()), deck.toString());
%>
<h1>Estamos preparando todo...</h1>
<h6>Recordá tomar agua.</h6>
<script type="text/javascript">
	setTimeout(function(){
		window.location="UNO.jsp";
	}, 5000);
</script>
</body>
</html>