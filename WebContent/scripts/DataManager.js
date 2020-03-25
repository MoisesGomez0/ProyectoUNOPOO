function DataManager() {

	this.update = function () {
		$.get("getContent.jsp", { "file": "game.json" }, function (data) {
			data = JSON.parse(data.trim());
			frontManager.json = info;
			if (data != info) {
				info = data;
				frontManager.updateCards();
			}
		});
	}



	this.cardsToParameter = function (data) {
		var result = "";

		if (data.length == 0) {
			return `${data[data.length - 1]}`
		}

		for (let i = 0; i < data.length - 1; i++) {
			result += `${data[i]},`;
		}
		result += `${data[data.length - 1]}`;

		return result;
	}

	this.sendToBack = function (action,card,selectedColor,challenge) {
		console.log("action",action);
		console.log("card",card);
		$.get("game.jsp",
			{
				"gameId": info.id,
				"currentPlayerId": info.currentPlayerId,
				"currentColor": info.currentColor,
				"clockWise": info.clockWise,
				"hostPlayerUNO": info.hostPlayer.UNO,
				"hostPlayerName": info.hostPlayer.name,
				"hostPLayerHand": dataManager.cardsToParameter(info.hostPlayer.hand),
				"guestPlayerName": info.guestPlayer.name,
				"guestPlayerUNO": info.guestPlayer.UNO,
				"guestPlayerHand": dataManager.cardsToParameter(info.guestPlayer.hand),
				"deck": dataManager.cardsToParameter(info.deck),
				"discardPile": dataManager.cardsToParameter(info.discardPile),
				"action": action,
				"droppedCard":card,
				"selectedColor":selectedColor,
				"challenge":challenge

			},
			function(){
				console.log("me retornó el back");
				});
	}


}