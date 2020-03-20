function Div(content = null,top = 0,left = 0,id="cardDiv", divClass = "Card",width=10,height=40){
	this.id = id;
	this.divClass = divClass;

	this.top = top;
	this.left  = left;

	this.width = width;
	this.height = height;

	this.style = `style="width:${this.width}vw;height:${this.height}vh;top:${this.top}vh;left:${this.left}vw; border:1px solid #000;"`;
	this.content = content;

	this.toHTML = function(){
		return `<div id="${this.id}" ${this.style}>${this.content}</div>`
	}

	this.move = function(destinationTop, destinationLeft, time=1) {
		intervalId = setInterval(function(){
			var element = document.querySelector("div");
			var intervalId;
			var newTop = this.top;
			var newLeft = this.left;
			
			if(newTop < destinationTop){
				this.top = newTop + 0.5;
				newTop+=0.5
				console.log(newTop);
			}else
			if(newTop > destinationTop){
				this.top = newTop - 0.5
				newTop-=0.5
			}

			if(newLeft < destinationLeft) {
				this.left  = newLeft + 0.5;
				newLeft+=0.5;
			}else
			if(newLeft > destinationLeft) {
				this.left  = newLeft - 0.5;
				newLeft-=0.5;
			}

			if (parseInt(newTop) == destinationTop && parseInt(newLeft) == destinationLeft) {
				clearInterval(intervalId);
			}
			this.top = newTop;
			this.left = newLeft;
		},time)
	}


}