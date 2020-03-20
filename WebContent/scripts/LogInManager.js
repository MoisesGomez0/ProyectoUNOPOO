function LogInManager(name = null, nPlayers = 0, gameId = null) {

	this.name = name;
	this.nPlayers = nPlayers;
	this.gameId = gameId;
	this.data = null;
	this.intervalId = null;

	this.generateGameId = function () {
		var random = new RandomGenerator();
		this.gameId = random.randomAlpha(8);
		return this.gameId;
	}

	this.generateData = function () {
		this.data = { "gameId": this.gameId, "players": [this.name], "nPlayers": this.nPlayers };
		return this.data;
	}

	this.saveData = function () {
		$.get("write.jsp", { "file": "logIn.json", "content": JSON.stringify(this.data), "override": true }, null);
	}

	this.verifyLoginAndRedirec = function () {
		var id = this.gameId;
		var intervalId = setInterval(function () {
			$.get("getContent.jsp", { "file": "logIn.json" }, function (data) {
				data = JSON.parse(data.trim());
				console.log(data.players.length == data.nPlayers)
				if (data.players.length == data.nPlayers) {
					$.get("gameMaker.jsp", { "hostPlayer": data.players[0], "guestPlayer": data.players[1], "gameId": id },function(data){
						console.log("redirijo a UNO.jsp");
						location="UNO.jsp"});
					clearInterval(intervalId);
				}
			})
		}, 100);

	}

	this.getIn = function () {
		var playerName = this.name;
		var id = this.gameId;
		$.get("getContent.jsp", { "file": "logIn.json" }, function (data) {
			if (data == "fail") {
				document.querySelector("Ocurrió un eror, no se encontró el archivo.")
			} else {
				data = JSON.parse(data.trim());
				if (data.gameId == id) {
					data.players.push(playerName);
					$.get("write.jsp", { "file": "logIn.json", "content": JSON.stringify(data), "override": true }, null);
				}else{
					document.querySelector("Al parecer el código es incorrecto.");
				}
			}
		});
	}
}