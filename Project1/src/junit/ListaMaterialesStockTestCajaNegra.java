package junit;

import datos.TipoProducto;

import exceptions.FaltantesException;

import listas.ListaMaterialesStock;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class ListaMaterialesStockTestCajaNegra
{
  private ListaMaterialesStock stock;
  
  @Before
  public void setUp()
  {
    this.stock = ListaMaterialesStock.getInstance();
  }
  
  @After
  public void tearDown()
  {
    this.stock = null;
  }
  
  @Test
  public void testVerificarExistencias_1()
  {
    try
    {
      this.stock.verificarExistencias("TIP000001", 300);
      fail("No deberian haber alcanzado las existencias_1");
    }
    catch (FaltantesException e)
    {
    }
    catch (Exception e)
    {
    }
  }
  
  @Test
  public void testVerificarExistencias_2()
  {
    try
    {
      this.stock.verificarExistencias("TIP000001", 1);
    }
    catch (FaltantesException e)
    {
      fail("Deberian haber alcanzado las existencias_2");
    }
    catch (Exception e)
    {
    }
  }
  
  @Test
  public void testVerificarExistencias_3()
  {
    try
    {
      this.stock.verificarExistencias("TIP000001", 999);
      fail("No deberian haber alcanzado las existencias_3");
    }
    catch (FaltantesException e)
    {
    }
    catch (Exception e)
    {
    }
  }
  
  @Test
  public void testGetCodigo()
  {
    String tipo = this.stock.getCodigo("Flipper");
    assertTrue(tipo,tipo.equals("TIP000001"));
  }
  
  @Test
  public void testGetProducto()
  {
    String codigo = "TIP000001";
    TipoProducto tipo = this.stock.getProducto(codigo);
    assertTrue("No devolvio el producto correcto",this.stock.getRecetas().get(codigo) == tipo);
  }
  
  @Test
  public void testActualizarExistencias()
  {
    TipoProducto tip = this.stock.getProducto("TIP000001");
    try
    {
      double cantMat1 = tip.getListaMateriales().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad();
      double cantMat2 = tip.getListaMateriales().getMaterial(ListaMaterialesStock.COD_METAL).getCantidad();
      double cantMat3 = tip.getListaMateriales().getMaterial(ListaMaterialesStock.COD_VIDRIO).getCantidad();
      double cantMat4 = tip.getListaMateriales().getMaterial(ListaMaterialesStock.COD_PLASTICO).getCantidad();
      double cant1 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad() - cantMat1;
      double cant2 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_METAL).getCantidad() - cantMat2;
      double cant3 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_VIDRIO).getCantidad() - cantMat3;
      double cant4 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_PLASTICO).getCantidad() - cantMat4;
      this.stock.actualizarExistencias(tip);
      double cantAct1 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_MADERA).getCantidad();
      double cantAct2 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_METAL).getCantidad();
      double cantAct3 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_VIDRIO).getCantidad();
      double cantAct4 = this.stock.getListaExistencias().getMaterial(ListaMaterialesStock.COD_PLASTICO).getCantidad();
      assertTrue("Actualizacion erronea",cant1 == cantAct1);
      assertTrue("Actualizacion erronea",cant2 == cantAct2);
      assertTrue("Actualizacion erronea",cant3 == cantAct3);
      assertTrue("Actualizacion erronea",cant4 == cantAct4);
    }
    catch (Exception e)
    {
    }
  }
}
