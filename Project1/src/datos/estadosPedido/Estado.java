package datos.estadosPedido;

import datos.Observacion;

import exceptions.StateException;

/**Interfaz que implementaran todos los estados que puede poseer un pedido
 */
public interface Estado
{
  /**Metodo que indica si en el estado actual el pedido es modificable o no
   * @return boolean
   */
  boolean isModificable();

  /**Metodo que agrega una observacion o no, dependiendo del estado actual
   * pre: observacion distinta de null
   * @param obs
   * post: se le agrega una observacion al pedido o se lanza una excepcion de estado
   * @throws StateException
   */
  void agregarObservacion(Observacion obs)
    throws StateException;

  /**Metodo que sirve para cambiar el estado a aceptado
   * post: cambia el estado del pedido a aceptado o lanza una excepcion de estado
   * @throws StateException
   */
  void aceptarPedido()
    throws StateException;

  /**Metodo que sirve para cambiar de estado a en evaluacion
   * post: cambia el estado del pedido a en evaluacion o lanza una excepcion de estado
   * @throws StateException
   */
  void evaluarPedido()
    throws StateException;

  /**Metodo que indica si el estado es Iniciado o no
   * @return boolean
   */
  boolean isIniciado();
  
  /**Metodo que indica si el estado es Aceptado o no
   * @return boolean
   */
  boolean isAceptado();

  /**Metodo que indica si el estado es en Evaluacion o no
   * @return boolean
   */
  boolean isEnEvaluacion();
}
