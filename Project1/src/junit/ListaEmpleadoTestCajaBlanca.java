package junit;

import datos.Observacion;

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

public class ListaEmpleadoTestCajaBlanca {
    
    private ListaEmpleados listaReal = null;
    
    public ListaEmpleadoTestCajaBlanca() {
        super();
    }
        
    @Test
    /* En este metodo el atributo de clase no esta inicializado, por lo cual se inicializa
     * y se guarda su referencia para comparar en las otras ramas*/
    public void getInstanceTest_1(){
        this.listaReal = ListaEmpleados.getInstance();
        assertTrue("El metodo no devuelve lo correcto", this.listaReal != null);
    }
    
    @Test
    /* En este caso ya se inicializo el atributo de clase en el test 1*/
    public void getInstanceTest_2(){
        ListaEmpleados lista = ListaEmpleados.getInstance();
        assertTrue("El metodo no devuelve lo correcto", lista != this.listaReal);
    }
    
    @Test
    public void buscarTest_1(){
        this.listaReal = ListaEmpleados.getInstance();
        try {
            Empleado buscado = this.listaReal.buscar("LEG123456");
        } catch (Exception e) {
            fail("Como el empleado existe, no deberia saltar la excepcion");
        }
    }
    
    @Test
    public void buscarTest_2(){
        this.listaReal = ListaEmpleados.getInstance();
        try {
            Empleado buscado = this.listaReal.buscar("LEG999742");
            fail("Como el empleado no existe, deberia saltar la excepcion");
        } catch (Exception e) {
            
        }
    } 
}
