package datos;

/**Clase que representa un lote de trabajo de la empresa. Contiene los datos del pedido y el numero
 * de lote correspondiente.
 * Invariante: luego de la creacion de la instancia se asume en todo momento que
 * - El pedido nunca sera null y tambien cumplira con el invariante de la clase Pedido
 * - El numero de lote nunca es null y es de la forma LOTXXXXXX siendo X un numero entre 0 y 9
 */
public class Lote implements ResumenClase {
    // Es el pedido que se construira en este lote
    private Pedido pedido = null;
    // Es el numero que representa a este lote dentro de la empresa
    private String numeroLote = null;

    /**Constructor principal de la clase.
     * Pre-condiciones:
     * -pedido distinto de null
     * -numeroLote distinto de null y que sea de la forma LOTXXXXXX, siendo X un numero entero
     * Pos-condiciones:
     * - Si los parametros cumplen con las restricciones, se creara un nuevo lote.
     * - Una vez instanciada la clase, se puede asumir que todos los atributos no variaran. Por ello
     * no es necesario verificarlos cada vez que se utiliza un metodo de clase.
     * @param pedido es una referencia al pedido que contendra este lote
     * @param numeroLote es el numero correspondiente a este lote
     */
    public Lote(Pedido pedido, String numeroLote) {
        super();

        assert pedido != null : "El pedido es null";
        assert Verificaciones.verificaNumeroLote(numeroLote) : "El numero de lote no verifica las condiciones";

        this.pedido = pedido;
        this.numeroLote = numeroLote;

        this.verificaInvariante();
    }


    /**Metodo que devuelve un String representativo de la instancia de clase.
     * @return String que contiene el numero de lote y la descripcion del pedido
     */
    public String toString() {
        return this.numeroLote + " - " + this.pedido.toString();
    }

    /**Metodo que devuelve un String con los detalles del pedido y el numero de lote
     * @return
     */
    public String detalles() {
        String ret = "";
        ret = this.pedido.detalles();
        ret += "\nNumero de lote: " + this.numeroLote;
        return ret;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
     */
    private void verificaInvariante() {
        assert this.pedido != null : "El pedido es null";
        assert Verificaciones.verificaNumeroLote(numeroLote) : "El numero de lote no verifica las condiciones";
    }
}
