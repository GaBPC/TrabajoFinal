package exceptions;

/**Exception que se lanza cuando no se cumple una restriccion de longitud en un String.
 */
public class LengthException extends Exception {
    
    // Atributo de clase que contiene una referencia al String que causo la exception
    private String texto;

    /**Constructor de la clase.
     * Pre:
     * - Se asume que el parametro mensaje es distinto de null
     * Pos:
     * - Se crea una instancia de la clase
     * Invariante:
     * - Una vez creada la instancia, se asume que en todo momento el atributo de la clase es distinto de null
     * @param mensaje
     * @param argumentoInvalido
     */
    public LengthException(String texto, String mensaje) {
        super(mensaje);
        this.texto = texto;
    }

    /**Devuelve la instancia del objeto que provoco el error.
     * Pos:
     * - Devuelve una referencia al objeto almacenado en el atributo de clase, que se asume distinto de null
     * @return
     */
    public String getObject() {
        return this.texto;
    }
}
