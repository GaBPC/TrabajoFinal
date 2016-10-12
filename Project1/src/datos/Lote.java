package datos;

import datos.estados.Estado;

import datos.estados.Iniciado;

import java.util.Calendar;
import java.util.TreeMap;
import java.util.TreeSet;

public class Lote {
    private String numeroPedido = null, numeroLote = null; ;
    private Calendar fechaPedido = null, fechaEntregaVentas = null, fechaPropuestaProduccion = null, fechaDefinitiva =
        null, fechaPedidoAceptado = null;
    private String tipoMaquina = null;
    private int cantProduccion = 0;
    private Estado estadoActual;

    private TreeSet<Observacion> listaObservaciones;

    public Lote() {
        super();
    }

    public Lote(String numeroPedido, String numeroLote, Calendar fechaPedido, Calendar fechaEntregaVentas,
                Calendar fechaPropuestaProduccion, Calendar fechaDefinitiva, Calendar fechaPedidoAceptado,
                String tipoMaquina, int cantProduccion) {
        this.numeroPedido = numeroPedido;
        this.numeroLote = numeroLote;
        this.fechaPedido = fechaPedido;
        this.fechaEntregaVentas = fechaEntregaVentas;
        this.fechaPropuestaProduccion = fechaPropuestaProduccion;
        this.fechaDefinitiva = fechaDefinitiva;
        this.fechaPedidoAceptado = fechaPedidoAceptado;
        this.tipoMaquina = tipoMaquina;
        this.cantProduccion = cantProduccion;

        this.listaObservaciones = new TreeSet<>();

        this.estadoActual = new Iniciado(this);
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void agregarObservacion(Observacion obs) throws Exception{
        this.estadoActual.agregarObservacion(obs);
    }
    
    public void aceptarLote() throws Exception {
        this.estadoActual.aceptarLote();
    }

    public TreeSet<Observacion> getListaObservaciones() {
        return listaObservaciones;
    }
}
