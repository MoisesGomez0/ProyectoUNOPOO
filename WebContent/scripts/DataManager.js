function DataManager(){
	
	this.update = function(){
		console.log("dentro de update");
		 $.get("getContent.jsp", { "file": "game.json" }, function(data){
			data = JSON.parse(data.trim());
			info = data;
			console.log(info);
			dataManager.sendToBack();
		 });
	}
	

	
	this.cardsToParameter = function(data){
		console.log("dentro de cards");
		var result = "";
		
		if (data.length == 0){
			return `${data[data.length-1]}`
		}
		
		for(let i = 0 ; i < data.length-1; i++){
			result += `${data[i]},`;
		}
		result += `${data[data.length-1]}`;
		
		return result;
	}
	
	this.sendToBack = function(){
		console.log("dentro de sendToBack")

		$.get("game.jsp",
				{
			"gameId": info.id,
			"currentPlayerId": info.currentPlayerId,
			"currentColor": info.currentColor,
			"clockWise": info.clockWise,
			"hostPlayerName":info.hostPlayer.name,
			"hostPLayerHand": dataManager.cardsToParameter(info.hostPlayer.hand),
			"guestPlayerName":info.guestPlayer.name,
			"guestPlayerHand":dataManager.cardsToParameter(info.guestPlayer.hand),
			"deck":dataManager.cardsToParameter(info.deck),
			"discardPile":dataManager.cardsToParameter(info.discardPile)
			
			
				},
				null);
	}
	
	
}