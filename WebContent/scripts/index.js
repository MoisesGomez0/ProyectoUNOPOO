function FormsManager(){
    this.backScreenHost = document.querySelector("#backScreenHost");
    this.hostPopUp = document.querySelector("#hostPopUp");


    this.showInputHost= function(){
        backScreenHost.classList.add("active");
    }

    this.showInputGuest = function(){
        backScreenGuest.classList.add("active");
    }

    this.hideInputHost = function(){
        backScreenHost.classList.remove("active");
    }

    this.hideInputGuest = function(){
        backScreenGuest.classList.remove("active");
    }
}