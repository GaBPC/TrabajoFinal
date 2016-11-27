package junit;

import exceptions.FaltantesException;

import listas.ListaMateriales;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class FaltantesExceptionTestCajaNegra
{
  private FaltantesException exception;
  
  @Before
  public void setUp()
  {
  }
  
  @After
  public void tearDown()
  {
    this.exception = null;
  }
  
  @Test
  public void testConstructor()
  {
    ListaMateriales faltantes = new ListaMateriales();
    String mensaje = "es una prueba";
    this.exception = new FaltantesException(mensaje,faltantes);
    assertTrue("FaltatesException mal creada",this.exception.getMessage().equals(mensaje));
    assertTrue("FaltatesException mal creada",this.exception.getFaltantes() == faltantes);
  }
}
