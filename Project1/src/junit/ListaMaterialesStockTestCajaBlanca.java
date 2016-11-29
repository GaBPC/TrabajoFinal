package junit;

import datos.Material;
import datos.TipoProducto;

import exceptions.FaltantesException;

import listas.ListaLotes;

import listas.ListaMateriales;
import listas.ListaMaterialesStock;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ListaMaterialesStockTestCajaBlanca
{
  private FixtureListaMaterialesStockTestCajaBlanca fixture;
  
  public ListaMaterialesStockTestCajaBlanca()
  {
    super();
    this.fixture = new FixtureListaMaterialesStockTestCajaBlanca();
  }
  @Before
  public void setUp()
  {
  }
  
  @After
  public void tearDown()
  {
    this.fixture.tearDown();
  }
  
  @Test
  public void testGetInstance_Camino1()
  {
    ListaMaterialesStock lista = ListaMaterialesStock.getInstance();
    assertTrue("ListaLotes mal creada_1",lista != null);
  }
  
  @Test
  public void testGetInstance_Camino2()
  {
    ListaMaterialesStock lista = ListaMaterialesStock.getInstance();
    ListaMaterialesStock lista2 = ListaMaterialesStock.getInstance();
    assertTrue("ListaLotes mal creada_2",lista == lista2);
  }
  
  @Test
  public void testVerificarExistencias_Camino1()
  {
    this.fixture.setUp_3();
    try
    {
      this.fixture.lista.verificarExistencias("TIP000001", 100);
      fail("No deberian haber alcanzado las existencias");
    }
    catch (FaltantesException e)
    {
    }
    catch (Exception e)
    {
    }
  }
  
  @Test
  public void testVerificarExistencias_Camino2()
  {
    this.fixture.setUp_3();
    try
    {
      this.fixture.lista.verificarExistencias("TIP000001", 1);
    }
    catch (FaltantesException e)
    {
      fail("Deberian haber alcanzado las existencias");
    }
    catch (Exception e)
    {
    }
  }
  
  @Test
  public void testVerificarExistencias_Camino3()
  {
    this.fixture.setUp_4();
    try
    {
      ListaMateriales lista = this.fixture.lista.verificarExistencias("TIP000001", 200);
      assertFalse("ListaFinal no deberia tener ningun elemento",lista.getIterator().hasNext());
    }
    catch(FaltantesException e)
    {
    }
    catch(Exception e)
    {
    }
  }
  
  @Test
  public void testActualizarExistencias_Camino1()
  {
    this.fixture.setUp_3();
    TipoProducto tip = this.fixture.lista.getProducto("TIP000001");
    try
    {
      double cantMat1 = tip.getListaMateriales().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad();
      double cant1 = this.fixture.lista.getListaExistencias().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad() - cantMat1;
      this.fixture.lista.actualizarExistencias(tip);
      double cantAct1 = this.fixture.lista.getListaExistencias().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad();
      assertTrue("Actualizacion erronea",cant1 == cantAct1);
    }
    catch (Exception e)
    {
    }
  }
  
  @Test
  public void testActualizarExistencias_Camino2()
  {
    this.fixture.setUp_4();
    TipoProducto tip = this.fixture.lista.getProducto("TIP000001");
    try
    {
      double cant1 = this.fixture.lista.getListaExistencias().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad();
      this.fixture.lista.actualizarExistencias(tip);
      double cantAct1 = this.fixture.lista.getListaExistencias().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad();
      assertTrue("No deberia haber actualizado",cant1 == cantAct1);
    }
    catch (Exception e)
    {
    }
  }
  
}
