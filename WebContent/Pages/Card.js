
/**
 * Carta para un juego de UNO.
 * @param {EValue} value 
 * @param {EColor} color 
 */
function Card(value, color){

    if(typeof(value) == typeof("bananas")){
        for (const key in EValue) {
            if (EValue[key].name = value) {
                value = EValue.name;
                break;
            }
        }

    }
    if(typeof(value) == typeof("bananas")){
        for (const key in EColor) {
            if (EValue[key].name = color) {
                value = EValue.name;
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