<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>UNO-Game</title>
    <script src="../jquery-3.4.1.min.js"></script>
</head>

<body>
    <% out.print(String.format("<input type='hidden' id='gameID' value='%s'>",request.getParameter("gameID").toString().trim())); %>
    <h1>Esperate un rato</h1>
    <h6>Debes lavarte las manos a menudo.</h6>
    <script>
        var gameID = document.querySelector("#gameID").value;
        console.log(gameID);
        var intervalID;

        intervalID = setInterval(function () {
            $.get("getContent.jsp",{"file":`${gameID}Hand.json`}, function (data) { 
                if(data != null && data != undefined){
                    clearInterval(intervalID);
                    setTimeout( function () {
                        window.location="UNO.jsp";
                    }, 4000);
                }
             })
        }, 100)

    </script>
</body>

</html>