package junit;

import datos.TipoProducto;

import listas.ListaMateriales;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class TipoProductoTestCajaBlanca
{
  private TipoProducto tp;
  
  @Before
  public void setUp()
  {
    ListaMateriales lista = new ListaMateriales();
    String descripcion = "descripcion de producto";
    this.tp = new TipoProducto(lista, descripcion);
  }
  
  @After
  public void tearDown()
  {
    this.tp = null;
  }
  
  @Test
  public void testGenerarTipoProducto_Camino1()
  {
    TipoProducto.setNumeroProd(11111);
    this.tp.generarTipoProd();
    String codigo = this.tp.getCodigoProducto();
    assertTrue("Generacion erronea de codigo",codigo.equals("TIP011111"));
  }
  
  @Test
  public void testGenerarTipoProducto_Camino2()
  {
    TipoProducto.setNumeroProd(111111);
    this.tp.generarTipoProd();
    String codigo = this.tp.getCodigoProducto();
    assertTrue("Generacion erronea de codigo",codigo.equals("TIP111111"));
  }
}
