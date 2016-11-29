package junit;

import datos.Observacion;
import datos.Pedido;

import datos.estadosPedido.Evaluacion;

import exceptions.StateException;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class EvaluacionTestCajaNegra
{
  private Evaluacion ev;
  private Pedido ped;
  
  @Before
  public void setUp()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
  }
  
  private void setUp_2()
  {
    this.ev = new Evaluacion(this.ped);
  }
  
  @After
  public void tearDown()
  {
    this.ev = null;
    this.ped = null;
  }
  
  @Test
  public void testConstructor()
  {
    this.ev = new Evaluacion(this.ped);
    assertTrue("Evaluacion mal creada",this.ev.getPedido() == this.ped);
  }
  
  @Test
  public void testAgregarObservacion()
  {
    this.setUp_2();
    Calendar fechaObservacion = GregorianCalendar.getInstance();
    Observacion obs = new Observacion("prueba", fechaObservacion, "LEG000000", "es una observacion");
    try
    {
      this.ev.agregarObservacion(obs);
    }
    catch (StateException e)
    {
      fail("No se agrego la observacion correctamente");
    }
  }
  
  @Test
  public void testAceptarPedido_1()
  {
    this.setUp_2();
    Calendar fecha = GregorianCalendar.getInstance();
    this.ev.getPedido().setFechaDefinitiva(fecha);
    this.ev.getPedido().setFechaPedidoAceptado(fecha);
    this.ev.getPedido().setFechaPropuestaProduccion(fecha);
    try
    {
      this.ev.aceptarPedido();
    }
    catch (StateException e)
    {
      fail("No se acepto correctamente_1");
    }
  }
  
  @Test
  public void testAceptarPedido_2()
  {
    this.setUp_2();
    Calendar fecha = GregorianCalendar.getInstance();
    this.ev.getPedido().setFechaDefinitiva(fecha);
    this.ev.getPedido().setFechaPropuestaProduccion(fecha);
    try
    {
      this.ev.aceptarPedido();
      fail("Deberia haber fallado, fechaPedidoAceptado = null_2");
    }
    catch (StateException e)
    {
    }
  }
  
  @Test
  public void testAceptarPedido_3()
  {
    this.setUp_2();
    Calendar fecha = GregorianCalendar.getInstance();
    this.ev.getPedido().setFechaDefinitiva(fecha);
    this.ev.getPedido().setFechaPedidoAceptado(fecha);
    try
    {
      this.ev.aceptarPedido();
      fail("Deberia haber fallado, fechaPropuestaProduccion = null_3");
    }
    catch (StateException e)
    {
    }
  }
  
  @Test
  public void testAceptarPedido_4()
  {
    this.setUp_2();
    Calendar fecha = GregorianCalendar.getInstance();
    this.ev.getPedido().setFechaPedidoAceptado(fecha);
    this.ev.getPedido().setFechaPropuestaProduccion(fecha);
    try
    {
      this.ev.aceptarPedido();
      fail("Deberia haber fallado, fechaDefinitiva = null_4");
    }
    catch (StateException e)
    {
    }
  }
  
}
