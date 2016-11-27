package junit;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import personal.Empleado;

public class EmpleadoTestCajaNegra
{
  private Empleado empleado;
  
  @Before
  public void setUp()
  {
  }
  
  @After
  public void tearDown()
  {
    this.empleado = null;
  }
  
  @Test
  public void testConstructor_1()
  {
    this.empleado = new Empleado("LEG213431","Rodrigo Cassanelli","Ventas");
    assertTrue("Empleado mal creado_1",this.empleado.getLegajo().equals("LEG213431"));
    assertTrue("Empleado mal creado_1",this.empleado.getNya().equals("Rodrigo Cassanelli"));
    assertTrue("Empleado mal creado_1",this.empleado.getSector().equals("Ventas"));
  }
  
  @Test
  public void testConstructor_2()
  {
    this.empleado = new Empleado("LEG999999","Rodrigo Cassanelli","Ventas");
    assertTrue("Empleado mal creado_2",this.empleado.getLegajo().equals("LEG999999"));
    assertTrue("Empleado mal creado_2",this.empleado.getNya().equals("Rodrigo Cassanelli"));
    assertTrue("Empleado mal creado_2",this.empleado.getSector().equals("Ventas"));
  }
  
  @Test
  public void testConstructor_3()
  {
    this.empleado = new Empleado("LEG000000","Rodrigo Cassanelli","Ventas");
    assertTrue("Empleado mal creado_3",this.empleado.getLegajo().equals("LEG000000"));
    assertTrue("Empleado mal creado_3",this.empleado.getNya().equals("Rodrigo Cassanelli"));
    assertTrue("Empleado mal creado_3",this.empleado.getSector().equals("Ventas"));
  }
  
  @Test
  public void testConstructor_4()
  {
    this.empleado = new Empleado("LEG213431",".","Ventas");
    assertTrue("Empleado mal creado_4",this.empleado.getLegajo().equals("LEG213431"));
    assertTrue("Empleado mal creado_4",this.empleado.getNya().equals("."));
    assertTrue("Empleado mal creado_4",this.empleado.getSector().equals("Ventas"));
  }
  
  @Test
  public void testConstructor_5()
  {
    String descripcion = "esta es una descripcion de longitud 100 para probar que funcione bien en casos excepcionales........";
    this.empleado = new Empleado("LEG213431",descripcion,"Ventas");
    assertTrue("Empleado mal creado_5",this.empleado.getLegajo().equals("LEG213431"));
    assertTrue("Empleado mal creado_5",this.empleado.getNya().equals(descripcion));
    assertTrue("Empleado mal creado_5",this.empleado.getSector().equals("Ventas"));
  }
  
}
