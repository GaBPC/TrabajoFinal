package datos.estadosLote;

import datos.Lote;
import datos.Observacion;

import exceptions.StateException;

public class Aceptado extends EstadoBase{
    public Aceptado(Lote lote) {
        super(lote);
    }

    @Override
    public void agregarObservacion(Observacion obs) throws Exception {
        throw new StateException("Imposible agregar, lote ya aceptado");
    }

    @Override
    public void aceptarLote() throws Exception{
        throw new StateException("Imposible aceptar, lote ya aceptado");
    }
}
