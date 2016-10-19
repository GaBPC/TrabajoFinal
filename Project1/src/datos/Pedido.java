package datos;

import datos.estadosPedido.Estado;

import datos.estadosPedido.Iniciado;

import exceptions.ArgumentoIlegalException;

import exceptions.StateException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Observable;

import listas.ListaMateriales;
import listas.ListaMaterialesStock;

/**Clase que contiene todos los datos correspondiente a un lote.
 */
public class Pedido extends Observable implements ResumenClase {
    private String numeroPedido = null;
    private Calendar fechaPedido = null, fechaEntregaVentas = null, fechaPropuestaProduccion = null, fechaDefinitiva =
        null, fechaPedidoAceptado = null;
    private String codigoMaquina = null;
    private String tipoMaquina = null;
    private int cantProduccion = 0;
    private Estado estadoActual;
    
    private TreeSet<Observacion> listaObservaciones;

    /* No hay constructor vacio ya que solo es posible crear el lote con todos los datos que deben
     * brindar los empleados de ventas*/

    /**Constructor principal de la clase. Como el lote es iniciado desde el sector de ventas, recibe
     * por parametros los datos que le corresponde completar a dicho sector de la empresa.
     * @param numeroPedido
     * @param fechaPedido
     * @param fechaEntregaVentas
     * @param tipoMaquina
     * @param cantProduccion
     * @throws ArgumentoIlegalException Si algun parametro no cumple con alguna restriccion, es informado mediante esta excepcion
     */
    public Pedido(String numeroPedido, Calendar fechaPedido, Calendar fechaEntregaVentas, String codigoMaquina, String tipoMaquina,
                  int cantProduccion) throws ArgumentoIlegalException {
        if (this.verificaNumeroPedido(numeroPedido))
            this.numeroPedido = numeroPedido;
        if (codigoMaquina != null)
            this.codigoMaquina = codigoMaquina;
        if (this.verificaCantProduccion(cantProduccion))
            this.cantProduccion = cantProduccion;
        if (tipoMaquina != null)
            this.tipoMaquina = tipoMaquina;
        this.fechaPedido = fechaPedido;
        this.fechaEntregaVentas = fechaEntregaVentas;
        this.listaObservaciones = new TreeSet<>();
        this.estadoActual = new Iniciado(this);
    }

    /**Metodo que verifica si el numero de los legajos o identificadores cumple con las restricciones de longitud
     * @param str
     * @return
     * @throws ArgumentoIlegalException
     */
    private boolean verifica(String str) throws ArgumentoIlegalException {
        boolean ret = false;
        if (str.length() == 9) {
            int num = Integer.parseInt(str.substring(3).trim());
            if (num >= 0 && num <= 999999)
                ret = true;
            else
                throw new ArgumentoIlegalException("El numero esta fuera de rango", num);
        } else
            throw new ArgumentoIlegalException("El numero debe tener 6 digitos", str);
        return ret;
    }

    /**Metodo que verifica si el numero de pedido cumple con la restriccion de longitud y que ademas
     * incluya el prefijo PED
     * @param numeroPedido
     * @return
     * @throws ArgumentoIlegalException
     */
    private boolean verificaNumeroPedido(String numeroPedido) throws ArgumentoIlegalException {
        boolean ret = false;
        String aux = numeroPedido.substring(0, 3);
        if (aux.compareTo("PED") == 0)
            ret = verifica(numeroPedido);
        else
            throw new ArgumentoIlegalException("El numero de pedido no contiene \"PED\"", numeroPedido);
        return ret;
    }

    /**Metodo que verifica que ningun atributo del lote este sin inicializar. Como los atributos que son
     * inicializados en el constructor no cambiaran, y ya se verifico que esten bien inicializados, se asumen
     * como correctos
     * @return
     */
    public boolean verificaNull() {
        return (this.fechaPedidoAceptado != null && this.fechaPropuestaProduccion != null);
    } //FALTA FECHA DEFINITIVA

    /**Metodo que verifica que la cantidad a producir este en el intervalo 0<cantidadProducir<999
     * @param cantProduccion
     * @return
     */
    private boolean verificaCantProduccion(int cantProduccion) throws ArgumentoIlegalException {
        if (cantProduccion > 0 && cantProduccion < 999)
            return true;
        else
            throw new ArgumentoIlegalException("La cantidad a producir debe ser 0 < cant < 999", cantProduccion);       
    }

    /**Metodo que permite cambiar los estados del lote, mediante el patron State
     * @param estadoActual
     */
    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
        this.setChanged();
        this.notifyObservers();
    }

    /**Metodo que devuelve el estado actual del lote, mediante el patron State
     * @return
     */
    public Estado getEstadoActual() {
        return estadoActual;
    }

    /**Metodo que permite a los empleados tanto de ventas como produccion agregar una observacion al lote
     * @param obs
     * @throws Exception si el lote ya esta aceptado, no es posible agregar mas comentarios, por lo que se produce la exception
     */
    public synchronized void agregarObservacion(Observacion obs) throws Exception {
      this.estadoActual.agregarObservacion(obs);
    }

    /**Metodo que utilizaran los empleados de produccion para aceptar el lote
     * @throws Exception si el lote aun no esta listo para ser aceptado se produce la exception
     */
    public void aceptarPedido(Calendar fechaProduccion) throws ArgumentoIlegalException, StateException {

        this.fechaPedidoAceptado = GregorianCalendar.getInstance();
        this.fechaPropuestaProduccion = fechaProduccion;
        this.estadoActual.aceptarPedido();
    }

    public void evaluarPedido() throws StateException {
        this.estadoActual.evaluarPedido();
    }

    /**Metodo que devuelve la coleccion que contiene todas las observaciones del lote
     * @return
     */
    public TreeSet<Observacion> getListaObservaciones() {
        return listaObservaciones;
    }

    public String toString() {
        String ret = this.numeroPedido + ": " + this.tipoMaquina + " " + this.cantProduccion + "u.";
        return ret;
    }

    public String detalles() {
        String ret = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");

        try {
            ret += "Numero de pedido: " + ((this.verificaNumeroPedido(this.numeroPedido)) ? this.numeroPedido : " - ");
            ret +=
                "\nFecha de pedido: " + ((this.fechaPedido != null) ? sdf.format(this.fechaPedido.getTime()) : " - ");
            ret += "\nTipo de maquina: " + ((this.codigoMaquina != null) ? this.codigoMaquina : " - ");
            ret +=
                "\nCantidad a producir: " +
                ((this.verificaCantProduccion(this.cantProduccion)) ? this.cantProduccion : " - ");
            ret +=
                "\nFecha de entrega solicitada por ventas: " +
                ((this.fechaEntregaVentas != null) ? sdf.format(this.fechaEntregaVentas.getTime()) : " - ");
            ret +=
                "\nFecha propuesta por produccion: " +
                ((this.fechaPropuestaProduccion != null) ? sdf.format(this.fechaPropuestaProduccion.getTime()) : " - ");
            ret +=
                "\nFecha definitiva: " +
                ((this.fechaDefinitiva != null) ? sdf.format(this.fechaDefinitiva.getTime()) : " - ");
            ret +=
                "\nFecha de pedido aceptado: " +
                ((this.fechaPedidoAceptado != null) ? sdf.format(this.fechaPedidoAceptado.getTime()) : " - ");
        } catch (ArgumentoIlegalException e) {
        }
        return ret;
    }

    public boolean isIniciado() {
        return this.estadoActual.isIniciado();
    }

    public boolean isEnEvaluacion() {
        return this.estadoActual.isEnEvaluacion();
    }

    public boolean isAceptado() {
        return this.estadoActual.isAceptado();
    }


  public String getCodigoMaquina()
  {
    return codigoMaquina;
  }

    public int getCantProduccion() {
        return cantProduccion;
    }
}
