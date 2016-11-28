package junit;

import datos.Observacion;
import datos.Pedido;

import datos.estadosPedido.Aceptado;

import exceptions.StateException;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class AceptadoTestCajaNegra {

    private Pedido pedido = null;
    private Aceptado aceptado = null;
    private Observacion obs = null;

    public AceptadoTestCajaNegra() {
        super();
    }

    @Before
    public void setUp() {
        this.pedido =
            new Pedido("PED123456", GregorianCalendar.getInstance(), GregorianCalendar.getInstance(), "TIP123456",
                       "Flipper", 5);
        this.aceptado = new Aceptado(this.pedido);
        this.obs = new Observacion("Esto es una prueba", GregorianCalendar.getInstance(), "LEG123456", "Pruebita");
    }

    @After
    public void tearDown() {
        this.pedido = null;
        this.aceptado = null;
        this.obs = null;
    }

    @Test
    public void constructorTest_1() {
        Aceptado estado = new Aceptado(this.pedido);
        assertTrue("El estado no se creo bien, no se asigno correctamente el pedido",
                   estado.getPedido() == this.pedido);
    }

    @Test
    public void agregarObservacionTest_1() {
        try {
            this.aceptado.agregarObservacion(this.obs);
            fail("No lanza la excepcion esperada");
        } catch (StateException e) {
        }
    }
}
