package datos.estados;

import datos.Lote;
import datos.Observacion;

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
        this.lote.setEstadoActual(new Evaluacion(this.lote));
        this.lote.getEstadoActual().agregarObservacion(obs);
    }

    @Override
    public void aceptarLote() throws Exception{
        throw new Exception("Lote aun sin evaluar");
    }
}
