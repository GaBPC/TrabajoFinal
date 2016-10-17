package datos.estadosLote;

import datos.Lote;
import datos.Observacion;

import exceptions.StateException;

public class Aceptado extends EstadoBase{
    public Aceptado(Lote lote) {
        super(lote);
    }

    @Override
    public boolean isAceptado()
    {
      return true;
    }

    @Override
    public void agregarObservacion(Observacion obs) throws StateException {
        throw new StateException("Imposible agregar, lote ya aceptado");
    }

    @Override
    public void aceptarLote() throws StateException{
        throw new StateException("Imposible aceptar, lote ya aceptado");
    }
    
    @Override
    public void evaluarLote() throws StateException {
        throw new StateException("El lote ha sido aceptado");
    }
}
