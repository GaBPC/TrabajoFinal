package datos.estadosPedido;

import datos.Pedido;
import datos.Observacion;

import exceptions.StateException;

/**Estado Evaluacion, en el mismo puede estar un pedido en cierto momento. Extiende de la clase EstadoBase donde se 
 * encuentra el comportamiento basico de todos los estados
 */
public class Evaluacion
  extends EstadoBase
{
  /**Constructor vacio de la clase, el cual le pasa el parametro pedido a la clase padre
   * @param pedido 
   */
  public Evaluacion(Pedido pedido)
  {
    super(pedido);
  }

  /**Metodo que indica que el pedido es modificable en el estado actual
   * @return boolean
   */
  @Override
  public boolean isModificable()
  {
    return true;
  }

  /**Metodo que indica que el estado actual es Evaluacion
   * @return boolean
   */
  @Override
  public boolean isEnEvaluacion()
  {
    return true;
  }
  
  /**Metodo que agrega una observacion al pedido, el parametro ya ha sido verificado distinto de null
   * pre: observacion distinta de null
   * @param obs
   * post: se le agrega una observacion al pedido o se lanza una excepcion de estado
   * @throws StateException
   */
  @Override
  public void agregarObservacion(Observacion obs)
    throws StateException
  {
    assert obs!=null : "Observacion nula";
    
    if (obs.verificacion())
      this.pedido
          .getListaObservaciones()
          .add(obs);
    else
      throw new StateException("Observacion invalida");
  }

  /**Metodo que cambia el estado del pedido a Aceptado, solo en caso de que se cumplan ciertos condicionantes.
   * post: se cambia de estado a Aceptado, o se lanza una excepcion de estado
   * @throws StateException
   */
  @Override
  public void aceptarPedido()
    throws StateException
  {
    if (this.pedido.verificaNull())
      this.pedido.setEstadoActual(new Aceptado(this.pedido));
    else
      throw new StateException("El pedido no está listo para ser aceptado");
  }

  /**Metodo que indica que el pedido se encuentra actualmente en estado de evaluacion
   * post: se lanza una excepcion de estado
   * @throws StateException
   */
  @Override
  public void evaluarPedido()
    throws StateException
  {
    throw new StateException("El pedido ya esta en estado de evaluacion");
  }
}
