package junit;

import datos.Observacion;
import datos.Pedido;

import datos.estadosPedido.Estado;

import datos.estadosPedido.Evaluacion;

import exceptions.StateException;

import java.util.Calendar;

import java.util.GregorianCalendar;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class PedidoTestCajaNegra
{
  private Pedido ped;

  @Before
  public void setUp()
  {
  }

  private void setUp_2()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
  }

  @After
  public void tearDown()
  {
    this.ped = null;
  }

  @Test
  public void testConstructor_1()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
    assertTrue("Pedido mal creado_1", ped.getNumeroPedido().equals("PED102111"));
    assertTrue("Pedido mal creado_1", ped.getFechaPedido() == fechaPedido);
    assertTrue("Pedido mal creado_1", ped.getFechaEntregaVentas() == fechaEntregaVentas);
    assertTrue("Pedido mal creado_1", ped.getCodigoMaquina().equals("TIP213431"));
    assertTrue("Pedido mal creado_1", ped.getTipoMaquina().equals("Flipper"));
    assertTrue("Pedido mal creado_1", ped.getCantProduccion() == 200);
  }

  @Test
  public void testConstructor_2()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED999999", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
    assertTrue("Pedido mal creado_2", ped.getNumeroPedido().equals("PED999999"));
    assertTrue("Pedido mal creado_2", ped.getFechaPedido() == fechaPedido);
    assertTrue("Pedido mal creado_2", ped.getFechaEntregaVentas() == fechaEntregaVentas);
    assertTrue("Pedido mal creado_2", ped.getCodigoMaquina().equals("TIP213431"));
    assertTrue("Pedido mal creado_2", ped.getTipoMaquina().equals("Flipper"));
    assertTrue("Pedido mal creado_2", ped.getCantProduccion() == 200);
  }

  @Test
  public void testConstructor_3()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED000000", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 200);
    assertTrue("Pedido mal creado_3", ped.getNumeroPedido().equals("PED000000"));
    assertTrue("Pedido mal creado_3", ped.getFechaPedido() == fechaPedido);
    assertTrue("Pedido mal creado_3", ped.getFechaEntregaVentas() == fechaEntregaVentas);
    assertTrue("Pedido mal creado_3", ped.getCodigoMaquina().equals("TIP213431"));
    assertTrue("Pedido mal creado_3", ped.getTipoMaquina().equals("Flipper"));
    assertTrue("Pedido mal creado_3", ped.getCantProduccion() == 200);
  }

  @Test
  public void testConstructor_4()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP999999", "Flipper", 200);
    assertTrue("Pedido mal creado_4", ped.getNumeroPedido().equals("PED102111"));
    assertTrue("Pedido mal creado_4", ped.getFechaPedido() == fechaPedido);
    assertTrue("Pedido mal creado_4", ped.getFechaEntregaVentas() == fechaEntregaVentas);
    assertTrue("Pedido mal creado_4", ped.getCodigoMaquina().equals("TIP999999"));
    assertTrue("Pedido mal creado_4", ped.getTipoMaquina().equals("Flipper"));
    assertTrue("Pedido mal creado_4", ped.getCantProduccion() == 200);
  }

  @Test
  public void testConstructor_5()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP000000", "Flipper", 200);
    assertTrue("Pedido mal creado_5", ped.getNumeroPedido().equals("PED102111"));
    assertTrue("Pedido mal creado_5", ped.getFechaPedido() == fechaPedido);
    assertTrue("Pedido mal creado_5", ped.getFechaEntregaVentas() == fechaEntregaVentas);
    assertTrue("Pedido mal creado_5", ped.getCodigoMaquina().equals("TIP000000"));
    assertTrue("Pedido mal creado_5", ped.getTipoMaquina().equals("Flipper"));
    assertTrue("Pedido mal creado_5", ped.getCantProduccion() == 200);
  }

  @Test
  public void testConstructor_6()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 1);
    assertTrue("Pedido mal creado_6", ped.getNumeroPedido().equals("PED102111"));
    assertTrue("Pedido mal creado_6", ped.getFechaPedido() == fechaPedido);
    assertTrue("Pedido mal creado_6", ped.getFechaEntregaVentas() == fechaEntregaVentas);
    assertTrue("Pedido mal creado_6", ped.getCodigoMaquina().equals("TIP213431"));
    assertTrue("Pedido mal creado_6", ped.getTipoMaquina().equals("Flipper"));
    assertTrue("Pedido mal creado_6", ped.getCantProduccion() == 1);
  }

  @Test
  public void testConstructor_7()
  {
    Calendar fechaPedido = GregorianCalendar.getInstance();
    Calendar fechaEntregaVentas = GregorianCalendar.getInstance();
    this.ped = new Pedido("PED102111", fechaPedido, fechaEntregaVentas, "TIP213431", "Flipper", 999);
    assertTrue("Pedido mal creado_7", ped.getNumeroPedido().equals("PED102111"));
    assertTrue("Pedido mal creado_7", ped.getFechaPedido() == fechaPedido);
    assertTrue("Pedido mal creado_7", ped.getFechaEntregaVentas() == fechaEntregaVentas);
    assertTrue("Pedido mal creado_7", ped.getCodigoMaquina().equals("TIP213431"));
    assertTrue("Pedido mal creado_7", ped.getTipoMaquina().equals("Flipper"));
    assertTrue("Pedido mal creado_7", ped.getCantProduccion() == 999);
  }

  @Test
  public void testAgregarObservacion()
  {
    this.setUp_2();
    Calendar fechaObservacion = GregorianCalendar.getInstance();
    Observacion obs = new Observacion("prueba", fechaObservacion, "LEG000000", "es una observacion");
    try
    {
      this.ped.evaluarPedido();
      this.ped.agregarObservacion(obs);
    }
    catch (StateException e)
    {
      fail("No se agrego la observacion correctamente");
    }
  }

  @Test
  public void testEvaluarPedido()
  {
    this.setUp_2();
    try
    {
      this.ped.evaluarPedido();
    }
    catch(StateException e)
    {
      fail("El pedido no fue cambiado a evaluacion correctamente");
    }
    assertTrue("El pedido no fue cambiado a evaluacion correctamente",this.ped.isEnEvaluacion());
  }
  
  @Test
  public void testAceptarPedido()
  {
    this.setUp_2();
    Calendar fechaProduccion = GregorianCalendar.getInstance();
    try
    {
      this.ped.evaluarPedido();
      this.ped.aceptarPedido(fechaProduccion);
    }
    catch (StateException e)
    {
      fail("El pedido no fue aceptado correctamente");
    }
    assertTrue("El pedido no fue aceptado correctamente", this.ped.isAceptado());
  }
  
  @Test
  public void testSetEstadoActual()
  {
    this.setUp_2();
    Estado estadoActual = new Evaluacion(this.ped);
    this.ped.setEstadoActual(estadoActual);
    assertTrue("El estado no fue cambiado de estado correctamente",this.ped.getEstadoActual() == estadoActual);
  }
  
}
