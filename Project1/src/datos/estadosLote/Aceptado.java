package datos.estados;

import datos.Lote;
import datos.Observacion;

public class Aceptado extends EstadoBase{
    public Aceptado(Lote lote) {
        super(lote);
    }

    @Override
    public void agregarObservacion(Observacion obs) throws Exception {
        throw new Exception("Imposible agregar, lote ya aceptado");
    }

    @Override
    public void aceptarLote() throws Exception{
        throw new Exception("Imposible aceptar, lote ya aceptado");
    }
}
