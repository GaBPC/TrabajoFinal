package junit;

import datos.Pedido;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class PedidoTestCajaBlanca
{
  private Pedido ped;
  
  @Before
  public void setUp()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
  }
  
  private String setUp_Detalles()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    String ret = "";
    
    ret += "Numero de pedido: " + this.ped.getNumeroPedido();
    ret += "\nFecha de pedido: " + sdf.format(this.ped.getFechaPedido().getTime());
    ret += "\nTipo de maquina: " + this.ped.getCodigoMaquina();
    ret += "\nCantidad a producir: " + this.ped.getCantProduccion();
    ret += "\nFecha de entrega solicitada por ventas: " + sdf.format(this.ped.getFechaEntregaVentas().getTime());
    
    return ret;
  }
  
  @After
  public void tearDown()
  {
    this.ped = null;
  }
  @Test
  public void testVerificaNull_Camino1()
  {
    boolean ret = this.ped.verificaNull();
    assertFalse("VerificaNull erroneo_1",ret);
  }
  
  @Test
  public void testVerificaNull_Camino2()
  {
    Calendar fechaPedidoAceptado = GregorianCalendar.getInstance();
    this.ped.setFechaPedidoAceptado(fechaPedidoAceptado);
    boolean ret = this.ped.verificaNull();
    assertFalse("VerificaNull erroneo_2",ret);
  }
  
  @Test
  public void testVerificaNull_Camino3()
  {
    Calendar fechaPedidoAceptado = GregorianCalendar.getInstance();
    Calendar fechaPropuestaProduccion = GregorianCalendar.getInstance();
    this.ped.setFechaPedidoAceptado(fechaPedidoAceptado);
    this.ped.setFechaPropuestaProduccion(fechaPropuestaProduccion);
    boolean ret = this.ped.verificaNull();
    assertFalse("VerificaNull erroneo_3",ret);
  }
  
  @Test
  public void testVerificaNull_Camino4()
  {
    Calendar fechaPedidoAceptado = GregorianCalendar.getInstance();
    Calendar fechaPropuestaProduccion = GregorianCalendar.getInstance();
    Calendar fechaDefinitiva = GregorianCalendar.getInstance();
    this.ped.setFechaPedidoAceptado(fechaPedidoAceptado);
    this.ped.setFechaPropuestaProduccion(fechaPropuestaProduccion);
    this.ped.setFechaDefinitiva(fechaDefinitiva);
    boolean ret = this.ped.verificaNull();
    assertTrue("VerificaNull erroneo_4",ret);
  }
  
  @Test 
  public void testDetalles_Camino1()
  {
    String ret = this.setUp_Detalles();
    ret +="\nFecha propuesta por produccion: " + " - ";
    ret += "\nFecha definitiva: " + " - ";
    ret +="\nFecha de pedido aceptado: " + " - ";
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_1",ret.equals(aux));
  }
  
  @Test 
  public void testDetalles_Camino2()
  {
    String ret = this.setUp_Detalles();
    Calendar fechaPropuestaProduccion = GregorianCalendar.getInstance();
    Calendar fechaPedidoAceptado = GregorianCalendar.getInstance();
    Calendar fechaDefinitiva = GregorianCalendar.getInstance();
    this.ped.setFechaPropuestaProduccion(fechaPropuestaProduccion);
    this.ped.setFechaPedidoAceptado(fechaPedidoAceptado);
    this.ped.setFechaDefinitiva(fechaDefinitiva);
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    ret +="\nFecha propuesta por produccion: " + sdf.format(this.ped.getFechaPropuestaProduccion().getTime());
    ret += "\nFecha definitiva: " + sdf.format(this.ped.getFechaDefinitiva().getTime());
    ret +="\nFecha de pedido aceptado: " + sdf.format(this.ped.getFechaPedidoAceptado().getTime());
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_2",ret.equals(aux));
  }
  
  @Test 
  public void testDetalles_Camino3()
  {
    String ret = this.setUp_Detalles();
    Calendar fechaPedidoAceptado = GregorianCalendar.getInstance();
    Calendar fechaDefinitiva = GregorianCalendar.getInstance();
    this.ped.setFechaPedidoAceptado(fechaPedidoAceptado);
    this.ped.setFechaDefinitiva(fechaDefinitiva);
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    ret +="\nFecha propuesta por produccion: " + " - ";
    ret += "\nFecha definitiva: " + sdf.format(this.ped.getFechaDefinitiva().getTime());
    ret +="\nFecha de pedido aceptado: " + sdf.format(this.ped.getFechaPedidoAceptado().getTime());
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_3",ret.equals(aux));
  }
  
  @Test 
  public void testDetalles_Camino4()
  {
    String ret = this.setUp_Detalles();
    Calendar fechaPedidoAceptado = GregorianCalendar.getInstance();
    this.ped.setFechaPedidoAceptado(fechaPedidoAceptado);
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    ret +="\nFecha propuesta por produccion: " + " - ";
    ret += "\nFecha definitiva: " + " - ";
    ret +="\nFecha de pedido aceptado: " + sdf.format(this.ped.getFechaPedidoAceptado().getTime());
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_4",ret.equals(aux));
  }
  
  @Test 
  public void testDetalles_Camino5()
  {
    String ret = this.setUp_Detalles();
    Calendar fechaPropuestaProduccion = GregorianCalendar.getInstance();
    Calendar fechaPedidoAceptado = GregorianCalendar.getInstance();
    this.ped.setFechaPropuestaProduccion(fechaPropuestaProduccion);
    this.ped.setFechaPedidoAceptado(fechaPedidoAceptado);
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    ret +="\nFecha propuesta por produccion: " + sdf.format(this.ped.getFechaPropuestaProduccion().getTime());
    ret += "\nFecha definitiva: " + " - ";
    ret +="\nFecha de pedido aceptado: " + sdf.format(this.ped.getFechaPedidoAceptado().getTime());
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_5",ret.equals(aux));
  }
  
  @Test 
  public void testDetalles_Camino6()
  {
    String ret = this.setUp_Detalles();
    Calendar fechaPropuestaProduccion = GregorianCalendar.getInstance();
    Calendar fechaDefinitiva = GregorianCalendar.getInstance();
    this.ped.setFechaPropuestaProduccion(fechaPropuestaProduccion);
    this.ped.setFechaDefinitiva(fechaDefinitiva);
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    ret +="\nFecha propuesta por produccion: " + sdf.format(this.ped.getFechaPropuestaProduccion().getTime());
    ret += "\nFecha definitiva: " + sdf.format(this.ped.getFechaDefinitiva().getTime());
    ret +="\nFecha de pedido aceptado: " + " - ";
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_6",ret.equals(aux));
  }
  
  @Test 
  public void testDetalles_Camino7()
  {
    String ret = this.setUp_Detalles();
    Calendar fechaPropuestaProduccion = GregorianCalendar.getInstance();
    this.ped.setFechaPropuestaProduccion(fechaPropuestaProduccion);
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    ret +="\nFecha propuesta por produccion: " + sdf.format(this.ped.getFechaPropuestaProduccion().getTime());
    ret += "\nFecha definitiva: " + " - ";
    ret +="\nFecha de pedido aceptado: " + " - ";
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_7",ret.equals(aux));
  }
  
  @Test 
  public void testDetalles_Camino8()
  {
    String ret = this.setUp_Detalles();
    Calendar fechaDefinitiva = GregorianCalendar.getInstance();
    this.ped.setFechaDefinitiva(fechaDefinitiva);
  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    ret +="\nFecha propuesta por produccion: " + " - ";
    ret += "\nFecha definitiva: " + sdf.format(this.ped.getFechaDefinitiva().getTime());
    ret +="\nFecha de pedido aceptado: " + " - ";
    String aux = this.ped.detalles();
    assertTrue("Detalles mal generado_8",ret.equals(aux));
  }
}
