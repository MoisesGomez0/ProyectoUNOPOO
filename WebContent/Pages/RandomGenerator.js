function RandomGenerator(){
    
    this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    this.randomAlpha = function(length){
        var result = "";        
        for(let i=0; i<length; i++){
            result += this.alphabet[this.randomInt(0,this.alphabet.length)];
        }
        return result;
    };
    
    this.randomInt = function(min,max){
        return parseInt(Math.random()*(max-min) + min);
    }

    /**
     * @return {Card} Una Carta UNO aleatoria.
     */
    this.UNOCard = function(){
        var color = this.cardColor();
        var value = this.cardValue();

        if (color.name = EColor.BLACK.name){
            value = this.blackCardValue();
        }
        return new Card(value,color);
    }

    /**
     * @return {Card} Una carta no especial de UNO leatoria.
     */
    this.noSepecialUNOCard = function(){
        return new Card(this.noBlackCardValue(), this.noBlackCardColor());
    }

    /**
     * @return {Card} Una carta que no es de acción UNO aleatoria.
     */
    this.noActionUNOCard = function(){
        return new Card(this.noActionCardValue(),this.noBlackCardColor());
    }

    /**
     * @return {EColor} Un color aleatorio para una carta de UNO.
     */
    this.cardColor = function(){
        var colors =[
            EColor.BLUE,           
            EColor.RED,           
            EColor.YELLOW,           
            EColor.GREEN,           
            EColor.BLACK           
        ];

        return colors[this.randomInt(0,5)];
    }

    /**
     * @return {EColor} Un color aleatorio exceptuando el negro.
     */
    this.noBlackCardColor = function(){
        var colors =[
            EColor.BLUE,           
            EColor.RED,           
            EColor.YELLOW,           
            EColor.GREEN         
        ];

        return colors[this.randomInt(0,4)];
    }    

    /**
     * @return {EValue} Un valor aleatorio para una carta UNO.
     */
    this.cardValue = function(){
        var values = [
        		EValue.CERO,
        		EValue.ONE,
        		EValue.TWO,
        		EValue.THREE,
        		EValue.FOUR,
        		EValue.FIVE,
        		EValue.SIX,
        		EValue.SEVEN,
        		EValue.EIGHT,
        		EValue.NINE,
        		EValue.DTWO,
        		EValue.REVERSE,
        		EValue.SKIP,
        		EValue.WILD,
        		EValue.DFOUR
        ];
        
        return values[this.randomInt(0, 15)];
    }

    /**
     * 
     * @return {EValue} Un valor aleatorio para las cartas que NO son especiales (cartas que no sean de color negro).
     */
	this.noBlackCardValues = function() {
        var values = [
        		EValue.CERO,
        		EValue.ONE,
        		EValue.TWO,
        		EValue.THREE,
        		EValue.FOUR,
        		EValue.FIVE,
        		EValue.SIX,
        		EValue.SEVEN,
        		EValue.EIGHT,
        		EValue.NINE,
        		EValue.DTWO,
        		EValue.REVERSE,
        		EValue.SKIP
        ];
        
        return values[this.randomInt(0, 13)];    	
    }

    /**
     * @return {EValue} Un valor aleatorio para las carta que no son de acción.
     */
    this.noActionCardValue = function() {
        var values = [
        		EValue.CERO,
        		EValue.ONE,
        		EValue.TWO,
        		EValue.THREE,
        		EValue.FOUR,
        		EValue.FIVE,
        		EValue.SIX,
        		EValue.SEVEN,
        		EValue.EIGHT,
        		EValue.NINE
        ];
        
        return values[this.randomInt(0, 10)];       	
    }

    /**
     * 
     * @return Un símbolo aleatorio para las cartas especiales (de color negro);
     */
    this.blackCardValues = function() {
    	var values = [
    			EValue.WILD,
    			EValue.DFOUR
        ];
    	
    	return values[this.randomInt(0,1)];
    }
}