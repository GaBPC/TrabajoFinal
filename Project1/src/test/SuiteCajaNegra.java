package test;

import junit.AceptadoTestCajaNegra;
import junit.ControladorTestCajaNegra;
import junit.EmpleadoTestCajaNegra;
import junit.EvaluacionTestCajaNegra;
import junit.FaltantesExceptionTestCajaNegra;
import junit.ListaEmpleadosTestCajaNegra;
import junit.ListaLotesTestCajaNegra;
import junit.ListaMaterialesStockTestCajaNegra;
import junit.ListaMaterialesTestCajaNegra;
import junit.ListaPedidosTestCajaNegra;
import junit.LoteTestCajaNegra;
import junit.MaterialTestCajaNegra;
import junit.ObservacionTestCajaNegra;
import junit.PedidoTestCajaNegra;
import junit.StateExceptionTestCajaNegra;
import junit.TipoProductoTestCajaNegra;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                    ControladorTestCajaNegra.class, TipoProductoTestCajaNegra.class, StateExceptionTestCajaNegra.class,
                    PedidoTestCajaNegra.class, ObservacionTestCajaNegra.class, MaterialTestCajaNegra.class,
                    LoteTestCajaNegra.class, ListaPedidosTestCajaNegra.class, ListaMaterialesTestCajaNegra.class,
                    ListaMaterialesStockTestCajaNegra.class, ListaLotesTestCajaNegra.class,
                    ListaEmpleadosTestCajaNegra.class, FaltantesExceptionTestCajaNegra.class,
                    EvaluacionTestCajaNegra.class, EmpleadoTestCajaNegra.class, AceptadoTestCajaNegra.class
    })
public class SuiteCajaNegra {
}
