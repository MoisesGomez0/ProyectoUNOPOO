/**Administra las acciones de los usuarios en la pantalla de index. */
function IndexManager(){

    this.backScreenHost = document.querySelector("#backScreenHost");
    this.hostPopUp = document.querySelector("#hostPopUp");

    /**Muestra al usuario los campos de texto parque introdusca datos de
     * un jugador que será el host de una partida.
     */
    this.showInputHost= function(){
        backScreenHost.classList.add("active");
        hostPopUp.classList.add("active");
    }

    /**Muestra al usuario los campos de texto parque introdusca datos de un jugador 
     * que va a entrar a una partida.
     */
    this.showInputGuest = function(){
        backScreenGuest.classList.add("active");
    }
    /**Muestra al usuario las estádisticas del juego*/
    this.showStadistics = function(){
    	$.get("scoreBoard.jsp",{},function(data){
    		table.innerHTML = data;
    		backScreenScore.classList.add("active");
    	})
        
    }

    /**Muestra al usuario un mensaje de error.
     * @param message {string} El mensaje que será mostrado al usuario en la pantalla
     * de error 
     */
    this.showError = function(message = false){
        sm = new SoundManager();
        if(message){
            errorMessage.innerHTML = message;
        }
        errorBackScreen.classList.add("active");
        sm.playError();
    }

    /**Oculta los elementos en pantalla donde el usuario host escribe datos. */
    this.hideInputHost = function(){
        backScreenHost.classList.remove("active");
    }

    /**Oculta los elementos en pantalla donde el usuario guest escribe datos. */
    this.hideInputGuest = function(){
        backScreenGuest.classList.remove("active");
    }

    /**Oculta el mensaje de error. */
    this.hideError = function(){
        errorBackScreen.classList.remove("active");
    }

    /**Oculta las estadisticas del juego */
    this.hideStadistics = function(){
        backScreenScore.classList.remove("active");

    }

    /**Verifica si el usuario host escribió los datos correctos y de ser así
     * lo redirecciona a una pantalla de espera.
     */
    this.verifyAndRedirectHost = function(){
        if(hostName.value != ""){
            cookiesManager.setCookie("name",hostName.value);
            location = "logInMaker.jsp";
        }else{
            this.showError();
        }
    }

    /**Verifica si el usuario gues escribió los datos correctos y de ser así
     * lo redirecciona a una pantall de espera.
     * 
     */
    this.verifyAndRedirectGuest = function(){
        if(guestName.value != "" && gameId.value != ""){
            cookiesManager.setCookie("name",guestName.value);
            cookiesManager.setCookie("gameId",gameId.value);
            location = "lobby.jsp";
        }else{
            this.showError();
        }
    }
    
}