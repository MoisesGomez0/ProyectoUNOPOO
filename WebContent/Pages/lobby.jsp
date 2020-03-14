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
    <% out.print(String.format("<input type='hidden' id='guestPlayer' value='%s'>",request.getParameter("guestPlayer").toString().trim())); %>
    
    <h1>Esperate un rato</h1>
    <h6>Debes lavarte las manos a menudo.</h6>
    <script>

        intervalID = setInterval(function () {
            $.get("getContent.jsp",{"file":"game.json"}, function (data) { 
                if(data != null && data != undefined){
                    clearInterval(intervalID);
                    setTimeout( function () {
                        window.location=`UNO.jsp?name=\${document.querySelector("#guestPlayer").value}`;
                    }, 4000);
                }
             })
        }, 100)

    </script>
</body>

</html>