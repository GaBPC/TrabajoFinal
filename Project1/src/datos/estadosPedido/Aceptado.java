package datos.estadosPedido;

import datos.Pedido;
import datos.Observacion;

import exceptions.StateException;

/**Clase que modela el estado aceptado de un pedido.
 */
public class Aceptado extends EstadoBase {
    /**Constructor de la clase. En la clase padre se verifica si el parametro es distinto de null.
     * @param pedido
     */
    public Aceptado(Pedido pedido) {
        super(pedido);
    }

    /**Metodo que indica si el metodo se encuentra aceptado.
     * Pos:
     * - El metodo devuelve verdadero ya que el pedido esta aceptado
     * @return
     */
    @Override
    public boolean isAceptado() {
        return true;
    }

    /**Metodo para agregar una observacion al lote que esta en estado aceptado.
     * Pos:
     * - El metodo lanza una StateException ya que el pedido se encuentra aceptado
     * @param obs
     * @throws StateException
     */
    @Override
    public void agregarObservacion(Observacion obs) throws StateException {
        assert obs != null : "El parametro es null";
        throw new StateException("Imposible agregar, pedido ya aceptado");
    }

    /**Metodo para aceptar un pedido que esta en estado aceptado
     * Pos:
     * - El metodo lanza una StateException ya que el pedido se encuentra aceptado
     * @throws StateException
     */
    @Override
    public void aceptarPedido() throws StateException {
        throw new StateException("Imposible aceptar, pedido ya aceptado");
    }

    /**Metodo para evaluar un pedido que esta en estado aceptado
     * Pos:
     * - El metodo lanza una StateException ya que el pedido se encuentra aceptado
     * @throws StateException
     */
    @Override
    public void evaluarPedido() throws StateException {
        throw new StateException("El pedido ha sido aceptado");
    }
}
