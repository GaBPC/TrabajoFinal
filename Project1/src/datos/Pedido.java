package datos;

import datos.estadosPedido.Estado;

import datos.estadosPedido.Iniciado;

import exceptions.StateException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.TreeSet;
import java.util.GregorianCalendar;
import java.util.Observable;

/**Clase que contiene todos los datos correspondiente a un pedido, extiende de la clase Observable e implementa la
 * interfaz ResumenClase
 * Invariante: los siguientes atributos cumpliran siempre las siguientes condiciones:
 *            numeroPedido: distinto de null y tiene que ser de la forma PEDXXXXXX siendo X un numero entero
 *            fechaPedido: Calendar distinto de null
 *            fechaEntregaVentas: Calendar distinto de null
 *            tipoMaqiuna: String distinto de null
 *            codigoMaquina: String distinto de null
 *            cantProduccion: float mayor o igual que 0 y menor que 1000
 *            estadoActual: distinto de null
 */
public class Pedido
  extends Observable
  implements ResumenClase
{
  private String numeroPedido = null; //numero que referencia a un pedido
  private Calendar fechaPedido = null; //fecha en la que se realiza el pedido
  private Calendar fechaEntregaVentas = null; //fecha en la que ventas propone la entrega
  private Calendar fechaPropuestaProduccion = null; //fecha en la que produccion propone la entrega
  private Calendar fechaDefinitiva = null; //fecha definitiva de entrega
  private Calendar fechaPedidoAceptado = null; //fecha en la que el pedido es aceptado
  private String codigoMaquina = null; //codigo que refiere al tipo de la maquina a producir en el pedido
  private String tipoMaquina = null; //tipo de maquina a producir en el pedido
  private int cantProduccion = 0; //cantidad a producir
  private Estado estadoActual = new Iniciado(this); //estado en el que se encuentra el pedido

  private TreeSet<Observacion> listaObservaciones = new TreeSet<>();

  /* No hay constructor vacio ya que solo es posible crear el lote con todos los datos que deben
     * brindar los empleados de ventas*/

  /**Constructor principal de la clase. Como el pedido es iniciado desde el sector de ventas, recibe
   * por parametros los datos que le corresponde completar a dicho sector de la empresa.
   * Pre: los siguientes parametros debe cumplir las siguientes precondiciones
   * @param numeroPedido: distinto de null y tiene que ser de la forma PEDXXXXXX siendo X un numero entero
   * @param fechaPedido: Calendar distinto de null
   * @param fechaEntregaVentas: Calendar distinto de null
   * @param tipoMaquina: String distinto de null
   * @param cantProduccion: float mayor o igual que 0 y menor que 1000
   * Post: se crea una instancia de la clase Pedido o se informa que parametro no es correcto
   */
  public Pedido(String numeroPedido, Calendar fechaPedido, Calendar fechaEntregaVentas, String codigoMaquina,
                String tipoMaquina, int cantProduccion)
  {
    assert Verificaciones.verificaNumeroPedido(numeroPedido): "Numero pedido invalido";
    assert Verificaciones.verificaCantProduccion(cantProduccion): "Cantidad invalida";
    assert fechaEntregaVentas != null: "Fecha de entrega invalida";
    assert Verificaciones.verificaCodigo(codigoMaquina) : "Codigo de maquina invalido";
    assert Verificaciones.verificaTipoProducto(tipoMaquina) : "Tipo de maquina invalido";
    assert fechaPedido != null: "Fecha de pedido invalida";

    this.numeroPedido = numeroPedido;
    this.codigoMaquina = codigoMaquina;
    this.cantProduccion = cantProduccion;
    this.tipoMaquina = tipoMaquina;
    this.fechaPedido = fechaPedido;
    this.fechaEntregaVentas = fechaEntregaVentas;

    this.verificarInvariantes();
  }


  /**Metodo que permite a los empleados tanto de ventas como produccion agregar una observacion al pedido, esta
   * sincronizado para que no se produzcan accesos simultaneos al metodo
   * Pre: obs distinto de null
   * @param obs
   * Post: se le agrega una observacion al pedido actual o se lanza una excepcion indicando que la observacion no es
   * correcta
   * @throws Exception si el pedido ya esta aceptado, no es posible agregar mas comentarios, por lo que se produce la exception
   */
  public synchronized void agregarObservacion(Observacion obs)
    throws StateException
  {
    assert obs != null: "Observacion nula";
    this.estadoActual.agregarObservacion(obs);
    this.verificarInvariantes();
  }

  /**Metodo que verifica que ningun atributo del pedido este sin inicializar. Como los atributos que son
   * inicializados en el constructor se mantienen constantes, y ya se verifico que esten bien inicializados, se asumen
   * como correctos
   * Post: se indica si el pedido cumple las condiciones o no
   * @return boolean
   */
  public boolean verificaNull()
  {
    return (this.fechaPedidoAceptado != null && this.fechaPropuestaProduccion != null && this.fechaDefinitiva != null);
  }

  /**Metodo que utilizaran los empleados de produccion para aceptar el pedido
   * Pre: fechaProduccion distinta de null
   * Post: se acepta el pedido si se cumplen todas las condiciones, sino se lanza una excepcion
   * @throws Exception si el pedido aun no esta listo para ser aceptado se produce la exception
   */
  public void aceptarPedido(Calendar fechaProduccion)
  throws StateException
  {
    assert fechaProduccion != null: "Fecha propuesta por produccion nula";
    this.fechaPedidoAceptado = GregorianCalendar.getInstance();
    this.fechaPropuestaProduccion = fechaProduccion;
    this.fechaDefinitiva = fechaProduccion;
    this.estadoActual.aceptarPedido();
    this.verificarInvariantes();
  }

  /**Metodo que se encarga de cambiar el estado del pedido a en Evaluacion
   * Post: Se cambia el estado del pedido o se lanza una excepcion informando el error
   * @throws StateException
   */
  public void evaluarPedido()
    throws StateException
  {
    this.estadoActual.evaluarPedido();
    this.verificarInvariantes();
  }

  /**Metodo que genera un String con los datos basicos del pedido
   * @return String
   */
  public String toString()
  {
    String ret = this.numeroPedido + ": " + this.tipoMaquina + " " + this.cantProduccion + "u.";
    return ret;
  }

  /**Metodo que genera un String con los detalles necesarios del pedido
   * @return String con los detalles
   */
  public String detalles()
  {
    String ret = "";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");

    ret += "Numero de pedido: " + this.numeroPedido;
    ret += "\nFecha de pedido: " + sdf.format(this.fechaPedido.getTime());
    ret += "\nTipo de maquina: " + this.codigoMaquina;
    ret += "\nCantidad a producir: " + this.cantProduccion;
    ret += "\nFecha de entrega solicitada por ventas: " + sdf.format(this.fechaEntregaVentas.getTime());
    ret +=
      "\nFecha propuesta por produccion: " +
      ((this.fechaPropuestaProduccion != null)? sdf.format(this.fechaPropuestaProduccion.getTime()): " - ");
    ret += "\nFecha definitiva: " + ((this.fechaDefinitiva != null)? sdf.format(this.fechaDefinitiva.getTime()): " - ");
    ret +=
      "\nFecha de pedido aceptado: " +
      ((this.fechaPedidoAceptado != null)? sdf.format(this.fechaPedidoAceptado.getTime()): " - ");
    return ret;
  }

  /**Metodo que indica si el pedido se encuentra en estado Iniciado o no
   * @return boolean
   */
  public boolean isIniciado()
  {
    return this.estadoActual.isIniciado();
  }

  /**Metodo que indica si el pedido se encuentra en estado en Evaluacion o no
   * @return boolean
   */
  public boolean isEnEvaluacion()
  {
    return this.estadoActual.isEnEvaluacion();
  }

  /**Metodo que indica si el pedido se encuentra en estado Aceptado o no
   * @return boolean
   */
  public boolean isAceptado()
  {
    return this.estadoActual.isAceptado();
  }

  public String getCodigoMaquina()
  {
    return codigoMaquina;
  }

  public int getCantProduccion()
  {
    return cantProduccion;
  }

  public TreeSet<Observacion> getListaObservaciones()
  {
    return listaObservaciones;
  }

  /**Metodo que establece el estado actual, cuando se modifica el atributo estadoActual, se le avisa a la ventana para
   * que actualice
   * pre: estadoActual distinto de null
   * @param estadoActual
   * post: cambia el estado del pedido
   */
  public void setEstadoActual(Estado estadoActual)
  {
    assert estadoActual != null: "Estado nulo";
    this.estadoActual = estadoActual;
    this.setChanged();
    this.notifyObservers();
    this.verificarInvariantes();
  }

  public Estado getEstadoActual()
  {
    return estadoActual;
  }

  /**Metodo que verifica que los invariantes de clase se cumplan. Si algo falla lanza un AssertError
   */
  private void verificarInvariantes()
  {
    assert Verificaciones.verificaNumeroPedido(this.numeroPedido): "Atributo numeroPedido invalido";
    assert Verificaciones.verificaCantProduccion(this.cantProduccion): "Atributo cantProduccion invalido";
    assert this.fechaEntregaVentas != null: "Atributo fechaEntregaVentas invalido";
    assert this.fechaPedido != null: "Atributo fechaPedido invalido";
    assert this.tipoMaquina != null: "Atributo tipoMaquina invalido";
    assert this.codigoMaquina != null: "Atributo codigoMaquina invalido";
    assert this.estadoActual != null: "Atributo estadoActual invalido";
  }

}
