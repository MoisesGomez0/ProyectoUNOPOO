/**Este objeto se encarga de administrar la información y comunicarse con el asincronamente con el servidor.*/
function DataManager() {
	/**Hace una petición asincrona al servidor que devuelve la información del estado actual del juego.
	 * Ejecuta los métodos necesarios para actualizar lo que se es mostrado al jugador en la pantalla. 
	 * */
	this.update = function () {
		$.get("getContent.jsp", { "file": "game.json" }, function (data) {
			data = JSON.parse(data.trim());
			frontManager.json = info;
				clearInterval(idSetIntervalUpdate);
				info = data;
				frontManager.updateCards();
			
		});
	}


	/**@param {list} data Una lista de cartas
	 * return {string} result Un string facil de procesar para el servidor que contenga todas
	 * las cartas existentes en el parámetro data.
	 * */
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

	/**Ejecutado cada ves que se realiza una acción en el front que necesita la actualización de los datos
	 * existentes en el servidor, ejecuta ciertas acciones segun la respuesta del servidor.
	 * @param {string} action Un string que representa la instrucción que el servidor debe ejecutar.
	 * @param {string} card Un string con la nomenclatura de una carta, paramétro usado solo en
	 * combinación de una acción en la que el servidor necesite saber una carta específica.
	 * @param {string} selectedColor Representa el color selecionado por el jugador cuando se ejecuta un 
	 * cambio de color, tambien solo usado en combinación de acciones que lo necesiten.
	 * @param {string} challenge Un string que determina si el jugador escogió retar o no a su oponente en una
	 * juganda que involucre una carta +4.
	 * */
	this.sendToBack = function (action,card,selectedColor,challenge) {
		console.log("action",action);
		console.log("card",card);
		$.get("game.jsp",
			{
				"gameId": info.id,
				"currentPlayerId": info.currentPlayerId,
				"currentColor": info.currentColor,
				"clockWise": info.clockWise,
				"endGame": info.endGame,
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
			function(callback){
				console.log("me retornó el back");
				if(callback.trim() == "true"){
					backScreenDrop.classList.add("active");
				}else if(callback.trim() == "cardDropped"){
					sm.playCard();
				}
				});
	}


}