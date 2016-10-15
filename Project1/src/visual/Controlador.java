package visual;

import datos.Lote;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import personal.Empleado;

public class Controlador {
    public Controlador() {
        super();
    }

    /**Metodo para buscar un empleado por su numero de legajo
     * @param legajo es el legajo del empleado buscado
     * @return una referencia al empleado si es encontrado, null encaso contrario
     */
    public static Empleado buscarEmpleado(String legajo) {
        Empleado encontrado = new Empleado("LEG00", "Prieto Gabriel", "Compras");

        if (encontrado.getLegajo().compareTo(legajo) == 0)

            return encontrado;
        else
            return null;
    }

    /**Metodo para crear un nuevo lote desde el sector de ventas.
     * @param numeroPedido es el numero asignado al pedido que se esta creando
     * @param fechaPedido es la fecha en la que se realiza el nuevo pedido
     * @param tipoMaquina es el tipo de maquina que debera ser producida
     * @param cantProducir es la cantidad de unidades a producir
     * @param fechaSolicitadaVentas es la fecha que propone ventas para tener el pedido listo
     */
    public static void crearNuevoLote(String numeroPedido, Calendar fechaPedido, String tipoMaquina, int cantProducir,
                                      Calendar fechaSolicitadaVentas) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
        System.out.println(numeroPedido + "\n" + sdf.format(fechaPedido.getTime()) + "\n" + tipoMaquina + "\n" +
                           cantProducir + "\n" + sdf.format(fechaSolicitadaVentas.getTime()));

    }
}
