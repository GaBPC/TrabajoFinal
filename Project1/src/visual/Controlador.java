package visual;

import datos.Lote;

import exceptions.ArgumentoIlegalException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Iterator;

import personal.Empleado;

public abstract class Controlador {

    public static final String VENTAS = "Ventas";
    public static final String PRODUCCION = "Produccion";
    public static final String CONTABILIDAD = "Contabilidad";
    public static final String INSPECCION = "Inspeccion y Calidad";

    private static HashMap<String, Empleado> empleados = new HashMap<>();

    static {
        empleados.put("LEG123456", new Empleado("LEG123456", "Prieto Gabriel", VENTAS));
        empleados.put("LEG000000", new Empleado("LEG000000", "Cassanelli Rodrigo", VENTAS));
        empleados.put("LEG456789", new Empleado("LEG456789", "Colautti Bruno", PRODUCCION));
        empleados.put("LEG111111", new Empleado("LEG111111", "Coppes Lucia", PRODUCCION));
        empleados.put("LEG333333", new Empleado("LEG333333", "Ruiz Gonzalo", CONTABILIDAD));
        empleados.put("LEG888888", new Empleado("LEG888888", "Khun Franco", INSPECCION));
    }


    private static ArrayList<Lote> lotes = new ArrayList();

    /**Metodo para buscar un empleado por su numero de legajo
     * @param legajo es el legajo del empleado buscado
     * @return una referencia al empleado si es encontrado, null en caso contrario
     */
    public static Empleado buscarEmpleado(String legajo) {
        return empleados.get(legajo);
    }

    /**Metodo para crear un nuevo lote desde el sector de ventas.
     * @param numeroPedido es el numero asignado al pedido que se esta creando
     * @param fechaPedido es la fecha en la que se realiza el nuevo pedido
     * @param tipoMaquina es el tipo de maquina que debera ser producida
     * @param cantProducir es la cantidad de unidades a producir
     * @param fechaSolicitadaVentas es la fecha que propone ventas para tener el pedido listo
     */
    public static void crearNuevoLote(String numeroPedido, Calendar fechaPedido, String tipoMaquina, int cantProducir,
                                      Calendar fechaSolicitadaVentas) throws ArgumentoIlegalException {

        Lote nuevo = new Lote(numeroPedido, fechaPedido, fechaSolicitadaVentas, tipoMaquina, cantProducir);
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
        System.out.println(numeroPedido + "\n" + sdf.format(fechaPedido.getTime()) + "\n" + tipoMaquina + "\n" +
                           cantProducir + "\n" + sdf.format(fechaSolicitadaVentas.getTime()));*/
        lotes.add(nuevo);

    }

    /**Metodo que devuelve un iterator con todos los lotes que aun no han sido aceptados
     * @return iterator con los lotes
     */
    public static Iterator getLotesNoAceptados() {
        return lotes.iterator();
    }
}
