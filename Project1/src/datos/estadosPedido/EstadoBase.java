package datos.estadosPedido;

import datos.Pedido;

import exceptions.StateException;

public abstract class EstadoBase
  implements Estado
{
  protected Pedido pedido;

  public EstadoBase(Pedido pedido)
  {
    super();
    this.pedido = pedido;
  }

  @Override
  public boolean isModificable()
  {
    return false;
  }

  @Override
  public boolean isIniciado()
  {
    return false;
  }

  @Override
  public boolean isAceptado()
  {
    return false;
  }

  @Override
  public boolean isEnEvaluacion()
  {
    return false;
  }
}
