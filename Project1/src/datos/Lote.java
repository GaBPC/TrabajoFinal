package datos;

import java.util.Calendar;

public class Lote {
    private String numeroPedido = null, numeroLote = null; ;
    private Calendar fechaPedido = null, fechaEntregaVentas = null, fechaPropuestaProduccion = null, fechaDefinitiva =
        null, fechaPedidoAceptado = null;
    private String tipoMaquina = null, estado = null;
    private int cantProduccion = 0;

    public Lote() {
        super();
    }
}
