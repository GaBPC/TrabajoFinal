package junit;

import datos.Observacion;
import datos.Pedido;

import datos.estadosPedido.Aceptado;

import java.util.GregorianCalendar;

import listas.ListaEmpleados;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import personal.Empleado;

public class ListaEmpleadosTestCajaNegra {

    private ListaEmpleados lista = null;

    public ListaEmpleadosTestCajaNegra() {
        super();
    }

    @Before
    public void setUp() {
        this.lista = ListaEmpleados.getInstance();
    }

    @After
    public void tearDown() {
        this.lista = null;
    }

    @Test
    public void buscarTest_1() {
        try {
            Empleado empleado = this.lista.buscar("LEG123456");
            assertTrue("Buscar no devuelve el empleado correct", empleado.getLegajo().equals("LEG123456"));
        } catch (Exception e) {
            fail("El empleado deberia ser encontrado, ya que es parte de la empresa");
        }
    }
    
    @Test
    public void buscarTest_2() {
        try {
            Empleado empleado = this.lista.buscar("LEG000000");
            assertTrue("Buscar no devuelve el empleado correct", empleado.getLegajo().equals("LEG000000"));
        } catch (Exception e) {
            fail("El empleado deberia ser encontrado, ya que es parte de la empresa");
        }
    }
    
    @Test
    public void buscarTest_3() {
        try {
            Empleado empleado = this.lista.buscar("LEG999999");
            assertTrue("Buscar no devuelve el empleado correct", empleado.getLegajo().equals("LEG999999"));
        } catch (Exception e) {
            fail("El empleado deberia ser encontrado, ya que es parte de la empresa");
        }
    }
    
    @Test
    public void buscarTest_4() {
        try {
            Empleado empleado = this.lista.buscar("LEG123");
            fail("El empleado no deberia ser encontrado, ya que el legajo es invalido");
        } catch (Exception e) {
        }
    }
    
    @Test
    public void buscarTest_5() {
        try {
            Empleado empleado = this.lista.buscar("LEG1234567");
            fail("El empleado no deberia ser encontrado, ya que el legajo es invalido");
        } catch (Exception e) {
        }
    }
}
