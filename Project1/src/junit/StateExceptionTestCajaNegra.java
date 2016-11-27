package junit;

import exceptions.StateException;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class StateExceptionTestCajaNegra
{
  private StateException exception;
  
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
    String mensaje = "es una prueba";
    this.exception = new StateException(mensaje);
    assertTrue("StateException mal creada",this.exception.getMessage().equals(mensaje));
  }
}
