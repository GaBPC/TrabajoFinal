package datos;

import java.util.Calendar;

public class Observacion {
    private String tema = null;
    private Calendar fechaObservacion = null;
    private String legajoEmpleado = null;
    private String texto;
    
    public Observacion() {
        super();
    }


    public String getTema() {
        return tema;
    }

    public Calendar getFechaObservacion() {
        return fechaObservacion;
    }

    public String getLegajoEmpleado() {
        return legajoEmpleado;
    }

    public String getTexto() {
        return texto;
    }
}
