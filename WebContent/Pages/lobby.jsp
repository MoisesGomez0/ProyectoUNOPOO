<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>UNO-Game</title>
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="../scripts/LogInManager.js"></script>
</head>

<body>
    <% out.print(String.format("<input type='hidden' id='name' value='%s'>",request.getParameter("name").toString().trim())); %>
    <%out.print(String.format("<input type='hidden' id='gameId' value='%s'>",request.getParameter("gameId").toString().trim()));%>    
    <h1>Esperate un rato</h1>
    <form action="UNO.jsp" method="POST" id="dataForm" style="visibility: hidden;">
        <input type="text" name="name" id="formName">
        <input type="text" name="nPlayers" id="formNPlayers">
        <input type="text" name="gameId" id="gameId">
    </form>
    <script>
		var playerName = document.querySelector("#name").value;
		var gameId = document.querySelector("#gameId").value;
		
		var logInManager = new LogInManager(playerName,0,gameId);
		
		logInManager.getIn();
		
		logInManager.verifyLoginAndRedirect();
		
		
        
    </script>
</body>

</html>