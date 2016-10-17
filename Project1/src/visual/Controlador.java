package visual;

import listas.ListaPedidos;

import datos.Lote;
import datos.Pedido;

import datos.estadosPedido.Evaluacion;

import exceptions.ArgumentoIlegalException;

import exceptions.StateException;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Iterator;

import listas.ListaLotes;

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
        try {
            empleados.put("LEG123456", new Empleado("LEG123456", "Prieto Gabriel", VENTAS));
            empleados.put("LEG000000", new Empleado("LEG000000", "Cassanelli Rodrigo", VENTAS));
            empleados.put("LEG456789", new Empleado("LEG456789", "Colautti Bruno", PRODUCCION));
            empleados.put("LEG111111", new Empleado("LEG111111", "Coppes Lucia", PRODUCCION));
            empleados.put("LEG333333", new Empleado("LEG333333", "Ruiz Gonzalo", CONTABILIDAD));
            empleados.put("LEG888888", new Empleado("LEG888888", "Khun Franco", INSPECCION));
        } catch (ArgumentoIlegalException e) {
            System.out.println(e.getMessage());
        }
    }

    private ListaPedidos pedidos = ListaPedidos.getInstance();
    private ListaLotes lotes = ListaLotes.getInstance();

    private Empleado empleadoActual = null;
    private Pedido pedidoActual = null;

    public Controlador() {
        super();
    }

    public void setEmpeladoActual(Empleado actual) {
        this.empleadoActual = actual;
    }

    public Empleado getEmpeladoActual() {
        return this.empleadoActual;
    }

    public void setLoteActual(Pedido lote) {
        this.pedidoActual = lote;
    }

    public Pedido getLoteActual() {
        return this.pedidoActual;
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
        Pedido nuevo = new Pedido(numeroPedido, fechaPedido, fechaSolicitadaVentas, tipoMaquina, cantProducir);
        pedidos.agregarNuevo(nuevo);
    }

    /**Metodo que devuelve un iterator con todos los lotes que aun no han sido aceptados
     * @return iterator con los lotes
     */
    public Iterator<Pedido> getPedidosEvaluacion() {
        Iterator<Pedido> it = this.pedidos.getIterator();
        ArrayList<Pedido> lotesEv = new ArrayList<>();
        while (it.hasNext()) {
            Pedido lot = (Pedido) it.next();
            if (lot.isEnEvaluacion())
                lotesEv.add(lot);
        }
        it = lotesEv.iterator();
        return it;
    }

    public Iterator<Pedido> getPedidosIniciados() {
        Iterator<Pedido> it = this.pedidos.getIterator();
        ArrayList<Pedido> lotesEv = new ArrayList<>();
        while (it.hasNext()) {
            Pedido lot = (Pedido) it.next();
            if (lot.isIniciado())
                lotesEv.add(lot);
        }
        it = lotesEv.iterator();
        return it;
    }
    
    public Iterator<Lote> getLotes() {
        Iterator<Lote> it = this.lotes.getIterator();
        ArrayList<Lote> lotes = new ArrayList<>();
        while (it.hasNext()) {
            Lote lot = (Lote) it.next();
            lotes.add(lot);
        }
        it = lotes.iterator();
        return it;
    }

    public void cambiarAEvaluacion() throws StateException {
        this.pedidoActual.evaluarPedido();
    }

    public void cambiarAAceptado(Calendar fechaProduccion) throws ArgumentoIlegalException, StateException {
        this.pedidoActual.aceptarPedido(fechaProduccion);
    }

    public void generarLote(String numeroLote) throws ArgumentoIlegalException {
        Lote lote = new Lote(this.pedidoActual, numeroLote);
        this.lotes.agregarNuevo(lote);
    }
}
