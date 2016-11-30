package junit;

import datos.Lote;
import datos.Pedido;
import datos.TipoProducto;

import exceptions.StateException;

import java.util.GregorianCalendar;

import listas.ListaEmpleados;
import listas.ListaMateriales;
import listas.ListaMaterialesStock;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import personal.Empleado;

import visual.Controlador;

public class ControladorTestCajaNegra {

    private Controlador control = null;

    public ControladorTestCajaNegra() {
        super();
    }

    @Before
    public void setUp() {
        this.control = new Controlador();
    }

    @After
    public void tearDown() {
        this.control = null;
    }

    @Test
    public void setEmpleadoActualTest_1() {
        try {
            Empleado empleado = ListaEmpleados.getInstance().buscar("LEG123456");
            this.control.setEmpeladoActual(empleado);
            Empleado devuelto = this.control.getEmpleadoActual();
            assertTrue("El empleado no se setea correctamente", empleado == devuelto);
        } catch (Exception e) {
            fail("El empleado deberia ser encontrado ya que existe");
        }
    }

    @Test
    public void setPedidoActualTest_1() {
        Pedido pedido =
            new Pedido("PED123456", GregorianCalendar.getInstance(), GregorianCalendar.getInstance(), "TIP123456",
                       ListaMaterialesStock.FLIPPER, 1);
        this.control.setPedidoActual(pedido);
        try {
            Pedido devuelto = this.control.getPedidoActual();
            assertTrue("El pedido no se seteo correctamente", pedido == devuelto);
        } catch (Exception e) {
            fail("El pedido no se seteo correctamente ya que salta la excepcion");
        }
    }

    @Test
    public void setLoteActualTest_1() {
        Pedido pedido =
            new Pedido("PED123456", GregorianCalendar.getInstance(), GregorianCalendar.getInstance(), "TIP123456",
                       ListaMaterialesStock.FLIPPER, 1);
        Lote lote = new Lote(pedido, "LOT123456");
        this.control.setLoteActual(lote);
        try {
            Lote devuelto = this.control.getLoteActual();
            assertTrue("El lote no se seteo correctamente", lote == devuelto);
        } catch (Exception e) {
            fail("El lote no se seteo correctamente ya que salta la excepcion");
        }
    }

    @Test
    public void setProductoActualTest_1() {
        TipoProducto producto = new TipoProducto(new ListaMateriales(), "Esto es una prueba");
        this.control.setProductoActual(producto);
        try {
            TipoProducto devuelto = this.control.getProductoActual();
            assertTrue("El producto no se seteo correctamente", producto == devuelto);
        } catch (Exception e) {
            fail("El producto no se seteo correctamente ya que salta la excepcion");
        }
    }

    @Test
    public void buscarEmpleadoTest_1() {
        try {
            Empleado empleado = this.control.buscarEmpleado("LEG123456");
            assertTrue("El metodo no devuelve el empelado correcto", empleado.getLegajo().equals("LEG123456"));
        } catch (Exception e) {
            fail("El empleado existe, por lo que no deberia saltar la excepcion");
        }
    }

    @Test
    public void buscarEmpleadoTest_2() {
        try {
            Empleado empleado = this.control.buscarEmpleado("LEG000000");
            assertTrue("El metodo no devuelve el empelado correcto", empleado.getLegajo().equals("LEG000000"));
        } catch (Exception e) {
            fail("El empleado existe, por lo que no deberia saltar la excepcion");
        }
    }

    @Test
    public void buscarEmpleadoTest_3() {
        try {
            Empleado empleado = this.control.buscarEmpleado("LEG999999");
            assertTrue("El metodo no devuelve el empelado correcto", empleado.getLegajo().equals("LEG999999"));
        } catch (Exception e) {
            fail("El empleado existe, por lo que no deberia saltar la excepcion");
        }
    }

    @Test
    public void buscarEmpleadoTest_4() {
        try {
            Empleado empleado = this.control.buscarEmpleado("LEG123");
            fail("El empleado no existe, por lo que deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void buscarEmpleadoTest_5() {
        try {
            Empleado empleado = this.control.buscarEmpleado("LEG1234567");
            fail("El empleado no existe, por lo que deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void cambiarAAceptadoTest_1() {
        try {
            Pedido pedido =
                new Pedido("PED123456", GregorianCalendar.getInstance(), GregorianCalendar.getInstance(), "TIP123456",
                           ListaMaterialesStock.FLIPPER, 1);
            this.control.setPedidoActual(pedido);
            this.control.cambiarAEvaluacion();
            this.control.cambiarAAceptado(GregorianCalendar.getInstance());
            Pedido devuelto = this.control.getPedidoActual();
            assertTrue("El estado del pedido no cambia correctamente", devuelto.isAceptado() == true);
        } catch (StateException e) {
            fail("No deberia saltar la excepcion");
        } catch (Exception e) {
            fail("No deberia saltar la excepcion ya que se supone que se seteo bien el pedido");
        }
    }

    @Test
    public void crearNuevoPedidoTest_1() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 25,
                                          GregorianCalendar.getInstance());
            Pedido devuelto = this.control.getPedidoActual();
            assertTrue("El pedido no se crea correctamente",
                       devuelto.getCantProduccion() == 25 &&
                       devuelto.getTipoMaquina().equals(ListaMaterialesStock.FLIPPER));
        } catch (Exception e) {
            fail("La excepcion no deberia saltar " + e.getMessage());
        }
    }

    @Test
    public void crearNuevoPedidoTest_2() {
        try {
            this.control.crearNuevoPedido(null, ListaMaterialesStock.FLIPPER, 25, GregorianCalendar.getInstance());
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }
    
    @Test
    public void crearNuevoPedidoTest_3() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), "TIP123", 25, GregorianCalendar.getInstance());
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }
    
    @Test
    public void crearNuevoPedidoTest_4() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), "TIP1234567", 25, GregorianCalendar.getInstance());
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }
    
    @Test
    public void crearNuevoPedidoTest_5() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, -1, GregorianCalendar.getInstance());
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }
    
    @Test
    public void crearNuevoPedidoTest_6() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 10, null);
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }
}
