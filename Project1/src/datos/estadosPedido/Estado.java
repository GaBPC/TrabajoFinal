package datos.estadosPedido;

import datos.Observacion;

import exceptions.StateException;

public interface Estado
{
  boolean isModificable();

  void agregarObservacion(Observacion obs)
    throws StateException;

  void aceptarPedido()
    throws StateException;

  void evaluarPedido()
    throws StateException;

  boolean isIniciado();

  boolean isAceptado();

  boolean isEnEvaluacion();
}
