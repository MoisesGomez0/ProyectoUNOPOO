function IndexManager(){
    this.backScreenHost = document.querySelector("#backScreenHost");
    this.hostPopUp = document.querySelector("#hostPopUp");


    this.showInputHost= function(){
        backScreenHost.classList.add("active");
    }

    this.showInputGuest = function(){
        backScreenGuest.classList.add("active");
    }

    this.showError = function(){
        errorBackScreen.classList.add("active");
    }

    this.hideInputHost = function(){
        backScreenHost.classList.remove("active");
    }

    this.hideInputGuest = function(){
        backScreenGuest.classList.remove("active");
    }

    this.hideError = function(){
        errorBackScreen.classList.remove("active");
    }

    this.verifyAndRedirectHost = function(){
        if(hostName.value != ""){
            cookiesManager.setCookie("name",hostName.value);
            location = "logInMaker.jsp";
        }else{
            this.showError();
        }
    }

    this.verifyAndRedirectGuest = function(){
        if(guestName.value != "" && gameId.value != ""){
            cookiesManager.setCookie("name",guestName.value);
            cookiesManager.setCookie("gameId",gameId.value);
            location = "lobby.jsp";
            [].forEach()
        }else{
            this.showError();
        }
    }




    
}