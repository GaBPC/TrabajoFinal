package junit;

import datos.Lote;
import datos.Pedido;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class LoteTestCajaNegra {

    private Pedido pedido = null;

    public LoteTestCajaNegra() {
        super();
    }

    @Before
    public void setUp() {
        this.pedido =
            new Pedido("PED123456", GregorianCalendar.getInstance(), GregorianCalendar.getInstance(), "TIP123456",
                       "Flipper", 5);
    }
    
    @After
    public void tearDown()
    {
      this.pedido = null;
    }

    @Test
    public void testConstructor_1() {
        Lote lote = new Lote(this.pedido,"LOT123456");
        assertTrue("Lote mal creado, el pedido no es el correcto", lote.getPedido() == this.pedido);
        assertTrue("Lote mal creado, el codigo no es el correcto", lote.getNumeroLote().equals("LOT123456"));
    }
    
    @Test
    public void testConstructor_2() {
        Lote lote = new Lote(this.pedido,"LOT000000");
        assertTrue("Lote mal creado, el pedido no es el correcto", lote.getPedido() == this.pedido);
        assertTrue("Lote mal creado, el codigo no es el correcto", lote.getNumeroLote().equals("LOT000000"));
    }
    
    @Test
    public void testConstructor_3() {
        Lote lote = new Lote(this.pedido,"LOT999999");
        assertTrue("Lote mal creado, el pedido no es el correcto", lote.getPedido() == this.pedido);
        assertTrue("Lote mal creado, el codigo no es el correcto", lote.getNumeroLote().equals("LOT999999"));
    }
}
