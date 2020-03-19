/** Abstración que representa un chat con su información y metodos*/
function Chat(textArea,inputArea){
	this.textArea = textArea ;
	this.inputArea = inputArea;
	
	this.init = function(){
		setInterval(function(){
			$.get("getContent.jsp",{"file":"chat.txt"},function(data){
				textArea.innerHTML = data;
			})
		},1000);
	}
	
	this.send = function(e){
		if(e.keyCode == 13){
			$.get("write.jsp",{"file":"chat.txt","content":`${player.name}:${inputArea.value}`},null);
			inputArea.value = "";
		}
	}
}