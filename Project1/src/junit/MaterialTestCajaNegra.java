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

public class MaterialTestCajaNegra
{
  private Material material;
  
  public MaterialTestCajaNegra()
  {
    super();
  }
  
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
  public void testConstructor_1()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,300);
    }
    catch(AssertionError e)
    {
      fail("Se esperaba que la instancia se creara correctamente_1");
    }
  }
  
  @Test
  public void testConstructor_2()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT99999",descripcion,300);
    }
    catch(AssertionError e)
    {
      fail("Se esperaba que la instancia se creara correctamente_2");
    }
  }
  
  @Test
  public void testConstructor_3()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT00000",descripcion,300);
    }
    catch(AssertionError e)
    {
      fail("Se esperaba que la instancia se creara correctamente_3");
    }
  }
  
  @Test
  public void testConstructor_4()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor..";
    this.material = new Material("MAT10211",descripcion,300);
    }
    catch(AssertionError e)
    {
      fail("Se esperaba que la instancia se creara correctamente_4");
    }
  }
  
  @Test
  public void testConstructor_5()
  {
    try
    {
    String descripcion = "1";
    this.material = new Material("MAT10211",descripcion,300);
    }
    catch(AssertionError e)
    {
      fail("Se esperaba que la instancia se creara correctamente_5");
    }
  }
  
  @Test
  public void testConstructor_6()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,999.9999);
    }
    catch(AssertionError e)
    {
      fail("Se esperaba que la instancia se creara correctamente_6");
    }
  }
  
  @Test
  public void testConstructor_7()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,000.0001);
    }
    catch(AssertionError e)
    {
      fail("Se esperaba que la instancia se creara correctamente_7");
    }
  }
  
  @Test
  public void testConstructor_8()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT101",descripcion,999.9999);
    fail("MAT101: valor no valido");
    }
    catch(AssertionError e)
    {
      
    }
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT0000",descripcion,999.9999);
    fail("MAT0000: valor no valido");
    }
    catch(AssertionError e)
    {
      
    }
  }
  
  @Test
  public void testConstructor_9()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT0000",descripcion,999.9999);
    fail("MAT0000: valor no valido");
    }
    catch(AssertionError e)
    {
      
    }
  }
  
  @Test
  public void testConstructor_10()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT1000000",descripcion,999.9999);
    fail("MAT1000000: valor no valido");
    }
    catch(AssertionError e)
    {
      
    }
  }
  
  @Test
  public void testConstructor_11()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT999999",descripcion,999.9999);
    fail("MAT999999: valor no valido");
    }
    catch(AssertionError e)
    {
      
    }
  }
  
  @Test
  public void testConstructor_12()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor ahora es d 200 porque hay que corroborar que siga siendo robusto para cualquier caso que se presente.";
    this.material = new Material("MAT10211",descripcion,300);
    fail("Descripcion mas larga de la debida_12");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testConstructor_13()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor...";
    this.material = new Material("MAT10211",descripcion,300);
    fail("Descripcion mas larga de la debida_13");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testConstructor_14()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,000.0000);
      fail("La cantidad es invalida_14");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testConstructor_15()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,1001.0000);
      fail("La cantidad es invalida_15");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testConstructor_16()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,1000.0000);
      fail("La cantidad es invalida_16");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test 
  public void testSetCodigo_1()
  {
    try
    {
      this.material.setCodigo("MAT10211");
    }
    catch(AssertionError e)
    {
      fail("No deberia tener ningun error");
    }
  }
  
  @Test 
  public void testSetCodigo_2()
  {
    try
    {
      this.material.setCodigo("MAT99999");
    }
    catch(AssertionError e)
    {
      fail("No deberia tener ningun error_2");
    }
  }
  
  @Test 
  public void testSetCodigo_3()
  {
    try
    {
      this.material.setCodigo("MAT00000");
    }
    catch(AssertionError e)
    {
      fail("No deberia tener ningun error_3");
    }
  }
  
  @Test 
  public void testSetCodigo_4()
  {
    try
    {
      this.material.setCodigo("MAT101");
      fail("Deberia saltar aserto_4");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test 
  public void testSetCodigo_5()
  {
    try
    {
      this.material.setCodigo("MAT0000");
      fail("Deberia saltar aserto_5");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test 
  public void testSetCodigo_6()
  {
    try
    {
      this.material.setCodigo("MAT1000000");
      fail("Deberia saltar aserto_6");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test 
  public void testSetCodigo_7()
  {
    try
    {
      this.material.setCodigo("MAT999999");
      fail("Deberia saltar aserto_7");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testSetDescripcion_1()
  {
    try
    {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
      fail("No deberia fallar");
    }
  }
  
  @Test
  public void testSetDescripcion_2()
  {
    try
    {
      String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor..";
    this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
      fail("No deberia fallar_2");
    }
  }
  
  @Test
  public void testSetDescripcion_3()
  {
    try
    {
      String descripcion = ".";
    this.material.setDescripcion(descripcion);
    }
    catch(AssertionError e)
    {
      fail("No deberia fallar_3");
    }
  }
  
  @Test
  public void testSetDescripcion_4()
  {
    try
    {
      String descripcion = "";
    this.material.setDescripcion(descripcion);
      fail("Descripcion invalida_4, deberia fallar");
    }
    catch(AssertionError e)
    {
      
    }
  }
  
  @Test
  public void testSetDescripcion_5()
  {
    try
    {
      String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor ahora es d 200 porque hay que corroborar que siga siendo robusto para cualquier caso que se presente.";
    this.material.setDescripcion(descripcion);
      fail("Descripcion invalida_5, deberia fallar");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testSetDescripcion_6()
  {
    try
    {
      String descripcion = "esta es una descripcion de longitud 100 caracteres para comprobar que funciona bien el constructor...";
    this.material.setDescripcion(descripcion);
      fail("Descripcion invalida_6, deberia fallar");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testSetCantidad_1()
  {
    try
    {
      this.material.setCantidad(300);
    }
    catch(AssertionError e)
    {
      fail("No deberia fallar_1");
    }
  }
  
  
  @Test
  public void testSetCantidad_2()
  {
    try
    {
      this.material.setCantidad(999.9999);
    }
    catch(AssertionError e)
    {
      fail("No deberia fallar_2");
    }
  }
  
  @Test
  public void testSetCantidad_3()
  {
    try
    {
      this.material.setCantidad(000.0001);
    }
    catch(AssertionError e)
    {
      fail("No deberia fallar_3");
    }
  }
  
  @Test
  public void testSetCantidad_4()
  {
    try
    {
      this.material.setCantidad(000.0000);
      fail("Deberia fallar_4, cantidad invalida");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testSetCantidad_5()
  {
    try
    {
      this.material.setCantidad(1001.0000);
      fail("Deberia fallar_5, cantidad invalida");
    }
    catch(AssertionError e)
    {
    }
  }
  
  @Test
  public void testSetCantidad_6()
  {
    try
    {
      this.material.setCantidad(1000.0000);
      fail("Deberia fallar_6, cantidad invalida");
    }
    catch(AssertionError e)
    {
    }
  }
  
}
