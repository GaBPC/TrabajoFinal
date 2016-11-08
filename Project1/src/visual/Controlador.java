package visual;

import listas.ListaPedidos;

import datos.Lote;
import datos.Material;
import datos.Pedido;

import datos.TipoProducto;

import datos.estadosPedido.Evaluacion;

import exceptions.ArgumentoIlegalException;

import exceptions.FaltantesException;
import exceptions.StateException;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Iterator;

import listas.ListaEmpleados;
import listas.ListaLotes;

import listas.ListaMateriales;
import listas.ListaMaterialesStock;

import personal.Empleado;

/** Clase utilizada para controlar todas las acciones que una ventana tiene permitido realizar.
 *  Cada ventana recibe una instancia de un controlador, la cual contiene todos los datos del
 *  sistema que permiten realizar las acciones soportadas.
 */
public class Controlador {
    // Atributo de clase que contiene la referencia a la lista de empleados del sistema
    private ListaEmpleados empleados = ListaEmpleados.getInstance();
    // Atributo de clase que contiene la referencia a la lista de pedidos del sistema
    private ListaPedidos pedidos = ListaPedidos.getInstance();
    // Atributo de clase que contiene la referencia a la lista de lotes del sistema
    private ListaLotes lotes = ListaLotes.getInstance();
    // Atributo de clase que contiene la referencia a la lista de materiales en stock del sistema
    private ListaMaterialesStock stock = ListaMaterialesStock.getInstance();

    // Atributo de clase que contiene una referencia al empleado que esta actualmente logeado en el sistema
    private Empleado empleadoActual = null;
    // Atributo de clase que contiene una referencia al pedido que se selecciono actualmente
    private Pedido pedidoActual = null;
    // Atributo de clase que contiene una referencia al tipo de producto del lote actual
    private TipoProducto productoActual = null;
    // Atributo de clase que contiene una referencia al lote que se selecciono actualmente
    private Lote loteActual = null;

    /**Constructor principal de la clase.
     */
    public Controlador() {
        super();
    }

    /* Metodos de set y get de la clase. En todos se verifica mediante assert que
     * no se trabaje con referencias null*/

    public void setEmpeladoActual(Empleado actual) {
        assert actual != null : "El parametro es null";
        this.empleadoActual = actual;
    }

    public Empleado getEmpeladoActual() {
        assert this.empleadoActual != null : "Atributo null";
        return this.empleadoActual;
    }

    public void setPedidoActual(Pedido pedido) {
        assert pedido != null : "El parametro es null";
        this.pedidoActual = pedido;
    }

    public Pedido getPedidoActual() {
        assert this.pedidoActual != null : "Atributo null";
        return this.pedidoActual;
    }

    public void setLoteActual(Lote loteActual) {
        assert loteActual != null : "El parametro es null";
        this.loteActual = loteActual;
    }

    public Lote getLoteActual() {
        assert this.loteActual != null : "Atributo null";
        return loteActual;
    }

    public void setProductoActual(TipoProducto productoActual) {
        assert productoActual != null : "El parametro es null";
        this.productoActual = productoActual;
    }

    public TipoProducto getProductoActual() {
        assert this.productoActual != null : "Atributo null";
        return productoActual;
    }

    /* -----------Fin de los metodos de set y get de la clase----------*/

    /**Metodo para buscar un empleado por su numero de legajo
     * @param legajo es el legajo del empleado buscado
     * @return una referencia al empleado si es encontrado, null en caso contrario
     */
    public Empleado buscarEmpleado(String legajo) throws Exception {
        return empleados.buscar(legajo);
    }

    /**Metodo que devuelve un iterator con todos los lotes que aun no han sido aceptados
     * @return iterator con los lotes
     */
    public Iterator<Pedido> getPedidosEvaluacion() {
        Iterator<Pedido> it = this.pedidos.getIterator();
        ArrayList<Pedido> lotesEv = new ArrayList<>();
        while (it.hasNext()) {
            Pedido lot = it.next();
            if (lot.isEnEvaluacion())
                lotesEv.add(lot);
        }
        it = lotesEv.iterator();
        return it;
    }

    /**Metodo que devuelve un iterator con todos los lotes que estan en estado iniciado
     * @return iterator con los lotes
     */
    public Iterator<Pedido> getPedidosIniciados() {
        Iterator<Pedido> it = this.pedidos.getIterator();
        ArrayList<Pedido> lotesEv = new ArrayList<>();
        while (it.hasNext()) {
            Pedido lot = it.next();
            if (lot.isIniciado())
                lotesEv.add(lot);
        }
        it = lotesEv.iterator();
        return it;
    }

    /**Metodo que devuelve un iterador con todos los lotes del sistema.
     * @return
     */
    public Iterator<Lote> getLotes() {
        return this.lotes.getIterator();
    }

    /**Metodo que cambia el estado del lote actual a estado evaluacion
     * @throws StateException en caso de que exista un error en el pasaje de estado
     */
    public void cambiarAEvaluacion() throws StateException {
        this.pedidoActual.evaluarPedido();
    }

    /**Metodo que cambia el estado del lote actual a estado aceptado
     * @param fechaProduccion
     * @throws StateException en caso de que exista un error en el pasaje de estado
     * @throws ArgumentoIlegalException
     */
    public void cambiarAAceptado(Calendar fechaProduccion) throws ArgumentoIlegalException, StateException {

        assert fechaProduccion != null : "El parametro es null";

        this.pedidoActual.aceptarPedido(fechaProduccion);
    }

    /**Metodo para crear un nuevo lote desde el sector de ventas.
     * @param numeroPedido es el numero asignado al pedido que se esta creando
     * @param fechaPedido es la fecha en la que se realiza el nuevo pedido
     * @param tipoMaquina es el tipo de maquina que debera ser producida
     * @param cantProducir es la cantidad de unidades a producir
     * @param fechaSolicitadaVentas es la fecha que propone ventas para tener el pedido listo
     */
    public void crearNuevoPedido(Calendar fechaPedido, String tipoMaquina, int cantProducir,
                                 Calendar fechaSolicitadaVentas) throws ArgumentoIlegalException {
        String aux = Integer.toString(this.pedidos.getProximoNumeroPedido());
        int longitud = aux.length();
        String maquina = ListaMaterialesStock.getInstance().getCodigo(tipoMaquina);
        String numeroPedido = "PED";
        for (int i = 0; i < (6 - longitud); i++)
            numeroPedido += "0";
        numeroPedido += aux;
        Pedido nuevo = new Pedido(numeroPedido, fechaPedido, fechaSolicitadaVentas, maquina, tipoMaquina, cantProducir);
        pedidos.agregarNuevo(nuevo);
    }

    public void generarLote() throws ArgumentoIlegalException {
        String aux = Integer.toString(this.lotes.getProximoNumeroLote());
        int longitud = aux.length();
        String numeroLote = "LOT";
        for (int i = 0; i < (6 - longitud); i++)
            numeroLote += "0";
        numeroLote += aux;
        Lote lote = new Lote(this.pedidoActual, numeroLote);
        this.lotes.agregarNuevo(lote);
    }

    public void removePedido() {
        this.pedidos.borrarPedido(this.pedidoActual);
    }

    public void removeLote() {
        this.lotes.borrarLote(this.loteActual);
    }

    public String detallesStock() {
        return ListaMaterialesStock.getInstance().detalles();
    }

    public ListaMateriales verificaExistencias(String tipo) throws FaltantesException, Exception {
        return this.stock.verificarExistencias(tipo, this.pedidoActual.getCantProduccion());
    }

    public void actualizarExistencias(TipoProducto tipo) {
        ListaMaterialesStock.getInstance().actualizarExistencias(tipo);
    }

    public TipoProducto getProducto(String codigo) {
        return ListaMaterialesStock.getInstance().getProducto(codigo);
    }

    public void borrarMaterial(String codigo) throws ArgumentoIlegalException {
        this.productoActual.getListaMateriales().borrarMaterial(codigo);
    }
}
