package junit;

import datos.Lote;
import datos.Pedido;
import datos.TipoProducto;

import java.util.GregorianCalendar;

import listas.ListaEmpleados;

import listas.ListaMateriales;
import listas.ListaPedidos;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import visual.Controlador;

public class ListaPedidosTestCajaBlanca {
        
    public ListaPedidosTestCajaBlanca() {
        super();
    }
    
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    /* En este metodo el atributo de clase no esta inicializado, por lo cual se inicializa
     * y se guarda su referencia para comparar en las otras ramas*/
    public void getInstanceTest_1(){
        ListaPedidos listaReal = ListaPedidos.getInstance();
        assertTrue("El metodo no devuelve lo correcto", listaReal != null);
    }
    
    @Test
    /* En este caso ya se inicializo el atributo de clase en el test 1*/
    public void getInstanceTest_2(){
        ListaPedidos lista1 = ListaPedidos.getInstance();
        ListaPedidos lista2 = ListaPedidos.getInstance();
        assertTrue("El metodo no devuelve lo correcto", lista1 == lista2);
    }
}
