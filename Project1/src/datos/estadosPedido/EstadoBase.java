package datos.estadosPedido;

import datos.Pedido;

/**Clase que modela la base de todos los estados que puede tomar un pedido.
 * Es una clase abstracta, ya que no modela un estado real, si no las caracteristicas comunes
 * de todos.
 */
public abstract class EstadoBase
  implements Estado
{
  protected Pedido pedido;

  /**Constructor de la clase. Verifica que el parametro sea distinto de null.
   * Invariante:
   * - Una vez que se crea la instancia, se asume que el atributo pedido sera distinto de null
   * en todo momento.
   * @param pedido
   */
  public EstadoBase(Pedido pedido)
  {
    super();
    this.pedido = pedido;
  }

  /**Metodo que devuelve verdadero o falso en caso de que el pedido permita ser modificado
   * @return
   */
  @Override
  public boolean isModificable()
  {
    return false;
  }

  /**Metodo que devuelve verdadero o falso en caso de que el pedido permita ser iniciado
   * @return
   */
  @Override
  public boolean isIniciado()
  {
    return false;
  }

  /**Metodo que devuelve verdadero o falso en caso de que el pedido permita ser aceptado
   * @return
   */
  @Override
  public boolean isAceptado()
  {
    return false;
  }

  /**Metodo que devuelve verdadero o falso en caso de que el pedido este en evaluacion
   * @return
   */
  @Override
  public boolean isEnEvaluacion()
  {
    return false;
  }

  protected void verificaInvariante()
  {
    assert this.pedido != null: "El atributo pedido es null";
  }

  public Pedido getPedido()
  {
    return pedido;
  }
}
