/**Administra y valida a los usuario que entrar en una partida.
 * Redirecciona a la pantalla de juego.
 * @param name {string} Nombre del jugador.
 * @param nPlayers {int} Número de jugadores en la partida.
 * @param gameId  {string} Id del juego.
 */
function LogInManager(name = null, nPlayers = 0, gameId = null) {

	/**Nombre del jugador */
	this.name = name;

	/**Número de jugadores */
	this.nPlayers = nPlayers;

	/**Id del juego*/
	this.gameId = gameId;

	/**Infomación de los jugadores de la partida en formato json */
	this.data = null;

	/**ID de un setInterval que verifica los datos en un archivo */
	this.intervalId = null;

	/**Establece el gameId.
	 * @returns {string} Una cadena de 8 caracteres aleatorios */
	this.generateGameId = function () {
		var random = new RandomGenerator();
		this.gameId = random.randomAlpha(4);
		return this.gameId;
	}

	/**Establece la información de los jugadores en la partida
	 * @return {json} Información de los jugadores en la partida.
	 */
	this.generateData = function () {
		this.data = { "gameId": this.gameId, "players": [this.name], "nPlayers": this.nPlayers };
		return this.data;
	}

	/**Guarda el contenido de el atributo data en el archivo logIn.json */
	this.saveData = function () {
		$.get("write.jsp", { "file": "logIn.json", "content": JSON.stringify(this.data), "override": true }, null);
	}

	/**Verifica hayan ingresado la cantidad de jugadores correctos y los rediercciona a la pantalla de juego. */
	this.verifyLoginAndRedirec = function () {
		var id = this.gameId;
		var intervalId = setInterval(function () {
			$.get("getContent.jsp", { "file": "logIn.json" }, function (data) {
				data = JSON.parse(data.trim());
				if (data.players.length == data.nPlayers) {
					$.get("gameMaker.jsp", { "hostPlayer": data.players[0], "guestPlayer": data.players[1], "gameId": id }, function (data) {
						location = "UNO.jsp"
					});
					clearInterval(intervalId);
				}
			})
		}, 750);

	}

	/**Valida y se encarga que un jugador entre a una partida ya creada, redirecciona a la pantalla de juego. */
	this.getIn = function () {
		var playerName = this.name;
		var id = this.gameId;
		var isIn = this.isIn;
		$.get("getContent.jsp", { "file": "logIn.json" }, function (data) {
			if (data == "fail") {
				indexManager.showError("No encontramos lo archivos necesarios, llama a soporte técnico de MAL inc.");
		    	loadingScreenGuest.classList.remove("active");
				return false;
			} else {
				data = JSON.parse(data.trim());
				if (data.gameId == id) {
					if (data.players.length == data.nPlayers) {
						if (isIn(data.players, playerName)) {
							location = "UNO.jsp"
							return true;
						} else {
					    	loadingScreenGuest.classList.remove("active");
							indexManager.showError("Al parecer tu nombre no está registrado en la partida.");
							return false;
						}
					} else {
						if (data.players[0] == playerName) {
							indexManager.showError(`El anfitrión tambien se llama ${playerName}, elige otro nombre.`);
					    	loadingScreenGuest.classList.remove("active");

						} else {
							data.players.push(playerName);
							$.get("write.jsp", { "file": "logIn.json", "content": JSON.stringify(data), "override": true }, function (info) {
								location = "UNO.jsp";
							});
						}
					}
				} else {
					indexManager.showError("El código de la partida no es correcto.");
			    	loadingScreenGuest.classList.remove("active");

				}
			}
		});
	}
	/**Verifica si un elemento está dentro de un arreglo
	 * @param vector {list} Una lista de elementos.
	 * @param value {element} el elemento a verificar su existencia dentro de la 
	 * lista vector.
	 * @return {boolean} true si el elemento pertenece a vector, false de lo contrario.
	 */
	this.isIn = function (vector, value) {
		for (let index in vector) {
			if (vector[index] == value) {
				return true;
			}
		}
		return false;
	}
}