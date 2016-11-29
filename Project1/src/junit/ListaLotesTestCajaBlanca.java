package junit;

import listas.ListaLotes;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ListaLotesTestCajaBlanca
{
  private ListaLotes lista;
  
  @Before
  public void setUp()
  {
  }
  
  @After
  public void tearDown()
  {
    this.lista = null;
  }
  
  @Test
  public void testGetInstance_Camino1()
  {
    this.lista = ListaLotes.getInstance();
    assertTrue("ListaLotes mal creada_1",lista != null);
  }
  
  @Test
  public void testGetInstance_Camino2()
  {
    this.lista = ListaLotes.getInstance();
    ListaLotes lista2 = ListaLotes.getInstance();
    assertTrue("ListaLotes mal creada_2",lista == lista2);
  }
}
