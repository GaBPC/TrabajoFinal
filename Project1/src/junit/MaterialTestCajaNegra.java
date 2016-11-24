package junit;

import datos.Material;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import visual.Controlador;

public class MaterialTestCajaNegra
{
  
  private Material material;
  
  public MaterialTestCajaNegra()
  {
    super();
  }
  
  /*No se testea el metodo Constructor debido a que no es posible realizarle ninguna comprobación sin utilizar asertos,
   * los materiales se crean de manera manual, por lo que (al ser externos al sistema) no hay forma de saber si están 
   * bien creados*/
  
  @Before
  public void setUp()
  {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,300);
  }
  @After
  public void tearDown()
  {
    this.material = null;
  }

  @Test
  public void setCodigoTest_1()
  {
    try
    {
      this.material.setCodigo("MAT10211");
    }
    catch(AssertionError e)
    {
    }
    String cod = this.material.getCodigo();
    assertTrue("Esta mal seteado el codigo_1" ,cod.equals("MAT10211"));
  }
  
  @Test
  public void setCodigoTest_2()
  {
    try
    {
      this.material.setCodigo("MAT99999");
    }
    catch(AssertionError e)
    {
    }
    String cod = this.material.getCodigo();
    assertTrue("Esta mal seteado el codigo_2", cod.equals("MAT99999"));
  }
  
  @Test
  public void setCodigoTest_3()
  {
    try
    {
      this.material.setCodigo("MAT00000");
    }
    catch(AssertionError e)
    {
    }
    String cod = this.material.getCodigo();
    assertTrue("Esta mal seteado el codigo_3", cod.equals("MAT00000"));
  }
  
  @Test
  public void setCodigoTest_4()
  {
    try
    {
      this.material.setCodigo("MAT101");
    }
    catch(AssertionError e)
    {
    }
    String cod = this.material.getCodigo();
    assertTrue("No deberia setear el codigo_4", !cod.equals("MAT101"));
  }
  
  @Test 
  public void setCodigoTest_5()
  {
    try
    {
      this.material.setCodigo("MAT0000");
    }
    catch(AssertionError e)
    {
    }
    String cod = this.material.getCodigo();
    assertTrue("No deberia setear el codigo_5", !cod.equals("MAT0000"));
  }
  
  @Test 
  public void setCodigoTest_6()
  {
    try
    {
      this.material.setCodigo("MAT1000000");
    }
    catch(AssertionError e)
    {
    }
    String cod = this.material.getCodigo();
    assertTrue("No deberia setear el codigo_6", !cod.equals("MAT1000000"));
  }
  
  @Test 
  public void setCodigoTest_7()
  {
    try
    {
      this.material.setCodigo("MAT999999");
    }
    catch(AssertionError e)
    {
    }
    String cod = this.material.getCodigo();
    assertTrue("No deberia setear el codigo_7", !cod.equals("MAT999999"));
  }
  
  @Test
  public void setDescripcionTest_1()
  {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    try
    {
      this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
    }
    String desc2 = this.material.getDescripcion();
    assertTrue("Esta mal seteada la descripcion_1", desc2.equals(descripcion));      
  }
  
  @Test
  public void setDescripcionTest_2()
  {
    String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor..";
    try
    {
      this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
    }
    String desc2 = this.material.getDescripcion();
    assertTrue("Esta mal seteada la descripcion_2", desc2.equals(descripcion));      
  }
  
  
  @Test
  public void setDescripcionTest_3()
  {
    String descripcion = ".";
    try
    {
      this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
    }
    String desc2 = this.material.getDescripcion();
    assertTrue("Esta mal seteada la descripcion_3", desc2.equals(descripcion));      
  }
  
  @Test
  public void setDescripcionTest_4()
  {
    String descripcion = "";
    try
    {
      this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
    }
    String desc2 = this.material.getDescripcion();
    assertTrue("No deberia setear la descripcion_4", !desc2.equals(descripcion));      
  }
  
  @Test
  public void setDescripcionTest_5()
  {
    String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor ahora es d 200 porque hay que corroborar que siga siendo robusto para cualquier caso que se presente.";
    try
    {
      this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
    }
    String desc2 = this.material.getDescripcion();
    assertTrue("No deberia setear la descripcion_5", !desc2.equals(descripcion));      
  }
  
  @Test
  public void setDescripcionTest_6()
  {
    String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor...";
    try
    {
      this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
    }
    String desc2 = this.material.getDescripcion();
    assertTrue("No deberia setear la descripcion_6", !desc2.equals(descripcion));      
  }
  
  @Test 
  public void setCantidadTest_1()
  {
    try
    {
      this.material.setCantidad(300);
    }
    catch(AssertionError e)
    {
    }
    double cant = this.material.getCantidad();
    assertTrue("Esta mal seteada la cantidad_1",cant != 300);
  }
  
  @Test 
  public void setCantidadTest_2()
  {
    try
    {
      this.material.setCantidad(999.9999);
    }
    catch(AssertionError e)
    {
    }
    double cant = this.material.getCantidad();
    assertTrue("Esta mal seteada la cantidad_2",cant != 999.9999);
  }
  
  @Test 
  public void setCantidadTest_3()
  {
    try
    {
      this.material.setCantidad(000.0001);
    }
    catch(AssertionError e)
    {
    }
    double cant = this.material.getCantidad();
    assertTrue("Esta mal seteada la cantidad_3",cant != 000.0001);
  }
  
  @Test 
  public void setCantidadTest_4()
  {
    try
    {
      this.material.setCantidad(000.0000);
    }
    catch(AssertionError e)
    {
    }
    double cant = this.material.getCantidad();
    assertTrue("No deberia setear la cantidad_4",cant == 000.0000);
  }
  
  @Test 
  public void setCantidadTest_5()
  {
    try
    {
      this.material.setCantidad(1001.0000);
    }
    catch(AssertionError e)
    {
    }
    double cant = this.material.getCantidad();
    assertTrue("No deberia setear la cantidad_5",cant == 1001.0000);
  }
  
  @Test 
  public void setCantidadTest_6()
  {
    try
    {
      this.material.setCantidad(1000.0000);
    }
    catch(AssertionError e)
    {
    }
    double cant = this.material.getCantidad();
    assertTrue("No deberia setear la cantidad_6",cant == 1000.0000);
  }
  
  
}
