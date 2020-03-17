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
        return parseInt(Math.random()*(max-min) + min)
    }
}