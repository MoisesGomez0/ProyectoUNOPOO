
/**
 * Carta para un juego de UNO.
 * @param {EValue} value 
 * @param {EColor} color 
 */
function Card(value, color){
    this.value = value;
    this.color = color;
    this.image = `src/images/\${value.name}_\${color.name}`;

    this.toString = function(){
        return (`${value.name}_${color.name}`);
    }
}   