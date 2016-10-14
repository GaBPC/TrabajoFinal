package datos;

import java.util.Calendar;

public class Observacion {
    private String tema = null;
    private Calendar fechaObservacion = null;
    private String legajoEmpleado = null;
    private String texto;
    
    public Observacion(String tema,Calendar fechaObservacion,String legajoEmpleado,String texto) {
        super();
        this.tema = tema;
        this.fechaObservacion = fechaObservacion;
        this.legajoEmpleado = legajoEmpleado;
        if(this.verificaTexto(texto))
          this.texto = texto;
    }

    private boolean verificaTexto(String texto)
    {
      return (texto.length() <= 500);
    }
    
    public boolean verificacion()
    {
      return (this.tema != null && this.fechaObservacion != null && this.legajoEmpleado != null && this.texto != null);
    }
}
