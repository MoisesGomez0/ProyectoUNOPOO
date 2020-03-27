/**Este objeto se encarga de generar animaciónes cambiando atributos a objetos HTML */
function Animator(){

    /**Genera una animación parecida a de una máquina de escribir.
     * Escribe el texto de derecha a izquierda, luego borra de izquierda a derecha y se repite.
     * @param text {string} El texto a escribirse con el efecto adecuado.
     * @param selector {string} Una cadena que represente el argumento de un selector de DOM.
     */
    this.writeMachine = function(text,selector){
        var counter = 0;
        var IdSetInterval;
        var element = document.querySelector(selector);
        var condition = true;
        IdSetInterval = setInterval(function () {
            if(counter == text.length){
                condition = false;
            }else if(counter == 1){
                condition = true;
            }
            if (condition) {
                element.innerHTML += text[counter];
                counter++;
            } else {
                counter--;
                element.innerHTML = text.slice(0,counter);
            }
        }, 300);
    }

    /**Da el efecto de aumentar y reducir el fontSize del texto en los objetos
     * @param selector {string} Una cadena que represente el argumento de un selector de DOM.
     * @param maxSize {int} El tamaño máximo que debe alcanzar el texto.
     * @param minSize {int} El tamaño minimo que debe alcanzar el texto.
     */
    this.splashText = function(selector,maxSize = 40, minSize = 10){
        var condition = false;
        var idInterval;
        var element = document.querySelector(selector);
        var actualSize = parseInt(window.getComputedStyle(element).fontSize.replace(/\D/g, ""));
        idInterval = setInterval(function () {
            if (parseInt(actualSize) == minSize) {
                condition = false;
            } else if (parseInt(actualSize) == maxSize) {
                condition = true;
            }
            if (condition) {
                element.style.fontSize = `${actualSize}px`;
                actualSize -= 0.1;
            } else {
                element.style.fontSize = `${actualSize}px`;
                actualSize += 0.1
            }
            
        }, 10);
    }

}