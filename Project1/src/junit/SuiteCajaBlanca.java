package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ VerificacionesTestCajaBlanca.class, TipoProductoTestCajaBlanca.class, PedidoTestCajaBlanca.class,
                      ListaMaterialesStockTestCajaBlanca.class, ListaLotesTestCajaBlanca.class,
                      EvaluacionTestCajaBlanca.class
  })
public class SuiteCajaBlanca
{
}
