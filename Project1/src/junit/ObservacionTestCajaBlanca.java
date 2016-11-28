package junit;

import datos.Observacion;
import datos.Pedido;

import java.util.Calendar;
import java.util.GregorianCalendar;

import listas.ListaPedidos;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class ObservacionTestCajaBlanca {

    private String tema = null;
    private Calendar fechaObservacion = null;
    private Observacion obs = null;

    public ObservacionTestCajaBlanca() {
        super();
    }

    @Before
    public void setUp() {
        this.tema = "Esto es un tema de prueba";
        this.fechaObservacion = GregorianCalendar.getInstance();
        this.obs = new Observacion(this.tema, this.fechaObservacion, "LEG123456", "Esto es una prueba");
    }

    @After
    public void tearDown() {
        this.tema = null;
        this.fechaObservacion = null;
        this.obs = null;
    }

    @Test
    public void compareToTest_1() {
        Observacion obs1 = new Observacion(this.tema, GregorianCalendar.getInstance(), "LEG123456", "Esto es la mayor");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        Observacion obs2 = new Observacion(this.tema, GregorianCalendar.getInstance(), "LEG123456", "Esto es la menor");
        assertTrue("La comparacion no funciona correctamente", obs2.compareTo(obs1) > 0);
    }

    @Test
    public void compareToTest_2() {
        Observacion obs1 = new Observacion(this.tema, this.fechaObservacion, "LEG123456", "Esto es la mayor");
        Observacion obs2 = new Observacion(this.tema, this.fechaObservacion, "LEG123456", "Esto es la menor");
        assertTrue("La comparacion no funciona correctamente", obs1.compareTo(obs2) == 0);
    }
    
    @Test
    public void compareToTest_3() {
        Observacion obs1 = new Observacion(this.tema, GregorianCalendar.getInstance(), "LEG123456", "Esto es la mayor");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        Observacion obs2 = new Observacion(this.tema, GregorianCalendar.getInstance(), "LEG123456", "Esto es la menor");
        assertTrue("La comparacion no funciona correctamente", obs1.compareTo(obs2) < 0);
    }
    
    @Test
    public void compareToTest_4() {
        Observacion obs1 = new Observacion("ZZZZ", this.fechaObservacion, "LEG123456", "Esto es la mayor");
        Observacion obs2 = new Observacion("A", this.fechaObservacion, "LEG123456", "Esto es la menor");
        assertTrue("La comparacion no funciona correctamente", obs2.compareTo(obs1) < 0);
    }
    
    @Test
    public void compareToTest_5() {
        Observacion obs1 = new Observacion("ZZZZ", this.fechaObservacion, "LEG123456", "Esto es la mayor");
        Observacion obs2 = new Observacion("A", this.fechaObservacion, "LEG123456", "Esto es la menor");
        assertTrue("La comparacion no funciona correctamente", obs1.compareTo(obs2) > 0);
    }
}
