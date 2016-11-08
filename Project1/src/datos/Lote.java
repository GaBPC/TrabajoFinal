package datos;

import exceptions.ArgumentoIlegalException;

/**Clase que representa un lote de trabajo de la empresa. Contiene los datos del pedido y el numero
 * de lote correspondiente.
 */
public class Lote implements ResumenClase {
    // Es el pedido que se construira en este lote
    private Pedido pedido = null;
    // Es el numero que representa a este lote dentro de la empresa
    private String numeroLote = null;

    /**Constructor principal de la clase.
     * Pos-condiciones:
     * - Si los parametros cumplen con las restricciones, se creara un nuevo lote. En caso contrario
     * se lanzara una ArgumentoIlegalException.
     * - Una vez instanciada la clase, se puede asumir que todos los atributos no variaran. Por ello
     * no es necesario verificarlos cada vez que se utiliza un metodo de clase.
     * @param pedido es una referencia al pedido que contendra este lote
     * @param numeroLote es el numero correspondiente a este lote
     * @throws ArgumentoIlegalException
     */
    public Lote(Pedido pedido, String numeroLote) throws ArgumentoIlegalException {
        super();
        
        assert pedido != null : "El pedido es null";
        assert this.verificaNumeroLote(numeroLote) : "El numero de lote no verifica las condiciones";
        
        if (pedido != null)
            this.pedido = pedido;
        else
            throw new ArgumentoIlegalException("El pedido es null", pedido);
        if (this.verificaNumeroLote(numeroLote))
            this.numeroLote = numeroLote;
    }

    /**Metodo que verifica si el numero cumple con las restricciones de longitud.
     * Pre-condiciones:
     * - Se asume que el parametro es distinto de null
     * Pos-condiciones:
     * - Si el parametro cumple las restricciones, el metodo devuelve true. En caso contrario
     * se lanza una exception.
     * @param str
     * @return
     * @throws ArgumentoIlegalException
     */
    private boolean verifica(String str) throws ArgumentoIlegalException {
        boolean ret = false;
        if (str.length() == 9) {
            int num = Integer.parseInt(str.substring(3).trim());
            if (num >= 0 && num <= 999999)
                ret = true;
            else
                throw new ArgumentoIlegalException("El numero esta fuera de rango", num);
        } else
            throw new ArgumentoIlegalException("El numero debe tener 6 digitos", str);
        return ret;
    }

    /**Metodo que verifica si el numero de lote cumple con la restriccion de longitud y que ademas
     * incluya el prefijo LOT
     * Pos-condiciones:
     * - Si el parametro cumple las restricciones, el metodo devuelve true. En caso contrario
     * se lanza una exception.
     * @param numeroLote
     * @return
     * @throws ArgumentoIlegalException
     */
    private boolean verificaNumeroLote(String numeroLote) throws ArgumentoIlegalException {
        boolean ret = false;
        if (numeroLote == null)
            ret = false;
        else {
            String aux = numeroLote.substring(0, 3);
            if (aux.compareTo("LOT") == 0)
                ret = verifica(numeroLote);
            else
                throw new ArgumentoIlegalException("El numero de lote no contiene \"LOT\"", numeroLote);
        }
        return ret;
    }

    /**Metodo que devuelve un String representativo de la instancia de clase.
     * @return String que contiene el numero de lote y la descripcion del pedido
     */
    public String toString() {
        return this.numeroLote + " - " + this.pedido.toString();
    }

    /**
     * @return
     */
    public String detalles() {
        String ret = "";
        try {
            ret = this.pedido.detalles();
            ret += "\nNumero de lote: " + ((this.verificaNumeroLote(this.numeroLote)) ? this.numeroLote : " - ");
        } catch (ArgumentoIlegalException e) {
        }
        return ret;
    }
    
    public Pedido getPedido()
    {
      return this.pedido;
    }
}
