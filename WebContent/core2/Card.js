
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
    this.image = `src/images/\${value.name}_\${color.name}`;

    this.toString = function(){
        return (`${value.name}_${color.name}`);
    }
    
}   