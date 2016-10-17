package datos.estadosLote;

import datos.Observacion;

public interface Estado {
    boolean isModificable();
    void agregarObservacion(Observacion obs) throws Exception;
    void aceptarLote() throws Exception;
    boolean isIniciado();
    boolean isAceptado();
    boolean isEvaluado();
}
