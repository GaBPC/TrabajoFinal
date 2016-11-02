package datos.estadosPedido;

import datos.Pedido;
import datos.Observacion;

import exceptions.StateException;

public class Evaluacion
  extends EstadoBase
{
  public Evaluacion(Pedido pedido)
  {
    super(pedido);
  }

  @Override
  public boolean isModificable()
  {
    return true;
  }

  @Override
  public boolean isEnEvaluacion()
  {
    return true;
  }

  @Override
  public void agregarObservacion(Observacion obs)
    throws StateException
  {
    if (obs.verificacion())
      this.pedido
          .getListaObservaciones()
          .add(obs);
    else
      throw new StateException("Observacion invalida");
  }

  @Override
  public void aceptarPedido()
    throws StateException
  {
    if (this.pedido.verificaNull())
      this.pedido.setEstadoActual(new Aceptado(this.pedido));
    else
      throw new StateException("El pedido no está listo para ser aceptado");
  }

  @Override
  public void evaluarPedido()
    throws StateException
  {
    throw new StateException("El pedido ya esta en estado de evaluacion");
  }
}
