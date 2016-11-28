package junit;

import datos.Lote;
import datos.Material;
import datos.Pedido;

import datos.TipoProducto;

import exceptions.FaltantesException;
import exceptions.StateException;

import java.util.GregorianCalendar;

import java.util.Iterator;

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

public class ControladorTestCajaBlanca {

    private Controlador control = null;
    private Pedido pedido = null;
    private TipoProducto producto = null;
    private Lote lote = null;

    public ControladorTestCajaBlanca() {
        super();
    }

    @Before
    public void setUp() {
        this.control = new Controlador();
        this.pedido =
            new Pedido("PED123456", GregorianCalendar.getInstance(), GregorianCalendar.getInstance(), "TIP123456",
                       ListaMaterialesStock.FLIPPER, 1);
        this.producto = new TipoProducto(new ListaMateriales(), "Prueba");
        this.lote = new Lote(this.pedido, "LOT123456");
    }

    @After
    public void tearDown() {
        this.control = null;
        this.pedido = null;
        this.producto = null;
        this.lote = null;
    }

    @Test
    public void getPedidoActualTest_1() {

        this.control.setPedidoActual(pedido);
        try {
            Pedido buscado = this.control.getPedidoActual();
            assertTrue("El metodo no devuelve correctamente", buscado == pedido);
        } catch (Exception e) {
            fail("No deberia producirse la excepcion");
        }
    }

    @Test
    public void getPedidoActualTest_2() {
        try {
            Pedido buscado = this.control.getPedidoActual();
            fail("Como no se asigno ningun pedido, deberia haber saltado la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void getProductoActualTest_1() {
        this.control.setProductoActual(this.producto);
        try {
            TipoProducto buscado = this.control.getProductoActual();
            assertTrue("El metodo no devuelve correctamente", buscado == this.producto);
        } catch (Exception e) {
            fail("No deberia producirse la excepcion");
        }
    }

    @Test
    public void getProductoActualTest_2() {
        try {
            TipoProducto buscado = this.control.getProductoActual();
            fail("Como no se asigno ningun producto, deberia haber saltado la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void removePedidoTest_1() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 25,
                                          GregorianCalendar.getInstance());
        } catch (Exception e) {
            fail("No deberia saltar la excepcion al agregar");
        }

        Pedido original = null;
        try {
            original = this.control.getPedidoActual();
        } catch (Exception e) {
            fail("No deberia saltar la excepcion al obtener el pedido actual");
        }

        Iterator it = this.control.getPedidosIniciados();
        boolean agregado = false;
        while (it.hasNext()) {
            if (it.next() == original)
                agregado = true;
        }
        assertTrue("El pedido no se agrego", agregado == true);
        this.control.removePedido();
        it = this.control.getPedidosIniciados();
        boolean borrado = true;
        while (it.hasNext()) {
            if (it.next() == original)
                agregado = false;
        }
        assertTrue("El pedido no se borro correctamente", borrado == true);
    }

    @Test
    public void removePedidoTest_2() {
        this.control.removePedido();
        try {
            this.control.getPedidoActual();
            fail("Como el pedido actual es null y no modifica nada, deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void removeLoteTest_1() {
        try {
            this.control.setPedidoActual(this.pedido);
            this.control.generarLote();
            Iterator it = this.control.getLotes();
            boolean agregado = false;
            while (it.hasNext()) {
                Lote lote = (Lote) it.next();
                if (lote.getPedido() == this.pedido) {
                    agregado = true;
                    // Lo setea como actual para luego borrarlo
                    this.control.setLoteActual(lote);
                }
            }
            assertTrue("El lote no se agrego correctamente", agregado == true);
            this.control.removeLote();
            it = this.control.getLotes();
            boolean borrado = true;
            while (it.hasNext()) {
                Lote lote = (Lote) it.next();
                if (lote.getPedido() == this.pedido)
                    agregado = false;
            }
            assertTrue("El lote no se borro correctamente", borrado == true);

        } catch (Exception e) {
            fail("No deberia saltar la excepcion al generar el lote");
        }
    }

    @Test
    public void removeLoteTest_2() {
        this.control.removeLote();
        try {
            Lote buscado = this.control.getLoteActual();
            fail("Como el pedido actual es null y no modifica nada, deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void borrarMaterialTest_1() {
        try {
            this.control.setProductoActual(this.producto);
            TipoProducto producto = this.control.getProductoActual();
            ListaMateriales listaAux = producto.getListaMateriales();
            Material mat = new Material("MAT12345", "Prueba", 5);
            listaAux.agregarMaterial(mat);

            this.control.borrarMaterial("MAT12345");

            boolean bool = false;
            Iterator it = listaAux.getIterator();
            while (it.hasNext()) {
                if (it.next() == mat)
                    bool = true;
            }
            assertTrue("El metodo no borra correctamente", bool == false);
        } catch (Exception e) {
            fail("No deberia saltar la excepcion" + e.getMessage());
        }
    }

    @Test
    public void borrarMaterialTest_2() {
        try {
            this.control.borrarMaterial("MAT12345");
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void crearNuevoPedidoTest_1() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 25,
                                          GregorianCalendar.getInstance());
        } catch (Exception e) {
            fail("La excepcion no deberia saltar ya que se tendria que crear el nuevo pedido" + " " + e.getMessage());
        }
    }

    @Test
    public void crearNuevoPedidoTest_2() {
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), "123456", 60,
                                          GregorianCalendar.getInstance());
            fail("La excepcion deberia saltar ya que el tipo de producto es invalido");
        } catch (Exception e) {
        }
    }

    @Test
    public void getPedidosEvaluacionTest_1() {
        int cont = 0;
        Iterator it = this.control.getPedidosEvaluacion();
        while (it.hasNext()) {
            cont++;
            it.next();
        }
        assertTrue("No devuelve correctamente los pedidos", cont == 0);
    }

    @Test
    public void getPedidosEvaluacionTest_2() {
        int cantInicial = 0;
        Iterator it = this.control.getPedidosEvaluacion();
        while (it.hasNext()) {
            cantInicial++;
            it.next();
        }
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 3,
                                          GregorianCalendar.getInstance());
        } catch (Exception e) {
            fail("No deberia saltar la excepcion");
        }
        int cantFinal = 0;
        it = this.control.getPedidosEvaluacion();
        while (it.hasNext()) {
            cantFinal++;
            it.next();
        }
        assertTrue("No devuelve correctamente los pedidos", cantFinal - cantInicial == 0);
    }

    @Test
    public void getPedidosEvaluacionTest_3() {
        int cantInicial = 0;
        Iterator it = this.control.getPedidosEvaluacion();
        while (it.hasNext()) {
            cantInicial++;
            it.next();
        }
        try {
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 5,
                                          GregorianCalendar.getInstance());
        } catch (Exception e) {
            fail("No deberia saltar la excepcion");
        }
        try {
            this.control.cambiarAEvaluacion();
        } catch (StateException e) {
            fail("No deberia saltar la excepcion");
        }
        int cantFinal = 0;
        it = this.control.getPedidosEvaluacion();
        while (it.hasNext()) {
            cantFinal++;
            it.next();
        }
        assertTrue("No devuelve correctamente los pedidos", cantFinal - cantInicial == 1);
    }

    @Test
    public void getPedidosIniciadosTest_1() {
        int cont = 0;
        Iterator it = this.control.getPedidosIniciados();
        while (it.hasNext()) {
            cont++;
            it.next();
        }
        assertTrue("No devuelve correctamente los pedidos", cont == 0);
    }

    @Test
    public void getPedidosIniciadosTest_2() {
        int cantInicial = 0;
        try {
            Iterator it = this.control.getPedidosIniciados();
            while (it.hasNext()) {
                cantInicial++;
                it.next();
            }
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 1,
                                          GregorianCalendar.getInstance());
        } catch (Exception e) {
            fail("No deberia saltar la excepcion de creacion");
        }
        try {
            this.control.cambiarAEvaluacion();
        } catch (StateException e) {
            fail("No deberia saltar la excepcion de cambio");
        }
        int cantFinal = 0;
        Iterator it = this.control.getPedidosIniciados();
        while (it.hasNext()) {
            cantFinal++;
            it.next();
        }
        assertTrue("No devuelve correctamente los pedidos", cantFinal - cantInicial == 0);
    }

    @Test
    public void getPedidosIniciadosTest_3() {
        int cantInicial = 0;
        try {
            Iterator it = this.control.getPedidosIniciados();
            while (it.hasNext()) {
                cantInicial++;
                it.next();
            }
            this.control.crearNuevoPedido(GregorianCalendar.getInstance(), ListaMaterialesStock.FLIPPER, 1,
                                          GregorianCalendar.getInstance());
        } catch (Exception e) {
            fail("No deberia saltar la excepcion de creacion");
        }
        int cantFinal = 0;
        Iterator it = this.control.getPedidosIniciados();
        while (it.hasNext()) {
            cantFinal++;
            it.next();
        }
        assertTrue("No devuelve correctamente los pedidos", cantFinal - cantInicial == 1);
    }

    @Test
    public void generarLoteTest_1() {
        TipoProducto.setNumeroProd(11111);
        this.control.setPedidoActual(this.pedido);
        try {
            int cantInicial = 0;
            Iterator it = this.control.getLotes();
            while (it.hasNext()) {
                cantInicial++;
                it.next();
            }
            this.control.generarLote();
            int cantFinal = 0;
            it = this.control.getLotes();
            while (it.hasNext()) {
                cantFinal++;
                it.next();
            }
            assertTrue("No se genero correctamente", cantFinal - cantInicial == 1);
        } catch (Exception e) {
            fail("No deberia lanzar el error");
        }
    }

    @Test
    public void generarLoteTest_2() {
        TipoProducto.setNumeroProd(111111);
        this.control.setPedidoActual(this.pedido);
        try {
            int cantInicial = 0;
            Iterator it = this.control.getLotes();
            while (it.hasNext()) {
                cantInicial++;
                it.next();
            }
            this.control.generarLote();
            int cantFinal = 0;
            it = this.control.getLotes();
            while (it.hasNext()) {
                cantFinal++;
                it.next();
            }
            assertTrue("No se genero correctamente", cantFinal - cantInicial == 1);
        } catch (Exception e) {
            fail("No deberia lanzar el error");
        }
    }

    @Test
    public void generarLoteTest_3() {
        TipoProducto.setNumeroProd(11111);
        try {
            this.control.generarLote();
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void generarLoteTest_5() {
        TipoProducto.setNumeroProd(111111);
        try {
            this.control.generarLote();
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void crearObservacionTest_1() {
        try {
            this.control.crearObservacion("Tema", null);
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void crearObservacionTest_2() {
        //Crea un empleado con un legajo invalido
        this.control.setEmpeladoActual(new Empleado("0", "Gabriel", "Ventas"));
        try {
            this.control.crearObservacion("Tema", "Esto es una prueba");
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void crearObservacionTest_3() {
        try {
            this.control.setEmpeladoActual(ListaEmpleados.getInstance().buscar("LEG123456"));
        } catch (Exception e) {
            fail("El empleado deberia existir");
        }
        try {
            String textoInvalido = "";
            for (int i = 0; i < 600; i++) {
                textoInvalido += " A";
            }
            this.control.crearObservacion("Tema", textoInvalido);
            fail("Deberia saltar la excepcion");
        } catch (Exception e) {
        }
    }

    @Test
    public void crearObservacionTest_4() {
        try {
            this.control.setEmpeladoActual(ListaEmpleados.getInstance().buscar("LEG123456"));
        } catch (Exception e) {
            fail("El empleado deberia existir");
        }
        try {
            this.control.crearObservacion("Tema", "Esto es un texto valido");
        } catch (Exception e) {
            fail("No deberia saltar la excepcion");
        }
    }
}
