package datos.estadosPedido;

import datos.Pedido;
import datos.Observacion;

import exceptions.StateException;

public class Aceptado extends EstadoBase{
    public Aceptado(Pedido pedido) {
        super(pedido);
    }

    @Override
    public boolean isAceptado()
    {
      return true;
    }

    @Override
    public void agregarObservacion(Observacion obs) throws StateException {
        throw new StateException("Imposible agregar, pedido ya aceptado");
    }

    @Override
    public void aceptarPedido() throws StateException{
        throw new StateException("Imposible aceptar, pedido ya aceptado");
    }
    
    @Override
    public void evaluarPedido() throws StateException {
        throw new StateException("El pedido ha sido aceptado");
    }
}
