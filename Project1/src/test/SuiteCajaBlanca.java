package test;

import junit.ControladorTestCajaBlanca;
import junit.EvaluacionTestCajaBlanca;
import junit.ListaEmpleadoTestCajaBlanca;
import junit.ListaLotesTestCajaBlanca;
import junit.ListaMaterialesStockTestCajaBlanca;
import junit.ListaMaterialesTestCajaBlanca;
import junit.ListaPedidosTestCajaBlanca;
import junit.ObservacionTestCajaBlanca;
import junit.PedidoTestCajaBlanca;
import junit.TipoProductoTestCajaBlanca;
import junit.VerificacionesTestCajaBlanca;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                    VerificacionesTestCajaBlanca.class, TipoProductoTestCajaBlanca.class, PedidoTestCajaBlanca.class,
                    ObservacionTestCajaBlanca.class, ListaPedidosTestCajaBlanca.class,
                    ListaMaterialesTestCajaBlanca.class, ListaMaterialesStockTestCajaBlanca.class,
                    ListaLotesTestCajaBlanca.class, ListaEmpleadoTestCajaBlanca.class, EvaluacionTestCajaBlanca.class,
                    ControladorTestCajaBlanca.class
    })
public class SuiteCajaBlanca {
}
