package visual;

import datos.Lote;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Iterator;

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

    /**Metodo que devuelve un iterator con todos los lotes que aun no han sido aceptados
     * @return iterator con los lotes
     */
    public static Iterator getLotesNoAceptados() {
        //TODO crear array list con los lotes y devolverlo. Lo que esta es de ejemplo
        ArrayList<String> array = new ArrayList();
        array.add("Lote 1");
        array.add("Lote 2");
        array.add("Lote 3");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        array.add("Lote 4");
        return array.iterator();
    }
}
