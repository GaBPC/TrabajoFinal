package visual;

import listas.ListaPedidos;

import datos.Lote;
import datos.Observacion;
import datos.Pedido;

import datos.TipoProducto;

import datos.Verificaciones;

import exceptions.FaltantesException;
import exceptions.StateException;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
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
public class Controlador
{
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
  public Controlador()
  {
    super();
  }

  /* Metodos de set y get de la clase. En todos se verifica mediante assert que
     * no se trabaje con referencias null*/

  public void setEmpeladoActual(Empleado actual)
  {
    assert actual != null: "El parametro es null";
    this.empleadoActual = actual;
  }

  public Empleado getEmpeladoActual()
  {
    assert this.empleadoActual != null: "Atributo null";
    return this.empleadoActual;
  }

  public void setPedidoActual(Pedido pedido)
  {
    assert pedido != null: "El parametro es null";
    this.pedidoActual = pedido;
  }

  public Pedido getPedidoActual()
  {
    assert this.pedidoActual != null: "Atributo null";
    return this.pedidoActual;
  }

  public void setLoteActual(Lote loteActual)
  {
    assert loteActual != null: "El parametro es null";
    this.loteActual = loteActual;
  }

  public Lote getLoteActual()
  {
    assert this.loteActual != null: "Atributo null";
    return loteActual;
  }

  public void setProductoActual(TipoProducto productoActual)
  {
    assert productoActual != null: "El parametro es null";
    this.productoActual = productoActual;
  }

  public TipoProducto getProductoActual()
  {
    assert this.productoActual != null: "Atributo null";
    return productoActual;
  }

  /* -----------Fin de los metodos de set y get de la clase----------*/

  /**Metodo para buscar un empleado por su numero de legajo
   * @param legajo es el legajo del empleado buscado
   * @return una referencia al empleado si es encontrado, null en caso contrario
   */
  public Empleado buscarEmpleado(String legajo)
    throws Exception
  {
    return empleados.buscar(legajo);
  }

  /**Metodo que devuelve un iterator con todos los lotes que aun no han sido aceptados
   * @return iterator con los lotes
   */
  public Iterator<Pedido> getPedidosEvaluacion()
  {
    Iterator<Pedido> it = this.pedidos.getIterator();
    ArrayList<Pedido> lotesEv = new ArrayList<>();
    while (it.hasNext())
    {
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
  public Iterator<Pedido> getPedidosIniciados()
  {
    Iterator<Pedido> it = this.pedidos.getIterator();
    ArrayList<Pedido> lotesEv = new ArrayList<>();
    while (it.hasNext())
    {
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
  public Iterator<Lote> getLotes()
  {
    return this.lotes.getIterator();
  }

  /**Metodo que cambia el estado del lote actual a estado evaluacion
   * @throws StateException en caso de que exista un error en el pasaje de estado
   */
  public void cambiarAEvaluacion()
    throws StateException
  {
    this.pedidoActual.evaluarPedido();
  }

  /**Metodo que cambia el estado del lote actual a estado aceptado
   * @param fechaProduccion
   * @throws StateException en caso de que exista un error en el pasaje de estado
   * @throws ArgumentoIlegalException
   */
  public void cambiarAAceptado(Calendar fechaProduccion)
    throws StateException
  {

    assert fechaProduccion != null: "El parametro es null";

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
                               Calendar fechaSolicitadaVentas)
    throws Exception
  {
    if (Verificaciones.verificaTipoProducto(tipoMaquina))
    {
      String maquina = ListaMaterialesStock.getInstance().getCodigo(tipoMaquina);
      String aux = Integer.toString(this.pedidos.getProximoNumeroPedido());
      int longitud = aux.length();
      String numeroPedido = "PED";
      for (int i = 0; i < (6 - longitud); i++)
        numeroPedido += "0";
      numeroPedido += aux;
      if (Verificaciones.verificaTipoCodigo(maquina) && Verificaciones.verificaNumeroPedido(numeroPedido) &&
          fechaPedido != null && Verificaciones.verificaTipoProducto(tipoMaquina) &&
          Verificaciones.verificaCantProduccion(cantProducir) && fechaSolicitadaVentas != null)
      {
        Pedido nuevo = new Pedido(numeroPedido, fechaPedido, fechaSolicitadaVentas, maquina, tipoMaquina, cantProducir);
        pedidos.agregarNuevo(nuevo);
      }
      else
        throw new Exception("Pedido invalido");
    }
    else
      throw new Exception("Tipo producto invalido");
  }

  /**Metodo que genera un lote con el pedido actualmente seleccionado. El sistema se encarga
   * de calcular el numero de lote correspondiente.
   * @throws Exception
   */
  public void generarLote()
    throws Exception
  {
    String aux = Integer.toString(this.lotes.getProximoNumeroLote());
    int longitud = aux.length();
    String numeroLote = "LOT";
    for (int i = 0; i < (6 - longitud); i++)
      numeroLote += "0";
    numeroLote += aux;
    if (this.pedidoActual != null && Verificaciones.verificaNumeroLote(numeroLote))
    {
      Lote lote = new Lote(this.pedidoActual, numeroLote);
      if (lote != null)
        this.lotes.agregarNuevo(lote);
      else
        throw new Exception("El lote no se creo");
    }
    else
      throw new Exception("El lote es invalido");
  }

  /**Metodo que elimina de la lista de pedidos el pedido actualmente seleccionado.
   */
  public void removePedido()
  {
    this.pedidos.borrarPedido(this.pedidoActual);
  }

  /**Metodo que elimina de la lista de lotes el lote actualmente seleccionado.
   */
  public void removeLote()
  {
    if (this.loteActual != null)
      this.lotes.borrarLote(this.loteActual);
  }

  /**Metodo que devuelve un String con los detalles de los materiales que hay actualmente
   * en stock.
   * @return
   */
  public String detallesStock()
  {
    return ListaMaterialesStock.getInstance().detalles();
  }

  /**Metodo que devuelve una lista con las existencias actuales del tipo de material
   * indicado por parametro.
   * @param tipo
   * @return
   * @throws FaltantesException
   * @throws Exception
   */
  public ListaMateriales verificaExistencias(String tipo)
    throws FaltantesException, Exception
  {
    ListaMateriales list;
    if (Verificaciones.verificaTipoCodigo(tipo) &&
        Verificaciones.verificaCantidad(this.pedidoActual.getCantProduccion()))
      list = this.stock.verificarExistencias(tipo, this.pedidoActual.getCantProduccion());
    else
      throw new Exception("Cantidad o producto invalido");
    return list;
  }

  public void actualizarExistencias(TipoProducto tipo)
  {
    ListaMaterialesStock.getInstance().actualizarExistencias(tipo);
  }

  public TipoProducto getProducto(String codigo)
  {
    return ListaMaterialesStock.getInstance().getProducto(codigo);
  }

  public void borrarMaterial(String codigo)
    throws Exception
  {
    if (Verificaciones.verificaCodigo(codigo))
      this.productoActual
          .getListaMateriales()
          .borrarMaterial(codigo);
    else
      throw new Exception("Codigo invalido");
  }

  /**Metodo para generar una nueva observacion. Esta observacion sera generada por el empleado
   * que este actualmente logeado al sistema.
   * @param temaIngresado es el tema que tendra la nueva observacion
   * @param texto es el texto que el empleado quiere ingresar como observacion
   * @return
   * @throws Exception
   */
  public Observacion crearObservacion(String temaIngresado, String texto)
    throws Exception
  {
    Observacion obs = null;
    String numeroLegajo = this.empleadoActual.getLegajo();
    Calendar cal = GregorianCalendar.getInstance();
    if (temaIngresado != null && Verificaciones.verificaNumeroLegajo(numeroLegajo) &&
        Verificaciones.verificaTexto(texto) && cal != null)
      obs = new Observacion(temaIngresado, cal, numeroLegajo, texto);
    else
      throw new Exception("La observacion es invalida");
    return obs;
  }
}
