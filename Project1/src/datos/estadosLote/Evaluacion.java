package datos.estadosLote;

import datos.Lote;
import datos.Observacion;

public class Evaluacion extends EstadoBase {
    public Evaluacion(Lote lote) {
        super(lote);
    }
    
    private boolean verificaTexto(String texto) {
        return (texto.length() <= 500);
    }
    
    @Override
    public boolean isModificable() {
        return true;
    }

    @Override
    public void agregarObservacion(Observacion obs) throws Exception {
        if (obs.getTema() != null && obs.getFechaObservacion() != null && obs.getLegajoEmpleado() != null && this.verificaTexto(obs.getTexto()))
            this.lote.getListaObservaciones().add(obs);
        else
            throw new Exception("Observacion invalida");
    }

    @Override
    public void aceptarLote() throws Exception{
        this.lote.setEstadoActual(new Aceptado(this.lote));
    }
}
