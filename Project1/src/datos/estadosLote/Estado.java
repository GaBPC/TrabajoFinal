package datos.estadosLote;

import datos.Observacion;

import exceptions.StateException;

public interface Estado {
    boolean isModificable();
    void agregarObservacion(Observacion obs) throws StateException;
    void aceptarLote() throws StateException;
    void evaluarLote() throws StateException;
    boolean isIniciado();
    boolean isAceptado();
    boolean isEnEvaluacion();
}
