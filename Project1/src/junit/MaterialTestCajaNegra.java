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
  
  @Before
  public void setUp()
  {
    String descripcion = "esta es una descripcion de longitud 50 que debe aa";
    this.material = new Material("MAT10211",descripcion,300);
  }
  
  @Test
  public void setCodigoTest_1()
  {
    this.material.setCodigo("MAT10211");
    String cod = this.material.getCodigo();
    assertTrue("Esta mal seteado el codigo_1" ,cod.equals("MAT10211"));
  }
  
  @Test
  public void setCodigoTest_2()
  {
    this.material.setCodigo("MAT99999");
    String cod = this.material.getCodigo();
    assertTrue("Esta mal seteado el codigo_2", cod.equals("MAT99999"));
  }
  
  @Test
  public void setCodigoTest_3()
  {
    this.material.setCodigo("MAT00000");
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
    assertTrue("No deberia setear el codigo_4", cod.equals("MAT101"));
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
    assertTrue("No deberia setear el codigo_5", cod.equals("MAT0000"));
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
    assertTrue("No deberia setear el codigo_6", cod.equals("MAT1000000"));
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
    assertTrue("No deberia setear el codigo_7", cod.equals("MAT999999"));
  }
  
  
}
