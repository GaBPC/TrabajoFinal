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
  
  private Material mat;
  
  @Before 
  public void setUp()
  {
  }
  
  private void setUp_2()
  {
    String descripcion = "esta es una descripcion de longitud 50 para probar";
    this.mat = new Material("MAT10211",descripcion, 300);
  }
  
  @After
  public void tearDown()
  {
    this.mat = null;
  }
  
  @Test
  public void testConstructor_1()
  {
    String descripcion = "esta es una descripcion de longitud 50 para probar";
    this.mat = new Material("MAT10211",descripcion, 300);
    assertTrue("Material mal creado_1",mat.getCodigo().equals("MAT10211"));
    assertTrue("Material mal creado_1",mat.getDescripcion().equals(descripcion));
    assertTrue("Material mal creado_1",mat.getCantidad() == 300);
  }
  
  @Test 
  public void testConstructor_2()
  {
    String descripcion = "esta es una descripcion de longitud 50 para probar";
    this.mat = new Material("MAT99999",descripcion, 300);
    assertTrue("Material mal creado_2",mat.getCodigo().equals("MAT99999"));
    assertTrue("Material mal creado_2",mat.getDescripcion().equals(descripcion));
    assertTrue("Material mal creado_2",mat.getCantidad() == 300);
  }
  
  @Test
  public void testConstructor_3()
  {
    String descripcion = "esta es una descripcion de longitud 50 para probar";
    this.mat = new Material("MAT00000",descripcion, 300);
    assertTrue("Material mal creado_3",mat.getCodigo().equals("MAT00000"));
    assertTrue("Material mal creado_3",mat.getDescripcion().equals(descripcion));
    assertTrue("Material mal creado_3",mat.getCantidad() == 300);
  }
  
  @Test
  public void testConstructor_4()
  {
    String descripcion = "esta es una descripcion de longitud 100 para probar que funcione bien en casos excepcionales........";
    this.mat = new Material("MAT10211",descripcion, 300);
    assertTrue("Material mal creado_4",mat.getCodigo().equals("MAT10211"));
    assertTrue("Material mal creado_4",mat.getDescripcion().equals(descripcion));
    assertTrue("Material mal creado_4",mat.getCantidad() == 300);
  }
  
  @Test
  public void testConstructor_5()
  {
    String descripcion = ".";
    this.mat = new Material("MAT10211",descripcion, 300);
    assertTrue("Material mal creado_5",mat.getCodigo().equals("MAT10211"));
    assertTrue("Material mal creado_5",mat.getDescripcion().equals(descripcion));
    assertTrue("Material mal creado_5",mat.getCantidad() == 300);
  }
  
  @Test
  public void testConstructor_6()
  {
    String descripcion = "esta es una descripcion de longitud 50 para probar";
    this.mat = new Material("MAT10211",descripcion, 999.9999);
    assertTrue("Material mal creado_6",mat.getCodigo().equals("MAT10211"));
    assertTrue("Material mal creado_6",mat.getDescripcion().equals(descripcion));
    assertTrue("Material mal creado_6",mat.getCantidad() == 999.9999);
  }
  
  @Test
  public void testConstructor_7()
  {
    String descripcion = "esta es una descripcion de longitud 50 para probar";
    this.mat = new Material("MAT10211",descripcion, 000.0001);
    assertTrue("Material mal creado_7",mat.getCodigo().equals("MAT10211"));
    assertTrue("Material mal creado_7",mat.getDescripcion().equals(descripcion));
    assertTrue("Material mal creado_7",mat.getCantidad() == 000.0001);
  }
  
  @Test
  public void testSetDescripcion_1()
  {
    this.setUp_2();
    String descripcion = "esta es una descripcion de longitud 50 para probar que funci";
    this.mat.setDescripcion(descripcion);
    assertTrue("Mal seteada la descripcion_1",mat.getDescripcion().equals(descripcion));
  }
  
  @Test
  public void testSetDescripcion_2()
  {
    this.setUp_2();
    String descripcion = "esta es una descripcion de longitud 100 para probar que funcione bien en casos excepcionales........";
    this.mat.setDescripcion(descripcion);
    assertTrue("Mal seteada la descripcion_2",mat.getDescripcion().equals(descripcion));
  }
  
  @Test
  public void testSetDescripcion_3()
  {
    this.setUp_2();
    String descripcion = ".";
    this.mat.setDescripcion(descripcion);
    assertTrue("Mal seteada la descripcion_3",mat.getDescripcion().equals(descripcion));
  }
  
  @Test
  public void testSetCantidad_1()
  {
    this.setUp_2();
    this.mat.setCantidad(400);
    assertTrue("Mal seteada la cantidad_1",mat.getCantidad() == 400);
  }
  
  @Test
  public void testSetCantidad_2()
  {
    this.setUp_2();
    this.mat.setCantidad(999.9999);
    assertTrue("Mal seteada la cantidad_2",mat.getCantidad() == 999.9999);
  }
  
  @Test
  public void testSetCantidad_3()
  {
    this.setUp_2();
    this.mat.setCantidad(000.0001);
    assertTrue("Mal seteada la cantidad_2",mat.getCantidad() == 000.0001);
  }
  
  
}
