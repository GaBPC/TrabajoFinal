package junit;

import datos.Pedido;

import java.util.GregorianCalendar;

import java.util.Iterator;

import listas.ListaEmpleados;

import listas.ListaPedidos;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class ListaPedidosTestCajaNegra {
    
    private ListaPedidos lista = null;
    private Pedido pedido = null;
    
    public ListaPedidosTestCajaNegra() {
        super();
    }
    
    @Before
    public void setUp() {
        this.lista = ListaPedidos.getInstance();
        this.pedido =
            new Pedido("PED123456", GregorianCalendar.getInstance(), GregorianCalendar.getInstance(), "TIP123456",
                       "Flipper", 5);
    }

    @After
    public void tearDown() {
        this.lista = null;
        this.pedido = null;
    }
    
    @Test
    public void agregarNuevoTest_1(){
        boolean bool = false;
        this.lista.agregarNuevo(this.pedido);
        Iterator it = this.lista.getIterator();
        while(it.hasNext()){
            if(it.next() == this.pedido)
                bool = true;
        }
        assertTrue("El pedido no fue agregado", bool == true);
    }
    
    @Test
    public void agregarNuevoTest_2(){
        
        int cantidadInicio = 0;
        Iterator it = this.lista.getIterator();
        while(it.hasNext()){
            cantidadInicio++;
            it.next();
        }
        this.lista.agregarNuevo(null);
        int cantidadFinal = 0;
        it = this.lista.getIterator();
        while(it.hasNext()){
            cantidadFinal++;
            it.next();
        }
        assertTrue("La lista tiene un elemento que no deberia tener", cantidadInicio == cantidadFinal);
    }
    
    @Test
    public void borrarPedidoTest_1(){
        boolean bool = false;
        this.lista.agregarNuevo(this.pedido);
        this.lista.borrarPedido(this.pedido);
        Iterator it = this.lista.getIterator();
        while(it.hasNext()){
            if(it.next() == this.pedido)
                bool = true;
        }
        assertTrue("El pedido no fue borrado", bool == false);
    }
}
