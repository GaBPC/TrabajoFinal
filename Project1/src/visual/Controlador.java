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

    private ListaEmpleados empleados = ListaEmpleados.getInstance();
    private ListaPedidos pedidos = ListaPedidos.getInstance();
    private ListaLotes lotes = ListaLotes.getInstance();
    private ListaMaterialesStock stock = ListaMaterialesStock.getInstance();

    private Empleado empleadoActual = null;
    private Pedido pedidoActual = null;
    private TipoProducto productoActual = null;

    public Controlador() {
        super();
    }

    public void setEmpeladoActual(Empleado actual) {
        this.empleadoActual = actual;
    }

    public Empleado getEmpeladoActual() {
        return this.empleadoActual;
    }

    public void setPedidoActual(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    public Pedido getPedidoActual() {
        return this.pedidoActual;
    }

  public void setProductoActual(TipoProducto productoActual)
  {
    this.productoActual = productoActual;
  }

  public TipoProducto getProductoActual()
  {
    return productoActual;
  }

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
        return this.lotes.getIterator();
    }

    public void cambiarAEvaluacion() throws StateException {
        this.pedidoActual.evaluarPedido();
    }

    public void cambiarAAceptado(Calendar fechaProduccion) throws ArgumentoIlegalException, StateException {
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

    public ListaMateriales verificaExistencias(String tipo) throws FaltantesException, Exception {
        return this.stock.verificarExistencias(tipo, this.pedidoActual.getCantProduccion());
    }
}
