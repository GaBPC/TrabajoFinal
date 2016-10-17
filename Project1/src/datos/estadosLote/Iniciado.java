package datos.estadosLote;

import datos.Lote;
import datos.Observacion;

import exceptions.StateException;

public class Iniciado extends EstadoBase{
    
    public Iniciado(Lote lote) {
        super(lote);
    }

    @Override
    public boolean isModificable() {
        return true;
    }
    
    @Override
    public void agregarObservacion(Observacion obs) throws Exception{
        throw new StateException("No se pueden realizar observaciones sobre este lote");
    }

    @Override
    public void aceptarLote() throws Exception{
        throw new StateException("Lote aun sin evaluar");
    }
}
