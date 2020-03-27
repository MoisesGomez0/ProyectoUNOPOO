/**
 * Objeto encargado de administrar las cookies.
 */
function CookiesManager() {

    /**Establece una cookie.
     * @param name {string} El nombre que debe tener la cookie.
     * @param value {string} El valor que se debe almacenar en la cookie.
     */
    this.setCookie = function (name, value) {
        document.cookie = `${name}=${value}`;
    }

    /**Retorna el valor que tiene una coockie en específico
     * @param cname {string} El nombre de la cookie.
     */
    this.getCookie = function (cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
}