package datos.estadosLote;

import datos.Lote;
import datos.Observacion;

import exceptions.StateException;

public class Evaluacion extends EstadoBase {
    public Evaluacion(Lote lote) {
        super(lote);
    }
    
    @Override
    public boolean isModificable() {
        return true;
    }

    @Override
    public void agregarObservacion(Observacion obs) throws StateException {
        if (obs.verificacion())
            this.lote.getListaObservaciones().add(obs);
        else
            throw new StateException("Observacion invalida");
    }

    @Override
    public void aceptarLote() throws StateException{
        if(this.lote.verificaNull())
          this.lote.setEstadoActual(new Aceptado(this.lote));
        else
          throw new StateException("El lote no está listo para ser aceptado");
    }
}
