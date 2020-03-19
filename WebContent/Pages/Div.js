function Div(){
	this.id = null;
	this.class = null;

	this.top = null;
	this.left  = null;


	this.width = null;
	this.height = null;

	this.style = null;
	this.image = null;

	this.move = function(id, destinationTop, destinationLeft, time) {
		var element = document.querySelector("#" + id);
		var intervalId;
		var newTop = this.top;
		var newLeft = this.left;
		intervalId = setInterval(function(){

			if(newTop < destinationTop){
				element.style.destinationTop = newTop + 0.5;
				newTop+=0.5
			}else
			if(newTop > destinationTop){
				element.style.destinationTop = newTop - 0.5
				newTop-=0.5
			}

			if(newLeft < destinationLeft) {
				element.style.destinationLeft  = newLeft + 0.5;
				newLeft+=0.5;
			}else
			if(newLeft > destinationLeft) {
				element.style.destinationLeft  = newLeft - 0.5;
				newLeft-=0.5;
			}

			if (parseInt(newTop) == destinationTop && parseInt(newLeft) == destinationLeft) {
				clearInterval(intervalId);
			}

		},time)
	}

	this.toHTML = function(){
		return 
	}

}