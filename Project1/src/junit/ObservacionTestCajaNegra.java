package junit;

import datos.Observacion;
import datos.Pedido;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class ObservacionTestCajaNegra {

    private String tema = null;
    private Calendar fechaObservacion = null;

    public ObservacionTestCajaNegra() {
        super();
    }

    @Before
    public void setUp() {
        this.tema = "Esto es un tema de prueba";
        this.fechaObservacion = GregorianCalendar.getInstance();
    }

    @After
    public void tearDown() {
        this.tema = null;
        this.fechaObservacion = null;
    }

    @Test
    public void constructorTest_1() {
        String texto = "Esto es un texto de longitud intermedia para probar";
        Observacion observacion = new Observacion(this.tema, this.fechaObservacion, "LEG123456", texto);
        assertTrue("Observacion mal creada, el tema no se asigno bien", observacion.getTema().equals(this.tema));
        assertTrue("Observacion mal creada, la fecha no se asigno bien",
                   observacion.getFechaObservacion().compareTo(this.fechaObservacion) == 0);
        assertTrue("Observacion mal creada, el legajo no se asigno bien",
                   observacion.getLegajoEmpleado().equals("LEG123456"));
        assertTrue("Observacion mal creada, el texto no se asigno bien", observacion.getTexto().equals(texto));
    }

    @Test
    public void constructorTest_2() {
        String texto = "Esto es un texto de longitud intermedia para probar";
        Observacion observacion = new Observacion(this.tema, this.fechaObservacion, "LEG000000", texto);
        assertTrue("Observacion mal creada, el tema no se asigno bien", observacion.getTema().equals(this.tema));
        assertTrue("Observacion mal creada, la fecha no se asigno bien",
                   observacion.getFechaObservacion().compareTo(this.fechaObservacion) == 0);
        assertTrue("Observacion mal creada, el legajo no se asigno bien",
                   observacion.getLegajoEmpleado().equals("LEG000000"));
        assertTrue("Observacion mal creada, el texto no se asigno bien", observacion.getTexto().equals(texto));
    }

    @Test
    public void constructorTest_3() {
        String texto = "Esto es un texto de longitud intermedia para probar";
        Observacion observacion = new Observacion(this.tema, this.fechaObservacion, "LEG999999", texto);
        assertTrue("Observacion mal creada, el tema no se asigno bien", observacion.getTema().equals(this.tema));
        assertTrue("Observacion mal creada, la fecha no se asigno bien",
                   observacion.getFechaObservacion().compareTo(this.fechaObservacion) == 0);
        assertTrue("Observacion mal creada, el legajo no se asigno bien",
                   observacion.getLegajoEmpleado().equals("LEG999999"));
        assertTrue("Observacion mal creada, el texto no se asigno bien", observacion.getTexto().equals(texto));
    }

    @Test
    public void constructorTest_4() {
        String texto = "1";
        Observacion observacion = new Observacion(this.tema, this.fechaObservacion, "LEG123456", texto);
        assertTrue("Observacion mal creada, el tema no se asigno bien", observacion.getTema().equals(this.tema));
        assertTrue("Observacion mal creada, la fecha no se asigno bien",
                   observacion.getFechaObservacion().compareTo(this.fechaObservacion) == 0);
        assertTrue("Observacion mal creada, el legajo no se asigno bien",
                   observacion.getLegajoEmpleado().equals("LEG123456"));
        assertTrue("Observacion mal creada, el texto no se asigno bien", observacion.getTexto().equals(texto));
    }

    @Test
    public void constructorTest_5() {
        String texto = "";
        for (int i = 0; i < 500; i++)
            texto += "A";
        Observacion observacion = new Observacion(this.tema, this.fechaObservacion, "LEG123456", texto);
        assertTrue("Observacion mal creada, el tema no se asigno bien", observacion.getTema().equals(this.tema));
        assertTrue("Observacion mal creada, la fecha no se asigno bien",
                   observacion.getFechaObservacion().compareTo(this.fechaObservacion) == 0);
        assertTrue("Observacion mal creada, el legajo no se asigno bien",
                   observacion.getLegajoEmpleado().equals("LEG123456"));
        assertTrue("Observacion mal creada, el texto no se asigno bien", observacion.getTexto().equals(texto));
    }
}
