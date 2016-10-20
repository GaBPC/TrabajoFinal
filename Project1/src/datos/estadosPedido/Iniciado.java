package datos.estadosPedido;

import datos.Pedido;
import datos.Observacion;

import exceptions.StateException;

public class Iniciado extends EstadoBase {

    public Iniciado(Pedido pedido) {
        super(pedido);
    }

    @Override
    public boolean isModificable() {
        return true;
    }
    
    @Override
    public boolean isIniciado() 
    {
      return true;
    }

    @Override
    public void agregarObservacion(Observacion obs) throws StateException {
        throw new StateException("No se pueden realizar observaciones sobre este pedido");
    }

    @Override
    public void aceptarPedido() throws StateException {
        throw new StateException("pedido aun sin evaluar");
    }

    @Override
    public void evaluarPedido() {
        this.pedido.setEstadoActual(new Evaluacion(this.pedido));
    }
}
