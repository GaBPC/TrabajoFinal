package exceptions;

import listas.ListaMateriales;

public class FaltantesException extends Exception {
    private ListaMateriales faltantes;

    public FaltantesException(String mensaje, ListaMateriales faltantes) {
        super(mensaje);
        this.faltantes = faltantes;
    }

    public ListaMateriales getFaltantes() {
        return this.faltantes;
    }
}
