/**@fileoverview En este archivos se escribe la clase DocAdmin y funciones que
 * modifican el html del algun archivos
 */

/**@class Modifica el html de algún documento*/
function DocAdmin() {

    /**@method disableByID deshabilita o habilita elementos que sus IDs son pasados
     * como parámetros
     * @param parameters @type {list} El primer elemento debe ser un boolean, los demas
     * son los IDs de los elementos a deshabilitar o habilitar.
     */
    this.disableByID = function (parameters) {
        for (let i = 1; i < parameters.length; i++) {
            document.querySelector("#" + parameters[i]).disabled = parameters[0];
        }
    }
    
    /**@method addContent agrega contenido html a un elemento
     * @param father @type {string} es el nombre o algo que identifique al nodo padre.
     * @param child @type {string} es el contenido a añadirse.
     */    
    this.addContent = function (father, child) {
        document.querySelector(father).innerHTML += child;
    }
    /**@method removeByID Elimina un contenido por su id
     * @param ID @type {string} Id del elemento a remover del documento.
    */
    this.removeByID = function(ID) {
        document.querySelector("#"+ID).remove();

    }
}