package junit;

import datos.TipoProducto;

import listas.ListaMateriales;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class TipoProductoTestCajaNegra
{
  private TipoProducto tp;

  @Before
  public void setUp()
  {
  }

  @After
  public void tearDown()
  {
    this.tp = null;
  }

  @Test
  public void testConstructor()
  {
    ListaMateriales lista = new ListaMateriales();
    String descripcion = "descripcion de producto";
    this.tp = new TipoProducto(lista, descripcion);
    assertTrue("TipoProducto mal creado", this.tp.getListaMateriales() == lista);
    assertTrue("TipoProducto mal creado", this.tp.getDescripcion() == descripcion);

  }
}
