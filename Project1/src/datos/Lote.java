package datos;

import datos.estadosLote.Estado;

import datos.estadosLote.Iniciado;

import java.util.Calendar;
import java.util.TreeSet;
import java.util.ArrayList;

public class Lote
{
  private String numeroPedido = null, numeroLote = null; ;
  private Calendar fechaPedido = null, fechaEntregaVentas = null, fechaPropuestaProduccion = null, fechaDefinitiva =
    null, fechaPedidoAceptado = null;
  private String tipoMaquina = null;
  private int cantProduccion = 0;
  private Estado estadoActual;

  private TreeSet<Observacion> listaObservaciones;

  public Lote()
  {
    super();
  }

  public Lote(String numeroPedido, String numeroLote, Calendar fechaPedido, Calendar fechaEntregaVentas,
              Calendar fechaPropuestaProduccion, Calendar fechaDefinitiva, Calendar fechaPedidoAceptado,
              String tipoMaquina, int cantProduccion)
  {
    if (this.verificaNumeroPedido(numeroPedido))
      this.numeroPedido = numeroPedido;
    if(this.verificaNumeroLote(numeroLote))
      this.numeroLote = numeroLote;
    if(this.verficaTipoMaquina(tipoMaquina))
      this.tipoMaquina = tipoMaquina;
    if(this.verificaCantProduccion(cantProduccion))
      this.cantProduccion = cantProduccion;
    this.fechaPedido = fechaPedido;
    this.fechaEntregaVentas = fechaEntregaVentas;
    this.fechaPropuestaProduccion = fechaPropuestaProduccion;
    this.fechaDefinitiva = fechaDefinitiva;
    this.fechaPedidoAceptado = fechaPedidoAceptado;

    this.listaObservaciones = new TreeSet<>();

    this.estadoActual = new Iniciado(this);
  }

  private boolean verifica(String str)
  {
    boolean ret = false;
    int num = Integer.parseInt(numeroPedido.substring(3).trim());
    if (num >= 0 && num <= 999999)
      ret = true;
    return ret;
  }

  private boolean verificaNumeroPedido(String numeroPedido)
  {
    boolean ret = false;
    String aux = numeroPedido.substring(0, 3);
    if (aux.compareTo("PED") == 0)
      ret = verifica(numeroPedido);
    return ret;
  }

  private boolean verificaNumeroLote(String numeroLote)
  {
    boolean ret = false;
    String aux = numeroLote.substring(0, 3);
    if (aux.compareTo("LOT") == 0)
      ret = verifica(numeroLote);
    return ret;
  }
  
  private boolean verficaTipoMaquina(String tipoMaquina)
  {
    return (tipoMaquina != null);
  }
  
  private boolean verificaCantProduccion(int cantProduccion)
  {
    return (cantProduccion > 0 && cantProduccion < 999);
  }

  public void setEstadoActual(Estado estadoActual)
  {
    this.estadoActual = estadoActual;
  }

  public Estado getEstadoActual()
  {
    return estadoActual;
  }

  public void agregarObservacion(Observacion obs)
    throws Exception
  {
    this.estadoActual.agregarObservacion(obs);
  }

  public void aceptarLote()
    throws Exception
  {
    this.estadoActual.aceptarLote();
  }

  public TreeSet<Observacion> getListaObservaciones()
  {
    return listaObservaciones;
  }
}
