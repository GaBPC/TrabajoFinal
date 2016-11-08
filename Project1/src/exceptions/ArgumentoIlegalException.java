package exceptions;

/**Exception que se lanza cuando el parametro de un metodo o constructor no cumple
 * con las condiciones necesarias para su utilizacion.
 */
public class ArgumentoIlegalException extends Exception {

    // Atributo que contiene una referencia al object que causo el error
    private Object argumentoInvalido;

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
    public ArgumentoIlegalException(String mensaje, Object argumentoInvalido) {
        super(mensaje);

        assert argumentoInvalido != null : "El parametro argumentoInvalido es null";

        this.argumentoInvalido = argumentoInvalido;
    }

    /**Devuelve la instancia del objeto que provoco el error.
     * Pos:
     * - Devuelve una referencia al objeto almacenado en el atributo de clase, que se asume distinto de null
     * @return
     */
    public Object gerArgumentoInvalido() {
        this.verificaInvariante();
        return this.argumentoInvalido;
    }

    private void verificaInvariante() {
        assert this.argumentoInvalido != null : "El parametro argumentoInvalido es null";
    }
}
