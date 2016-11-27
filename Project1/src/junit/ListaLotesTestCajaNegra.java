package junit;

import datos.Lote;

import datos.Pedido;

import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.Iterator;

import listas.ListaLotes;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class ListaLotesTestCajaNegra
{
  private ListaLotes lista;
  private Lote lote;
  
  @Before
  public void setUp()
  {
    this.lista = ListaLotes.getInstance();
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    Pedido ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
    this.lote = new Lote(ped,"LOT000000");
  }
  
  @After
  public void tearDown()
  {
    this.lista = null;
  }
  
  @Test
  public void testAgregarNuevo()
  {
    this.lista.agregarNuevo(this.lote);
    assertTrue("No agrego correctamente el lote",this.verifica());
  }
  
  @Test
  public void testBorrarLote()
  {
    this.lista.borrarLote(this.lote);
    assertFalse("No borro correctamente el lote",this.verifica());
  }
  
  private boolean verifica()
  {
    boolean ret = false;
    Iterator<Lote> it = this.lista.getIterator();
    Lote lot = null;
    while(it.hasNext())
    {
      lot = it.next();
      if(lot == this.lote)
        ret = true;
    }
    return ret;
  }
}
