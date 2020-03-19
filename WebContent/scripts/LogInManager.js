function LogInManager(name=null,nPlayers=0,gameId = null){
	
	this.name = name;
	this.nPlayers = nPlayers;
	this.gameId = gameId;
	this.data = null;
	this.intervalId = null;
	
	this.generateGameId = function(){
		var random = new RandomGenerator();
		this.gameId = random.randomAlpha(8);
		return this.gameId;
	}
	
	this.generateData = function(){
		this.data = {"gameId":this.gameId,"players":[this.name],"nPlayers":this.nPlayers};
		return this.data;
	}
	
	this.saveData = function(){
		$.get("write.jsp",{"file":`logIn${this.gameId}.json`,"content":JSON.stringify(this.data),"override":true},null);
	}

	this.setDataToSubmit = function(){
		document.querySelector("#formNPlayers").value = this.nPlayers;
		document.querySelector("#formName").value = this.name;
		document.querySelector("#gameId").value = this.gameId;
	}
	
	this.verifyLoginAndRedirect = function() {
		var id = this.gameId;
		var intervalId = setInterval(function () {
            $.get("getContent.jsp",{"file":`logIn${id}.json`}, function (data) { 
				data = JSON.parse(data.trim());
				console.log(data.players.length == data.nPlayers)
                if(data.players.length == data.nPlayers){
                    clearInterval(intervalId);
                    document.querySelector("#dataForm").submit();
                }
             })
        }, 100);
		
    }
	
	this.getIn = function(){
		var playerName = this.name;
		var id = this.gameId;
		$.get("getContent.jsp",{"file":`logIn${id}.json`},function(data){
			data = JSON.parse(data.trim());
			data.players.push(playerName);
			$.get("write.jsp",{"file":`logIn${id}.json`,"content":JSON.stringify(data),"override":true},null);
			
		});
	}
}