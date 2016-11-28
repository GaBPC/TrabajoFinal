package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                    TipoProductoTestCajaNegra.class, StateExceptionTestCajaNegra.class, PedidoTestCajaNegra.class,
                    ObservacionTestCajaNegra.class, ObservacionTestCajaBlanca.class, MaterialTestCajaNegra.class,
                    LoteTestCajaNegra.class, ListaPedidosTestCajaNegra.class, ListaPedidosTestCajaBlanca.class,
                    ListaMaterialesTestCajaNegra.class, ListaMaterialesTestCajaBlanca.class,
                    ListaMaterialesStockTestCajaNegra.class, ListaLotesTestCajaNegra.class,
                    ListaEmpleadoTestCajaBlanca.class, ListaEmpleadosTestCajaNegra.class,
                    FaltantesExceptionTestCajaNegra.class, EvaluacionTestCajaNegra.class, EmpleadoTestCajaNegra.class,
                    ControladorTestCajaBlanca.class, AceptadoTestCajaNegra.class
    })
public class CNB {
}
