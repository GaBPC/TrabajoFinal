package visual;

import personal.Empleado;

public class Controlador {
    public Controlador() {
        super();
    }

    public static Empleado buscarEmpleado(String legajo) {
        Empleado encontrado = new Empleado("LEG00", "Prieto Gabriel", "Ventas");

        if (encontrado.getLegajo().compareTo(legajo) == 0)

            return encontrado;
        else
            return null;
    }
}
