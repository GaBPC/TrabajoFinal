package junit;

import datos.Observacion;
import datos.Pedido;

import datos.estadosPedido.Evaluacion;

import exceptions.StateException;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EvaluacionTestCajaBlanca
{
  private Evaluacion ev;
  private Pedido ped;
  
  @Before
  public void setUp()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
    this.ev = new Evaluacion(this.ped);
  }
  
  @After
  public void tearDown()
  {
    this.ev = null;
    this.ped =null;
  }
  
  @Test
  public void testAgregarObservacion_Camino1()
  {
    Calendar fechaObservacion = GregorianCalendar.getInstance();
    Observacion obs = new Observacion("prueba", fechaObservacion, "LEG000000", "es una observacion");
    try
    {
      this.ev.agregarObservacion(obs);
    }
    catch (StateException e)
    {
      fail("Observacion debia ser valida");
    }
  }
  
  @Test
  public void testAgregarObservacion_Camino2()
  {
    Calendar fechaObservacion = GregorianCalendar.getInstance();
    Observacion obs = new Observacion("prueba", fechaObservacion, "LEG000000", "es una observacion invalida");
    obs.setLegajoEmpleado(null);
    try
    {
      this.ev.agregarObservacion(obs);
      fail("Observacion debia ser invalida");
    }
    catch (StateException e)
    {
    }
  }
  
  @Test
  public void aceptarPedido_Camino1()
  {
    Calendar fecha = GregorianCalendar.getInstance();
    this.ped.setFechaPropuestaProduccion(fecha);
    this.ped.setFechaDefinitiva(fecha);
    this.ped.setFechaPedidoAceptado(fecha);

    try
    {
      this.ev.aceptarPedido();
    }
    catch (StateException e)
    {
      fail("Pedido mal aceptado");
    }
  }
  
  @Test
  public void aceptarPedido_Camino2()
  {
    try
    {
      this.ev.aceptarPedido();
      fail("El pedido no debia ser aceptado");
    }
    catch (StateException e)
    {
    }
  }
  
}
