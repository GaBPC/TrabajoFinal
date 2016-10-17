package datos.estadosLote;

import datos.Lote;
import datos.Observacion;

import exceptions.StateException;

public class Iniciado extends EstadoBase {

    public Iniciado(Lote lote) {
        super(lote);
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
        throw new StateException("No se pueden realizar observaciones sobre este lote");
    }

    @Override
    public void aceptarLote() throws StateException {
        throw new StateException("Lote aun sin evaluar");
    }

    @Override
    public void evaluarLote() throws StateException {
        this.lote.setEstadoActual(new Evaluacion(this.lote));
    }
}
