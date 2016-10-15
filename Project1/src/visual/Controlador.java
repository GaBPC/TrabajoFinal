package visual;

import datos.ListaLotes;
import datos.Lote;

import exceptions.ArgumentoIlegalException;

import java.util.Calendar;

import java.util.HashMap;
import java.util.Iterator;

import personal.Empleado;

/** Clase utilizada para controlar todas las acciones que una ventana tiene permitido realizar.
 *  Cada ventana recibe una instancia de un controlador, la cual contiene todos los datos del 
 *  sistema que permiten realizar las acciones soportadas.
 */
public class Controlador {

    public static final String VENTAS = "Ventas";
    public static final String PRODUCCION = "Produccion";
    public static final String CONTABILIDAD = "Contabilidad";
    public static final String INSPECCION = "Inspeccion y Calidad";

    private HashMap<String, Empleado> empleados = new HashMap<>();
    {
        empleados.put("LEG123456", new Empleado("LEG123456", "Prieto Gabriel", VENTAS));
        empleados.put("LEG000000", new Empleado("LEG000000", "Cassanelli Rodrigo", VENTAS));
        empleados.put("LEG456789", new Empleado("LEG456789", "Colautti Bruno", PRODUCCION));
        empleados.put("LEG111111", new Empleado("LEG111111", "Coppes Lucia", PRODUCCION));
        empleados.put("LEG333333", new Empleado("LEG333333", "Ruiz Gonzalo", CONTABILIDAD));
        empleados.put("LEG888888", new Empleado("LEG888888", "Khun Franco", INSPECCION));
    }

    private ListaLotes lotes = ListaLotes.getInstance();
    
    private Empleado empleadoActual = null;
    private Lote loteActual = null;
    
    public Controlador(){
        super();
    }
    
    public void setEmpeladoActual(Empleado actual){
        this.empleadoActual = actual;
    }
    
    public Empleado getEmpeladoActual(){
        return this.empleadoActual;
    }
    
    public void setLoteActual(Lote lote){
        this.loteActual = lote;
    }
    
    public Lote getLoteActual(){
        return this.loteActual;
    }


    /**Metodo para buscar un empleado por su numero de legajo
     * @param legajo es el legajo del empleado buscado
     * @return una referencia al empleado si es encontrado, null en caso contrario
     */
    public Empleado buscarEmpleado(String legajo) {
        return empleados.get(legajo);
    }

    /**Metodo para crear un nuevo lote desde el sector de ventas.
     * @param numeroPedido es el numero asignado al pedido que se esta creando
     * @param fechaPedido es la fecha en la que se realiza el nuevo pedido
     * @param tipoMaquina es el tipo de maquina que debera ser producida
     * @param cantProducir es la cantidad de unidades a producir
     * @param fechaSolicitadaVentas es la fecha que propone ventas para tener el pedido listo
     */
    public void crearNuevoLote(String numeroPedido, Calendar fechaPedido, String tipoMaquina, int cantProducir,
                               Calendar fechaSolicitadaVentas) throws ArgumentoIlegalException {
        Lote nuevo = new Lote(numeroPedido, fechaPedido, fechaSolicitadaVentas, tipoMaquina, cantProducir);
        lotes.agregarNuevo(nuevo);
    }

    /**Metodo que devuelve un iterator con todos los lotes que aun no han sido aceptados
     * @return iterator con los lotes
     */
    public Iterator<Lote> getLotesNoAceptados() {
        return lotes.getIterator();
    }
}
