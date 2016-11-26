package datos.estadosPedido;

import datos.Pedido;
import datos.Observacion;

import exceptions.StateException;

/**Clase que modela el estado iniciado de un pedido.
 */
public class Iniciado extends EstadoBase {

    /**Constructor de la clase. En la clase padre se verifica si el parametro es distinto de null.
     * @param pedido
     */
    public Iniciado(Pedido pedido) {
        super(pedido);
    }

    /**Metodo que devuelve verdadero o falso en caso de que el pedido permita ser modificado.
     * Pos:
     * - El metodo devuelve true ya que un pedido iniciado permite que se lo modifique
     * @return
     */
    @Override
    public boolean isModificable() {
        return true;
    }

    /**Metodo que devuelve verdadero o falso encaso de que el pedido este iniciado.
     * Pos:
     * - El metodo devuelve true ya que esta en estado iniciado
     * @return
     */
    @Override
    public boolean isIniciado() {
        return true;
    }

    /**Metodo para agregar una observacion al lote que esta en estado iniciado.
     * Pos:
     * - El metodo lanza una StateException ya que el pedido se encuentra en estado iniciado y no
     * puede agregarse una observacion.
     * @param obs
     * @throws StateException
     */
    @Override
    public void agregarObservacion(Observacion obs) throws StateException {
        throw new StateException("No se pueden realizar observaciones sobre este pedido");
    }

    /**Metodo para aceptar un pedido que esta en estado iniciado
     * Pos:
     * - El metodo lanza una StateException ya que no es posible aceptar un pedido que esta en estado
     * iniciado
     * @throws StateException
     */
    @Override
    public void aceptarPedido() throws StateException {
        throw new StateException("pedido aun sin evaluar");
    }

    /**Metodo para evaluar un pedido que esta en estado iniciado
     * Pos:
     * - El pedido deja de estar iniciado y pasa a estar en estado de evaluacion
     * Invariante:
     * - Se asume que el parametro pedido es distinto de null ya que paso por el constructor.
     * @throws StateException
     */
    @Override
    public void evaluarPedido() {
        this.verificaInvariante();
        this.pedido.setEstadoActual(new Evaluacion(this.pedido));
    }
}
