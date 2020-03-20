
/**
 * Carta para un juego de UNO.
 * @param {EValue} value 
 * @param {EColor} color 
 */
function Card(value, color){

    if(typeof(color) == typeof("bananas")){
        for (let key in EColor) {
            if (EColor[key].name = color) {
                color = EColor[key];
                break;
            }
        }

    }
    if(typeof(value) == typeof("bananas")){
        for (let key in EValue) {
            if (EValue[key].name = value) {
                value = EValue[key];
                break;
            }
        }
    }
    this.value = value;
    this.color = color;
    this.image = `../images/${this.value.name}_${this.color.name}.png`;
    
    this.div = new Div(`${this.value.name}_${this.color.name}`);
    
    this.toString = function(){
        return `${this.value.name}_${this.color.name}`;
    }

    this.toDiv=function(){
        return this.div.toHTML();
    }
    
}   